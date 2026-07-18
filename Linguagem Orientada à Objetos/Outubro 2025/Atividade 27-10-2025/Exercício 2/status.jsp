<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Status do Login</title></head>
    <body>
        <h2>Status do Usuário</h2>
        
        <c:choose>
            <c:when test="${sessionScope.logado}">
                <c:choose>
                    <c:when test="${sessionScope.administrador}">
                        <p>Usuário é <strong>administrador</strong>.</p>
                    </c:when>
                    <c:otherwise>
                        <p>Usuário <strong>não é administrador</strong>.</p>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <p>Usuário <strong>não está logado</strong>.</p>
            </c:otherwise>
        </c:choose>
                
        <a href="login.jsp">Voltar ao login</a>
    </body>
</html>
