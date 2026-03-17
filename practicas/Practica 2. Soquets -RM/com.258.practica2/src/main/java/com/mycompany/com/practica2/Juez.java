/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.com.practica2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class Juez {

    private static void mostrarCuentas(RespuestaCuenta r) {
        System.out.println("\n========== VISTA DE CUENTAS ==========");
        if (r.getCuentas().isEmpty()) {
            System.out.println("No hay cuentas encontradas.");
        } else {
            for (int i = 0; i < r.getCuentas().size(); i++) {
                Cuenta c = r.getCuentas().get(i);
                System.out.println(i + ") Banco: " + c.getBanco()
                        + " | Nro: " + c.getNroCuenta()
                        + " | Saldo: " + c.getSaldo());
            }
        }
        if (r.getMensaje() != null && !r.getMensaje().trim().isEmpty()) {
            System.out.println("\nAvisos:\n" + r.getMensaje());
        }
        System.out.println("======================================\n");
    }

    public static void main(String[] args) {
        try {
            IJusticia justicia = (IJusticia) Naming.lookup("rmi://localhost/Justicia");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("=== JUEZ (RMI CLIENT) ===");
            System.out.print("CI: ");
            String ci = br.readLine();
            System.out.print("Nombres: ");
            String nom = br.readLine();
            System.out.print("Apellidos: ");
            String ape = br.readLine();

            RespuestaCuenta r = justicia.ConsultarCuentas(ci, nom, ape);
            mostrarCuentas(r);

            if (r.getCuentas().isEmpty()) return;

            while (true) {
                System.out.print("Elige índice de cuenta a congelar (-1 salir): ");
                int idx = Integer.parseInt(br.readLine());
                if (idx == -1) break;

                if (idx < 0 || idx >= r.getCuentas().size()) {
                    System.out.println("Índice inválido.");
                    continue;
                }

                System.out.print("Monto a congelar: ");
                float monto = Float.parseFloat(br.readLine());

                Boolean ok = justicia.Congelar(r.getCuentas().get(idx), monto);
                System.out.println(ok ? "Congelamiento: OK" : "Congelamiento: NO");
            }

        } catch (Exception ex) {
            System.out.println("Error Juez: " + ex.getMessage());
        }
    }
}
