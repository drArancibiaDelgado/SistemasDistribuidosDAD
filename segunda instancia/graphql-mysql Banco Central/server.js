const express = require('express');
const { graphqlHTTP } = require('express-graphql');
const schema = require('./schema');

const app = express();

// Middleware CORS para permitir que el Orquestador lo consuma sin errores
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
  if (req.method === 'OPTIONS') {
    return res.sendStatus(200);
  }
  next();
});

// Configuración de GraphQL
app.use('/graphql', graphqlHTTP({
  schema: schema,
  graphiql: true // Esto habilita la interfaz visual para probar las consultas
}));

// Iniciamos el Servidor
app.listen(4001, () => {
  console.log('Servidor GraphQL Banco Central corriendo en http://localhost:4001/graphql');
});