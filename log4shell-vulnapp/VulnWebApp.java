import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VulnWebApp extends HttpServlet {
    static Logger logger = LogManager.getLogger(VulnWebApp.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) name = "world";
        logger.info("User input: " + name);
        response.setContentType("text/html");
        response.getWriter().println("<html><body><form method='GET'>Name: <input name='name'/><input type='submit' value='OK'/></form><br>Hello, " + name + "</body></html>");
    }
}
