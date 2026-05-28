using System;
using System.Collections.Generic;
using System.Web.Services;

namespace SoapEconomico
{
    [WebService(Namespace = "http://bancoeconomico.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    public class WebService1 : System.Web.Services.WebService
    {
        // Estructura del movimiento
        public class Movimiento
        {
            public string Fecha { get; set; }
            public double Monto { get; set; }
        }

        [WebMethod]
        public double consultarSaldo(string cuenta)
        {
            // Aquí conectarías a MySQL usando MySql.Data. Simulación para prueba:
            if (cuenta == "ECO-456") return 1500.00;
            return 0.0;
        }

        [WebMethod]
        public List<Movimiento> historial(string cuenta)
        {
            List<Movimiento> lista = new List<Movimiento>();
            if (cuenta == "ECO-456")
            {
                lista.Add(new Movimiento { Fecha = "2026-05-20 10:00", Monto = 1500.00 });
            }
            return lista;
        }
    }
}