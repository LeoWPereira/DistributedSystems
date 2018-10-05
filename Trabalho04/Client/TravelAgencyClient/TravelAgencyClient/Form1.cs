using System;
using System.Windows.Forms;

namespace TravelAgencyClient
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

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

            for (int i = 0; i < cities.Length; i++)
            {
                cityDestPackCombo.Items.Add(cities[i]);
            }

            return;
        }

        private void searchTicketButton_Click(object sender, EventArgs e)
        {

        }

        private void searchHotelButton_Click(object sender, EventArgs e)
        {

        }

        private void searchPackagesButton_Click(object sender, EventArgs e)
        {

        }

        private void interestTicketButton_Click(object sender, EventArgs e)
        {

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
