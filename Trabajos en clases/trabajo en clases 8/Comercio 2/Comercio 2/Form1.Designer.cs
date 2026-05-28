namespace Comercio_2
{
    partial class Form1
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnSaldo = new System.Windows.Forms.Button();
            this.btnHistorial = new System.Windows.Forms.Button();
            this.lblResultadoSoap = new System.Windows.Forms.Label();
            this.lblResultadoRest = new System.Windows.Forms.Label();
            this.txtCuentaSoap = new System.Windows.Forms.TextBox();
            this.dgvHistorial = new System.Windows.Forms.DataGridView();
            this.btnLogin = new System.Windows.Forms.Button();
            this.txtMonto = new System.Windows.Forms.TextBox();
            this.btnPagar = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dgvHistorial)).BeginInit();
            this.SuspendLayout();
            // 
            // btnSaldo
            // 
            this.btnSaldo.Location = new System.Drawing.Point(70, 89);
            this.btnSaldo.Name = "btnSaldo";
            this.btnSaldo.Size = new System.Drawing.Size(75, 23);
            this.btnSaldo.TabIndex = 0;
            this.btnSaldo.Text = "Saldo";
            this.btnSaldo.UseVisualStyleBackColor = true;
            this.btnSaldo.Click += new System.EventHandler(this.btnSaldo_Click);
            // 
            // btnHistorial
            // 
            this.btnHistorial.Location = new System.Drawing.Point(70, 118);
            this.btnHistorial.Name = "btnHistorial";
            this.btnHistorial.Size = new System.Drawing.Size(75, 23);
            this.btnHistorial.TabIndex = 1;
            this.btnHistorial.Text = "Historial";
            this.btnHistorial.UseVisualStyleBackColor = true;
            this.btnHistorial.Click += new System.EventHandler(this.btnHistorial_Click);
            // 
            // lblResultadoSoap
            // 
            this.lblResultadoSoap.AutoSize = true;
            this.lblResultadoSoap.Location = new System.Drawing.Point(67, 150);
            this.lblResultadoSoap.Name = "lblResultadoSoap";
            this.lblResultadoSoap.Size = new System.Drawing.Size(35, 13);
            this.lblResultadoSoap.TabIndex = 2;
            this.lblResultadoSoap.Text = "label1";
            // 
            // lblResultadoRest
            // 
            this.lblResultadoRest.AutoSize = true;
            this.lblResultadoRest.Location = new System.Drawing.Point(547, 253);
            this.lblResultadoRest.Name = "lblResultadoRest";
            this.lblResultadoRest.Size = new System.Drawing.Size(35, 13);
            this.lblResultadoRest.TabIndex = 3;
            this.lblResultadoRest.Text = "label2";
            this.lblResultadoRest.Click += new System.EventHandler(this.label2_Click);
            // 
            // txtCuentaSoap
            // 
            this.txtCuentaSoap.Location = new System.Drawing.Point(70, 58);
            this.txtCuentaSoap.Name = "txtCuentaSoap";
            this.txtCuentaSoap.Size = new System.Drawing.Size(100, 20);
            this.txtCuentaSoap.TabIndex = 4;
            this.txtCuentaSoap.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // dgvHistorial
            // 
            this.dgvHistorial.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dgvHistorial.Location = new System.Drawing.Point(70, 187);
            this.dgvHistorial.Name = "dgvHistorial";
            this.dgvHistorial.Size = new System.Drawing.Size(240, 150);
            this.dgvHistorial.TabIndex = 5;
            // 
            // btnLogin
            // 
            this.btnLogin.Location = new System.Drawing.Point(550, 89);
            this.btnLogin.Name = "btnLogin";
            this.btnLogin.Size = new System.Drawing.Size(75, 23);
            this.btnLogin.TabIndex = 6;
            this.btnLogin.Text = "login";
            this.btnLogin.UseVisualStyleBackColor = true;
            this.btnLogin.Click += new System.EventHandler(this.btnLogin_Click);
            // 
            // txtMonto
            // 
            this.txtMonto.Location = new System.Drawing.Point(550, 143);
            this.txtMonto.Name = "txtMonto";
            this.txtMonto.Size = new System.Drawing.Size(100, 20);
            this.txtMonto.TabIndex = 7;
            // 
            // btnPagar
            // 
            this.btnPagar.Location = new System.Drawing.Point(550, 201);
            this.btnPagar.Name = "btnPagar";
            this.btnPagar.Size = new System.Drawing.Size(75, 23);
            this.btnPagar.TabIndex = 8;
            this.btnPagar.Text = "Pagar";
            this.btnPagar.UseVisualStyleBackColor = true;
            this.btnPagar.Click += new System.EventHandler(this.btnPagar_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.btnPagar);
            this.Controls.Add(this.txtMonto);
            this.Controls.Add(this.btnLogin);
            this.Controls.Add(this.dgvHistorial);
            this.Controls.Add(this.txtCuentaSoap);
            this.Controls.Add(this.lblResultadoRest);
            this.Controls.Add(this.lblResultadoSoap);
            this.Controls.Add(this.btnHistorial);
            this.Controls.Add(this.btnSaldo);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dgvHistorial)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnSaldo;
        private System.Windows.Forms.Button btnHistorial;
        private System.Windows.Forms.Label lblResultadoSoap;
        private System.Windows.Forms.Label lblResultadoRest;
        private System.Windows.Forms.TextBox txtCuentaSoap;
        private System.Windows.Forms.DataGridView dgvHistorial;
        private System.Windows.Forms.Button btnLogin;
        private System.Windows.Forms.TextBox txtMonto;
        private System.Windows.Forms.Button btnPagar;
    }
}

