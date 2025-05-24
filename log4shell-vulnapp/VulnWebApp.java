import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.logging.log4j.*;

public class VulnWebApp extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<form method='POST'>");
        out.println("Enter your name: <input type='text' name='name'/>");
        out.println("<input type='submit' value='OK'/>");
        out.println("</form>");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        logger.info("User input: {}", name);

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<h1>Hello, " + name + "</h1>");
    }
}