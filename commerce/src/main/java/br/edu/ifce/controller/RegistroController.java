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

@WebServlet(name = "RegistroController", value = "/registrar")
public class RegistroController extends BaseServlet {
    private final UsuarioDAO usuarioDAO;

    public RegistroController(){
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        View("views/registro.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario u = new Usuario();
        BeanUtilities.populateBean(u, request);

        if(usuarioDAO.VerificarSeEmailJaCadastrado(u.getEmail())) {
            AddErro("Email em uso!");
            View("views/registro.jsp");
            return;
        }

        usuarioDAO.Inserir(u);
        SetarSession(u, request.getSession());
        response.sendRedirect("/itens");
    }

    private void SetarSession(Usuario u, HttpSession session) {
        session.setAttribute("id", u.getId());
        session.setAttribute("nome", u.getNome());
        session.setAttribute("email", u.getEmail());
    }
}
