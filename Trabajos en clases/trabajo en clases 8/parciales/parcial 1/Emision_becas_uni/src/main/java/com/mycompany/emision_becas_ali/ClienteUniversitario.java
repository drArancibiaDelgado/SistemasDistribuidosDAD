/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.emision_becas_ali;

/**
 *
 * @author USUARIO
 */
import java.rmi.Naming;
import java.util.Scanner;

public class ClienteUniversitario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("CI: "); String ci = sc.nextLine();
        System.out.print("Nombres: "); String nombres = sc.nextLine();
        System.out.print("Apellidos: "); String apellidos = sc.nextLine();
        
        try {
            ServidorUniversitarioInterface server = (ServidorUniversitarioInterface)
                Naming.lookup("rmi://localhost/ServidorUniversitario");
            
            RespuestaBeca resp = server.solicitarBeca(ci, nombres, apellidos);
            System.out.println("\n RESULTADO:");
            System.out.println(resp);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}