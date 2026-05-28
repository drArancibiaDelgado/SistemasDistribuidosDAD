<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;

class JwtMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param  Closure(Request): (Response)  $next
     */
    public function handle(Request $request, Closure $next): Response
    {
        try{

        $autorizacion = $request->header('Authorization');
        $jwt = substr($autorizacion, 7);
        $key = env('JWT_ALGORITHM');

        $datos = JWT::decode($jwt, new Key($key, $algoritmo));
        $request->merge(['datos' => $datos]);
        } catch (\Exception $e) {
            return response()->json(['error' => 'Token inválido'], 401);

    }
}
