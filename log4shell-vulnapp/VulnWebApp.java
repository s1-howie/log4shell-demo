import java.io.*;
import java.net.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VulnWebApp {
    private static final Logger logger = LogManager.getLogger(VulnWebApp.class);

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Vulnerable Log4j web server listening on port 8080...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            StringBuilder request = new StringBuilder();
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                request.append(line).append("\n");
            }

            logger.error("Received request:\n" + request.toString());

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            out.write("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello from vulnerable server\n");
            out.flush();
            clientSocket.close();
        }
    }
