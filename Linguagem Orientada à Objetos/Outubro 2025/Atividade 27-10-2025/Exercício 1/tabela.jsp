<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Tabela 9x9</title></head>
    <body>
        <h2>Tabela 9x9</h2>
        <table border="1" cellpading="5">
            <c:forEach var="i" begin="1" end="9">
                <tr>
                    <c:forEach var="j" begin="1" end="9">
                        <td>(${i}, ${j})</td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>