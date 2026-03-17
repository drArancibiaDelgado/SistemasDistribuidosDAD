/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.com.practica2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Justicia extends UnicastRemoteObject implements IJusticia {
    private static final long serialVersionUID = 1L;

    // Puertos (usamos los que tú ya estabas usando)
    private static final String HOST_MERCANTIL = "localhost";
    private static final int PORT_MERCANTIL = 5002;

    private static final String HOST_BCP = "localhost";
    private static final int PORT_BCP = 6789;

    public Justicia() throws RemoteException {
        super();
    }

    // Consulta a ambos bancos y consolida en RespuestaCuenta (según arquitectura del enunciado) [Source](https://www.genspark.ai/api/files/s/wbcaH8Tp)
    @Override
    public RespuestaCuenta ConsultarCuentas(String ci, String nombres, String apellidos) throws RemoteException {
        RespuestaCuenta resp = new RespuestaCuenta();

        // 1) Mercantil TCP: enviar CI y operación (ej: buscar) [Source](https://www.genspark.ai/api/files/s/wbcaH8Tp)
        try {
            Socket s = new Socket(HOST_MERCANTIL, PORT_MERCANTIL);
            PrintStream toServer = new PrintStream(s.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));

            toServer.println(ci);
            toServer.println("buscar");

            String data = fromServer.readLine(); // "cuenta-saldo:cuenta-saldo..."
            s.close();

            parseCuentas(data, EBanco.MERCANTIL, ci, nombres, apellidos, resp);
        } catch (Exception ex) {
            resp.setMensaje(resp.getMensaje() + "Error Mercantil(TCP): " + ex.getMessage() + "\n");
        }

        // 2) BCP UDP: enviar "Operacion:ci" (usamos "buscar:ci") [Source](https://www.genspark.ai/api/files/s/wbcaH8Tp)
        try {
            DatagramSocket sock = new DatagramSocket();
            String req = "buscar:" + ci;

            byte[] out = req.getBytes();
            InetAddress ip = InetAddress.getByName(HOST_BCP);

            DatagramPacket p = new DatagramPacket(out, out.length, ip, PORT_BCP);
            sock.send(p);

            byte[] in = new byte[1000];
            DatagramPacket r = new DatagramPacket(in, in.length);
            sock.receive(r);

            String data = new String(r.getData(), 0, r.getLength()).trim();
            sock.close();

            parseCuentas(data, EBanco.BCP, ci, nombres, apellidos, resp);
        } catch (Exception ex) {
            resp.setMensaje(resp.getMensaje() + "Error BCP(UDP): " + ex.getMessage() + "\n");
        }

        return resp;
    }

    // Convierte "cuenta-saldo:cuenta-saldo" a objetos Cuenta [Source](https://www.genspark.ai/api/files/s/wbcaH8Tp)
    private void parseCuentas(String data, EBanco banco, String ci, String nombres, String apellidos, RespuestaCuenta resp) {
        if (data == null) return;
        data = data.trim();
        if (data.isEmpty()) return;

        String[] pares = data.split(":");
        for (String par : pares) {
            par = par.trim();
            if (par.isEmpty()) continue;

            String[] cs = par.split("-");
            if (cs.length != 2) continue;

            String nro = cs[0].trim();
            float saldo = 0;
            try { saldo = Float.parseFloat(cs[1].trim()); } catch (Exception e) {}

            resp.getCuentas().add(new Cuenta(banco, nro, ci, nombres, apellidos, saldo));
        }
    }

    @Override
    public Boolean Congelar(Cuenta cuenta, Float monto) throws RemoteException {
        if (cuenta == null || cuenta.getBanco() == null) return false;

        // El enunciado exige Congelar a nivel del sistema [Source](https://www.genspark.ai/api/files/s/wbcaH8Tp)
        // pero NO fija el formato por sockets; usamos el más simple:
        // - Mercantil TCP: CI, "congelar", "nro-monto" -> "OK"/"NO"
        // - BCP UDP: "congelar:ci:nro:monto" -> "OK"/"NO"

        switch (cuenta.getBanco()) {
            case MERCANTIL:
                return congelarMercantilTCP(cuenta, monto);
            case BCP:
                return congelarBCPUDP(cuenta, monto);
            default:
                return false;
        }
    }

    private Boolean congelarMercantilTCP(Cuenta cuenta, Float monto) {
        try {
            Socket s = new Socket(HOST_MERCANTIL, PORT_MERCANTIL);
            PrintStream toServer = new PrintStream(s.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));

            toServer.println(cuenta.getCi());
            toServer.println("congelar");
            toServer.println(cuenta.getNroCuenta() + "-" + monto);

            String ans = fromServer.readLine(); // OK / NO
            s.close();

            return ans != null && ans.trim().equalsIgnoreCase("OK");
        } catch (Exception ex) {
            return false;
        }
    }

    private Boolean congelarBCPUDP(Cuenta cuenta, Float monto) {
        try {
            DatagramSocket sock = new DatagramSocket();
            String req = "congelar:" + cuenta.getCi() + ":" + cuenta.getNroCuenta() + ":" + monto;

            byte[] out = req.getBytes();
            InetAddress ip = InetAddress.getByName(HOST_BCP);

            DatagramPacket p = new DatagramPacket(out, out.length, ip, PORT_BCP);
            sock.send(p);

            byte[] in = new byte[1000];
            DatagramPacket r = new DatagramPacket(in, in.length);
            sock.receive(r);

            String ans = new String(r.getData(), 0, r.getLength()).trim();
            sock.close();

            return ans.equalsIgnoreCase("OK");
        } catch (Exception ex) {
            return false;
        }
    }
}




