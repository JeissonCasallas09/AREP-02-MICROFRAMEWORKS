package edu.eci.arep.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * La clase HttpServer implementa un servidor HTTP básico.
 * Maneja solicitudes HTTP, ya sea para servir archivos estáticos o procesar rutas dinámicas
 * definidas por el usuario. El servidor escucha en el puerto 8080 y responde a solicitudes
 * con diferentes tipos de contenido según la ruta solicitada.
 */
public class HttpServer {
    private static Map<String, BiFunction<HttpRequest, HttpResponse, String>> servicios = new HashMap<>();

    public static void start(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8080.");
            System.exit(1);
        }
        
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            handleClient(clientSocket);
        }
    }

    /**
     * Procesa la solicitud de un cliente HTTP.
     * Dependiendo de la ruta solicitada, puede servir un archivo estático o ejecutar una ruta dinámica.
     * 
     * @param clientSocket Socket conectado del cliente.
     * @throws IOException Si ocurre un error al leer o escribir la solicitud o respuesta.
     * @throws URISyntaxException Si la URI solicitada tiene una sintaxis incorrecta.
     */
    private static void handleClient(Socket clientSocket) throws IOException, URISyntaxException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        String inputLine, file = "";
        boolean isFirstLine = true;

        while ((inputLine = in.readLine()) != null) {
            if (isFirstLine) {
                file = inputLine.split(" ")[1];
                isFirstLine = false;
            }
            System.out.println("Received: " + inputLine);
            if (!in.ready()) break;
        }
        
        URI resourceUri = new URI(file);
        if (resourceUri.getPath().startsWith("/app/")) {
            HttpRequest req = new HttpRequest(resourceUri.getPath(), resourceUri.getQuery());
            HttpResponse resp = new HttpResponse();
            out.println(processRequest(req, resp));
        } else {
            serveStaticFile(out, resourceUri.getPath());
        }
        
        out.close();
        in.close();
        clientSocket.close();
    }

    /**
     * Registra un manejador para una ruta GET específica.
     * 
     * @param route La ruta para la cual registrar el manejador.
     * @param handler El manejador que procesará las solicitudes para esta ruta.
     */    
    public static void get(String route, BiFunction<HttpRequest, HttpResponse, String> handler) {
        servicios.put("/app" + route, handler);
    }


    /**
    * Procesa una solicitud HTTP, ejecutando el manejador asociado a la ruta solicitada.
    * Si la ruta no existe, devuelve un error 404.
    * 
    * @param req La solicitud HTTP.
    * @param resp La respuesta HTTP.
    * @return La respuesta HTTP como un string, que incluye el código de estado y el cuerpo.
    */
    public static String processRequest(HttpRequest req, HttpResponse resp) {
        BiFunction<HttpRequest, HttpResponse, String> handler = servicios.get(req.getPath());
        if (handler == null) {
            return "HTTP/1.1 404 Not Found\r\n\r\n";
        }
        return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n" +  handler.apply(req, resp);
    }

    /**
     * Sirve un archivo estático desde el servidor de acuerdo con la ruta solicitada.
     * Si el archivo no se encuentra, responde con un error 404.
     * 
     * @param out El escritor de salida para enviar la respuesta.
     * @param path La ruta del archivo solicitado.
     */
    private static void serveStaticFile(PrintWriter out, String path) {
        String filePath = "src/main/java/edu/eci/arep/webserver/static" + path;
        if (filePath.endsWith("/")) {
            filePath += "index.html";
        }
        
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: " + getContentType(filePath));
            out.println();
            out.write(new String(fileContent));
        } catch (IOException e) {
            out.println("HTTP/1.1 404 Not Found\r\n\r\n<h1>404 Not Found</h1>");
        }
    }

    /**
     * Determina el tipo de contenido de un archivo basado en su extensión.
     * 
     * @param file La ruta del archivo.
     * @return El tipo de contenido correspondiente al archivo.
     */
    private static String getContentType(String file) {
        if (file.endsWith(".html")) return "text/html";
        if (file.endsWith(".css")) return "text/css";
        if (file.endsWith(".js")) return "application/javascript";
        if (file.endsWith(".png")) return "image/png";
        if (file.endsWith(".jpg") || file.endsWith(".jpeg")) return "image/jpeg";
        if (file.endsWith(".gif")) return "image/gif";
        return "text/plain";
    }
}
