
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.logging.log4j.*;

public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Log4Shell Test App</h1>");
        out.println("<form method='POST'><input name='userInput' type='text'/><input type='submit'/></form>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String userInput = request.getParameter("userInput");
        logger.error("User input: " + userInput);
        response.getWriter().println("Input received: " + userInput);
    }
}
