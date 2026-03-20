/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.tictactoermi;

/**
 *
 * @author Animetx
 */
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorJuego extends UnicastRemoteObject implements InterfazJuego {
    
    private char[] tablero;
    private boolean juegoTerminado;

    public ServidorJuego() throws RemoteException {
        super();
        tablero = new char[9];
        reiniciar();
    }

    @Override
    public void reiniciar() throws RemoteException {
        for(int i = 0; i < 9; i++) {
            tablero[i] = '-'; // '-' significa casilla vacía
        }
        juegoTerminado = false;
    }

    @Override
    public char[] obtenerTablero() throws RemoteException {
        return tablero;
    }

    @Override
    public String jugar(int pos) throws RemoteException {
        // Si el juego ya terminó o la casilla está ocupada, no hacemos nada
        if (juegoTerminado || tablero[pos] != '-') {
            return "Invalido";
        }

        // 1. Turno del Cliente (X)
        tablero[pos] = 'X'; 
        if (verificarGanador() == 'X') return "¡Ganaste (Cliente)!";
        if (tableroLleno()) return "¡Empate!";

        // 2. Turno del Servidor (O) - Lógica simple: busca el primer espacio vacío
        for (int i = 0; i < 9; i++) {
            if (tablero[i] == '-') {
                tablero[i] = 'O';
                break;
            }
        }
        if (verificarGanador() == 'O') return "¡Gana el Servidor (O)!";
        if (tableroLleno()) return "¡Empate!";

        return "Continua"; // El juego sigue
    }

    // Comprueba todas las combinaciones ganadoras
    private char verificarGanador() {
        int[][] lineas = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Filas horizontales
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columnas verticales
            {0, 4, 8}, {2, 4, 6}             // Diagonales cruzadas
        };
        for (int[] l : lineas) {
            if (tablero[l[0]] != '-' && tablero[l[0]] == tablero[l[1]] && tablero[l[1]] == tablero[l[2]]) {
                juegoTerminado = true;
                return tablero[l[0]]; // Retorna 'X' o 'O'
            }
        }
        return '-';
    }

    private boolean tableroLleno() {
        for (char c : tablero) {
            if (c == '-') return false;
        }
        juegoTerminado = true;
        return true;
    }

    // Método principal para iniciar el servidor
    public static void main(String[] args) {
        try {
            // Crea el registro RMI en el puerto 1099
            Registry registro = LocateRegistry.createRegistry(1099);
            registro.rebind("JuegoTicTacToe", new ServidorJuego());
            System.out.println("Servidor RMI iniciado y listo para jugar...");
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}