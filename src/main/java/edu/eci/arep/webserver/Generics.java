package edu.eci.arep.webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Generics {
    public static void main(String[] args) throws IOException {
        List<Integer> intList = new LinkedList();
        intList.add (new Integer(0));
        //intList.add("hola");
        Integer x = (Integer) intList.iterator().next();
    }
}
