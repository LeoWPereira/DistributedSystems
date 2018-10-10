/**
 ******************************************************************************
 * @file    Form1.cs
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    30 de set de 2018
 * @brief
 ******************************************************************************
 */

using System;
using System.Collections.Generic;
using System.Windows.Forms;
using TravelAgencyClient.Classes;

namespace TravelAgencyClient
{
    /**
     * @name    Form1
     * @brief   Class responsible for showing the flight tickets, hotels
     *          and packages available (GUI). As well as searching for each
     *          of these and registering interests.
     */
    public partial class Form1 : Form
    {
        /**
         * @name    webService
         * @brief   WebService object used for calling the methods
         *          of the web service.
         */
        private localhost.TravelAgencyServiceImplService webService;

        /**
         * @name    registeredTicketInterests
         * @brief   List of flight ticket interests that had been registered.
         */
        private List<FlightTicketInterest>  registeredTicketInterests   = new List<FlightTicketInterest>();

        /**
         * @name    registeredHotelInterests
         * @brief   List of hotel interests that had been registered.
         */
        private List<AccommodationInterest> registeredHotelInterests    = new List<AccommodationInterest>();

        /**
         * @name    registeredPackageInterests
         * @brief   List of package interests that had been registered.
         */
        private List<PackageInt>            registeredPackageInterests  = new List<PackageInt>();

        /**
         * @name    Form1
         * @brief   Default Class Constructor
         *          Initializes the components, web service and states and cities combobox
         */
        public Form1()
        {
            InitializeComponent();

            // Firstly, configure the web service object
            webService = new localhost.TravelAgencyServiceImplService();

            // Fill the state combobox
            configStatesAndCities();
        }

        /**
         * @name    configStatesAndCities
         * @brief   Fill the state combo boxes
         */
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

        /**
         * @name    returnRadioButton_CheckedChanged
         * @brief   Process the change of the return ticket radio button
         *          Shows the return ticket information
         */
        private void returnRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            returnTicketDate.Visible = true;
            returnDateLabel.Visible = true;

            return;
        }

        /**
         * @name    goingRadioButton_CheckedChanged
         * @brief   Process the change of the return ticket radio button
         *          Removes the return ticket information
         */
        private void goingRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            returnTicketDate.Visible = false;
            returnDateLabel.Visible = false;

            return;
        }

        /**
         * @name    searchByCityRadioButton_CheckedChanged
         * @brief   Process the change of the return ticket radio button
         *          Shows the city name of the hotel information
         */
        private void searchByCityRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            hotelNameLabel.Visible = false;
            hotelTextBox.Visible = false;
            localLabel.Visible = true;
            stateHotelCombo.Visible = true;
            cityHotelCombo.Visible = true;

            return;
        }

        /**
         * @name    searchByHotelRadioButton_CheckedChanged
         * @brief   Process the change of the return ticket radio button
         *          Shows the hotel name information
         */
        private void searchByHotelRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            hotelNameLabel.Visible = true;
            hotelTextBox.Visible = true;
            localLabel.Visible = false;
            stateHotelCombo.Visible = false;
            cityHotelCombo.Visible = false;

            return;
        }

        /**
         * @name    returnPackageRadioButton_CheckedChanged
         * @brief   Process the change of the return ticket radio button
         *          Shows the return ticket package information
         */
        private void returnPackageRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            returnPackageDateLabel.Visible = true;
            dateReturnPackage.Visible = true;

            return;
        }

        /**
         * @name    goingPackageRadioButton_CheckedChanged
         * @brief   Process the change of the going ticket package radio button
         *          Remove the return ticket package information
         */
        private void goingPackageRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            returnPackageDateLabel.Visible = false;
            dateReturnPackage.Visible = false;

            return;
        }

        /**
         * @name    stateSrcComboBox_SelectedIndexChanged
         * @brief   Fill the city source combo box according to the choosen state
         */
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

        /**
         * @name    stateDestComboBox_SelectedIndexChanged
         * @brief   Fill the city destination combo box according to the choosen state
         */
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

        /**
         * @name    stateHotelCombo_SelectedIndexChanged
         * @brief   Fill the city combo box according to the choosen state
         */
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

        /**
         * @name    stateSrcPackCombo_SelectedIndexChanged
         * @brief   Fill the city source package combo box according to the choosen state
         */
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

        /**
         * @name    stateDestPackCombo_SelectedIndexChanged
         * @brief   Fill the city destination package combo box according to the choosen state
         */
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

        /**
         * @name    ticketCheckEmptyFields
         * @brief   Check if the text boxes are empty
         * @return  returnValue : false if there is an empty text box
         */
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

        /**
         * @name    hotelCheckEmptyFields
         * @brief   Check if the text boxes are empty
         * @return  returnValue : false if there is an empty text box
         */
        private bool hotelCheckEmptyFields()
        {
            bool returnValue = true;

            // Checks the text boxes according to the checked radiobutton
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

        /**
         * @name    packageCheckEmptyFields
         * @brief   Check if the text boxes are empty
         * @return  returnValue : false if there is an empty text box
         */
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

        /**
         * @name    searchTicketButton_Click
         * @brief   Search for flight ticket from the database
         *          according to the user inputs
         */
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

        /**
         * @name    configTicketDataGrid
         * @brief   Configure the data grid according to the result
         *          of the ticket search
         */
        private void configTicketDataGrid(localhost.flightTicket[] goingTicket,
                                          localhost.flightTicket[] returnTicket)
        {
            string[] row;

            ticketDataGridView.Rows.Clear();
            ticketDataGridView.Columns.Clear();

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

        /**
         * @name    ticketDataGridView_CellClick
         * @brief   Process the cell click of the ticket data grid
         *          Creates a ticket details window if the buy button was pressed
         */
        private void ticketDataGridView_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            // If the cell is where the reserve button is located
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

        /**
         * @name    searchHotelButton_Click
         * @brief   Search for hotels from the database
         *          according to the user inputs
         */
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

        /**
         * @name    configHotelDataGrid
         * @brief   Configure the data grid according to the result
         *          of the ticket search
         */
        private void configHotelDataGrid(localhost.accommodation[] accommodation)
        {
            string[] row;

            hotelDataGridView.Rows.Clear();
            hotelDataGridView.Columns.Clear();

            hotelDataGridView.ColumnCount = 4;
            hotelDataGridView.Columns[0].Name = "Cidade";
            hotelDataGridView.Columns[1].Name = "Nome do Hotel";
            hotelDataGridView.Columns[2].Name = "Hóspedes / Quarto";
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

        /**
         * @name    hotelDataGridView_CellClick_1
         * @brief   Process the cell click of the hotel data grid
         *          Creates a hotel details window if the buy button was pressed
         */
        private void hotelDataGridView_CellClick_1(object sender, DataGridViewCellEventArgs e)
        {
            // If the cell is where the reserve button is located
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

        /**
         * @name    searchPackagesButton_Click
         * @brief   Search for packages from the database
         *          according to the user inputs
         */
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

        /**
         * @name    configPackageDataGrid
         * @brief   Configure the data grid according to the result
         *          of the package search
         */
        private void configPackageDataGrid(localhost.packages[] packages)
        {
            string[] row;
            float totalPrice;
            float returnTicketPrice;
            String tipo;

            packageDataGridView.Rows.Clear();
            packageDataGridView.Columns.Clear();

            packageDataGridView.ColumnCount = 9;
            packageDataGridView.Columns[0].Name = "Tipo";
            packageDataGridView.Columns[1].Name = "Origem";
            packageDataGridView.Columns[2].Name = "Destino";
            packageDataGridView.Columns[3].Name = "Hotel";
            packageDataGridView.Columns[4].Name = "Hóspedes / Quarto";
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

        /**
         * @name    packageDataGridView_CellClick
         * @brief   Process the cell click of the package data grid
         *          Creates a package details window if the buy button was pressed
         */
        private void packageDataGridView_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            // If the cell is where the buy button is located
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

                PackageDetails packDetails = new PackageDetails(goingCityName,
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

                packDetails.Show();
            }
        }

        /**
         * @name    interestTicketButton_Click
         * @brief   Process the click of the register interest button
         *          Creates a ticket interest window if the buy button was pressed
         */
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

                ticketInterest.ShowDialog();

                if(ticketInterest.RegisterCompleted)
                {
                    FlightTicketInterest ticketInt = ticketInterest.RegisteredInterest;

                    registeredTicketInterests.Add(ticketInt);
                }
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }
        }

        /**
         * @name    interestHotelButton_Click
         * @brief   Process the click of the register interest button
         *          Creates a hotel interest window if the buy button was pressed
         */
        private void interestHotelButton_Click(object sender, EventArgs e)
        {
            if (hotelCheckEmptyFields())
            {
                String hotelName;
                String cityName;

                // Create interest according to the checked radio button
                if (searchByHotelRadioButton.Checked)
                {
                    hotelName = hotelTextBox.Text;
                    cityName = "";
                }
                else
                {
                    hotelName = "";
                    cityName = cityHotelCombo.Text;
                }

                HotelInterest hotelInterest = new HotelInterest(cityName,
                                                                hotelName);

                hotelInterest.ShowDialog();

                if(hotelInterest.RegisterCompleted)
                {
                    AccommodationInterest hotelInt = hotelInterest.RegisteredInterest;

                    registeredHotelInterests.Add(hotelInt);
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

        /**
         * @name    interestPackageButton_Click
         * @brief   Process the click of the register interest button
         *          Creates a package interest window if the buy button was pressed
         */
        private void interestPackageButton_Click(object sender, EventArgs e)
        {
            if (packageCheckEmptyFields())
            {
                bool isReturn = false;

                if (returnPackageRadioButton.Checked)
                {
                    isReturn = true;
                }
                // Send request to the webservice
                PackageInterest packageInterest = new PackageInterest(citySrcPackCombo.Text.ToString(),
                                                                       cityDestPackCombo.Text.ToString(),
                                                                       dateGoingPackage.Value.Day,
                                                                       dateGoingPackage.Value.Month,
                                                                       dateGoingPackage.Value.Year,
                                                                       isReturn,
                                                                       dateReturnPackage.Value.Day,
                                                                       dateReturnPackage.Value.Month,
                                                                       dateReturnPackage.Value.Year);

                packageInterest.ShowDialog();

                if(packageInterest.RegisterCompleted)
                {
                    PackageInt packageInt = packageInterest.RegisteredInterest;

                    registeredPackageInterests.Add(packageInt);
                }
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }
        }

        /**
         * @name    ticketInterestRadioButton_CheckedChanged
         * @brief   Process the change of the ticket interest radio button
         *          Configure the interest data grid to show the registered ticket interests
         */
        private void ticketInterestRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            string[] row;

            interestDataGridView.Rows.Clear();
            interestDataGridView.Columns.Clear();

            interestDataGridView.ColumnCount = 7;
            interestDataGridView.Columns[0].Name = "Tipo";
            interestDataGridView.Columns[1].Name = "Origem";
            interestDataGridView.Columns[2].Name = "Destino";
            interestDataGridView.Columns[3].Name = "Data ida";
            interestDataGridView.Columns[4].Name = "Data volta";
            interestDataGridView.Columns[5].Name = "Quantidade";
            interestDataGridView.Columns[6].Name = "Preço Máx";

            DataGridViewButtonColumn btn = new DataGridViewButtonColumn();
            interestDataGridView.Columns.Add(btn);
            btn.HeaderText = "Excluir";
            btn.Text = "Excluir";
            btn.Name = "btn";
            btn.UseColumnTextForButtonValue = true;

            for (int i = 0; i < registeredTicketInterests.Count; i++)
            {
                if (!registeredTicketInterests[i].returnTicket)
                {
                    row = new string[] { "Ida", registeredTicketInterests[i].citySource, registeredTicketInterests[i].cityDest,
                        registeredTicketInterests[i].goingDay + "-" + registeredTicketInterests[i].goingMonth + "-" + registeredTicketInterests[i].goingYear, "-",
                        registeredTicketInterests[i].quantity.ToString(), registeredTicketInterests[i].maxPrice.ToString()};
                }
                else
                {
                    row = new string[] { "Ida/Volta", registeredTicketInterests[i].citySource, registeredTicketInterests[i].cityDest,
                        registeredTicketInterests[i].goingDay + "-" + registeredTicketInterests[i].goingMonth + "-" + registeredTicketInterests[i].goingYear,
                        registeredTicketInterests[i].returnDay + "-" + registeredTicketInterests[i].returnMonth + "-" + registeredTicketInterests[i].returnYear,
                        registeredTicketInterests[i].quantity.ToString(), registeredTicketInterests[i].maxPrice.ToString()};
                }

                interestDataGridView.Rows.Add(row);
            }

            return;
        }

        /**
         * @name    hotelInterestRadioButton_CheckedChanged
         * @brief   Process the change of the hotel interest radio button
         *          Configure the interest data grid to show the registered hotel interests
         */
        private void hotelInterestRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            string[] row;

            interestDataGridView.Rows.Clear();
            interestDataGridView.Columns.Clear();

            interestDataGridView.ColumnCount = 5;
            interestDataGridView.Columns[0].Name = "Cidade";
            interestDataGridView.Columns[1].Name = "Nome do Hotel";
            interestDataGridView.Columns[2].Name = "Hóspedes / Quarto";
            interestDataGridView.Columns[3].Name = "Quantidade";
            interestDataGridView.Columns[4].Name = "Preço Máx";

            DataGridViewButtonColumn btn = new DataGridViewButtonColumn();
            interestDataGridView.Columns.Add(btn);
            btn.HeaderText = "Excluir";
            btn.Text = "Excluir";
            btn.Name = "btn";
            btn.UseColumnTextForButtonValue = true;

            for (int i = 0; i < registeredHotelInterests.Count; i++)
            {
                row = new string[] { registeredHotelInterests[i].cityName, registeredHotelInterests[i].accommodationName,
                                     registeredHotelInterests[i].numberOfGuests.ToString(), registeredHotelInterests[i].quantity.ToString(),
                                     registeredHotelInterests[i].maxPrice.ToString()};

                interestDataGridView.Rows.Add(row);
            }

            return;
        }

        /**
         * @name    packageInterestRadioButton_CheckedChanged
         * @brief   Process the change of the ticpackageket interest radio button
         *          Configure the interest data grid to show the registered package interests
         */
        private void packageInterestRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            string[] row;

            interestDataGridView.Rows.Clear();
            interestDataGridView.Columns.Clear();

            interestDataGridView.ColumnCount = 8;
            interestDataGridView.Columns[0].Name = "Tipo";
            interestDataGridView.Columns[1].Name = "Origem";
            interestDataGridView.Columns[2].Name = "Destino";
            interestDataGridView.Columns[3].Name = "Data ida";
            interestDataGridView.Columns[4].Name = "Data volta";
            interestDataGridView.Columns[5].Name = "Hóspedes / Quarto";
            interestDataGridView.Columns[6].Name = "Quantidade";
            interestDataGridView.Columns[7].Name = "Preço Total";

            DataGridViewButtonColumn btn = new DataGridViewButtonColumn();
            interestDataGridView.Columns.Add(btn);
            btn.HeaderText = "Excluir";
            btn.Text = "Excluir";
            btn.Name = "btn";
            btn.UseColumnTextForButtonValue = true;

            for (int i = 0; i < registeredPackageInterests.Count; i++)
            {
                if (registeredPackageInterests[i].isReturn)
                {
                    row = new string[] { "Ida/Volta", registeredPackageInterests[i].citySource, registeredPackageInterests[i].cityDest,
                                         registeredPackageInterests[i].goingDay + "-" + registeredPackageInterests[i].goingMonth + "-" + registeredPackageInterests[i].goingYear,
                                         registeredPackageInterests[i].returnDay + "-" + registeredPackageInterests[i].returnMonth + "-" + registeredPackageInterests[i].returnYear,
                                         registeredPackageInterests[i].numberOfGuests.ToString(), registeredPackageInterests[i].quantity.ToString(),
                                         registeredPackageInterests[i].maxPrice.ToString()};

                    interestDataGridView.Rows.Add(row);
                }
                else
                {
                    row = new string[] { "Ida", registeredPackageInterests[i].citySource, registeredPackageInterests[i].cityDest,
                                         registeredPackageInterests[i].goingDay + "-" + registeredPackageInterests[i].goingMonth + "-" + registeredPackageInterests[i].goingYear,
                                         "-",
                                         registeredPackageInterests[i].numberOfGuests.ToString(), registeredPackageInterests[i].quantity.ToString(),
                                         registeredPackageInterests[i].maxPrice.ToString()};

                    interestDataGridView.Rows.Add(row);
                }
            }

            return;
        }
        
        private void interestDataGridView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            /*
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
             */


        }
    }
}
