/**
 ******************************************************************************
 * @file    HotelInterest.cs
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    30 de set de 2018
 * @brief
 ******************************************************************************
 */

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
    /**
     * @name    HotelInterest
     * @brief   Class that shows the information of the interest
     *          to be registered. The user has to inform the quantity
     *          of rooms, number of guests per room and desired price.
     */
    public partial class HotelInterest : Form
    {
        /**
         * @name    webService
         * @brief   WebService object used for calling the methods
         *          of the web service.
         */
        private localhost.TravelAgencyServiceImplService webService;

        /**
         * @name    cityName
         * @brief   Name of the city where the hotel is located
         */
        private String cityName;

        /**
         * @name    hotelName
         * @brief   Name of the accommodation
         */
        private String hotelName;

        /**
         * @name    registerCompleted
         * @brief   Flag that indicates if the interest was registered
         */
        private bool registerCompleted;

        /**
         * @name    HotelInterest
         * @brief   Default Class Constructor
         *          Its only work is to call the constructor of every needed private member
         * @param   _cityName       : The city name
         * @param   _hotelName      : The hotel name
         */
        public HotelInterest(String _cityName,
                             String _hotelName)
        {
            InitializeComponent();

            cityName    = _cityName;
            hotelName   = _hotelName;

            // Firstly, configure the web service object
            webService = new localhost.TravelAgencyServiceImplService();

            // Interest is not registered yet
            registerCompleted = false;

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

        /**
         * @name    checkForEmptyFields
         * @brief   Check if the text boxes are empty
         * @return  returnValue : false if there is an empty text box
         */
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

        /**
         * @name    interestOnHotelByHotelCompleted
         * @brief   Function called after the callback has been completed
         */
        private void interestOnHotelByHotelCompleted(object arg, localhost.registerHotelInterestByHotelCompletedEventArgs e)
        {
            var result = MessageBox.Show(e.Result,
                                         "Notificação",
                                         MessageBoxButtons.OK,
                                         MessageBoxIcon.Information);
        }

        /**
         * @name    interestOnHotelByCityCompleted
         * @brief   Function called after the callback has been completed
         */
        private void interestOnHotelByCityCompleted(object arg, localhost.registerHotelInterestByCityCompletedEventArgs e)
        {
            var result = MessageBox.Show(e.Result,
                                         "Notificação",
                                         MessageBoxButtons.OK,
                                         MessageBoxIcon.Information);
        }

        /**
         * @name    registerButton_Click
         * @brief   Process the register button given the user input
         */
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

                // Sets the flag to completed
                registerCompleted = true;
                
                MessageBox.Show("Interesse registrado com sucesso!");

                Close();
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }
        }

        /**
         * @name    RegisteredInterest
         * @brief   Default getter
         * @return  hotelInt    : AccommodationInterest
         */
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

        /**
         * @name    RegisterCompleted
         * @brief   Default getter
         * @return  registerCompleted    : RegisterCompleted
         */
        public bool RegisterCompleted
        {
            get
            {
                return registerCompleted;
            }
        }
    }
}
