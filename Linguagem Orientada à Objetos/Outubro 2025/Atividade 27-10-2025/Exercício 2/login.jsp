<!DOCTYPE html>
<html>
    <head><meta charset="UTF-8"><title>Login</title></head>
    <body>
        <h2>Login</h2>
        <form method="post" action="ServletLogin">
            <label>Usuário:</label>
            <input type="text" name="login" required><br><br>
            <label>Senha:</label>
            <input type="password" name="senha" required><br><br>
            <button type="submit">Entrar</button>
        </form>
    </body>
</html>