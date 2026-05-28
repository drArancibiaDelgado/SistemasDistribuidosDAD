<?php
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\PagoController;

// Endpoint público para obtener el token
Route::post('/login', [AuthController::class, 'login']);

// Endpoints protegidos (solo clientes autenticados)
Route::group(['middleware' => 'auth:api'], function () {
    Route::post('/transaccion', [PagoController::class, 'transaccion']);
});