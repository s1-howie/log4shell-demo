
import java.io.*;
import java.net.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String line;
            String firstLine = in.readLine();
            StringBuilder request = new StringBuilder(firstLine + "\n");

            int contentLength = 0;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                request.append(line).append("\n");
                if (line.toLowerCase().startsWith("content-length:")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }
            }

            char[] bodyChars = new char[contentLength];
            if (contentLength > 0) {
                in.read(bodyChars);
            }
            String body = new String(bodyChars);
            request.append("\n").append(body);

            logger.error("Received request:\n" + request.toString());

            String path = firstLine.split(" ")[1];

            if (firstLine.startsWith("GET")) {
                if (path.equals("/") || path.equals("/index.html")) {
                    serveFile(out, "form.html", "text/html");
                } else if (path.equals("/style.css")) {
                    serveFile(out, "style.css", "text/css");
                } else if (path.equals("/script.js")) {
                    serveFile(out, "script.js", "application/javascript");
                } else {
                    out.write("HTTP/1.1 404 Not Found\r\nContent-Type: text/plain\r\n\r\nNot Found");
                }
            } else if (firstLine.startsWith("POST")) {
                String name = "";
                for (String param : body.split("&")) {
                    String[] kv = param.split("=");
                    if (kv.length == 2 && kv[0].equals("name")) {
                        name = URLDecoder.decode(kv[1], StandardCharsets.UTF_8);
                        break;
                    }
                }

                out.write("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\n");
                out.write("Hello, " + name + "!\nYour submission was received.");
            } else {
                out.write("HTTP/1.1 405 Method Not Allowed\r\nContent-Type: text/plain\r\n\r\nUnsupported method.");
            }

            out.flush();
            clientSocket.close();
        }
    }

    private static void serveFile(BufferedWriter out, String fileName, String contentType) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            out.write("HTTP/1.1 404 Not Found\r\nContent-Type: text/plain\r\n\r\nFile not found.");
            return;
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        out.write("HTTP/1.1 200 OK\r\nContent-Type: " + contentType + "\r\n\r\n");
        out.write(content.toString());
    }
}
