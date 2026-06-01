const { GraphQLObjectType, GraphQLString, GraphQLSchema, GraphQLList, GraphQLNonNull, GraphQLInt, GraphQLFloat, GraphQLBoolean } = require('graphql');
const { Habitacion } = require('./database');

const HabitacionType = new GraphQLObjectType({
  name: 'Habitacion',
  fields: {
    codHabitacion: { type: new GraphQLNonNull(GraphQLInt) },
    tipo: { type: GraphQLString },
    capacidad: { type: GraphQLInt },
    tarifa: { type: GraphQLFloat },
    disponible: { type: GraphQLBoolean }
  }
});

const RootQuery = new GraphQLObjectType({
  name: 'RootQueryType',
  fields: {
    habitaciones: {
      type: new GraphQLList(HabitacionType),
      resolve() {
        return Habitacion.findAll();
      }
    },
    habitacion: {
      type: HabitacionType,
      args: { codHabitacion: { type: new GraphQLNonNull(GraphQLInt) } },
      resolve(parent, args) {
        return Habitacion.findByPk(args.codHabitacion);
      }
    }
  }
});

const Mutation = new GraphQLObjectType({
  name: 'Mutation',
  fields: {
    actualizarDisponibilidadHabitacion: {
      type: HabitacionType,
      args: {
        codHabitacion: { type: new GraphQLNonNull(GraphQLInt) },
        disponible: { type: new GraphQLNonNull(GraphQLBoolean) }
      },
      async resolve(parent, args) {
        const habitacion = await Habitacion.findByPk(args.codHabitacion);
        if (!habitacion) {
          throw new Error('Habitacion no encontrada');
        }

        return habitacion.update({
          disponible: args.disponible
        });
      }
    }
  }
});

module.exports = new GraphQLSchema({
  query: RootQuery,
  mutation: Mutation
});
