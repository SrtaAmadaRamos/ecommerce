<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Itens - Ecommerce TJW</title>

    <%@ include file="/layout/estilos.jsp" %>
</head>
<body>

<%@ include file="/layout/navbar.jsp" %>

<div class="ui main text grid fluid container" style="padding-top: 1rem;">

    <div class="ui grid">
        <div class="sixteen wide column centered">
            <% if(request.getParameter("acao").equals("cadastrar")) { %>
            <h1 class="ui header left floated">Cadastro de Item</h1>
            <% } %>

            <% if(request.getParameter("acao").equals("editar")) { %>
            <h1 class="ui header left floated">Alterar de Item</h1>
            <% } %>

            <a href="?acao=listar" class="ui button right floated">
                Voltar para Listagem
            </a>
        </div>
        <div class="sixteen wide column centered grid">
            <form class="ui form" method="post">
                <input type="hidden" name="id" value="<c:out value="${item.id}" />">
                <div class="field">
                    <label>Nome</label>
                    <input type="text" name="nome" placeholder="Nome" value="<c:out value="${item.nome}" />">
                </div>
                <div class="field">
                    <label>Descrição</label>
                    <input type="text" name="descricao" placeholder="Descrição" value="<c:out value="${item.descricao}" />">
                </div>
                <div class="field">
                    <label>Preço</label>
                    <input type="text" name="preco" placeholder="Preço" value="<c:out value="${item.preco}" />">
                </div>
                <div class="field">
                    <label>Imagem</label>
                    <input type="text" name="imagem" placeholder="URL Imagem" value="<c:out value="${item.imagem}" />">
                </div>
                <button class="ui button green" type="submit">Salvar</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>