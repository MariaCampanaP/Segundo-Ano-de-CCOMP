<%-- 
    Document   : tabela
    Created on : 22 de out. de 2025, 14:01:49
    Author     : maria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabela 9x9</title>
        <style>
            table {
                border-collapse: collapse;
                margin: 20px auto;
                font-family: Arial, sans-serif;
            }
            td{
                border: 1px solid #444;
                padding: 8px 12px;
                text-align: center;
            }
            th{
                background-color: #ddd;
                border: 1px solid #444;
                padding: 8px 12px;
            }
            h2{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <h2>Tabela 9 x 9 (valores de (i, j))</h2>
        
        <table>
            <%
                for(int i = 1; i <= 9; i++){
            %>
                <tr>
                    <%
                        for(int j = 1; j <= 9; j++){
                    %>
                        <td>(<%= i %>, <%= j %>)</td>
                    <%
                        }
                    %>
                </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
