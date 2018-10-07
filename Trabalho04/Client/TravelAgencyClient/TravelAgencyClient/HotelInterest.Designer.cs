namespace TravelAgencyClient
{
    partial class HotelInterest
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.qtyText = new System.Windows.Forms.TextBox();
            this.hotelNameLabel = new System.Windows.Forms.Label();
            this.cityLabel = new System.Windows.Forms.Label();
            this.registerButton = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.hotelFixLabel = new System.Windows.Forms.Label();
            this.cityFixLabel = new System.Windows.Forms.Label();
            this.guestsText = new System.Windows.Forms.TextBox();
            this.priceText = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(129, 157);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(101, 36);
            this.label3.TabIndex = 31;
            this.label3.Text = "Preço:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(34, 207);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(288, 36);
            this.label2.TabIndex = 30;
            this.label2.Text = "Número de pessoas:";
            // 
            // qtyText
            // 
            this.qtyText.Location = new System.Drawing.Point(418, 267);
            this.qtyText.Name = "qtyText";
            this.qtyText.Size = new System.Drawing.Size(100, 35);
            this.qtyText.TabIndex = 29;
            // 
            // hotelNameLabel
            // 
            this.hotelNameLabel.AutoSize = true;
            this.hotelNameLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.hotelNameLabel.Location = new System.Drawing.Point(257, 43);
            this.hotelNameLabel.Name = "hotelNameLabel";
            this.hotelNameLabel.Size = new System.Drawing.Size(230, 36);
            this.hotelNameLabel.TabIndex = 28;
            this.hotelNameLabel.Text = "hotelNameLabel";
            // 
            // cityLabel
            // 
            this.cityLabel.AutoSize = true;
            this.cityLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cityLabel.Location = new System.Drawing.Point(257, 43);
            this.cityLabel.Name = "cityLabel";
            this.cityLabel.Size = new System.Drawing.Size(133, 36);
            this.cityLabel.TabIndex = 27;
            this.cityLabel.Text = "cityLabel";
            // 
            // registerButton
            // 
            this.registerButton.Location = new System.Drawing.Point(106, 350);
            this.registerButton.Name = "registerButton";
            this.registerButton.Size = new System.Drawing.Size(345, 66);
            this.registerButton.TabIndex = 26;
            this.registerButton.Text = "Registrar interesse";
            this.registerButton.UseVisualStyleBackColor = true;
            this.registerButton.Click += new System.EventHandler(this.registerButton_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(100, 264);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(176, 36);
            this.label4.TabIndex = 25;
            this.label4.Text = "Quantidade:";
            // 
            // hotelFixLabel
            // 
            this.hotelFixLabel.AutoSize = true;
            this.hotelFixLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.hotelFixLabel.Location = new System.Drawing.Point(138, 43);
            this.hotelFixLabel.Name = "hotelFixLabel";
            this.hotelFixLabel.Size = new System.Drawing.Size(92, 36);
            this.hotelFixLabel.TabIndex = 24;
            this.hotelFixLabel.Text = "Hotel:";
            // 
            // cityFixLabel
            // 
            this.cityFixLabel.AutoSize = true;
            this.cityFixLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.cityFixLabel.Location = new System.Drawing.Point(115, 43);
            this.cityFixLabel.Name = "cityFixLabel";
            this.cityFixLabel.Size = new System.Drawing.Size(117, 36);
            this.cityFixLabel.TabIndex = 23;
            this.cityFixLabel.Text = "Cidade:";
            // 
            // guestsText
            // 
            this.guestsText.Location = new System.Drawing.Point(418, 210);
            this.guestsText.Name = "guestsText";
            this.guestsText.Size = new System.Drawing.Size(100, 35);
            this.guestsText.TabIndex = 32;
            // 
            // priceText
            // 
            this.priceText.Location = new System.Drawing.Point(418, 157);
            this.priceText.Name = "priceText";
            this.priceText.Size = new System.Drawing.Size(100, 35);
            this.priceText.TabIndex = 33;
            // 
            // HotelInterest
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(14F, 29F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(588, 450);
            this.Controls.Add(this.priceText);
            this.Controls.Add(this.guestsText);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.qtyText);
            this.Controls.Add(this.hotelNameLabel);
            this.Controls.Add(this.cityLabel);
            this.Controls.Add(this.registerButton);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.hotelFixLabel);
            this.Controls.Add(this.cityFixLabel);
            this.Name = "HotelInterest";
            this.Text = "HotelInterest";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox qtyText;
        private System.Windows.Forms.Label hotelNameLabel;
        private System.Windows.Forms.Label cityLabel;
        private System.Windows.Forms.Button registerButton;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label hotelFixLabel;
        private System.Windows.Forms.Label cityFixLabel;
        private System.Windows.Forms.TextBox guestsText;
        private System.Windows.Forms.TextBox priceText;
    }
}