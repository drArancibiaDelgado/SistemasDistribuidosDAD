/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.emision_becas_ali;

/**
 *
 * @author USUARIO
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

interface ServidorUniversitarioInterface extends Remote {
    RespuestaBeca solicitarBeca(String ci, String nombres, String apellidos) 
        throws RemoteException;
}