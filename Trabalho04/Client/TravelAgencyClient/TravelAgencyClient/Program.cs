using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TravelAgencyClient
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            //localhost.TravelAgencyServiceImplService webService = new localhost.TravelAgencyServiceImplService();

            //localhost.accommodation[] list =  webService.searchHotelByCity("Curitiba");

            //Console.WriteLine(list[0].accommodationName);

            Application.Run(new Form1());
        }
    }
}
