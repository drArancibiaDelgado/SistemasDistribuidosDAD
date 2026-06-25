const { Sequelize, DataTypes } = require('sequelize');

// Conectamos a db_plataforma que creamos en el paso anterior de SQL
const sequelize = new Sequelize('db_plataforma', 'root', '', {
  host: 'localhost',
  dialect: 'mysql',
  logging: false
});

// Definimos el modelo para la tabla tokens_atc
const TokenATC = sequelize.define('TokenATC', {
  token: {
    type: DataTypes.STRING(100),
    primaryKey: true,
    allowNull: false
  },
  usuario: {
    type: DataTypes.STRING(50),
    allowNull: false
  },
  activo: {
    type: DataTypes.BOOLEAN, // Sequelize mapea esto al TINYINT(1) de MySQL
    allowNull: false,
    defaultValue: true
  }
}, {
  tableName: 'tokens_atc',
  timestamps: false // No necesitamos createdAt ni updatedAt por ahora
});

module.exports = { sequelize, TokenATC };