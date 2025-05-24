
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.logging.log4j.*;

public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        if (user != null) {
            logger.info("User input: " + user);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form method='GET'>");
        out.println("Enter your name: <input type='text' name='user' />");
        out.println("<input type='submit' value='OK' />");
        out.println("</form>");
        out.println("</body></html>");
    }
}
