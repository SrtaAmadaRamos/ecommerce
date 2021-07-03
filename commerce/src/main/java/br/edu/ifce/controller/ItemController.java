package br.edu.ifce.controller;

import br.edu.ifce.dao.ItemDAO;
import br.edu.ifce.model.Item;
import br.edu.ifce.utils.BeanUtilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "ItemController", value = "/itens")
public class ItemController extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private final ItemDAO itemDAO;

	public ItemController() {
		super();

		itemDAO = new ItemDAO();
	}

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
		String acao = request.getParameter("acao");

		if(acao != null && acao.equals("cadastrar")) {
			request.setAttribute("item", new Item());
			View("views/item/cadastrar.jsp");
			return;
		}

		if(acao != null && acao.equals("editar")) {
			int id = parseInt(request.getParameter("id"));

			Item item = itemDAO.BuscarItem(id);
			request.setAttribute("item", item);

			View("views/item/cadastrar.jsp");
			return;
		}

		if(acao != null && acao.equals("excluir")) {
			int id = parseInt(request.getParameter("id"));
			itemDAO.Excluir(id);

			response.sendRedirect("/itens");
			return;
		}

		List<Item> itens = itemDAO.ListarTodos();
		request.setAttribute("itens", itens);
		View("views/item/index.jsp");
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Item item = new Item();
		BeanUtilities.populateBean(item, request.getParameterMap());

		if(item.getId() == 0) {
			itemDAO.Inserir(item);
			response.sendRedirect("/itens");
		}

		if(item.getId() != 0) {

			System.out.println(item.getDescricao());

			itemDAO.Atualizar(item);
			response.sendRedirect("/itens");
		}
    }
}
