using System;
using System.Configuration;
using System.Web.Services;
using MySql.Data.MySqlClient;

namespace CotizacionSOAP
{
    /// <summary>
    /// Servicio SOAP para gestión de cotizaciones
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/CotizacionSOAP/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    public class ServicioCotizacion : System.Web.Services.WebService
    {
        private string connectionString =
            ConfigurationManager.ConnectionStrings["MySQLConnection"].ConnectionString;

        /// <summary>
        /// Obtiene la cotización de una fecha específica.
        /// Si la cotización es mayor a la oficial, devuelve la oficial.
        /// </summary>
        [WebMethod(Description = "Obtiene la cotización según la fecha")]
        public string obtenerCotizacion(string fecha)
        {
            try
            {
                DateTime fechaConsulta;
                if (!DateTime.TryParse(fecha, out fechaConsulta))
                {
                    return "ERROR: Formato de fecha inválido. Use YYYY-MM-DD";
                }

                using (MySqlConnection conn = new MySqlConnection(connectionString))
                {
                    conn.Open();
                    string query = @"SELECT cotizacion, cotizacion_oficial 
                                     FROM cotizaciones 
                                     WHERE fecha = @fecha";

                    using (MySqlCommand cmd = new MySqlCommand(query, conn))
                    {
                        cmd.Parameters.AddWithValue("@fecha", fechaConsulta.ToString("yyyy-MM-dd"));

                        using (MySqlDataReader reader = cmd.ExecuteReader())
                        {
                            if (reader.Read())
                            {
                                decimal cotizacion = reader.GetDecimal("cotizacion");
                                decimal cotizacionOficial = reader.GetDecimal("cotizacion_oficial");

                                // Lógica: si la cotización supera la oficial, se aplica la oficial
                                decimal cotizacionFinal = (cotizacion > cotizacionOficial)
                                    ? cotizacionOficial
                                    : cotizacion;

                                return $"Fecha: {fechaConsulta:yyyy-MM-dd} | " +
                                       $"Cotización: {cotizacion} | " +
                                       $"Oficial: {cotizacionOficial} | " +
                                       $"Aplicable: {cotizacionFinal}";
                            }
                            else
                            {
                                return $"No existe cotización registrada para la fecha {fecha}";
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                return "ERROR: " + ex.Message;
            }
        }

        /// <summary>
        /// Registra una nueva cotización en la base de datos.
        /// </summary>
        [WebMethod(Description = "Registra una nueva cotización")]
        public string registrarCotizacion(string fecha, decimal monto)
        {
            try
            {
                DateTime fechaRegistro;
                if (!DateTime.TryParse(fecha, out fechaRegistro))
                {
                    return "ERROR: Formato de fecha inválido. Use YYYY-MM-DD";
                }

                if (monto <= 0)
                {
                    return "ERROR: El monto debe ser mayor a 0";
                }

                using (MySqlConnection conn = new MySqlConnection(connectionString))
                {
                    conn.Open();

                    // Verificar si ya existe la fecha
                    string checkQuery = "SELECT COUNT(*) FROM cotizaciones WHERE fecha = @fecha";
                    using (MySqlCommand checkCmd = new MySqlCommand(checkQuery, conn))
                    {
                        checkCmd.Parameters.AddWithValue("@fecha", fechaRegistro.ToString("yyyy-MM-dd"));
                        int count = Convert.ToInt32(checkCmd.ExecuteScalar());

                        if (count > 0)
                        {
                            // Si existe, actualizar
                            string updateQuery = @"UPDATE cotizaciones 
                                                   SET cotizacion = @monto 
                                                   WHERE fecha = @fecha";
                            using (MySqlCommand updCmd = new MySqlCommand(updateQuery, conn))
                            {
                                updCmd.Parameters.AddWithValue("@fecha", fechaRegistro.ToString("yyyy-MM-dd"));
                                updCmd.Parameters.AddWithValue("@monto", monto);
                                updCmd.ExecuteNonQuery();
                                return $"Cotización ACTUALIZADA para {fecha} con monto {monto}";
                            }
                        }
                        else
                        {
                            // Insertar nueva
                            string insertQuery = @"INSERT INTO cotizaciones (fecha, cotizacion, cotizacion_oficial) 
                                                   VALUES (@fecha, @monto, 6.97)";
                            using (MySqlCommand insCmd = new MySqlCommand(insertQuery, conn))
                            {
                                insCmd.Parameters.AddWithValue("@fecha", fechaRegistro.ToString("yyyy-MM-dd"));
                                insCmd.Parameters.AddWithValue("@monto", monto);
                                insCmd.ExecuteNonQuery();
                                return $"Cotización REGISTRADA exitosamente: Fecha={fecha}, Monto={monto}";
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                return "ERROR: " + ex.Message;
            }
        }
    }
}
