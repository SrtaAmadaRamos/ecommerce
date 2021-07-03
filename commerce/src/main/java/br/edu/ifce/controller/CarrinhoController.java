package br.edu.ifce.controller;

import br.edu.ifce.dao.ItemDAO;
import br.edu.ifce.model.Item;
import br.edu.ifce.model.ItemCarrinho;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "CarrinhoController", value = "/carrinho")
public class CarrinhoController extends BaseServlet {
    private final ItemDAO itemDAO;
    private List<ItemCarrinho> carrinho;

    public CarrinhoController() {
        itemDAO = new ItemDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!UsuarioLogado(req.getSession())) {
            resp.sendRedirect("/login");
            return;
        }

        carrinho = (List<ItemCarrinho>)req.getSession().getAttribute("carrinho");
        if(carrinho == null)
            carrinho = new ArrayList<>();

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        String id = request.getParameter("id");

        if(acao != null && acao.equals("adicionar") && id != null) {
            Item item = itemDAO.BuscarItem(parseInt(id));

            if(VerificaSeJaTemNoCarrinho(item, carrinho)) {
                AtualizarQuantidade(item, 1, carrinho);

                response.sendRedirect("/");
                return;
            }

            ItemCarrinho itemCarrinho = new ItemCarrinho();
            itemCarrinho.setItem(item);
            itemCarrinho.setQuantidade(1);

            carrinho.add(itemCarrinho);

            request.getSession().setAttribute("carrinho", carrinho);

            response.sendRedirect("/");
            return;
        }

        if(acao != null && acao.equals("remover") && id != null) {
            Item item = itemDAO.BuscarItem(parseInt(id));

            if(!VerificaSeJaTemNoCarrinho(item, carrinho)) {
                response.sendRedirect("/carrinho");
                return;
            }

            ItemCarrinho itemCarrinho = ObterItemDoCarrinho(item, carrinho);
            carrinho.remove(itemCarrinho);
        }

        request.setAttribute("itens", carrinho);
        View("views/carrinho/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String quantidade = request.getParameter("quantidade");

        if(id != null && quantidade != null) {
            Item item = itemDAO.BuscarItem(parseInt(id));
            ItemCarrinho itemCarrinho = ObterItemDoCarrinho(item, carrinho);

            if(itemCarrinho != null) {
                itemCarrinho.setQuantidade(parseInt(quantidade));
                if(itemCarrinho.getQuantidade() <= 0) {
                    System.out.println("REMOVENDOOOOO");
                    carrinho.remove(itemCarrinho);
                }
            }
        }

        request.getSession().setAttribute("carrinho", carrinho);
        request.setAttribute("itens", carrinho);
        View("views/carrinho/index.jsp");
    }

    private boolean VerificaSeJaTemNoCarrinho(Item item, List<ItemCarrinho> carrinho) {
        for(ItemCarrinho itemCarrinho : carrinho) {
            if(itemCarrinho.getItemId() == item.getId())
                return true;
        }

        return false;
    }

    private ItemCarrinho ObterItemDoCarrinho(Item item, List<ItemCarrinho> carrinho) {
        for(ItemCarrinho itemCarrinho : carrinho) {
            if(itemCarrinho.getItemId() == item.getId())
                return itemCarrinho;
        }

        return null;
    }

    private void AtualizarQuantidade(Item item, int quantidade, List<ItemCarrinho> carrinho) {
        ItemCarrinho itemCarrinho = ObterItemDoCarrinho(item, carrinho);

        itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + quantidade);
    }
}
