/**
 ******************************************************************************
 * @file    HotelDetails.cs
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

namespace TravelAgencyClient
{
    /**
     * @name    HotelDetails
     * @brief   Class responsible for showing the selected hotel information
     *          and reserving the hotel if the reserve button is pressed.
     *          The user has to input the quantity of rooms and number of
     *          guests to be able to reserve the hotel.
     */
    public partial class HotelDetails : Form
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
         * @name    guests
         * @brief   Number of guests per room
         */
        private int guests;

        /**
         * @name    price
         * @brief   Price per night per room
         */
        private float price;

        /**
         * @name    HotelDetails
         * @brief   Default Class Constructor
         *          Its only work is to call the constructor of every needed private member
         * @param   _cityName       : The city name
         * @param   _hotelName      : The hotel name
         * @param   _guests         : The number of guests per room
         * @param   _price          : The price per night per room
         */
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

        /**
         * @name    checkForEmptyFields
         * @brief   Check if the text boxes are empty
         * @return  returnValue : false if there is an empty text box
         */
        private bool checkForEmptyFields()
        {
            bool returnValue = true;

            if (qtyText.Text.Equals(""))
            {
                returnValue = false;
            }

            return returnValue;
        }

        /**
         * @name    reserveButton_Click
         * @brief   Process the reserve button given the user input
         */
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
