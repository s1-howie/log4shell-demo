import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "VulnWebApp", urlPatterns = {"/"})
public class VulnWebApp extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Log4Shell PoC</title></head><body>");
        out.println("<h2>Enter your name:</h2>");
        out.println("<form method='POST'>");
        out.println("<input type='text' name='name' />");
        out.println("<input type='submit' value='Submit' />");
        out.println("</form>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        org.apache.logging.log4j.LogManager.getLogger(VulnWebApp.class).error("User input: " + name);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Thanks</title></head><body>");
        out.println("<h2>Thanks, " + name + "!</h2>");
        out.println("</body></html>");
    }
}
