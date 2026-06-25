using MySql.Data.MySqlClient; 
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Web.Services;

namespace BancoUnionSOAP
{
    [WebService(Namespace = "http://bancounion.bo/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    public class ServicioCuentas : System.Web.Services.WebService
    {
        // Cadena de conexión hacia tu MySQL en XAMPP (Base de datos: db_union)
        private string connectionString = "Server=localhost;Database=db_union;Uid=root;Pwd=;";

        // Clase auxiliar para devolver objetos Cuenta
        public class Cuenta
        {
            public string nro_cuenta { get; set; }
            public string moneda { get; set; }
            public decimal saldo { get; set; }
        }

        [WebMethod(Description = "Obtiene todas las cuentas del banco")]
        public List<Cuenta> obtenerCuentas()
        {
            List<Cuenta> lista = new List<Cuenta>();
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                string query = "SELECT * FROM cuentas";
                MySqlCommand cmd = new MySqlCommand(query, conn);
                conn.Open();
                using (MySqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        lista.Add(new Cuenta
                        {
                            nro_cuenta = reader["nro_cuenta"].ToString(),
                            moneda = reader["moneda"].ToString(),
                            saldo = Convert.ToDecimal(reader["saldo"])
                        });
                    }
                }
            }
            return lista;
        }

        [WebMethod(Description = "Obtiene el saldo de una cuenta específica")]
        public decimal obtenerSaldo(string nro_cuenta)
        {
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                string query = "SELECT saldo FROM cuentas WHERE nro_cuenta = @nro_cuenta";
                MySqlCommand cmd = new MySqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@nro_cuenta", nro_cuenta);
                conn.Open();

                object result = cmd.ExecuteScalar();
                if (result != null)
                {
                    return Convert.ToDecimal(result);
                }
                throw new Exception("Cuenta no encontrada");
            }
        }

        [WebMethod(Description = "Registra una nueva cuenta en el banco")]
        public bool registrarCuenta(string nro_cuenta, string moneda, decimal saldo)
        {
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                string query = "INSERT INTO cuentas (nro_cuenta, moneda, saldo) VALUES (@nro_cuenta, @moneda, @saldo)";
                MySqlCommand cmd = new MySqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@nro_cuenta", nro_cuenta);
                cmd.Parameters.AddWithValue("@moneda", moneda);
                cmd.Parameters.AddWithValue("@saldo", saldo);
                conn.Open();
                int rows = cmd.ExecuteNonQuery();
                return rows > 0;
            }
        }

        [WebMethod(Description = "Actualiza el saldo para transferencias (operacion: 'sumar' o 'restar')")]
        public string actualizarSaldo(string nro_cuenta, string operacion, decimal monto)
        {
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                conn.Open();
                // 1. Verificar saldo actual
                string queryCheck = "SELECT saldo FROM cuentas WHERE nro_cuenta = @nro_cuenta";
                MySqlCommand cmdCheck = new MySqlCommand(queryCheck, conn);
                cmdCheck.Parameters.AddWithValue("@nro_cuenta", nro_cuenta);

                object result = cmdCheck.ExecuteScalar();
                if (result == null) return "Error: Cuenta no encontrada";

                decimal saldoActual = Convert.ToDecimal(result);
                decimal nuevoSaldo = saldoActual;

                // 2. Calcular nuevo saldo
                if (operacion.ToLower() == "sumar")
                {
                    nuevoSaldo = saldoActual + monto;
                }
                else if (operacion.ToLower() == "restar")
                {
                    if (saldoActual >= monto)
                    {
                        nuevoSaldo = saldoActual - monto;
                    }
                    else
                    {
                        return "Error: Fondos insuficientes";
                    }
                }
                else
                {
                    return "Error: Operación no válida (use 'sumar' o 'restar')";
                }

                // 3. Actualizar en BD
                string queryUpdate = "UPDATE cuentas SET saldo = @nuevo_saldo WHERE nro_cuenta = @nro_cuenta";
                MySqlCommand cmdUpdate = new MySqlCommand(queryUpdate, conn);
                cmdUpdate.Parameters.AddWithValue("@nuevo_saldo", nuevoSaldo);
                cmdUpdate.Parameters.AddWithValue("@nro_cuenta", nro_cuenta);
                cmdUpdate.ExecuteNonQuery();

                return "Exito: Saldo actualizado. Nuevo saldo: " + nuevoSaldo;
            }
        }

        [WebMethod(Description = "Elimina una cuenta existente")]
        public bool eliminarCuenta(string nro_cuenta)
        {
            using (MySqlConnection conn = new MySqlConnection(connectionString))
            {
                string query = "DELETE FROM cuentas WHERE nro_cuenta = @nro_cuenta";
                MySqlCommand cmd = new MySqlCommand(query, conn);
                cmd.Parameters.AddWithValue("@nro_cuenta", nro_cuenta);
                conn.Open();
                int rows = cmd.ExecuteNonQuery();
                return rows > 0;
            }
        }
    }
}