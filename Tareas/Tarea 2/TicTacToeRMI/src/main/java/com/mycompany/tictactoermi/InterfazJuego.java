/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tictactoermi;

/**
 *
 * @author Animetx
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

// La interfaz debe extender de Remote
public interface InterfazJuego extends Remote {
    
    // Envía la posición del 0 al 8 donde el cliente quiere marcar su 'X'.
    // Retorna un mensaje indicando si alguien ganó, empató, o si el juego sigue.
    String jugar(int posicion) throws RemoteException;
    
    // Devuelve el estado actual del tablero
    char[] obtenerTablero() throws RemoteException;
    
    // Reinicia el juego
    void reiniciar() throws RemoteException;
}