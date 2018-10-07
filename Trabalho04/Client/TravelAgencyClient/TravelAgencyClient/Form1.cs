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

            if(stateSrcComboBox.Text.Equals("") ||
                stateDestComboBox.Text.Equals("") ||
                citySrcComboBox.Text.Equals("") ||
                cityDestComboBox.Text.Equals(""))
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
                if (stateHotelCombo.Text.Equals("") ||
                    cityHotelCombo.Text.Equals(""))
                {
                    returnValue = false;
                }
            }

            return returnValue;

        }

        private bool packageCheckEmptyFields()
        {
            bool returnValue = true;

            if (stateSrcPackCombo.Text.Equals("") ||
                stateDestPackCombo.Text.Equals("") ||
                citySrcPackCombo.Text.Equals("") ||
                cityDestPackCombo.Text.Equals(""))
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
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
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
                int month = goingTicket[i].dateMonth + 1;
                row = new string[] { goingTicket[i].source, goingTicket[i].dest, goingTicket[i].dateDay + "-" + month + "-" + 
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

                configHotelDataGrid(list);
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }

            return;
        }

        private void configHotelDataGrid(localhost.accommodation[] accommodation)
        {
            string[] row;

            hotelDataGridView.ColumnCount = 4;
            hotelDataGridView.Columns[0].Name = "Cidade";
            hotelDataGridView.Columns[1].Name = "Nome do Hotel";
            hotelDataGridView.Columns[2].Name = "Capacidade";
            hotelDataGridView.Columns[3].Name = "Preço";

            DataGridViewButtonColumn btn = new DataGridViewButtonColumn();
            hotelDataGridView.Columns.Add(btn);
            btn.HeaderText = "Reservar";
            btn.Text = "Reservar";
            btn.Name = "btn";
            btn.UseColumnTextForButtonValue = true;

            for (int i = 0; i < accommodation.Length; i++)
            {
                row = new string[] { accommodation[i].cityName, accommodation[i].accommodationName,
                                     accommodation[i].maxGuestsPerRoom.ToString(), accommodation[i].price.ToString()};
                hotelDataGridView.Rows.Add(row);
            }

            return;
        }

        private void hotelDataGridView_CellClick_1(object sender, DataGridViewCellEventArgs e)
        {
            if (e.ColumnIndex == 4)
            {
                String cityName = hotelDataGridView.Rows[e.RowIndex].Cells[0].Value.ToString();
                String hotelName = hotelDataGridView.Rows[e.RowIndex].Cells[1].Value.ToString();
                int numberOfGuests = Convert.ToInt32(hotelDataGridView.Rows[e.RowIndex].Cells[2].Value);
                float price = (float)Convert.ToDouble(hotelDataGridView.Rows[e.RowIndex].Cells[3].Value);

                HotelDetails hotelDetails = new HotelDetails(cityName,
                                                            hotelName,
                                                            numberOfGuests,
                                                            price);

                hotelDetails.Show();
            }
        }

        private void searchPackagesButton_Click(object sender, EventArgs e)
        {
            localhost.packages[] list;
            bool isReturn = false;

            if (packageCheckEmptyFields())
            {
                if (returnPackageRadioButton.Checked)
                {
                    isReturn = true;
                }

                list = webService.searchPackages(citySrcPackCombo.Text.ToString(),
                                                 cityDestPackCombo.Text.ToString(),
                                                 dateGoingPackage.Value.Day,
                                                 dateGoingPackage.Value.Month,
                                                 dateGoingPackage.Value.Year,
                                                 isReturn,
                                                 dateReturnPackage.Value.Day,
                                                 dateReturnPackage.Value.Month,
                                                 dateReturnPackage.Value.Year);


                configPackageDataGrid(list);
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }

            return;
        }

        private void configPackageDataGrid(localhost.packages[] packages)
        {
            string[] row;
            float totalPrice;
            float returnTicketPrice;
            String tipo;

            packageDataGridView.ColumnCount = 9;
            packageDataGridView.Columns[0].Name = "Tipo";
            packageDataGridView.Columns[1].Name = "Origem";
            packageDataGridView.Columns[2].Name = "Destino";
            packageDataGridView.Columns[3].Name = "Hotel";
            packageDataGridView.Columns[4].Name = "Capacidade";
            packageDataGridView.Columns[5].Name = "Preço Total";
            packageDataGridView.Columns[6].Name = "Preço Ida";
            packageDataGridView.Columns[7].Name = "Preço Volta";
            packageDataGridView.Columns[8].Name = "Preço Hotel";

            // Hide the prices columns (only used for storing info)
            packageDataGridView.Columns[6].Visible = false;
            packageDataGridView.Columns[7].Visible = false;
            packageDataGridView.Columns[8].Visible = false;

            DataGridViewButtonColumn btn = new DataGridViewButtonColumn();
            packageDataGridView.Columns.Add(btn);
            btn.HeaderText = "Comprar";
            btn.Text = "Comprar";
            btn.Name = "btn";
            btn.UseColumnTextForButtonValue = true;

            if (packages != null)
            {
                for (int i = 0; i < packages.Length; i++)
                {
                    if (packages[i].flightTicketReturn != null)
                    {
                        totalPrice = packages[i].flightTicketGoing.price + packages[i].flightTicketReturn.price +
                                     packages[i].accommodation.price;
                        returnTicketPrice = packages[i].flightTicketReturn.price;
                        tipo = "Ida/Volta";
                    }
                    else
                    {
                        totalPrice = packages[i].flightTicketGoing.price + packages[i].accommodation.price;
                        tipo = "Ida";
                        returnTicketPrice = 0;
                    }

                    row = new string[] { tipo, packages[i].flightTicketGoing.source,
                                         packages[i].flightTicketGoing.dest, packages[i].accommodation.accommodationName,
                                         packages[i].accommodation.maxGuestsPerRoom.ToString(), totalPrice.ToString(),
                                         packages[i].flightTicketGoing.price.ToString(), returnTicketPrice.ToString(),
                                         packages[i].accommodation.price.ToString()};

                    packageDataGridView.Rows.Add(row);
                }
            }

            return;
        }

        private void packageDataGridView_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if(e.ColumnIndex == 9)
            {
                String goingCityName = packageDataGridView.Rows[e.RowIndex].Cells[1].Value.ToString();
                String returnCityName = packageDataGridView.Rows[e.RowIndex].Cells[2].Value.ToString();
                String hotelName = packageDataGridView.Rows[e.RowIndex].Cells[3].Value.ToString();
                bool isReturn = false;
                int numberOfGuests = Convert.ToInt32(packageDataGridView.Rows[e.RowIndex].Cells[4].Value);
                float price = (float)Convert.ToDouble(packageDataGridView.Rows[e.RowIndex].Cells[5].Value);
                float goingTicketPrice = (float)Convert.ToDouble(packageDataGridView.Rows[e.RowIndex].Cells[6].Value);
                float returnTicketPrice = (float)Convert.ToDouble(packageDataGridView.Rows[e.RowIndex].Cells[7].Value);
                float hotelPrice = (float)Convert.ToDouble(packageDataGridView.Rows[e.RowIndex].Cells[8].Value);

                if (packageDataGridView.Rows[e.RowIndex].Cells[0].Value.ToString().Equals("Ida/Volta"))
                {
                    isReturn = true;
                }

                PackageDetails hotelDetails = new PackageDetails(goingCityName,
                                                                 returnCityName,
                                                                 hotelName,
                                                                 dateGoingPackage.Value.Day,
                                                                 dateGoingPackage.Value.Month,
                                                                 dateGoingPackage.Value.Year,
                                                                 isReturn,
                                                                 dateReturnPackage.Value.Day,
                                                                 dateReturnPackage.Value.Month,
                                                                 dateReturnPackage.Value.Year,
                                                                 numberOfGuests,
                                                                 goingTicketPrice,
                                                                 returnTicketPrice,
                                                                 hotelPrice,
                                                                 price);

                hotelDetails.Show();
            }
        }

        private void interestTicketButton_Click(object sender, EventArgs e)
        {
            if (ticketCheckEmptyFields())
            {
                bool isReturn = false;

                if(returnRadioButton.Checked)
                {
                    isReturn = true;
                }
                // Send request to the webservice
                TicketInterest ticketInterest = new TicketInterest(citySrcComboBox.Text.ToString(),
                                                                   cityDestComboBox.Text.ToString(),
                                                                   goingTicketDate.Value.Day,
                                                                   goingTicketDate.Value.Month,
                                                                   goingTicketDate.Value.Year,
                                                                   isReturn,
                                                                   returnTicketDate.Value.Day,
                                                                   returnTicketDate.Value.Month,
                                                                   returnTicketDate.Value.Year);

                ticketInterest.Show();
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }
        }

        private void interestHotelButton_Click(object sender, EventArgs e)
        {

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
