/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tictactoermi;

/**
 *
 * @author Animetx
 */
import javax.swing.*;
import java.awt.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteGUI extends JFrame {
    
    private InterfazJuego servidor;
    private JButton[] botones;

    public ClienteGUI() {
        // 1. Conectar al servidor RMI
        try {
            Registry registro = LocateRegistry.getRegistry("localhost", 1099);
            servidor = (InterfazJuego) registro.lookup("JuegoTicTacToe");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: Inicia el Servidor primero.");
            System.exit(0);
        }

        // 2. Configurar la ventana
        setTitle("Tic Tac Toe - Cliente (X) vs Servidor (O)");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3)); // Cuadrícula de 3x3

        // 3. Crear los 9 botones
        botones = new JButton[9];
        for (int i = 0; i < 9; i++) {
            final int posicion = i; // Guardamos la posición para el evento del botón
            botones[i] = new JButton("");
            botones[i].setFont(new Font("Arial", Font.BOLD, 60));
            
            // Acción al hacer clic en un botón
            botones[i].addActionListener(e -> enviarJugada(posicion));
            add(botones[i]);
        }
        
        actualizarPantalla();
    }

    private void enviarJugada(int posicion) {
        try {
            // Enviamos la jugada al servidor
            String estado = servidor.jugar(posicion);
            actualizarPantalla();
            
            // Si el servidor nos devuelve algo distinto a "Continua" o "Invalido", el juego terminó
            if (!estado.equals("Continua") && !estado.equals("Invalido")) {
                JOptionPane.showMessageDialog(this, estado);
                servidor.reiniciar(); // Reiniciamos para otra partida
                actualizarPantalla();
            }
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    private void actualizarPantalla() {
        try {
            char[] tablero = servidor.obtenerTablero();
            for (int i = 0; i < 9; i++) {
                // Si hay un '-', mostramos texto vacío, si no, mostramos la 'X' o la 'O'
                botones[i].setText(tablero[i] == '-' ? "" : String.valueOf(tablero[i]));
                // Damos un poco de color
                if (tablero[i] == 'X') botones[i].setForeground(Color.BLUE);
                if (tablero[i] == 'O') botones[i].setForeground(Color.RED);
            }
        } catch (Exception e) {
             System.err.println("Error al obtener tablero: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            new ClienteGUI().setVisible(true);
        });
    }
}
