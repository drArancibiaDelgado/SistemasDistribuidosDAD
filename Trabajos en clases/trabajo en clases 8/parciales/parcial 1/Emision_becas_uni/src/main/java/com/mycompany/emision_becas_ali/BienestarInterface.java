/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.emision_becas_ali;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */


public interface BienestarInterface extends Remote {
    ArrayList<Nota> obtenerHistorial(String ci) throws RemoteException;
}
