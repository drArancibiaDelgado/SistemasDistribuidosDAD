/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.orquestadorrmi;

/**
 *
 * @author USUARIO
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOrquestador extends Remote {
    // El método principal de la transacción 
    String realizarTransferencia(String token, String cuentaOrigen, String cuentaDestino, double monto) throws RemoteException;
}