const { GraphQLObjectType, GraphQLString, GraphQLSchema, GraphQLBoolean, GraphQLNonNull } = require('graphql');
const { TokenATC } = require('./database');
const crypto = require('crypto'); // Librería nativa de Node para generar tokens aleatorios

// Definimos el tipo de dato Token en GraphQL
const TokenType = new GraphQLObjectType({
  name: 'Token',
  fields: {
    token: { type: new GraphQLNonNull(GraphQLString) },
    usuario: { type: new GraphQLNonNull(GraphQLString) },
    activo: { type: new GraphQLNonNull(GraphQLBoolean) }
  }
});

// Consultas (Queries)
const RootQuery = new GraphQLObjectType({
  name: 'RootQueryType',
  fields: {
    // Retorna true si el token existe y está activo, false de lo contrario
    validarToken: {
      type: GraphQLBoolean,
      args: { token: { type: new GraphQLNonNull(GraphQLString) } },
      async resolve(parent, args) {
        const tokenRecord = await TokenATC.findOne({ where: { token: args.token } });
        if (tokenRecord && tokenRecord.activo === true) {
          return true;
        }
        return false;
      }
    }
  }
});

// Mutaciones (Mutations)
const Mutation = new GraphQLObjectType({
  name: 'Mutation',
  fields: {
    // Genera un token nuevo para un usuario
    generarToken: {
      type: TokenType,
      args: {
        usuario: { type: new GraphQLNonNull(GraphQLString) }
      },
      async resolve(parent, args) {
        // Genera una cadena hexadecimal aleatoria de 32 caracteres
        const nuevoTokenStr = crypto.randomBytes(16).toString('hex');
        
        return TokenATC.create({
          token: nuevoTokenStr,
          usuario: args.usuario,
          activo: true
        });
      }
    }
  }
});

module.exports = new GraphQLSchema({
  query: RootQuery,
  mutation: Mutation
});