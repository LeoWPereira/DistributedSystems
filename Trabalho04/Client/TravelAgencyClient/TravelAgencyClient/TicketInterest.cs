/**
 ******************************************************************************
 * @file    TicketInterest.cs
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
     * @name    TicketInterest
     * @brief   Class that shows the information of the interest
     *          to be registered. The user has to inform the quantity
     *          of tickets and desired price.
     */
    public partial class TicketInterest : Form
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
         * @name    registerCompleted
         * @brief   Flag that indicates if the interest was registered
         */
        private bool registerCompleted;

        /**
         * @name    TicketInterest
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
         */
        public TicketInterest(String _citySource,
                              String _cityDest,
                              int _goingDay,
                              int _goingMonth,
                              int _goingYear,
                              bool _isReturn,
                              int _returnDay,
                              int _returnMonth,
                              int _returnYear)
        {
            InitializeComponent();

            citySource = _citySource;
            cityDest = _cityDest;
            goingDay = _goingDay;
            goingMonth = _goingMonth;
            goingYear = _goingYear;
            isReturn = _isReturn;
            returnDay = _returnDay;
            returnMonth = _returnMonth;
            returnYear = _returnYear;

            // Firstly, configure the web service object
            webService = new localhost.TravelAgencyServiceImplService();

            // Interest is not registered yet
            registerCompleted = false;

            sourceLabel.Text = citySource;
            destLabel.Text = cityDest;
            goingDateLabel.Text = goingDay.ToString() + "-" + goingMonth.ToString() + "-" + goingYear.ToString();

            if (isReturn)
            {
                returnDateLabel.Text = returnDay.ToString() + "-" + returnMonth.ToString() + "-" + returnYear.ToString();
            }
            else
            {
                dataVoltaLabel.Visible = false;
                returnDateLabel.Visible = false;
            }

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
                priceText.Text.Equals(""))
            {
                returnValue = false;
            }

            return returnValue;
        }

        /**
         * @name    interestOnPassageCompleted
         * @brief   Function called after the callback has been completed
         */
        private void interestOnPassageCompleted(object arg, localhost.registerPassageInterestCompletedEventArgs e)
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
                webService.registerPassageInterestCompleted += new localhost.registerPassageInterestCompletedEventHandler(interestOnPassageCompleted);

                webService.registerPassageInterestAsync(citySource,
                                                         cityDest,
                                                         goingDay,
                                                         goingMonth,
                                                         goingYear,
                                                         isReturn,
                                                         returnDay,
                                                         returnMonth,
                                                         returnYear,
                                                         Convert.ToInt32(qtyText.Text),
                                                         (float)Convert.ToDouble(priceText.Text));

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
         * @return  ticketInt    : FlightTicketInterest
         */
        public FlightTicketInterest RegisteredInterest
        {
            get
            {
                FlightTicketInterest ticketInt = new FlightTicketInterest(citySource,
                                                                         cityDest,
                                                                         goingDay,
                                                                         goingMonth,
                                                                         goingYear,
                                                                         isReturn,
                                                                         returnDay,
                                                                         returnMonth,
                                                                         returnYear,
                                                                         Convert.ToInt32(qtyText.Text),
                                                                         (float)Convert.ToDouble(priceText.Text));
                return ticketInt;
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
