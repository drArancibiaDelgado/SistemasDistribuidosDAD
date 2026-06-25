const express = require('express');
const { graphqlHTTP } = require('express-graphql');
const schema = require('./schema');
const { sequelize } = require('./database');

const app = express();

// IMPORTANTE: Middleware CORS
// Permitirá que nuestro Orquestador (que estará en otro puerto o dominio) pueda hacer peticiones a este servicio
app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
  if (req.method === 'OPTIONS') {
    return res.sendStatus(200);
  }
  next();
});

// Configuramos la ruta de GraphQL
app.use('/graphql', graphqlHTTP({
  schema: schema,
  graphiql: true, // Habilita la interfaz gráfica web para probar
  customFormatErrorFn: (err) => {
    console.error(err);
    return { message: err.message };
  }
}));

// Iniciamos BD y Servidor
sequelize.authenticate()
  .then(() => {
    console.log('Conexión a la base de datos (db_plataforma) establecida exitosamente.');
    // Sincronizamos el modelo con la BD (creará la tabla si no existe)
    return sequelize.sync();
  })
  .then(() => {
    app.listen(4000, () => {
      console.log('Servidor GraphQL ATC corriendo en http://localhost:4000/graphql');
    });
  })
  .catch(err => {
    console.error('No se pudo conectar a la base de datos:', err);
  });