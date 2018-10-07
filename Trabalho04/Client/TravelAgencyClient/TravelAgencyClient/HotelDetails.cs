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

        private String citySource;
        private String cityDest;
        private int day;
        private int month;
        private int year;
        private float price;

        public HotelDetails()
        {
            InitializeComponent();
        }
    }
}
