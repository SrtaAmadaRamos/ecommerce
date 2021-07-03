<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Ecommerce TJW</title>

        <%@ include file="/layout/estilos.jsp" %>
    </head>
    <body>
        
        <%@ include file="/layout/navbar.jsp" %>
    
        <div class="ui main text fluid container">
            <h1 class="ui header">Semantic UI Fixed Template</h1>

            <div class="ui grid">
                <c:forEach items="${itens}" var="item">
                    <div class="four wide column">
                        <div class="ui card">
                            <div class="image">
                                <img src="<c:out value="${item.imagem}" />">
                            </div>
                            <div class="content">
                                <a class="header">
                                    <c:out value="${item.nome}" />
                                </a>
                                <div class="description">
                                    <c:out value="${item.descricao}" />
                                </div>
                            </div>
                            <div class="extra content">
                                <% if (request.getSession().getAttribute("nome") != null) { %>
                                <a href="carrinho?acao=adicionar&id=<c:out value="${item.id}" />">
                                    <i class="cart plus icon"></i>
                                    Adicionar ao Carrinho
                                </a>
                                <% } else { %>
                                    <a href="/login" >
                                        <i class="cart plus icon"></i>
                                        Adicionar ao Carrinho
                                    </a>
                                <% } %>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <%@ include file="/layout/javascript.jsp" %>
    </body>
</html>
