/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.orquestadorrmi;

/**
 *
 * @author USUARIO
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorMain {
    public static void main(String[] args) {
        try {
            // Creamos el registro RMI en el puerto 1099
            Registry registro = LocateRegistry.createRegistry(1099);
            
            // Instanciamos nuestra implementación
            OrquestadorImpl orquestador = new OrquestadorImpl();
            
            // Publicamos el servicio con el nombre "ServicioIntercambio"
            registro.rebind("ServicioIntercambio", orquestador);
            
            System.out.println("Servidor Orquestador RMI iniciado y esperando peticiones...");
            
        } catch (Exception e) {
            System.err.println("Error al iniciar el servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}