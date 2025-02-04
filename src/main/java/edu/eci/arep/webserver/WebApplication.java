package edu.eci.arep.webserver;

import java.io.IOException;
import java.net.URISyntaxException;

import static edu.eci.arep.webserver.HttpServer.get;

/**
 * La clase WebApplication define una aplicación web sencilla utilizando un servidor HTTP.
 * En esta clase se definen las rutas y las respuestas asociadas que el servidor manejará.
 * La aplicación responde a solicitudes HTTP GET y POST.
 */
public class WebApplication {
    public static void main(String[] args) throws IOException, URISyntaxException {
        get("/pi", (req, res) -> String.valueOf(Math.PI));

        get("/hello", (req, res) -> "hello world!");

        
        get("/helloget", (req, res) -> "Hello " + req.getValue("name"));
        

        get("/hellopost", (req, res) -> "Hello " + req.getValue("name"));
        
        HttpServer.start(args);
        
    }
}

