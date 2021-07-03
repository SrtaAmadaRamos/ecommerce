<%@ page import="br.edu.ifce.model.ItemCarrinho" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>

<%
    List<ItemCarrinho> carrinho = (List<ItemCarrinho>)request.getSession().getAttribute("carrinho");
    String itensCarrinho = "";

    if(carrinho != null && carrinho.size() > 0)
        itensCarrinho = "( " + carrinho.size() + " )";
%>

<div class="ui fixed inverted menu" wfd-id="13">
    <div class="ui container" wfd-id="14">
        <a href="/" class="header item">
            Ecommerce TJW
        </a>
        <% if (request.getSession().getAttribute("nome") == null) { %>
            <div class="right menu">
                <a class="item" href="/registrar">Registrar-se</a>
                <a class="item" href="/login">Entrar</a>
            </div>
        <% } else { %>
            <a class="item" href="/itens">Gerenciar Itens</a>
            <div class="right menu">
                <a class="item"> Olá, <%= request.getSession().getAttribute("nome") %> </a>
                <a class="item" href="/carrinho">
                    <i class="cart icon"></i>
                    Carrinho &nbsp; <%= itensCarrinho %>
                </a>
                <a class="item" href="/login?acao=sair">Sair</a>
            </div>
        <% } %>
    </div>
</div>