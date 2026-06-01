const { Sequelize } = require('sequelize');

const sequelize = new Sequelize('bdhotel', 'root', '', {
  host: 'localhost',
  dialect: 'mysql'
});

const Habitacion = sequelize.define('Habitacion', {
  codHabitacion: {
    type: Sequelize.INTEGER,
    allowNull: false,
    primaryKey: true
  },
  tipo: {
    type: Sequelize.STRING(50),
    allowNull: false
  },
  capacidad: {
    type: Sequelize.INTEGER,
    allowNull: false
  },
  tarifa: {
    type: Sequelize.DECIMAL(10, 2),
    allowNull: false
  },
  disponible: {
    type: Sequelize.BOOLEAN,
    allowNull: false,
    defaultValue: true
  }
}, {
  tableName: 'habitacion',
  timestamps: false
});

module.exports = { sequelize, Habitacion };
