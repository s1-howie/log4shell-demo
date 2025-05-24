import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user");
        logger.info("User input: " + user);  // vulnerable line for Log4Shell
        response.setContentType("text/html");
        response.getWriter().println("<html><body><form method='GET'>" +
            "Username: <input name='user'/> <input type='submit'/>" +
            "</form></body></html>");
    }
}