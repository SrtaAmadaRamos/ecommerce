package br.edu.ifce.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseServlet extends HttpServlet {
    private HttpServletRequest request;
    private HttpServletResponse response;
    protected List<String> erros = new ArrayList<>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        request = req;
        response = resp;
        erros.clear();
        req.removeAttribute("erros");
        req.setAttribute("erros", erros);

        super.service(req, resp);
    }

    protected void AddErro(String erro) {
        erros.add(erro);
    }

    protected void View(String viewPath) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(viewPath);
        view.forward(request, response);
    }

    @Override
    public void destroy() {
        request.removeAttribute("erros");
        erros.clear();
        super.destroy();
    }

    protected Boolean UsuarioLogado(HttpSession session) {
        return session.getAttribute("id") != null;
    }
}
