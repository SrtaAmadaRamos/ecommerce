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
            <h1 class="ui header left floated">Itens</h1>
            <a href="?acao=cadastrar" class="ui button right floated">
                Novo Item
            </a>
        </div>
        <div class="sixteen wide column centered grid">
            <table class="ui celled table">
            <thead>
                <tr>
                    <th class="sorted ascending">#</th>
                    <th class="">Nome</th>
                    <th>Preço</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${itens}" var="item">
                    <tr>
                        <td><c:out value="${item.id}" /></td>
                        <td><c:out value="${item.nome}" /></td>
                        <td><c:out value="${item.getPrecoFormatado()}" /></td>
                        <td style="width: 15px">
                            <div class="ui buttons">
                                <a class="ui mini animated button yellow" href="?acao=editar&id=<c:out value="${item.id}" />">
                                    <div class="visible content">Editar</div>
                                    <div class="hidden content">
                                        <i class="edit icon"></i>
                                    </div>
                                </a>
                                <a class="ui mini animated button red" href="?acao=excluir&id=<c:out value="${item.id}" />">
                                    <div class="visible content">Excluir</div>
                                    <div class="hidden content">
                                        <i class="trash icon"></i>
                                    </div>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>

