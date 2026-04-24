using ClienteCotizacion.ReferenciaLaboratorio;
using System;
using System.Web.Services;
using System.Windows.Forms;

namespace ClienteCotizacion
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        // BOTÓN CONSULTAR
        private void btnConsultar_Click(object sender, EventArgs e)
        {
            try
            {
                // Usamos el nombre que salió en tu lista: ServicioCotizacion
                ReferenciaLaboratorio.ServicioCotizacion cliente = new ReferenciaLaboratorio.ServicioCotizacion();

                string resultado = cliente.obtenerCotizacion(txtFecha.Text);
                lblResultado.Text = resultado;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al consultar: " + ex.Message);
            }
        }

        // BOTÓN REGISTRAR
        private void btnRegistrar_Click(object sender, EventArgs e)
        {
            try
            {
                // Aquí también usamos ServicioCotizacion
                ReferenciaLaboratorio.ServicioCotizacion cliente = new ReferenciaLaboratorio.ServicioCotizacion();

                decimal monto;
                if (!decimal.TryParse(txtMonto.Text, out monto))
                {
                    MessageBox.Show("Por favor, ingrese un monto válido.");
                    return;
                }

                string respuesta = cliente.registrarCotizacion(txtNuevaFecha.Text, monto);
                MessageBox.Show(respuesta, "Sistema de Cotizaciones");
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al registrar: " + ex.Message);
            }
        }
    }
}