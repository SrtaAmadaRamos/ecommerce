<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registrar - Ecommerce TJW</title>

        <%@ include file="/layout/estilos.jsp" %>
    </head>
    <body>
        
        <%@ include file="/layout/navbar.jsp" %>

        <div class="ui main text grid container" style="padding-top: 1rem">
            <div class="ten wide column centered">
                <h1 class="ui header center aligned">Registre-se</h1>
                <div class="ui divider"></div>
            </div>

            <div class="ten wide column centered grid">
                <div class="six column centered row">
                    <div class="column">
                        <c:if test="${erros.size() > 0}">
                            <div class="ui error message">
                                <i class="close icon"></i>
                                <div class="header">
                                    Ops, ocorreram alguns erros!
                                </div>
                                <ul class="list">
                                    <c:forEach items="${erros}" var="erro">
                                        <li><c:out value="${erro}" /></li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                        <form class="ui form" style="margin-top: 10px;" method="post" action="/registrar">
                            <div class="field">
                                <label for="nome">Nome</label>
                                <input type="text" name="nome" id="nome" placeholder="Nome" required />
                            </div>
                            <div class="field">
                                <label for="email">Email</label>
                                <input type="email" name="email" id="email" placeholder="fulano_de_tal@email.com" required />
                            </div>
                            <div class="field">
                                <label for="senha">Senha</label>
                                <input type="password" name="senha" id="senha" placeholder="*****" required>
                            </div>

                            <button class="ui button" type="submit">Registrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="/layout/javascript.jsp" %>
    </body>
</html>
