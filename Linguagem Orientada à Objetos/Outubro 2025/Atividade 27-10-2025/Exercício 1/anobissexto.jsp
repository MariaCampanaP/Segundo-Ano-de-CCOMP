<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Ano Bissexto</title></head>
    <body>
        <h2>Verificar Ano</h2>
        <form method="get">
            <label>Informe o ano:</label>
            <input type="number" name="ano" required>
            <button type="submit">Enviar</button>
        </form>
        
        <c:if test="${not empty param.ano}">
            <p>Ano recebido: ${param.ano}</p>
            <c:choose>
                <c:when test="${(param.ano % 400 == 0) or ((param.ano % 4 == 0) and (param.ano % 100 != 0))}">
                    <p>O ano ${param.ano} é <strong>bissexto</strong>.</p>
                </c:when>
                <c:otherwise>
                    <p>O ano ${param.ano} <strong>não é bissexto</strong>.</p>
                </c:otherwise>
            </c:choose>
        </c:if>
    </body>
</html>