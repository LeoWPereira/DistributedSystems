using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TravelAgencyClient.Classes
{
    public class AccommodationInterest
    {
        /**
     * @brief
     */
        public String cityName;

        /**
         * @brief
         */
        public String accommodationName;

        /**
         * @brief	Quantity of room one is interested
         */
        public int quantity;

        /**
         * @brief	Number of guests interested (in this interest event)
         */
        public int numberOfGuests;

        /**
         * @brief	Max desired price to pay
         */
        public float maxPrice;

        /**
         * @brief   Default constructor
         * 
         * @param   _accommodation      : Accomodation
         * @param   _quantity           : int
         * @param   _numberOfGuests     : int
         * @param   _maxPrice           : float
         * @param   _clientName         : String
         */
        public AccommodationInterest(String _cityName,
                                     String _accommodationName,
                                     int _quantity,
                                     int _numberOfGuests,
                                     float _maxPrice)
        {
            this.cityName = _cityName;
            this.accommodationName = _accommodationName;
            this.quantity = _quantity;
            this.numberOfGuests = _numberOfGuests;
            this.maxPrice = _maxPrice;
        }

        /**
         * @brief	Default getter
         * 
         * @param 	Accommodation Name
         */
        public String getAccommodationName()
        {
            return this.accommodationName;
        }

        /**
         * @brief   Default getter
         * 
         * @return  City Name
         */
        public String getAccommodationCityName()
        {
            return this.cityName;
        }
    }
}
