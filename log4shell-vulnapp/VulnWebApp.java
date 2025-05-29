import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 🔥 Log User-Agent for GET-based exploit (e.g., curl-based attacks)
        String userAgent = request.getHeader("User-Agent");
        logger.error("User-Agent: " + userAgent);

        // Show the input form
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html><head><title>Log4Shell Test</title></head><body>");
        out.println("<h2>Log4Shell Demo</h2>");
        out.println("<form method='POST'>");
        out.println("<label>Enter something:</label><br>");
        out.println("<input type='text' name='userinput' size='60'><br><br>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 🔥 Log User-Agent again (optional for POST)
        String userAgent = request.getHeader("User-Agent");
        logger.error("User-Agent (POST): " + userAgent);

        // 🔥 Log form input for web-based exploit (e.g., JNDI string via input box)
        String userInput = request.getParameter("userinput");
        logger.error("User form input: " + userInput);

        // Return confirmation
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h3>Input submitted and logged.</h3>");
        out.println("<a href='/'>Back</a>");
        out.println("</body></html>");
    }
}
