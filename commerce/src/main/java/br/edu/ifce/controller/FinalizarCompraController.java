package br.edu.ifce.controller;

import br.edu.ifce.model.ItemCarrinho;
import br.edu.ifce.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FinalizarCompraController", value = "/finalizar-compra")
public class FinalizarCompraController extends BaseServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!UsuarioLogado(req.getSession())) {
            resp.sendRedirect("/login");
            return;
        }

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ItemCarrinho> carrinho = (List<ItemCarrinho>)request.getSession().getAttribute("carrinho");

        double valorTotal = ObterTotalDaCompra(carrinho);

        request.setAttribute("ValorTotal", StringUtils.FormatarParaDinheiro(valorTotal));
        request.setAttribute("Carrinho", carrinho);

        request.getSession().setAttribute("carrinho", new ArrayList<ItemCarrinho>());

        View("views/finalizar_compra/index.jsp");
    }

    private double ObterTotalDaCompra(List<ItemCarrinho> carrinho) {
        double valorTotal = 0d;

        for(ItemCarrinho item : carrinho) {
            valorTotal += item.getValorTotal();
        }

        return valorTotal;
    }
}
