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
    public partial class TicketInterest : Form
    {
        private localhost.TravelAgencyServiceImplService webService;

        private String citySource;
        private String cityDest;
        private int goingDay;
        private int goingMonth;
        private int goingYear;
        private bool isReturn;
        private int returnDay;
        private int returnMonth;
        private int returnYear;

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

        private void registerButton_Click(object sender, EventArgs e)
        {
            if (checkForEmptyFields())
            {
                 webService.registerPassageInterest(citySource,
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

                    MessageBox.Show("Interesse registrado com sucesso!");
                    Close();
            }
            else
            {
                MessageBox.Show("Há campos que ainda não foram preenchidos");
            }
        }
    }
}
