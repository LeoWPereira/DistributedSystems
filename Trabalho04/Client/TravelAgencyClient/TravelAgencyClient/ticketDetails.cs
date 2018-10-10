/**
 ******************************************************************************
 * @file    ticketDetails.cs
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
     * @name    ticketDetails
     * @brief   Class responsible for showing the selected flight ticket information
     *          and buying the flight ticket if the buy button is pressed.
     *          The user has to input the quantity of tickets to be able 
     *          to buy the flight ticket.
     */
    public partial class ticketDetails : Form
    {
        /**
         * @name    webService
         * @brief   WebService object used for calling the methods
         *          of the web service.
         */
        private localhost.TravelAgencyServiceImplService webService;

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
         * @name    day
         * @brief   Day where the flight will be taken
         */
        private int day;

        /**
         * @name    month
         * @brief   Month where the flight will be taken
         */
        private int month;

        /**
         * @name    year
         * @brief   Year where the flight will be taken
         */
        private int year;

        /**
         * @name    price
         * @brief   Price of the flight ticket
         */
        private float price;

        /**
         * @name    PackageDetails
         * @brief   Default Class Constructor
         *          Its only work is to call the constructor of every needed private member
         * @param  _citySource          :  City of origin (source) of where the flight will be taken                             
         * @param  _cityDest            :  City of destination of where the flight will be landed    
         * @param  _day                 :  Day where the flight will be taken
         * @param  _month               :  Month where the flight will be taken
         * @param  _year                :  Year where the flight will be taken
         * @param  _price               :  Total price of the ticket
         */
        public ticketDetails(String _citySource, 
                             String _cityDest, 
                             int    _day, 
                             int    _month,
                             int    _year,
                             float  _price)
        {
            InitializeComponent();

            citySource  = _citySource;
            cityDest    = _cityDest;
            day         = _day;
            month       = _month;
            year        = _year;
            price       = _price;

            // Firstly, configure the web service object
            webService = new localhost.TravelAgencyServiceImplService();

            sourceLabel.Text = citySource;
            destLabel.Text   = cityDest;
            dateLabel.Text   = day.ToString() + "-" + month.ToString() + "-" + year.ToString();
            priceLabel.Text  = price.ToString();
        }

        /**
         * @name    checkForEmptyFields
         * @brief   Check if the text boxes are empty
         * @return  returnValue : false if there is an empty text box
         */
        private bool checkForEmptyFields()
        {
            bool returnValue = true;

            if(qtyText.Text.Equals(""))
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
            bool success = false;

            if(checkForEmptyFields())
            {
                success = webService.buyPassage(citySource,
                                      cityDest,
                                      day,
                                      month,
                                      year,
                                      Convert.ToInt32(qtyText.Text),
                                      price);

                if(success)
                {
                    MessageBox.Show("Passagem comprada com sucesso!");
                    Close();
                }
                else
                {
                    MessageBox.Show("Não há quantidade de passagens suficiente!");
                }
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }
        }
    }
}
