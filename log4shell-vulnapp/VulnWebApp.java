import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Log4Shell Input Test</title></head><body>");
        out.println("<h1>Enter input to trigger Log4Shell:</h1>");
        out.println("<form method='POST'>");
        out.println("<input type='text' name='userinput' size='50' />");
        out.println("<input type='submit' value='Submit' />");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String userInput = request.getParameter("userinput");

        // 🔥 VULNERABLE LOGGING LINE — this is where Log4Shell gets triggered
        logger.error("User input received: " + userInput);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<p>Input received and logged.</p>");
        out.println("<a href='/'>Go back</a>");
        out.println("</body></html>");
    }
}
