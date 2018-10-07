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
    public partial class ticketDetails : Form
    {
        private localhost.TravelAgencyServiceImplService webService;

        private String citySource;
        private String cityDest;
        private int day;
        private int month;
        private int year;
        private float price;

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

        private bool checkForEmptyFields()
        {
            bool returnValue = true;

            if(qtyText.Text.Equals(""))
            {
                returnValue = false;
            }

            return returnValue;
        }

        private void buyButton_Click(object sender, EventArgs e)
        {
            localhost.flightTicket ticket = null;
            bool success = false;

            if(checkForEmptyFields())
            {
                ticket.source       = citySource;
                ticket.dest         = cityDest;
                ticket.dateDay      = day;
                ticket.dateMonth    = month;
                ticket.dateYear     = year;
                ticket.price        = price;
                ticket.quantity     = Convert.ToInt32(qtyText.Text);

                success = webService.buyPassage(ticket);
            }
        }
    }
}
