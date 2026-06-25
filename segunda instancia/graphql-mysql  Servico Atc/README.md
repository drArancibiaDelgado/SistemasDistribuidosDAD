## GraphQL de multas

Este proyecto quedó adaptado para la central de registro de multas.

### Base de datos

1. Abre XAMPP y levanta MySQL.
2. La base de datos se crea y administra desde phpMyAdmin de XAMPP.
3. Importa el archivo `database.sql` en phpMyAdmin o ejecútalo en MySQL.
4. Verifica que exista la base de datos `sistema_transito_db`.

### Levantar el servidor

```bash
npm install
npm run serve
```

El endpoint queda en `http://localhost:4000/graphql`.

### Consultas de ejemplo

```graphql
query {
  multas {
    codigo_multa
    ci_conductor
    fecha
    tipo_infraccion
    lugar
    monto
    estado
  }
}
```

```graphql
query {
  multa(codigoMulta: "M-0001") {
    codigo_multa
    ci_conductor
    estado
  }
}
```

```graphql
query {
  multasPorCi(ciConductor: "1234567") {
    codigo_multa
    tipo_infraccion
    estado
  }
}
```

### Mutaciones de ejemplo

```graphql
mutation {
  crearMulta(
    codigoMulta: "M-0001"
    ciConductor: "1234567"
    tipoInfraccion: "Exceso de velocidad"
    lugar: "Av. Buenos Aires"
    monto: 350
  ) {
    codigo_multa
    estado
  }
}
```

```graphql
mutation {
  actualizarEstadoMulta(codigoMulta: "M-0001", estado: "pagada") {
    codigo_multa
    estado
  }
}
```