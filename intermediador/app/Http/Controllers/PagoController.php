<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class PagoController extends Controller
{
    public function transaccion(Request $request)
    {
        // 1. Validar los datos recibidos del comercio
        $request->validate([
            'fecha' => 'required',
            'cuentaOrigen' => 'required',
            'cuentaDestino' => 'required',
            'monto' => 'required|numeric'
        ]);

        $monto = $request->monto;

        try {
            // 2. Invocar servicio REST del BNB (Puerto 4000) para DESCONTAR saldo
            // Enviamos el monto en negativo para simular el descuento
            $respuestaOrigen = Http::put("http://localhost:4000/cuenta/{$request->cuentaOrigen}", [
                'monto' => -$monto
            ]);

            // 3. Invocar servicio GraphQL del Económico (Puerto 4000) para SUMAR saldo
            $queryGraphQL = '
                mutation Actualizar($cuenta: String!, $monto: Float!) {
                    actualizarSaldo(cuenta: $cuenta, monto: $monto) {
                        saldo
                    }
                }
            ';
            
            $respuestaDestino = Http::post("http://localhost:4000/", [
                'query' => $queryGraphQL,
                'variables' => [
                    'cuenta' => $request->cuentaDestino,
                    'monto' => (float)$monto
                ]
            ]);

            // 4. Registrar el resultado y devolver respuesta al comercio
            return response()->json([
                'estado' => 'Éxito',
                'mensaje' => 'Transacción completada correctamente.',
                'detalles' => [
                    'origen' => $respuestaOrigen->json(),
                    'destino' => $respuestaDestino->json()
                ]
            ]);

        } catch (\Exception $e) {
            return response()->json([
                'estado' => 'Error',
                'mensaje' => 'Hubo un problema de conexión con los bancos: ' . $e->getMessage()
            ], 500);
        }
    }
}