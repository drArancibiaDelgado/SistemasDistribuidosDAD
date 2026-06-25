/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orquestadorrmi;

/**
 *
 * @author USUARIO
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class OrquestadorImpl extends UnicastRemoteObject implements IOrquestador {

    public OrquestadorImpl() throws RemoteException {
        super();
    }

    @Override
    public String realizarTransferencia(String token, String cuentaOrigen, String cuentaDestino, double monto) throws RemoteException {
        System.out.println(">> [Orquestador] Petición recibida de transferencia.");
        System.out.println("Token: " + token + " | Origen: " + cuentaOrigen + " | Destino: " + cuentaDestino + " | Monto: " + monto);
        
        // --- AQUÍ IRA TODA LA LÓGICA DE INTEGRACIÓN ---
        // 1. Validar Token en GraphQL (ATC)
        // 2. Buscar en base de datos mapa_clientes a qué banco pertenece cada cuenta
        // 3. Consultar cotización en GraphQL (Banco Central) si es necesario
        // 4. Descontar en origen (REST BNB o SOAP Unión)
        // 5. Abonar en destino (REST BNB o SOAP Unión)
        
        return "Respuesta del Orquestador: Transacción simulada recibida correctamente.";
    }
}