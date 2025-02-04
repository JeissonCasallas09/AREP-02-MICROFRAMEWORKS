package edu.eci.arep.webserver;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas unitarias para el servidor HTTP.
 * Se utilizan varios métodos de prueba para verificar el funcionamiento
 * correcto de los puntos finales del servidor y su respuesta.
 */
public class HttpServerUnitTest {


    /**
     * Configura los puntos finales del servidor antes de ejecutar las pruebas.
     * Se registran los manejadores para las rutas /pi, /hello, /helloget, y /hellopost.
     */
    @BeforeAll
    static void setUp() {
        HttpServer.get("/pi", (req, res) -> String.valueOf(Math.PI));
        HttpServer.get("/hello", (req, res) -> "hello world!");
        HttpServer.get("/helloget", (req, res) -> "Hello " + req.getValue("name"));
        HttpServer.get("/hellopost", (req, res) -> "Hello " + req.getValue("name"));
    }

    /**
     * Prueba para verificar el punto final "/pi".
     * Se espera que el resultado contenga el valor de Pi en la respuesta.
     */
    @Test
    void testPiEndpoint() throws URISyntaxException {
        HttpRequest req = new HttpRequest("/app/pi", null);
        HttpResponse resp = new HttpResponse();
        
        String result = HttpServer.processRequest(req, resp);
        
        assertTrue(result.contains("HTTP/1.1 200 OK"));
        assertTrue(result.contains("3.141592653589793"));
    }

    /**
    * Prueba para verificar el punto final "/hello".
    * Se espera que la respuesta contenga el saludo "hello world!".
    */
    @Test
    void testHelloEndpoint() throws URISyntaxException {
        HttpRequest req = new HttpRequest("/app/hello", null);
        HttpResponse resp = new HttpResponse();
        
        String result = HttpServer.processRequest(req, resp);
        
        assertTrue(result.contains("HTTP/1.1 200 OK"));
        assertTrue(result.contains("hello world!"));
    }

    /**
     * Prueba para verificar el punto final "/helloget".
     * Se espera que la respuesta contenga el saludo con el nombre proporcionado en el parámetro "name".
     */

    @Test
    void testHelloGetEndpoint() throws URISyntaxException {
        HttpRequest req = new HttpRequest("/app/helloget", "name=John");
        HttpResponse resp = new HttpResponse();
        
        String result = HttpServer.processRequest(req, resp);
        
        assertTrue(result.contains("HTTP/1.1 200 OK"));
        assertTrue(result.contains("Hello John"));
    }

    /**
     * Prueba para verificar el punto final "/hellopost".
     * Se espera que la respuesta contenga el saludo con el nombre proporcionado en el parámetro "name".
     */
    @Test
    void testHelloPostEndpoint() throws URISyntaxException {
        HttpRequest req = new HttpRequest("/app/hellopost", "name=Jane");
        HttpResponse resp = new HttpResponse();
        
        String result = HttpServer.processRequest(req, resp);
        
        assertTrue(result.contains("HTTP/1.1 200 OK"));
        assertTrue(result.contains("Hello Jane"));
    }

    /**
     * Prueba para verificar una ruta no válida "/invalid".
     * Se espera que la respuesta contenga un error 404 Not Found.
     */
    @Test
    void testInvalidEndpoint() throws URISyntaxException {
        HttpRequest req = new HttpRequest("/app/invalid", null);
        HttpResponse resp = new HttpResponse();
        
        String result = HttpServer.processRequest(req, resp);
        
        assertTrue(result.contains("HTTP/1.1 404 Not Found"));
    }

    /**
     * Prueba para verificar el punto final "/helloget" cuando no se proporciona el parámetro "name".
     * Se espera que el valor por defecto sea "null" en el saludo.
     */    
    @Test
    void testHelloGetWithoutName() throws URISyntaxException {
        HttpRequest req = new HttpRequest("/app/helloget", null);
        HttpResponse resp = new HttpResponse();

        String result = HttpServer.processRequest(req, resp);

        assertTrue(result.contains("HTTP/1.1 200 OK"));
        assertTrue(result.contains("Hello null"));
    }

     /**
     * Prueba para verificar que el tipo de contenido de la respuesta sea "application/json".
     * Se espera que la respuesta contenga el tipo de contenido adecuado.
     */   
    @Test
    void testResponseContentTypeJson() throws URISyntaxException {
        HttpRequest req = new HttpRequest("/app/hello", null);
        HttpResponse resp = new HttpResponse();

        String result = HttpServer.processRequest(req, resp);

        assertTrue(result.contains("HTTP/1.1 200 OK"));
        assertTrue(result.contains("Content-Type: application/json"));
    }

}
