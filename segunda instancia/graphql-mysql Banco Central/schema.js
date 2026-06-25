const { GraphQLObjectType, GraphQLString, GraphQLSchema, GraphQLFloat, GraphQLNonNull } = require('graphql');

const RootQuery = new GraphQLObjectType({
  name: 'RootQueryType',
  fields: {
    // Recibe una fecha y devuelve la cotización (Float)
    obtenerCotizacion: {
      type: GraphQLFloat,
      args: { 
        fecha: { type: new GraphQLNonNull(GraphQLString) } 
      },
      resolve(parent, args) {
        console.log(`[Banco Central] Consultando cotización para la fecha: ${args.fecha}`);
        
        // Según el enunciado del examen: "devuelve la cotización que para el ejercicio será 7"
        return 7.0;
      }
    }
  }
});

module.exports = new GraphQLSchema({
  query: RootQuery
});