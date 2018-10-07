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
        private float price;

        public HotelDetails(String _cityName,
                            String _hotelName,
                            float  _price)
        {
            InitializeComponent();

            cityName  = _cityName;
            hotelName = _hotelName;
            price     = _price;

            // Firstly, configure the web service object
            webService = new localhost.TravelAgencyServiceImplService();

            cityLabel.Text      = cityName;
            hotelNameLabel.Text = hotelName;
            priceLabel.Text     = price.ToString();
        }

        private void reserveButton_Click(object sender, EventArgs e)
        {

        }
    }
}
