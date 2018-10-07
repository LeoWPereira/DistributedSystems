namespace TravelAgencyClient
{
    partial class Form1
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
            this.Control = new System.Windows.Forms.TabControl();
            this.ticketsTab = new System.Windows.Forms.TabPage();
            this.label1 = new System.Windows.Forms.Label();
            this.ticketDataGridView = new System.Windows.Forms.DataGridView();
            this.returnTicketDate = new System.Windows.Forms.DateTimePicker();
            this.goingTicketDate = new System.Windows.Forms.DateTimePicker();
            this.cityDestComboBox = new System.Windows.Forms.ComboBox();
            this.stateDestComboBox = new System.Windows.Forms.ComboBox();
            this.citySrcComboBox = new System.Windows.Forms.ComboBox();
            this.stateSrcComboBox = new System.Windows.Forms.ComboBox();
            this.searchTicketButton = new System.Windows.Forms.Button();
            this.interestTicketButton = new System.Windows.Forms.Button();
            this.returnDateLabel = new System.Windows.Forms.Label();
            this.goingDateLabel = new System.Windows.Forms.Label();
            this.destLabel = new System.Windows.Forms.Label();
            this.returnRadioButton = new System.Windows.Forms.RadioButton();
            this.sourceLabel = new System.Windows.Forms.Label();
            this.goingRadioButton = new System.Windows.Forms.RadioButton();
            this.accommodationTab = new System.Windows.Forms.TabPage();
            this.label2 = new System.Windows.Forms.Label();
            this.hotelTextBox = new System.Windows.Forms.TextBox();
            this.hotelDataGridView = new System.Windows.Forms.DataGridView();
            this.cityHotelCombo = new System.Windows.Forms.ComboBox();
            this.stateHotelCombo = new System.Windows.Forms.ComboBox();
            this.searchHotelButton = new System.Windows.Forms.Button();
            this.interestHotelButton = new System.Windows.Forms.Button();
            this.hotelNameLabel = new System.Windows.Forms.Label();
            this.searchByCityRadioButton = new System.Windows.Forms.RadioButton();
            this.localLabel = new System.Windows.Forms.Label();
            this.searchByHotelRadioButton = new System.Windows.Forms.RadioButton();
            this.packageTab = new System.Windows.Forms.TabPage();
            this.label3 = new System.Windows.Forms.Label();
            this.packageDataGridView = new System.Windows.Forms.DataGridView();
            this.dateReturnPackage = new System.Windows.Forms.DateTimePicker();
            this.dateGoingPackage = new System.Windows.Forms.DateTimePicker();
            this.cityDestPackCombo = new System.Windows.Forms.ComboBox();
            this.stateDestPackCombo = new System.Windows.Forms.ComboBox();
            this.citySrcPackCombo = new System.Windows.Forms.ComboBox();
            this.stateSrcPackCombo = new System.Windows.Forms.ComboBox();
            this.searchPackagesButton = new System.Windows.Forms.Button();
            this.interestPackageButton = new System.Windows.Forms.Button();
            this.returnPackageDateLabel = new System.Windows.Forms.Label();
            this.labels = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.returnPackageRadioButton = new System.Windows.Forms.RadioButton();
            this.label8 = new System.Windows.Forms.Label();
            this.goingPackageRadioButton = new System.Windows.Forms.RadioButton();
            this.eventsTab = new System.Windows.Forms.TabPage();
            this.interestLabel = new System.Windows.Forms.Label();
            this.packageInterestRadioButton = new System.Windows.Forms.RadioButton();
            this.interestDataGridView = new System.Windows.Forms.DataGridView();
            this.hotelInterestRadioButton = new System.Windows.Forms.RadioButton();
            this.ticketInterestRadioButton = new System.Windows.Forms.RadioButton();
            this.Control.SuspendLayout();
            this.ticketsTab.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.ticketDataGridView)).BeginInit();
            this.accommodationTab.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.hotelDataGridView)).BeginInit();
            this.packageTab.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.packageDataGridView)).BeginInit();
            this.eventsTab.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.interestDataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // Control
            // 
            this.Control.Controls.Add(this.ticketsTab);
            this.Control.Controls.Add(this.accommodationTab);
            this.Control.Controls.Add(this.packageTab);
            this.Control.Controls.Add(this.eventsTab);
            this.Control.Location = new System.Drawing.Point(28, 27);
            this.Control.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.Control.Name = "Control";
            this.Control.SelectedIndex = 0;
            this.Control.Size = new System.Drawing.Size(2028, 950);
            this.Control.TabIndex = 0;
            // 
            // ticketsTab
            // 
            this.ticketsTab.Controls.Add(this.label1);
            this.ticketsTab.Controls.Add(this.ticketDataGridView);
            this.ticketsTab.Controls.Add(this.returnTicketDate);
            this.ticketsTab.Controls.Add(this.goingTicketDate);
            this.ticketsTab.Controls.Add(this.cityDestComboBox);
            this.ticketsTab.Controls.Add(this.stateDestComboBox);
            this.ticketsTab.Controls.Add(this.citySrcComboBox);
            this.ticketsTab.Controls.Add(this.stateSrcComboBox);
            this.ticketsTab.Controls.Add(this.searchTicketButton);
            this.ticketsTab.Controls.Add(this.interestTicketButton);
            this.ticketsTab.Controls.Add(this.returnDateLabel);
            this.ticketsTab.Controls.Add(this.goingDateLabel);
            this.ticketsTab.Controls.Add(this.destLabel);
            this.ticketsTab.Controls.Add(this.returnRadioButton);
            this.ticketsTab.Controls.Add(this.sourceLabel);
            this.ticketsTab.Controls.Add(this.goingRadioButton);
            this.ticketsTab.Location = new System.Drawing.Point(10, 47);
            this.ticketsTab.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.ticketsTab.Name = "ticketsTab";
            this.ticketsTab.Padding = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.ticketsTab.Size = new System.Drawing.Size(2008, 893);
            this.ticketsTab.TabIndex = 0;
            this.ticketsTab.Text = "Passagens";
            this.ticketsTab.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(1314, 31);
            this.label1.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(195, 40);
            this.label1.TabIndex = 18;
            this.label1.Text = "Passagens";
            // 
            // ticketDataGridView
            // 
            this.ticketDataGridView.AllowUserToAddRows = false;
            this.ticketDataGridView.AllowUserToDeleteRows = false;
            this.ticketDataGridView.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.ticketDataGridView.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.DisplayedCells;
            this.ticketDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.ticketDataGridView.Location = new System.Drawing.Point(837, 83);
            this.ticketDataGridView.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.ticketDataGridView.Name = "ticketDataGridView";
            this.ticketDataGridView.Size = new System.Drawing.Size(1164, 796);
            this.ticketDataGridView.TabIndex = 17;
            this.ticketDataGridView.CellClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.ticketDataGridView_CellClick);
            // 
            // returnTicketDate
            // 
            this.returnTicketDate.Location = new System.Drawing.Point(278, 540);
            this.returnTicketDate.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.returnTicketDate.Name = "returnTicketDate";
            this.returnTicketDate.Size = new System.Drawing.Size(454, 35);
            this.returnTicketDate.TabIndex = 16;
            this.returnTicketDate.Visible = false;
            // 
            // goingTicketDate
            // 
            this.goingTicketDate.Location = new System.Drawing.Point(278, 431);
            this.goingTicketDate.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.goingTicketDate.Name = "goingTicketDate";
            this.goingTicketDate.Size = new System.Drawing.Size(454, 35);
            this.goingTicketDate.TabIndex = 15;
            // 
            // cityDestComboBox
            // 
            this.cityDestComboBox.FormattingEnabled = true;
            this.cityDestComboBox.Location = new System.Drawing.Point(373, 299);
            this.cityDestComboBox.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.cityDestComboBox.Name = "cityDestComboBox";
            this.cityDestComboBox.Size = new System.Drawing.Size(359, 37);
            this.cityDestComboBox.TabIndex = 14;
            // 
            // stateDestComboBox
            // 
            this.stateDestComboBox.FormattingEnabled = true;
            this.stateDestComboBox.Location = new System.Drawing.Point(229, 299);
            this.stateDestComboBox.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.stateDestComboBox.Name = "stateDestComboBox";
            this.stateDestComboBox.Size = new System.Drawing.Size(125, 37);
            this.stateDestComboBox.TabIndex = 13;
            this.stateDestComboBox.SelectedIndexChanged += new System.EventHandler(this.stateDestComboBox_SelectedIndexChanged);
            // 
            // citySrcComboBox
            // 
            this.citySrcComboBox.FormattingEnabled = true;
            this.citySrcComboBox.Location = new System.Drawing.Point(373, 203);
            this.citySrcComboBox.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.citySrcComboBox.Name = "citySrcComboBox";
            this.citySrcComboBox.Size = new System.Drawing.Size(359, 37);
            this.citySrcComboBox.TabIndex = 12;
            // 
            // stateSrcComboBox
            // 
            this.stateSrcComboBox.FormattingEnabled = true;
            this.stateSrcComboBox.Location = new System.Drawing.Point(229, 203);
            this.stateSrcComboBox.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.stateSrcComboBox.Name = "stateSrcComboBox";
            this.stateSrcComboBox.Size = new System.Drawing.Size(125, 37);
            this.stateSrcComboBox.TabIndex = 11;
            this.stateSrcComboBox.SelectedIndexChanged += new System.EventHandler(this.stateSrcComboBox_SelectedIndexChanged);
            // 
            // searchTicketButton
            // 
            this.searchTicketButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchTicketButton.Location = new System.Drawing.Point(147, 776);
            this.searchTicketButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.searchTicketButton.Name = "searchTicketButton";
            this.searchTicketButton.Size = new System.Drawing.Size(427, 78);
            this.searchTicketButton.TabIndex = 10;
            this.searchTicketButton.Text = "Procurar passagens";
            this.searchTicketButton.UseVisualStyleBackColor = true;
            this.searchTicketButton.Click += new System.EventHandler(this.searchTicketButton_Click);
            // 
            // interestTicketButton
            // 
            this.interestTicketButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.interestTicketButton.Location = new System.Drawing.Point(147, 658);
            this.interestTicketButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.interestTicketButton.Name = "interestTicketButton";
            this.interestTicketButton.Size = new System.Drawing.Size(427, 78);
            this.interestTicketButton.TabIndex = 9;
            this.interestTicketButton.Text = "Registrar interesse";
            this.interestTicketButton.UseVisualStyleBackColor = true;
            this.interestTicketButton.Click += new System.EventHandler(this.interestTicketButton_Click);
            // 
            // returnDateLabel
            // 
            this.returnDateLabel.AutoSize = true;
            this.returnDateLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.returnDateLabel.Location = new System.Drawing.Point(65, 540);
            this.returnDateLabel.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.returnDateLabel.Name = "returnDateLabel";
            this.returnDateLabel.Size = new System.Drawing.Size(189, 40);
            this.returnDateLabel.TabIndex = 8;
            this.returnDateLabel.Text = "Data volta:";
            this.returnDateLabel.Visible = false;
            // 
            // goingDateLabel
            // 
            this.goingDateLabel.AutoSize = true;
            this.goingDateLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.goingDateLabel.Location = new System.Drawing.Point(65, 431);
            this.goingDateLabel.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.goingDateLabel.Name = "goingDateLabel";
            this.goingDateLabel.Size = new System.Drawing.Size(161, 40);
            this.goingDateLabel.TabIndex = 7;
            this.goingDateLabel.Text = "Data ida:";
            // 
            // destLabel
            // 
            this.destLabel.AutoSize = true;
            this.destLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.destLabel.Location = new System.Drawing.Point(65, 301);
            this.destLabel.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.destLabel.Name = "destLabel";
            this.destLabel.Size = new System.Drawing.Size(149, 40);
            this.destLabel.TabIndex = 6;
            this.destLabel.Text = "Destino:";
            // 
            // returnRadioButton
            // 
            this.returnRadioButton.AutoSize = true;
            this.returnRadioButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.returnRadioButton.Location = new System.Drawing.Point(397, 83);
            this.returnRadioButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.returnRadioButton.Name = "returnRadioButton";
            this.returnRadioButton.Size = new System.Drawing.Size(209, 44);
            this.returnRadioButton.TabIndex = 5;
            this.returnRadioButton.Text = "Ida / Volta";
            this.returnRadioButton.UseVisualStyleBackColor = true;
            this.returnRadioButton.CheckedChanged += new System.EventHandler(this.returnRadioButton_CheckedChanged);
            // 
            // sourceLabel
            // 
            this.sourceLabel.AutoSize = true;
            this.sourceLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.sourceLabel.Location = new System.Drawing.Point(65, 199);
            this.sourceLabel.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.sourceLabel.Name = "sourceLabel";
            this.sourceLabel.Size = new System.Drawing.Size(145, 40);
            this.sourceLabel.TabIndex = 2;
            this.sourceLabel.Text = "Origem:";
            // 
            // goingRadioButton
            // 
            this.goingRadioButton.AutoSize = true;
            this.goingRadioButton.Checked = true;
            this.goingRadioButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.goingRadioButton.Location = new System.Drawing.Point(147, 83);
            this.goingRadioButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.goingRadioButton.Name = "goingRadioButton";
            this.goingRadioButton.Size = new System.Drawing.Size(97, 44);
            this.goingRadioButton.TabIndex = 0;
            this.goingRadioButton.TabStop = true;
            this.goingRadioButton.Text = "Ida";
            this.goingRadioButton.UseVisualStyleBackColor = true;
            this.goingRadioButton.CheckedChanged += new System.EventHandler(this.goingRadioButton_CheckedChanged);
            // 
            // accommodationTab
            // 
            this.accommodationTab.Controls.Add(this.label2);
            this.accommodationTab.Controls.Add(this.hotelTextBox);
            this.accommodationTab.Controls.Add(this.hotelDataGridView);
            this.accommodationTab.Controls.Add(this.cityHotelCombo);
            this.accommodationTab.Controls.Add(this.stateHotelCombo);
            this.accommodationTab.Controls.Add(this.searchHotelButton);
            this.accommodationTab.Controls.Add(this.interestHotelButton);
            this.accommodationTab.Controls.Add(this.hotelNameLabel);
            this.accommodationTab.Controls.Add(this.searchByCityRadioButton);
            this.accommodationTab.Controls.Add(this.localLabel);
            this.accommodationTab.Controls.Add(this.searchByHotelRadioButton);
            this.accommodationTab.Location = new System.Drawing.Point(10, 47);
            this.accommodationTab.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.accommodationTab.Name = "accommodationTab";
            this.accommodationTab.Padding = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.accommodationTab.Size = new System.Drawing.Size(2008, 893);
            this.accommodationTab.TabIndex = 1;
            this.accommodationTab.Text = "Hotéis";
            this.accommodationTab.UseVisualStyleBackColor = true;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(1348, 36);
            this.label2.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(119, 40);
            this.label2.TabIndex = 34;
            this.label2.Text = "Hotéis";
            // 
            // hotelTextBox
            // 
            this.hotelTextBox.Location = new System.Drawing.Point(348, 203);
            this.hotelTextBox.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.hotelTextBox.Name = "hotelTextBox";
            this.hotelTextBox.Size = new System.Drawing.Size(391, 35);
            this.hotelTextBox.TabIndex = 33;
            // 
            // hotelDataGridView
            // 
            this.hotelDataGridView.AllowUserToAddRows = false;
            this.hotelDataGridView.AllowUserToDeleteRows = false;
            this.hotelDataGridView.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.hotelDataGridView.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.DisplayedCells;
            this.hotelDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.hotelDataGridView.Location = new System.Drawing.Point(810, 83);
            this.hotelDataGridView.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.hotelDataGridView.Name = "hotelDataGridView";
            this.hotelDataGridView.Size = new System.Drawing.Size(1168, 796);
            this.hotelDataGridView.TabIndex = 32;
            // 
            // cityHotelCombo
            // 
            this.cityHotelCombo.FormattingEnabled = true;
            this.cityHotelCombo.Location = new System.Drawing.Point(350, 203);
            this.cityHotelCombo.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.cityHotelCombo.Name = "cityHotelCombo";
            this.cityHotelCombo.Size = new System.Drawing.Size(359, 37);
            this.cityHotelCombo.TabIndex = 27;
            this.cityHotelCombo.Visible = false;
            // 
            // stateHotelCombo
            // 
            this.stateHotelCombo.FormattingEnabled = true;
            this.stateHotelCombo.Location = new System.Drawing.Point(205, 203);
            this.stateHotelCombo.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.stateHotelCombo.Name = "stateHotelCombo";
            this.stateHotelCombo.Size = new System.Drawing.Size(125, 37);
            this.stateHotelCombo.TabIndex = 26;
            this.stateHotelCombo.Visible = false;
            this.stateHotelCombo.SelectedIndexChanged += new System.EventHandler(this.stateHotelCombo_SelectedIndexChanged);
            // 
            // searchHotelButton
            // 
            this.searchHotelButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchHotelButton.Location = new System.Drawing.Point(124, 776);
            this.searchHotelButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.searchHotelButton.Name = "searchHotelButton";
            this.searchHotelButton.Size = new System.Drawing.Size(427, 78);
            this.searchHotelButton.TabIndex = 25;
            this.searchHotelButton.Text = "Procurar hotéis";
            this.searchHotelButton.UseVisualStyleBackColor = true;
            this.searchHotelButton.Click += new System.EventHandler(this.searchHotelButton_Click);
            // 
            // interestHotelButton
            // 
            this.interestHotelButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.interestHotelButton.Location = new System.Drawing.Point(124, 658);
            this.interestHotelButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.interestHotelButton.Name = "interestHotelButton";
            this.interestHotelButton.Size = new System.Drawing.Size(427, 78);
            this.interestHotelButton.TabIndex = 24;
            this.interestHotelButton.Text = "Registrar interesse";
            this.interestHotelButton.UseVisualStyleBackColor = true;
            this.interestHotelButton.Click += new System.EventHandler(this.interestHotelButton_Click);
            // 
            // hotelNameLabel
            // 
            this.hotelNameLabel.AutoSize = true;
            this.hotelNameLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.hotelNameLabel.Location = new System.Drawing.Point(42, 205);
            this.hotelNameLabel.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.hotelNameLabel.Name = "hotelNameLabel";
            this.hotelNameLabel.Size = new System.Drawing.Size(261, 40);
            this.hotelNameLabel.TabIndex = 21;
            this.hotelNameLabel.Text = "Nome do hotel:";
            // 
            // searchByCityRadioButton
            // 
            this.searchByCityRadioButton.AutoSize = true;
            this.searchByCityRadioButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchByCityRadioButton.Location = new System.Drawing.Point(394, 83);
            this.searchByCityRadioButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.searchByCityRadioButton.Name = "searchByCityRadioButton";
            this.searchByCityRadioButton.Size = new System.Drawing.Size(326, 44);
            this.searchByCityRadioButton.TabIndex = 20;
            this.searchByCityRadioButton.Text = "Busca por cidade";
            this.searchByCityRadioButton.UseVisualStyleBackColor = true;
            this.searchByCityRadioButton.CheckedChanged += new System.EventHandler(this.searchByCityRadioButton_CheckedChanged);
            // 
            // localLabel
            // 
            this.localLabel.AutoSize = true;
            this.localLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.localLabel.Location = new System.Drawing.Point(42, 199);
            this.localLabel.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.localLabel.Name = "localLabel";
            this.localLabel.Size = new System.Drawing.Size(113, 40);
            this.localLabel.TabIndex = 19;
            this.localLabel.Text = "Local:";
            this.localLabel.Visible = false;
            // 
            // searchByHotelRadioButton
            // 
            this.searchByHotelRadioButton.AutoSize = true;
            this.searchByHotelRadioButton.Checked = true;
            this.searchByHotelRadioButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchByHotelRadioButton.Location = new System.Drawing.Point(51, 83);
            this.searchByHotelRadioButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.searchByHotelRadioButton.Name = "searchByHotelRadioButton";
            this.searchByHotelRadioButton.Size = new System.Drawing.Size(304, 44);
            this.searchByHotelRadioButton.TabIndex = 18;
            this.searchByHotelRadioButton.TabStop = true;
            this.searchByHotelRadioButton.Text = "Busca por Hotel";
            this.searchByHotelRadioButton.UseVisualStyleBackColor = true;
            this.searchByHotelRadioButton.CheckedChanged += new System.EventHandler(this.searchByHotelRadioButton_CheckedChanged);
            // 
            // packageTab
            // 
            this.packageTab.Controls.Add(this.label3);
            this.packageTab.Controls.Add(this.packageDataGridView);
            this.packageTab.Controls.Add(this.dateReturnPackage);
            this.packageTab.Controls.Add(this.dateGoingPackage);
            this.packageTab.Controls.Add(this.cityDestPackCombo);
            this.packageTab.Controls.Add(this.stateDestPackCombo);
            this.packageTab.Controls.Add(this.citySrcPackCombo);
            this.packageTab.Controls.Add(this.stateSrcPackCombo);
            this.packageTab.Controls.Add(this.searchPackagesButton);
            this.packageTab.Controls.Add(this.interestPackageButton);
            this.packageTab.Controls.Add(this.returnPackageDateLabel);
            this.packageTab.Controls.Add(this.labels);
            this.packageTab.Controls.Add(this.label7);
            this.packageTab.Controls.Add(this.returnPackageRadioButton);
            this.packageTab.Controls.Add(this.label8);
            this.packageTab.Controls.Add(this.goingPackageRadioButton);
            this.packageTab.Location = new System.Drawing.Point(10, 47);
            this.packageTab.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.packageTab.Name = "packageTab";
            this.packageTab.Padding = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.packageTab.Size = new System.Drawing.Size(2008, 893);
            this.packageTab.TabIndex = 2;
            this.packageTab.Text = "Pacotes";
            this.packageTab.UseVisualStyleBackColor = true;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(1325, 36);
            this.label3.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(147, 40);
            this.label3.TabIndex = 48;
            this.label3.Text = "Pacotes";
            // 
            // packageDataGridView
            // 
            this.packageDataGridView.AllowUserToAddRows = false;
            this.packageDataGridView.AllowUserToDeleteRows = false;
            this.packageDataGridView.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.packageDataGridView.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.DisplayedCells;
            this.packageDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.packageDataGridView.Location = new System.Drawing.Point(812, 83);
            this.packageDataGridView.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.packageDataGridView.Name = "packageDataGridView";
            this.packageDataGridView.Size = new System.Drawing.Size(1182, 796);
            this.packageDataGridView.TabIndex = 47;
            // 
            // dateReturnPackage
            // 
            this.dateReturnPackage.Location = new System.Drawing.Point(257, 540);
            this.dateReturnPackage.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.dateReturnPackage.Name = "dateReturnPackage";
            this.dateReturnPackage.Size = new System.Drawing.Size(454, 35);
            this.dateReturnPackage.TabIndex = 46;
            this.dateReturnPackage.Visible = false;
            // 
            // dateGoingPackage
            // 
            this.dateGoingPackage.Location = new System.Drawing.Point(257, 431);
            this.dateGoingPackage.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.dateGoingPackage.Name = "dateGoingPackage";
            this.dateGoingPackage.Size = new System.Drawing.Size(454, 35);
            this.dateGoingPackage.TabIndex = 45;
            // 
            // cityDestPackCombo
            // 
            this.cityDestPackCombo.FormattingEnabled = true;
            this.cityDestPackCombo.Location = new System.Drawing.Point(352, 299);
            this.cityDestPackCombo.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.cityDestPackCombo.Name = "cityDestPackCombo";
            this.cityDestPackCombo.Size = new System.Drawing.Size(359, 37);
            this.cityDestPackCombo.TabIndex = 44;
            // 
            // stateDestPackCombo
            // 
            this.stateDestPackCombo.FormattingEnabled = true;
            this.stateDestPackCombo.Location = new System.Drawing.Point(208, 299);
            this.stateDestPackCombo.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.stateDestPackCombo.Name = "stateDestPackCombo";
            this.stateDestPackCombo.Size = new System.Drawing.Size(125, 37);
            this.stateDestPackCombo.TabIndex = 43;
            this.stateDestPackCombo.SelectedIndexChanged += new System.EventHandler(this.stateDestPackCombo_SelectedIndexChanged);
            // 
            // citySrcPackCombo
            // 
            this.citySrcPackCombo.FormattingEnabled = true;
            this.citySrcPackCombo.Location = new System.Drawing.Point(352, 203);
            this.citySrcPackCombo.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.citySrcPackCombo.Name = "citySrcPackCombo";
            this.citySrcPackCombo.Size = new System.Drawing.Size(359, 37);
            this.citySrcPackCombo.TabIndex = 42;
            // 
            // stateSrcPackCombo
            // 
            this.stateSrcPackCombo.FormattingEnabled = true;
            this.stateSrcPackCombo.Location = new System.Drawing.Point(208, 203);
            this.stateSrcPackCombo.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.stateSrcPackCombo.Name = "stateSrcPackCombo";
            this.stateSrcPackCombo.Size = new System.Drawing.Size(125, 37);
            this.stateSrcPackCombo.TabIndex = 41;
            this.stateSrcPackCombo.SelectedIndexChanged += new System.EventHandler(this.stateSrcPackCombo_SelectedIndexChanged);
            // 
            // searchPackagesButton
            // 
            this.searchPackagesButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.searchPackagesButton.Location = new System.Drawing.Point(126, 776);
            this.searchPackagesButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.searchPackagesButton.Name = "searchPackagesButton";
            this.searchPackagesButton.Size = new System.Drawing.Size(427, 78);
            this.searchPackagesButton.TabIndex = 40;
            this.searchPackagesButton.Text = "Procurar pacotes";
            this.searchPackagesButton.UseVisualStyleBackColor = true;
            this.searchPackagesButton.Click += new System.EventHandler(this.searchPackagesButton_Click);
            // 
            // interestPackageButton
            // 
            this.interestPackageButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.interestPackageButton.Location = new System.Drawing.Point(126, 658);
            this.interestPackageButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.interestPackageButton.Name = "interestPackageButton";
            this.interestPackageButton.Size = new System.Drawing.Size(427, 78);
            this.interestPackageButton.TabIndex = 39;
            this.interestPackageButton.Text = "Registrar interesse";
            this.interestPackageButton.UseVisualStyleBackColor = true;
            this.interestPackageButton.Click += new System.EventHandler(this.interestPackageButton_Click);
            // 
            // returnPackageDateLabel
            // 
            this.returnPackageDateLabel.AutoSize = true;
            this.returnPackageDateLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.returnPackageDateLabel.Location = new System.Drawing.Point(44, 540);
            this.returnPackageDateLabel.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.returnPackageDateLabel.Name = "returnPackageDateLabel";
            this.returnPackageDateLabel.Size = new System.Drawing.Size(189, 40);
            this.returnPackageDateLabel.TabIndex = 38;
            this.returnPackageDateLabel.Text = "Data volta:";
            this.returnPackageDateLabel.Visible = false;
            // 
            // labels
            // 
            this.labels.AutoSize = true;
            this.labels.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labels.Location = new System.Drawing.Point(44, 431);
            this.labels.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.labels.Name = "labels";
            this.labels.Size = new System.Drawing.Size(161, 40);
            this.labels.TabIndex = 37;
            this.labels.Text = "Data ida:";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label7.Location = new System.Drawing.Point(44, 301);
            this.label7.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(149, 40);
            this.label7.TabIndex = 36;
            this.label7.Text = "Destino:";
            // 
            // returnPackageRadioButton
            // 
            this.returnPackageRadioButton.AutoSize = true;
            this.returnPackageRadioButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.returnPackageRadioButton.Location = new System.Drawing.Point(376, 83);
            this.returnPackageRadioButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.returnPackageRadioButton.Name = "returnPackageRadioButton";
            this.returnPackageRadioButton.Size = new System.Drawing.Size(209, 44);
            this.returnPackageRadioButton.TabIndex = 35;
            this.returnPackageRadioButton.Text = "Ida / Volta";
            this.returnPackageRadioButton.UseVisualStyleBackColor = true;
            this.returnPackageRadioButton.CheckedChanged += new System.EventHandler(this.returnPackageRadioButton_CheckedChanged);
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label8.Location = new System.Drawing.Point(44, 199);
            this.label8.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(145, 40);
            this.label8.TabIndex = 34;
            this.label8.Text = "Origem:";
            // 
            // goingPackageRadioButton
            // 
            this.goingPackageRadioButton.AutoSize = true;
            this.goingPackageRadioButton.Checked = true;
            this.goingPackageRadioButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.goingPackageRadioButton.Location = new System.Drawing.Point(126, 83);
            this.goingPackageRadioButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.goingPackageRadioButton.Name = "goingPackageRadioButton";
            this.goingPackageRadioButton.Size = new System.Drawing.Size(97, 44);
            this.goingPackageRadioButton.TabIndex = 33;
            this.goingPackageRadioButton.TabStop = true;
            this.goingPackageRadioButton.Text = "Ida";
            this.goingPackageRadioButton.UseVisualStyleBackColor = true;
            this.goingPackageRadioButton.CheckedChanged += new System.EventHandler(this.goingPackageRadioButton_CheckedChanged);
            // 
            // eventsTab
            // 
            this.eventsTab.Controls.Add(this.interestLabel);
            this.eventsTab.Controls.Add(this.packageInterestRadioButton);
            this.eventsTab.Controls.Add(this.interestDataGridView);
            this.eventsTab.Controls.Add(this.hotelInterestRadioButton);
            this.eventsTab.Controls.Add(this.ticketInterestRadioButton);
            this.eventsTab.Location = new System.Drawing.Point(10, 47);
            this.eventsTab.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.eventsTab.Name = "eventsTab";
            this.eventsTab.Padding = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.eventsTab.Size = new System.Drawing.Size(2008, 893);
            this.eventsTab.TabIndex = 3;
            this.eventsTab.Text = "Eventos";
            this.eventsTab.UseVisualStyleBackColor = true;
            // 
            // interestLabel
            // 
            this.interestLabel.AutoSize = true;
            this.interestLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.interestLabel.Location = new System.Drawing.Point(902, 28);
            this.interestLabel.Margin = new System.Windows.Forms.Padding(7, 0, 7, 0);
            this.interestLabel.Name = "interestLabel";
            this.interestLabel.Size = new System.Drawing.Size(213, 48);
            this.interestLabel.TabIndex = 64;
            this.interestLabel.Text = "Interesses";
            // 
            // packageInterestRadioButton
            // 
            this.packageInterestRadioButton.AutoSize = true;
            this.packageInterestRadioButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.packageInterestRadioButton.Location = new System.Drawing.Point(1508, 91);
            this.packageInterestRadioButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.packageInterestRadioButton.Name = "packageInterestRadioButton";
            this.packageInterestRadioButton.Size = new System.Drawing.Size(178, 44);
            this.packageInterestRadioButton.TabIndex = 63;
            this.packageInterestRadioButton.TabStop = true;
            this.packageInterestRadioButton.Text = "Pacotes";
            this.packageInterestRadioButton.UseVisualStyleBackColor = true;
            this.packageInterestRadioButton.CheckedChanged += new System.EventHandler(this.packageInterestRadioButton_CheckedChanged);
            // 
            // interestDataGridView
            // 
            this.interestDataGridView.AllowUserToAddRows = false;
            this.interestDataGridView.AllowUserToDeleteRows = false;
            this.interestDataGridView.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.interestDataGridView.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.DisplayedCells;
            this.interestDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.interestDataGridView.Location = new System.Drawing.Point(68, 149);
            this.interestDataGridView.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.interestDataGridView.Name = "interestDataGridView";
            this.interestDataGridView.Size = new System.Drawing.Size(1881, 729);
            this.interestDataGridView.TabIndex = 62;
            // 
            // hotelInterestRadioButton
            // 
            this.hotelInterestRadioButton.AutoSize = true;
            this.hotelInterestRadioButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.hotelInterestRadioButton.Location = new System.Drawing.Point(928, 83);
            this.hotelInterestRadioButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.hotelInterestRadioButton.Name = "hotelInterestRadioButton";
            this.hotelInterestRadioButton.Size = new System.Drawing.Size(150, 44);
            this.hotelInterestRadioButton.TabIndex = 50;
            this.hotelInterestRadioButton.TabStop = true;
            this.hotelInterestRadioButton.Text = "Hotéis";
            this.hotelInterestRadioButton.UseVisualStyleBackColor = true;
            this.hotelInterestRadioButton.CheckedChanged += new System.EventHandler(this.hotelInterestRadioButton_CheckedChanged);
            // 
            // ticketInterestRadioButton
            // 
            this.ticketInterestRadioButton.AutoSize = true;
            this.ticketInterestRadioButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ticketInterestRadioButton.Location = new System.Drawing.Point(266, 83);
            this.ticketInterestRadioButton.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.ticketInterestRadioButton.Name = "ticketInterestRadioButton";
            this.ticketInterestRadioButton.Size = new System.Drawing.Size(226, 44);
            this.ticketInterestRadioButton.TabIndex = 48;
            this.ticketInterestRadioButton.TabStop = true;
            this.ticketInterestRadioButton.Text = "Passagens";
            this.ticketInterestRadioButton.UseVisualStyleBackColor = true;
            this.ticketInterestRadioButton.CheckedChanged += new System.EventHandler(this.ticketInterestRadioButton_CheckedChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(14F, 29F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(2063, 1004);
            this.Controls.Add(this.Control);
            this.Margin = new System.Windows.Forms.Padding(7, 7, 7, 7);
            this.Name = "Form1";
            this.Text = "Cliente";
            this.Control.ResumeLayout(false);
            this.ticketsTab.ResumeLayout(false);
            this.ticketsTab.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.ticketDataGridView)).EndInit();
            this.accommodationTab.ResumeLayout(false);
            this.accommodationTab.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.hotelDataGridView)).EndInit();
            this.packageTab.ResumeLayout(false);
            this.packageTab.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.packageDataGridView)).EndInit();
            this.eventsTab.ResumeLayout(false);
            this.eventsTab.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.interestDataGridView)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl Control;
        private System.Windows.Forms.TabPage ticketsTab;
        private System.Windows.Forms.DataGridView ticketDataGridView;
        private System.Windows.Forms.DateTimePicker returnTicketDate;
        private System.Windows.Forms.DateTimePicker goingTicketDate;
        private System.Windows.Forms.ComboBox cityDestComboBox;
        private System.Windows.Forms.ComboBox stateDestComboBox;
        private System.Windows.Forms.ComboBox citySrcComboBox;
        private System.Windows.Forms.ComboBox stateSrcComboBox;
        private System.Windows.Forms.Button searchTicketButton;
        private System.Windows.Forms.Button interestTicketButton;
        private System.Windows.Forms.Label returnDateLabel;
        private System.Windows.Forms.Label goingDateLabel;
        private System.Windows.Forms.Label destLabel;
        private System.Windows.Forms.RadioButton returnRadioButton;
        private System.Windows.Forms.Label sourceLabel;
        private System.Windows.Forms.RadioButton goingRadioButton;
        private System.Windows.Forms.TabPage accommodationTab;
        private System.Windows.Forms.TabPage packageTab;
        private System.Windows.Forms.TabPage eventsTab;
        private System.Windows.Forms.ComboBox cityHotelCombo;
        private System.Windows.Forms.ComboBox stateHotelCombo;
        private System.Windows.Forms.Button searchHotelButton;
        private System.Windows.Forms.Button interestHotelButton;
        private System.Windows.Forms.Label hotelNameLabel;
        private System.Windows.Forms.RadioButton searchByCityRadioButton;
        private System.Windows.Forms.Label localLabel;
        private System.Windows.Forms.RadioButton searchByHotelRadioButton;
        private System.Windows.Forms.DataGridView packageDataGridView;
        private System.Windows.Forms.DateTimePicker dateReturnPackage;
        private System.Windows.Forms.DateTimePicker dateGoingPackage;
        private System.Windows.Forms.ComboBox cityDestPackCombo;
        private System.Windows.Forms.ComboBox stateDestPackCombo;
        private System.Windows.Forms.ComboBox citySrcPackCombo;
        private System.Windows.Forms.ComboBox stateSrcPackCombo;
        private System.Windows.Forms.Button searchPackagesButton;
        private System.Windows.Forms.Button interestPackageButton;
        private System.Windows.Forms.Label returnPackageDateLabel;
        private System.Windows.Forms.Label labels;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.RadioButton returnPackageRadioButton;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.RadioButton goingPackageRadioButton;
        private System.Windows.Forms.Label interestLabel;
        private System.Windows.Forms.RadioButton packageInterestRadioButton;
        private System.Windows.Forms.DataGridView interestDataGridView;
        private System.Windows.Forms.RadioButton hotelInterestRadioButton;
        private System.Windows.Forms.RadioButton ticketInterestRadioButton;
        private System.Windows.Forms.DataGridView hotelDataGridView;
        private System.Windows.Forms.TextBox hotelTextBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
    }
}

