
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import org.apache.logging.log4j.*;

public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) name = "world";
        logger.info("Received request from " + name);
        response.setContentType("text/html");
        response.getWriter().println("<form method='get'>" +
            "Name: <input type='text' name='name'/>" +
            "<input type='submit' value='OK'/>" +
            "</form><p>Hello, " + name + "!</p>");
    }
}
