using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using TravelAgencyClient.Classes;

namespace TravelAgencyClient
{
    public partial class HotelInterest : Form
    {
        private localhost.TravelAgencyServiceImplService webService;

        private String cityName;
        private String hotelName;

        public HotelInterest(String _cityName,
                             String _hotelName)
        {
            InitializeComponent();

            cityName    = _cityName;
            hotelName   = _hotelName;

            // Firstly, configure the web service object
            webService = new localhost.TravelAgencyServiceImplService();

            // Checks if the interest is by hotel name
            if(cityName.Equals(""))
            {
                cityFixLabel.Visible = false;
                cityLabel.Visible = false;
                hotelNameLabel.Text = hotelName;

            }
            else
            {
                hotelNameLabel.Visible = false;
                hotelFixLabel.Visible = false;
                cityLabel.Text = hotelName;
            }
            cityLabel.Text = cityName;
            hotelNameLabel.Text = hotelName;
        }

        private bool checkForEmptyFields()
        {
            bool returnValue = true;

            if (qtyText.Text.Equals("") ||
                priceText.Text.Equals("") ||
                guestsText.Text.Equals(""))
            {
                returnValue = false;
            }

            return returnValue;
        }

        private void interestOnHotelByHotelCompleted(object arg, localhost.registerHotelInterestByHotelCompletedEventArgs e)
        {
            var result = MessageBox.Show(e.Result,
                                         "Notificação",
                                         MessageBoxButtons.OK,
                                         MessageBoxIcon.Information);
        }

        private void interestOnHotelByCityCompleted(object arg, localhost.registerHotelInterestByCityCompletedEventArgs e)
        {
            var result = MessageBox.Show(e.Result,
                                         "Notificação",
                                         MessageBoxButtons.OK,
                                         MessageBoxIcon.Information);
        }

        private void registerButton_Click(object sender, EventArgs e)
        {
            if (checkForEmptyFields())
            {
                // Checks if the interest is by hotel name
                if (cityName.Equals(""))
                {
                    webService.registerHotelInterestByHotelCompleted += new localhost.registerHotelInterestByHotelCompletedEventHandler(interestOnHotelByHotelCompleted);
                    
                    webService.registerHotelInterestByHotelAsync(hotelName,
                                                                 Convert.ToInt32(qtyText.Text),
                                                                 Convert.ToInt32(guestsText.Text),
                                                                 (float)Convert.ToDouble(priceText.Text));
                }
                else
                {
                    webService.registerHotelInterestByCityCompleted += new localhost.registerHotelInterestByCityCompletedEventHandler(interestOnHotelByCityCompleted);

                    webService.registerHotelInterestByCityAsync(cityName,
                                                                Convert.ToInt32(qtyText.Text),
                                                                Convert.ToInt32(guestsText.Text),
                                                                (float)Convert.ToDouble(priceText.Text));
                }
                
                MessageBox.Show("Interesse registrado com sucesso!");

                Close();
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }
        }

        public AccommodationInterest RegisteredInterest
        {
            get
            {
                AccommodationInterest hotelInt = new AccommodationInterest(cityName,
                                                                           hotelName,
                                                                           Convert.ToInt32(qtyText.Text),
                                                                           Convert.ToInt32(guestsText.Text),
                                                                           (float)Convert.ToDouble(priceText.Text));
                return hotelInt;
            }
        }
    }
}
