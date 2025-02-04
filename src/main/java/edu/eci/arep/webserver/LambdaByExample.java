package edu.eci.arep.webserver;

import java.util.function.Function;
import java.util.function.Supplier;


public class LambdaByExample {
    // public static void main(String[] args) {
    //     FunctionNoParam f= new FunctionNoParam(){
    //         public String execute(){
    //             return "hello world";
    //         }
    //     };
    //     System.out.println("function execution: "+ f.execute());
    // }

    public static void main(String[] args) {
        FunctionNoParam<Double> f= () -> Math.PI;
        System.out.println("Function Execution: "+ f.execute());

        FunctionOneParameter<Integer,String> size= (str) -> str.length(); 
        System.out.println("Function Execution: "+ size.execute("Hola mundo"));

        FunctionOneParameter<Double,Double> sin= (angulo) -> Math.sin(angulo); 
        System.out.println("Function Execution: "+ sin.execute(0.8));

        Supplier <Double> a= () -> Math.PI;
        System.out.println("Function Execution: "+ a.get());

        Function <String,Integer> b= (str) -> str.length(); 
        System.out.println("Function Execution: "+ b.apply("Hola mundo"));

        Function <Double,Double> c= (angulo) -> Math.sin(angulo); 
        System.out.println("Function Execution: "+ c.apply(0.8));
    }
}
