/**
 ******************************************************************************
 * @file    PackageDetails.cs
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
     * @name    PackageDetails
     * @brief   Class responsible for showing the selected package information
     *          and buying the package if the buy button is pressed.
     *          The user has to input the quantity of rooms/tickets, number of
     *          guests to be able to buy the package.
     */
    public partial class PackageDetails : Form
    {
        /**
         * @name    citySource
         * @brief   City of origin (source) of where the flight
         *          will be taken.
         */
        private String citySource;

        /**
         * @name    cityDest
         * @brief   City of destination of where the flight
         *          will be landed.
         */
        private String cityDest;

        /**
         * @name    hotelName
         * @brief   Name of the hotel located in the destination city
         */
        private String hotelName;

        /**
         * @name    goingDay
         * @brief   Day where the flight will be taken
         */
        private int goingDay;

        /**
         * @name    goingMonth
         * @brief   Month where the flight will be taken
         */
        private int goingMonth;

        /**
         * @name    goingYear
         * @brief   Year where the flight will be taken
         */
        private int goingYear;

        /**
         * @name    isReturn
         * @brief   Flag that indicates if the package includes
         *          a return ticket.
         */
        private bool isReturn;

        /**
         * @name    returnDay
         * @brief   Day where the return flight will be taken
         */
        private int returnDay;

        /**
         * @name    returnMonth
         * @brief   Month where the return flight will be taken
         */
        private int returnMonth;

        /**
         * @name    returnYear
         * @brief   Year where the return flight will be taken
         */
        private int returnYear;

        /**
         * @name    numberOfGuests
         * @brief   Number of guests per room
         */
        private int numberOfGuests;

        /**
         * @name    totalPrice
         * @brief   Sum price of the flight tickets and hotel
         */
        private float totalPrice;

        /**
         * @name    goingTicketPrice
         * @brief   Price of the going ticket
         */
        private float goingTicketPrice;

        /**
         * @name    returnTicketPrice
         * @brief   Price of the return ticket
         */
        private float returnTicketPrice;

        /**
         * @name    hotelPrice
         * @brief   Price of the hotel
         */
        private float hotelPrice;

        /**
         * @name    webService
         * @brief   WebService object used for calling the methods
         *          of the web service.
         */
        private localhost.TravelAgencyServiceImplService webService;

        /**
         * @name    PackageDetails
         * @brief   Default Class Constructor
         *          Its only work is to call the constructor of every needed private member
         * @param  _citySource         :  City of origin (source) of where the flight will be taken                             
         * @param  _cityDest           :  City of destination of where the flight will be landed    
         * @param  _hotelName          :  Hotel name
         * @param  _goingDay           :  Day where the flight will be taken
         * @param  _goingMonth         :  Month where the flight will be taken
         * @param  _goingYear          :  Year where the flight will be taken
         * @param   _isReturn          :  Flag that indicates if the package includes a return ticket
         * @param   _returnDay         :  Day where the return flight will be taken
         * @param  _returnMonth        :  Month where the return flight will be taken
         * @param  _returnYear         :  Year where the return flight will be taken
         * @param  _guests             :  Number of guests per room
         * @param  _goingTicketPrice   :  Price of the going ticket
         * @param  _returnTicketPrice  :  Price of the return ticket
         * @param  _hotelPrice         :  Price of the hotel
         * @param  _price              :  Total price of the package
         */
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
         * @name    buyButton_Click
         * @brief   Process the buy button given the user input
         */
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
