package br.edu.ifce.controller;

import br.edu.ifce.dao.UsuarioDAO;
import br.edu.ifce.model.Usuario;
import br.edu.ifce.utils.BeanUtilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends BaseServlet {
    private final UsuarioDAO usuarioDAO;

    public LoginController() {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if(acao != null && acao.equals("sair")) {
            request.getSession().invalidate();
            response.sendRedirect("/login");
            return;
        }

        View("views/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario u = new Usuario();
        BeanUtilities.populateBean(u, request);

        u = usuarioDAO.BuscarPorEmailESenha(u.getEmail(), u.getSenha());
        if(u != null) {
            SetarSession(u, request.getSession());
            response.sendRedirect("/itens");
        }

        if( u == null) {
            AddErro("Usuário ou Senha incorretos!");
            View("views/login.jsp");
        }
    }

    private void SetarSession(Usuario u, HttpSession session) {
        session.setAttribute("id", u.getId());
        session.setAttribute("nome", u.getNome());
        session.setAttribute("email", u.getEmail());
    }
}
