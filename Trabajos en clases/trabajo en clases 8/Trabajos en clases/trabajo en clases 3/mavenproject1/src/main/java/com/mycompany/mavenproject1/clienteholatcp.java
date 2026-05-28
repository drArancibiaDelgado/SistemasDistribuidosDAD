/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

/**
 *
 * @author Animetx
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
public class clienteholatcp {

         public static void main(String[] args) {
        Scanner leer= new Scanner(System.in);
       int port = 5002;
       
       //String mensaje="";
       System.out.println("Escribe la cadena");
       String mensaje = leer.nextLine();
        try {
            Socket client = new Socket("26.245.193.41", port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            toServer.println(mensaje);
            String result = fromServer.readLine();
            System.out.println("cadena devuelta por el servidor es:  " + result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
