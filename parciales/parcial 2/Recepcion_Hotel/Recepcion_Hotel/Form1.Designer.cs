namespace Recepcion_Hotel
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            btnRegistrar = new Button();
            btnConsultar = new Button();
            lblregistra = new Label();
            dtGHISTORIAL = new DataGridView();
            lbltarifa = new Label();
            lblhistorial = new Label();
            btnhistorialo = new Button();
            lblrh = new Label();
            ((System.ComponentModel.ISupportInitialize)dtGHISTORIAL).BeginInit();
            SuspendLayout();
            // 
            // btnRegistrar
            // 
            btnRegistrar.Location = new Point(147, 50);
            btnRegistrar.Name = "btnRegistrar";
            btnRegistrar.Size = new Size(114, 23);
            btnRegistrar.TabIndex = 0;
            btnRegistrar.Text = "Registrar Reserva";
            btnRegistrar.UseVisualStyleBackColor = true;
            btnRegistrar.Click += btnRegistrar_Click;
            // 
            // btnConsultar
            // 
            btnConsultar.Location = new Point(461, 50);
            btnConsultar.Name = "btnConsultar";
            btnConsultar.Size = new Size(117, 23);
            btnConsultar.TabIndex = 1;
            btnConsultar.Text = "Consultar Tarifa";
            btnConsultar.UseVisualStyleBackColor = true;
            btnConsultar.Click += btnConsultar_Click;
            // 
            // lblregistra
            // 
            lblregistra.AutoSize = true;
            lblregistra.Location = new Point(147, 86);
            lblregistra.Name = "lblregistra";
            lblregistra.Size = new Size(133, 15);
            lblregistra.TabIndex = 2;
            lblregistra.Text = "Aqui saldra si se registra";
            lblregistra.Click += lblregistra_Click;
            // 
            // dtGHISTORIAL
            // 
            dtGHISTORIAL.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dtGHISTORIAL.Location = new Point(461, 197);
            dtGHISTORIAL.Name = "dtGHISTORIAL";
            dtGHISTORIAL.Size = new Size(240, 150);
            dtGHISTORIAL.TabIndex = 3;
            dtGHISTORIAL.CellContentClick += dtGHISTORIAL_CellContentClick;
            // 
            // lbltarifa
            // 
            lbltarifa.AutoSize = true;
            lbltarifa.Location = new Point(461, 86);
            lbltarifa.Name = "lbltarifa";
            lbltarifa.Size = new Size(97, 15);
            lbltarifa.TabIndex = 4;
            lbltarifa.Text = "aqui sale tu tarifa";
            // 
            // lblhistorial
            // 
            lblhistorial.AutoSize = true;
            lblhistorial.Location = new Point(465, 164);
            lblhistorial.Name = "lblhistorial";
            lblhistorial.Size = new Size(128, 15);
            lblhistorial.TabIndex = 5;
            lblhistorial.Text = "Historial de Ocupacion";
            // 
            // btnhistorialo
            // 
            btnhistorialo.Location = new Point(461, 129);
            btnhistorialo.Name = "btnhistorialo";
            btnhistorialo.Size = new Size(158, 23);
            btnhistorialo.TabIndex = 6;
            btnhistorialo.Text = "Consultar Historial ";
            btnhistorialo.UseVisualStyleBackColor = true;
            // 
            // lblrh
            // 
            lblrh.AutoSize = true;
            lblrh.Location = new Point(275, 5);
            lblrh.Name = "lblrh";
            lblrh.Size = new Size(111, 15);
            lblrh.TabIndex = 7;
            lblrh.Text = "Recepcion del hotel";
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(lblrh);
            Controls.Add(btnhistorialo);
            Controls.Add(lblhistorial);
            Controls.Add(lbltarifa);
            Controls.Add(dtGHISTORIAL);
            Controls.Add(lblregistra);
            Controls.Add(btnConsultar);
            Controls.Add(btnRegistrar);
            Name = "Form1";
            Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)dtGHISTORIAL).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Button btnRegistrar;
        private Button btnConsultar;
        private Label lblregistra;
        private DataGridView dtGHISTORIAL;
        private Label lbltarifa;
        private Label lblhistorial;
        private Button btnhistorialo;
        private Label lblrh;
    }
}
