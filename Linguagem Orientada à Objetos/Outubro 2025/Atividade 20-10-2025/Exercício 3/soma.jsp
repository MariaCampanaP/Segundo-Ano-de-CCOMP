<%-- 
    Document   : somajsp
    Created on : 22 de out. de 2025, 13:11:51
    Author     : maria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultado da Soma</title>
    </head>
    <body>
        <h2>Resultado</h2>
        <%
            String sNum1 = request.getParameter("num1");
            String sNum2 = request.getParameter("num2");
            
            if(sNum1 == null || sNum2 == null){
        %>
            <p>Parâmetros não recebidos. Volte e envie os dois números.</p>
        <%
           }else{
            try{
              double num1 = Double.parseDouble(sNum1);
              double num2 = Double.parseDouble(sNum2);
              double soma = num1 + num2;
        %>
            <p><strong><%= num1 %></strong> + <strong><%= num2 %></strong> = <strong><%= soma %></strong></p>
        <%
            }catch (NumberFormatException e){
        %>
            <p>Entrada inválida. Certifique-se de digitar números válidos.</p>
        <%
            }    
        }
        %>
        <p><a href="index.html">Voltar</a></p>
    </body>
</html>
