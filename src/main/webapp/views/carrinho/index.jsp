<%@ page import="br.edu.ifce.utils.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>

<%
    List<ItemCarrinho> itens = (List<ItemCarrinho>)request.getSession().getAttribute("carrinho");
%>

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
            <h1 class="ui header left floated">Meu Carrinho</h1>
            <a href="/" class="ui button right floated">
                Continuar Comprando
            </a>
        </div>
        <div class="sixteen wide column centered grid">
            <% if(itens != null && itens.size() > 0) { %>
            <table class="ui celled table">
                <thead>
                <tr>
                    <th class="">Nome</th>
                    <th>Preço</th>
                    <th>Quantidade</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${itens}" var="item">
                    <tr>
                        <td><c:out value="${item.item.nome}" /></td>
                        <td><c:out value="${item.item.getPrecoFormatado()}" /></td>
                        <td style="width: 10px;">
                            <form action="" method="post">
                                <div class="ui mini action input">
                                    <input type="hidden" name="id" value="<c:out value="${item.itemId}" />">
                                    <input type="number" name="quantidade" value="<c:out value="${item.quantidade}" />">
                                    <button type="submit" class="ui button green">
                                        <i class="refresh icon"></i>
                                    </button>
                                </div>
                            </form>
                        </td>
                        <td style="width: 15px">
                            <div class="ui buttons">
                                <a class="ui mini animated button red" href="?acao=remover&id=<c:out value="${item.itemId}" />">
                                    <div class="visible content">Remover</div>
                                    <div class="hidden content">
                                        <i class="minus icon"></i>
                                    </div>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2"></td>
                    <td style="text-align: right; font-size: 1.3rem; font-weight: bold;">Total</td>
                    <td>
                        <%

                            double valorTotal = 0d;
                            for(ItemCarrinho i : itens) {
                                valorTotal += i.getValorTotal();
                            }

                            String valorFormatado = StringUtils.FormatarParaDinheiro(valorTotal);
                        %>

                        <%= valorFormatado %>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="sixteen wide column centered grid">
                <a class="fluid ui button green" href="/finalizar-compra">Finalizar Compra</a>
            </div>
            <% } else { %>
                <br>
                <h1 class="ui header center aligned">Seu carrinho está vazio!</h1>
            <% } %>
        </div>

    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>

