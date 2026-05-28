using Comercio_2.BancoEconomicoSoap;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Comercio_2
{
    public partial class Form1 : Form
    {
        // Variable para guardar el token temporalmente en memoria
        private string tokenJWT = "";

        public Form1()
        {
            InitializeComponent();
        }

        // SERVICIO SOAP: Consultar Saldo e Historial
        private void btnSaldo_Click(object sender, EventArgs e)
        {
            try
            {
                var clienteSoap = new WebService1SoapClient();

                // Llamamos a la operación y le pasamos lo que escribiste en el TextBox
                double saldo = clienteSoap.consultarSaldo(txtCuentaSoap.Text);

                lblResultadoSoap.Text = "Saldo actual: " + saldo.ToString() + " Bs.";
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error SOAP: " + ex.Message);
            }
        }

        private void btnHistorial_Click(object sender, EventArgs e)
        {
            try
            {
                var clienteSoap = new WebService1SoapClient();

                // Llamamos a la operación de historial
                var movimientos = clienteSoap.historial(txtCuentaSoap.Text);

                // Llenamos el DataGridView directamente con la respuesta
                dgvHistorial.DataSource = movimientos;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error SOAP: " + ex.Message);
            }
        }

        // SERVICIO REST: Login y Transacción
        private async void btnLogin_Click(object sender, EventArgs e)
        {
            try
            {
                using (HttpClient client = new HttpClient())
                {
                    // JSON manual para evitar requerir paquetes extra sin internet
                    string jsonBody = "{\"email\":\"comercio@test.com\",\"password\":\"123456\"}";
                    var content = new StringContent(jsonBody, Encoding.UTF8, "application/json");

                    // Apuntamos a tu XAMPP/Laravel
                    HttpResponseMessage response = await client.PostAsync("http://127.0.0.1:8000/api/login", content);
                    string responseString = await response.Content.ReadAsStringAsync();

                    if (response.IsSuccessStatusCode)
                    {
                        // Extracción rústica del token para entornos sin librerías JSON
                        string[] partes = responseString.Split('"');
                        tokenJWT = partes[3];
                        lblResultadoRest.Text = "Login exitoso. Token obtenido.";
                    }
                    else
                    {
                        lblResultadoRest.Text = "Error en login: " + responseString;
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error REST: " + ex.Message);
            }
        }

        private async void btnPagar_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(tokenJWT))
            {
                MessageBox.Show("Primero debes hacer click en Iniciar Sesión.");
                return;
            }

            try
            {
                using (HttpClient client = new HttpClient())
                {
                    // Agregamos el Token al Header para que Laravel nos deje pasar
                    client.DefaultRequestHeaders.Authorization = new System.Net.Http.Headers.AuthenticationHeaderValue("Bearer", tokenJWT);
                    client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));

                    string fechaActual = DateTime.Now.ToString("yyyy-MM-dd");
                    string monto = txtMonto.Text;

                    // Construimos el cuerpo de la transacción
          
                    string jsonBody = $"{{\"fecha\":\"{fechaActual}\",\"cuentaOrigen\":\"BNB-123\",\"cuentaDestino\":\"ECO-456\",\"monto\":{monto}}}";
                    var content = new StringContent(jsonBody, Encoding.UTF8, "application/json");

                    // Enviamos la petición al Intermediador
                    HttpResponseMessage response = await client.PostAsync("http://127.0.0.1:8000/api/transaccion", content);
                    string responseString = await response.Content.ReadAsStringAsync();

                    if (response.IsSuccessStatusCode)
                    {
                        lblResultadoRest.Text = "¡Transacción exitosa!";
                        MessageBox.Show(responseString);
                    }
                    else
                    {
                        lblResultadoRest.Text = "Fallo en la transacción.";
                        MessageBox.Show(responseString);
                    }
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error REST: " + ex.Message);
            }
        }
        private void Form1_Load(object sender, EventArgs e) { }
        private void label2_Click(object sender, EventArgs e) { }
        private void textBox1_TextChanged(object sender, EventArgs e) { }
    }
}