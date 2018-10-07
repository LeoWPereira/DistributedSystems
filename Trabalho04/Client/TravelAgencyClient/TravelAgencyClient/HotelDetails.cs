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
    public partial class HotelDetails : Form
    {
        private localhost.TravelAgencyServiceImplService webService;

        private String cityName;
        private String hotelName;
        private int guests;
        private float price;

        public HotelDetails(String _cityName,
                            String _hotelName,
                            int    _guests,
                            float  _price)
        {
            InitializeComponent();

            cityName  = _cityName;
            hotelName = _hotelName;
            price     = _price;
            guests = _guests;

            // Firstly, configure the web service object
            webService = new localhost.TravelAgencyServiceImplService();

            cityLabel.Text      = cityName;
            hotelNameLabel.Text = hotelName;
            guestsLabel.Text    = guests.ToString();
            priceLabel.Text     = price.ToString();
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

        private void reserveButton_Click(object sender, EventArgs e)
        {
            bool success = false;

            if (checkForEmptyFields())
            {
                success = webService.reserveHotel(cityName,
                                                  hotelName,
                                                  Convert.ToInt32(qtyText.Text),
                                                  price);

                if (success)
                {
                    MessageBox.Show("Hotel reservado com sucesso!");
                    Close();
                }
                else
                {
                    MessageBox.Show("Não há quantidade de quartos suficiente!");
                }
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }
        }
    }
}
