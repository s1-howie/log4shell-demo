import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.logging.log4j.*;

public class VulnWebApp extends HttpServlet {
    static Logger logger = LogManager.getLogger("VulnWebApp");

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null) name = "world";
        logger.info("Received request for user: " + name);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><form method='GET'>");
        out.println("Name: <input type='text' name='name'/><input type='submit' value='OK'/>");
        out.println("</form>");
        out.println("<h1>Hello, " + name + "</h1></body></html>");
    }
}
