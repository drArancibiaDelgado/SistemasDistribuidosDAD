/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.com.practica2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IJusticia extends Remote {
    RespuestaCuenta ConsultarCuentas(String ci, String nombres, String apellidos) throws RemoteException;
    Boolean Congelar(Cuenta cuenta, Float monto) throws RemoteException;
}
