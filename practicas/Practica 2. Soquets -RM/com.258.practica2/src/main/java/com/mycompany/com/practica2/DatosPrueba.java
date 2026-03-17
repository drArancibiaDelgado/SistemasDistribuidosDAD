/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.com.practica2;

import java.util.ArrayList;
import java.util.HashMap;

public class DatosPrueba {

    public static class RegCuenta {
        public String nro;
        public float saldo;

        public RegCuenta(String nro, float saldo) {
            this.nro = nro;
            this.saldo = saldo;
        }
    }

    private static void add(HashMap<String, ArrayList<RegCuenta>> db, String ci, String nro, float saldo) {
        db.putIfAbsent(ci, new ArrayList<>());
        db.get(ci).add(new RegCuenta(nro, saldo));
    }

    // 3 cuentas Mercantil
    public static HashMap<String, ArrayList<RegCuenta>> cargarMercantil() {
        HashMap<String, ArrayList<RegCuenta>> db = new HashMap<>();

        add(db, "11021654", "1515", 5200);
        add(db, "11021654", "2020", 1500);
        add(db, "12345678", "9090", 800);

        return db;
    }

    // 3 cuentas BCP
    public static HashMap<String, ArrayList<RegCuenta>> cargarBCP() {
        HashMap<String, ArrayList<RegCuenta>> db = new HashMap<>();

        add(db, "11021654", "657654", 6000);
        add(db, "87654321", "3333", 250);
        add(db, "12345678", "4444", 1200);

        return db;
    }

    public static String toCadenaRespuesta(HashMap<String, ArrayList<RegCuenta>> db, String ci) {
        ArrayList<RegCuenta> lst = db.get(ci);
        if (lst == null || lst.isEmpty()) return "";

        String s = "";
        for (int i = 0; i < lst.size(); i++) {
            RegCuenta r = lst.get(i);
            s += r.nro + "-" + r.saldo;
            if (i < lst.size() - 1) s += ":";
        }
        return s;
    }

    // Busca una cuenta por nro dentro de todas las CIs (simple)
    public static RegCuenta buscarPorNro(HashMap<String, ArrayList<RegCuenta>> db, String nroCuenta) {
        for (ArrayList<RegCuenta> lst : db.values()) {
            for (RegCuenta r : lst) {
                if (r.nro.equals(nroCuenta)) return r;
            }
        }
        return null;
    }
}
