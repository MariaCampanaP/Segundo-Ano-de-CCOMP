<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Soma</title></head>
    <body>
        <h2>Somar dois números</h2>
        <form method="get">
            <input type="number" name="a" required> + <input type="number" name="b" required>
            <button type="submit">Calcular</button>
        </form>
        
        <c:if test="${not empty param.a and not empty param.b}">
            <p>Soma: ${param.a + param.b}</p>
        </c:if>
    </body>
</html>