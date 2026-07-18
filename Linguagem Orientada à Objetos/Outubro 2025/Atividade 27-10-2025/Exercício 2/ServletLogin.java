import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ServletLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        HttpSession sessao = request.getSession();
        
        if("admin".equals(login) && "admin".equals(senha)){
            sessao.setAttribute("logado", true);
            sessao.setAttribute("administrado", true);
        } else if ("usuario".equals(login) && "usuario".equals(senha)){
            sessao.setAttribute("logado", true);
            sessao.setAttribute("administrado", false);
        } else {
            sessao.setAttribute("logado", false);
            sessao.setAttribute("administrador", false);
        }
        
        response.sendRedirect("status.jsp");
        
    }
}
