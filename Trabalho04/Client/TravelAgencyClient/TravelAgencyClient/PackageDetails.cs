using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TravelAgencyClient
{
    public partial class PackageDetails : Form
    {
        private String citySource;
        private String cityDest;
        private String hotelName;
        private int goingDay;
        private int goingMonth;
        private int goingYear;
        private bool isReturn;
        private int returnDay;
        private int returnMonth;
        private int returnYear;
        private int numberOfGuests;
        private float totalPrice;
        private float goingTicketPrice;
        private float returnTicketPrice;
        private float hotelPrice;

        private localhost.TravelAgencyServiceImplService webService;

        public PackageDetails(String _citySource,
                              String _cityDest,
                              String _hotelName,
                              int    _goingDay,
                              int    _goingMonth,
                              int    _goingYear,
                              bool   _isReturn,
                              int    _returnDay,
                              int    _returnMonth,
                              int    _returnYear,
                              int    _guests,
                              float  _goingTicketPrice,
                              float  _returnTicketPrice,
                              float  _hotelPrice,
                              float  _price)
        {
            InitializeComponent();

            citySource          = _citySource;
            cityDest            = _cityDest;
            hotelName           = _hotelName;
            goingDay            = _goingDay;
            goingMonth          = _goingMonth;
            goingYear           = _goingYear;
            isReturn            = _isReturn;
            returnDay           = _returnDay;
            returnMonth         = _returnMonth;
            returnYear          = _returnYear;
            numberOfGuests      = _guests;
            totalPrice          = _price;
            goingTicketPrice    = _goingTicketPrice;
            returnTicketPrice   = _returnTicketPrice;
            hotelPrice          = _hotelPrice;

            // Firstly, configure the web service object
            webService = new localhost.TravelAgencyServiceImplService();

            sourceLabel.Text = citySource;
            destLabel.Text = cityDest;
            goingDateLabel.Text = goingDay.ToString() + "-" + goingMonth.ToString() + "-" + goingYear.ToString();

            if(isReturn)
            {
                returnDateLabel.Text = returnDay.ToString() + "-" + returnMonth.ToString() + "-" + returnYear.ToString();
            }
            else
            {
                dataVoltaLabel.Visible = false;
                returnDateLabel.Visible = false;
            }
            hotelLabel.Text = hotelName;
            guestsLabel.Text = numberOfGuests.ToString();
            priceLabel.Text = totalPrice.ToString();
        }

        private bool checkForEmptyFields()
        {
            bool returnValue = true;

            if (qtyText.Text.Equals(""))
            {
                returnValue = false;
            }

            return returnValue;
        }

        private void buyButton_Click(object sender, EventArgs e)
        {
            int result;

            if (checkForEmptyFields())
            {
                result = webService.buyPackage(citySource,
                                                cityDest,
                                                hotelName,
                                                goingDay,
                                                goingMonth,
                                                goingYear,
                                                isReturn,
                                                returnDay,
                                                returnMonth,
                                                returnYear,
                                                goingTicketPrice,
                                                returnTicketPrice,
                                                hotelPrice,
                                                Convert.ToInt32(qtyText.Text));

                if (result == 1)
                {
                    MessageBox.Show("Pacote comprada com sucesso!");
                    Close();
                }
                else if(result == 2)
                {
                    MessageBox.Show("Não há quantidade de passagens suficiente!");
                }
                else if (result == 3)
                {
                    MessageBox.Show("Não há quantidade de quartos suficiente!");
                }
                else
                {
                    MessageBox.Show("Erro indefinido!");
                }
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }
        }
    }
}
