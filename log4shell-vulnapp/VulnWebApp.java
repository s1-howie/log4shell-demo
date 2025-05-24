import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "VulnWebApp", urlPatterns = {"/"})
public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ip = request.getHeader("X-Forwarded-For");
        logger.error("X-Forwarded-For: " + ip); // 🔥 Vulnerable Log4Shell injection point

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Vulnerable App</title>");
        out.println("<style>body { font-family: sans-serif; padding: 2em; background: #f0f0f0; } input, button { padding: 0.5em; }</style>");
        out.println("</head><body>");
        out.println("<h1>Welcome to the Vulnerable Log4Shell App</h1>");
        out.println("<p>This is a demo app vulnerable to CVE-2021-44228 (Log4Shell).</p>");
        out.println("<form method='POST'>");
        out.println("Enter your name: <input type='text' name='name' size='40'/> ");
        out.println("<button type='submit'>Submit</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");

        logger.error("User-supplied name: " + name);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body style='font-family:sans-serif;'>");
        out.println("<h2>Hello, " + name + "!</h2>");
        out.println("<p>Your input has been processed.</p>");
        out.println("<a href='/'>Go back</a>");
        out.println("</body></html>");
    }
}
