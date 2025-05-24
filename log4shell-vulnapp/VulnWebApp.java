import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.logging.log4j.*;

public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");
        if (user != null) {
            logger.info("User input: " + user);
            out.println("<h1>Hello, " + user + "!</h1>");
        } else {
            out.println("<form method='GET'>"
                      + "Name: <input type='text' name='user' />"
                      + "<input type='submit' value='OK' />"
                      + "</form>");
        }
    }
}
