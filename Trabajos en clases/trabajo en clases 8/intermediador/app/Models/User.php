<?php

namespace App\Models;

use Illuminate\Foundation\Auth\User as Authenticatable;
use PHPOpenSourceSaver\JWTAuth\Contracts\JWTSubject;

class User extends Authenticatable implements JWTSubject
{
    // Apuntamos a la tabla que creamos en SQL
    protected $table = 'usuarios';
    public $timestamps = false; // Desactivamos created_at y updated_at

    protected $fillable = ['email', 'password'];

    // Métodos requeridos por JWT
    public function getJWTIdentifier()
    {
        return $this->getKey();
    }

    public function getJWTCustomClaims()
    {
        return [];
    }
}
