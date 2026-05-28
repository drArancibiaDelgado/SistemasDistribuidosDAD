const { ApolloServer, gql } = require('apollo-server');

// Definición de estructura [cite: 59, 60]
const typeDefs = gql`
  type Cuenta {
    cuenta: String!
    ci: String!
    nombres: String!
    apellidos: String!
    saldo: Float!
  }
  type Query {
    obtenerCuenta(cuenta: String!): Cuenta
  }
  type Mutation {
    actualizarSaldo(cuenta: String!, monto: Float!): Cuenta
  }
`;

// Simulación de base de datos
let cuentas = [{ cuenta: "ECO-456", ci: "222", nombres: "Maria", apellidos: "Gomez", saldo: 1500.00 }];

const resolvers = {
  Query: {
    obtenerCuenta: (_, args) => cuentas.find(c => c.cuenta === args.cuenta),
  },
  Mutation: {
    actualizarSaldo: (_, args) => {
      let cuenta = cuentas.find(c => c.cuenta === args.cuenta);
      if (cuenta) cuenta.saldo += args.monto;
      return cuenta;
    }
  }
};

const server = new ApolloServer({ typeDefs, resolvers });
server.listen({ port: 4000 }).then(({ url }) => {
  console.log(`Servicio GraphQL Económico listo en ${url}`);
});