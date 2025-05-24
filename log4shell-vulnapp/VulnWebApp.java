import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<form method='POST'>" +
                                     "Name: <input type='text' name='name' />" +
                                     "<input type='submit' value='OK' />" +
                                     "</form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        logger.info("Received name: " + name);
        response.setContentType("text/html");
        response.getWriter().println("<h1>Hello, " + name + "!</h1>");
    }
}