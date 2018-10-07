using System;
using System.Windows.Forms;

namespace TravelAgencyClient
{
    public partial class Form1 : Form
    {
        private localhost.TravelAgencyServiceImplService webService;

        public Form1()
        {
            InitializeComponent();

            // Firstly, configure the web service object
            webService = new localhost.TravelAgencyServiceImplService();

            configStatesAndCities();
        }

        private void configStatesAndCities()
        {
            CitiesBrazil citiesBrazil = new CitiesBrazil();
            String[] states = citiesBrazil.getStates();

            for(int i = 0; i < states.Length; i++)
            {
                stateSrcComboBox.Items.Add(states[i]);
                stateDestComboBox.Items.Add(states[i]);
                stateHotelCombo.Items.Add(states[i]);
                stateSrcPackCombo.Items.Add(states[i]);
                stateDestPackCombo.Items.Add(states[i]);
            }

            return;
        }

        private void returnRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            returnTicketDate.Visible = true;
            returnDateLabel.Visible = true;

            return;
        }

        private void goingRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            returnTicketDate.Visible = false;
            returnDateLabel.Visible = false;

            return;
        }

        private void searchByCityRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            hotelNameLabel.Visible = false;
            hotelTextBox.Visible = false;
            localLabel.Visible = true;
            stateHotelCombo.Visible = true;
            cityHotelCombo.Visible = true;

            return;
        }

        private void searchByHotelRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            hotelNameLabel.Visible = true;
            hotelTextBox.Visible = true;
            localLabel.Visible = false;
            stateHotelCombo.Visible = false;
            cityHotelCombo.Visible = false;

            return;
        }

        private void returnPackageRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            returnPackageDateLabel.Visible = true;
            dateReturnPackage.Visible = true;

            return;
        }

        private void goingPackageRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            returnPackageDateLabel.Visible = false;
            dateReturnPackage.Visible = false;

            return;
        }

        private void stateSrcComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            CitiesBrazil citiesBrazil = new CitiesBrazil();
            String state = stateSrcComboBox.SelectedItem.ToString();

            String[] cities = citiesBrazil.getCities(state);

            // clear the comboBox
            citySrcComboBox.Items.Clear();

            for (int i = 0; i < cities.Length; i++)
            {
                citySrcComboBox.Items.Add(cities[i]);
            }

            return;
        }

        private void stateDestComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            CitiesBrazil citiesBrazil = new CitiesBrazil();
            String state = stateDestComboBox.SelectedItem.ToString();

            String[] cities = citiesBrazil.getCities(state);

            // clear the comboBox
            cityDestComboBox.Items.Clear();

            for (int i = 0; i < cities.Length; i++)
            {
                cityDestComboBox.Items.Add(cities[i]);
            }

            return;
        }

        private void stateHotelCombo_SelectedIndexChanged(object sender, EventArgs e)
        {
            CitiesBrazil citiesBrazil = new CitiesBrazil();
            String state = stateHotelCombo.SelectedItem.ToString();

            String[] cities = citiesBrazil.getCities(state);

            // clear the comboBox
            cityHotelCombo.Items.Clear();

            for (int i = 0; i < cities.Length; i++)
            {
                cityHotelCombo.Items.Add(cities[i]);
            }

            return;
        }

        private void stateSrcPackCombo_SelectedIndexChanged(object sender, EventArgs e)
        {
            CitiesBrazil citiesBrazil = new CitiesBrazil();
            String state = stateSrcPackCombo.SelectedItem.ToString();

            String[] cities = citiesBrazil.getCities(state);

            // clear the comboBox
            citySrcPackCombo.Items.Clear();

            for (int i = 0; i < cities.Length; i++)
            {
                citySrcPackCombo.Items.Add(cities[i]);
            }

            return;
        }

        private void stateDestPackCombo_SelectedIndexChanged(object sender, EventArgs e)
        {
            CitiesBrazil citiesBrazil = new CitiesBrazil();
            String state = stateDestPackCombo.SelectedItem.ToString();

            String[] cities = citiesBrazil.getCities(state);

            // clear the comboBox
            cityDestPackCombo.Items.Clear();

            for (int i = 0; i < cities.Length; i++)
            {
                cityDestPackCombo.Items.Add(cities[i]);
            }

            return;
        }

        private bool ticketCheckEmptyFields()
        {
            bool returnValue = true;

            if(stateSrcComboBox.Text.Equals("  ") ||
                stateDestComboBox.Text.Equals("  ") ||
                citySrcComboBox.Text.Equals("  ") ||
                cityDestComboBox.Text.Equals("  "))
            {
                returnValue = false;
            }

            return returnValue;

        }

        private bool hotelCheckEmptyFields()
        {
            bool returnValue = true;

            if(searchByHotelRadioButton.Checked)
            {
                if(hotelTextBox.Text.Equals(""))
                {
                    returnValue = false;
                }
            }
            else
            {
                if (stateHotelCombo.Text.Equals("  ") ||
                    cityHotelCombo.Text.Equals("  "))
                {
                    returnValue = false;
                }
            }

            return returnValue;

        }

        private bool packageCheckEmptyFields()
        {
            bool returnValue = true;

            if (stateSrcPackCombo.Text.Equals("  ") ||
                stateDestPackCombo.Text.Equals("  ") ||
                citySrcPackCombo.Text.Equals("  ") ||
                cityDestPackCombo.Text.Equals("  "))
            {
                returnValue = false;
            }

            return returnValue;

        }

        private void searchTicketButton_Click(object sender, EventArgs e)
        {
            localhost.flightTicket[] goingList;
            localhost.flightTicket[] returnList = null;

            if (ticketCheckEmptyFields())
            {
                // Send request to the webservice
                goingList = webService.searchPassages(citySrcComboBox.Text.ToString(),
                                                 cityDestComboBox.Text.ToString(),
                                                 goingTicketDate.Value.Day,
                                                 goingTicketDate.Value.Month,
                                                 goingTicketDate.Value.Year);

                if (returnRadioButton.Checked)
                {
                    // Send request to the webservice
                    returnList = webService.searchPassages(cityDestComboBox.SelectedItem.ToString(),
                                                           citySrcComboBox.SelectedItem.ToString(),
                                                           returnTicketDate.Value.Day,
                                                           returnTicketDate.Value.Month,
                                                           returnTicketDate.Value.Year);
                }

                configTicketDataGrid(goingList, returnList);
            }

            return;
        }

        private void configTicketDataGrid(localhost.flightTicket[] goingTicket,
                                          localhost.flightTicket[] returnTicket)
        {
            string[] row;

            ticketDataGridView.ColumnCount = 4;
            ticketDataGridView.Columns[0].Name = "Origem";
            ticketDataGridView.Columns[1].Name = "Destino";
            ticketDataGridView.Columns[2].Name = "Data";
            ticketDataGridView.Columns[3].Name = "Preço";

            DataGridViewButtonColumn btn = new DataGridViewButtonColumn();
            ticketDataGridView.Columns.Add(btn);
            btn.HeaderText = "Comprar";
            btn.Text = "Comprar";
            btn.Name = "btn";
            btn.UseColumnTextForButtonValue = true;

            for (int i = 0; i < goingTicket.Length; i++)
            {
                row = new string[] { goingTicket[i].source, goingTicket[i].dest, goingTicket[i].dateDay + "-" + goingTicket[i].dateMonth + "-" + 
                                     goingTicket[i].dateYear, goingTicket[i].price.ToString()};
                ticketDataGridView.Rows.Add(row);
            }

            return;
        }

        private void ticketDataGridView_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == 4)
            {
                String citySource = ticketDataGridView.Rows[e.RowIndex].Cells[0].Value.ToString();
                String cityDest   = ticketDataGridView.Rows[e.RowIndex].Cells[1].Value.ToString();
                int    day        = goingTicketDate.Value.Day;
                int    month      = goingTicketDate.Value.Month;
                int    year       = goingTicketDate.Value.Year;
                float  price      = (float) Convert.ToDouble(ticketDataGridView.Rows[e.RowIndex].Cells[3].Value);

                ticketDetails ticketDet = new ticketDetails(citySource,
                                                            cityDest,
                                                            day,
                                                            month,
                                                            year,
                                                            price);

                ticketDet.Show();
            }
        }

        private void searchHotelButton_Click(object sender, EventArgs e)
        {
            localhost.accommodation[] list;

            if (hotelCheckEmptyFields())
            {
                if (searchByHotelRadioButton.Checked)
                {
                    list = webService.searchHotelByName(hotelTextBox.Text.ToString());
                }
                else
                {
                    list = webService.searchHotelByCity(cityHotelCombo.SelectedItem.ToString());
                }
            }
            else
            {
                var result = MessageBox.Show("Existem campos não preenchidos!",
                                             "Aviso",
                                             MessageBoxButtons.OK,
                                             MessageBoxIcon.Warning);
            }

            return;
        }

        private void searchPackagesButton_Click(object sender, EventArgs e)
        {
            localhost.packages[] list;
            localhost.flightTicket goingTicket;
            localhost.flightTicket returnTicket = null;
            localhost.accommodation accommodation;

            if (ticketCheckEmptyFields())
            {
                /* goingTicket = new localhost.flightTicket(citySrcComboBox.Text.ToString(),
                                                          cityDestComboBox.Text.ToString(),
                                                          goingTicketDate.Value.Day,
                                                          goingTicketDate.Value.Month,
                                                          goingTicketDate.Value.Year,
                                                          0,
                                                          0);*/


                if (returnPackageRadioButton.Checked)
                {
                    
                }
            }

            return;
        }

        private void interestTicketButton_Click(object sender, EventArgs e)
        {

        }

        private void interestOnHotelCompleted(object arg, localhost.registerHotelInterestByCityCompletedEventArgs e)
        {
            var result = MessageBox.Show(e.Result,
                                         "Notificação",
                                         MessageBoxButtons.OK,
                                         MessageBoxIcon.Information);
        }

        private void interestHotelButton_Click(object sender, EventArgs e)
        {
            webService.registerHotelInterestByCityCompleted += new localhost.registerHotelInterestByCityCompletedEventHandler(interestOnHotelCompleted);

            if (hotelCheckEmptyFields())
            {
                if (searchByHotelRadioButton.Checked)
                {
                    //list = webService.searchHotelByName(hotelTextBox.Text.ToString());
                }
                else
                {
                    webService.registerHotelInterestByCityAsync(cityHotelCombo.Text, 
                                                                1,
                                                                2,
                                                                (float)299.99);
                }
            }
            else
            {
                var result = MessageBox.Show("Existem campos não preenchidos!",
                                             "Aviso",
                                             MessageBoxButtons.OK,
                                             MessageBoxIcon.Warning);
            }
        }

        private void interestPackageButton_Click(object sender, EventArgs e)
        {

        }

        private void ticketInterestRadioButton_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void hotelInterestRadioButton_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void packageInterestRadioButton_CheckedChanged(object sender, EventArgs e)
        {

        }

    }
}
