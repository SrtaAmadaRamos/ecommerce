<%@ page import="br.edu.ifce.model.Item" %>
<%@ page import="br.edu.ifce.utils.StringUtils" %>
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
            <h1 class="ui header center aligned">Compra finalizada com sucesso!</h1>
        </div>
        <div class="sixteen wide column centered grid">
            <table class="ui celled table">
                <thead>
                <tr>
                    <th class="">Nome</th>
                    <th>Valor Unitário</th>
                    <th>Quantidade</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${Carrinho}" var="item">
                    <tr>
                        <td><c:out value="${item.item.nome}" /></td>
                        <td><c:out value="${item.item.getPrecoFormatado()}" /></td>
                        <td><c:out value="${item.quantidade}" /></td>
                        <td><c:out value="${item.getPrecoFormatado()}" /></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2"></td>
                    <td style="text-align: right; font-size: 1.3rem; font-weight: bold;">Total</td>
                    <td><c:out value="${ValorTotal}" /></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="sixteen wide column centered grid">
            <a class="fluid ui button green" href="/">Ir para página inicial</a>
        </div>
    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>

