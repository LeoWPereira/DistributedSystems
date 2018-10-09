using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TravelAgencyClient.Classes
{
    public class FlightTicketInterest
    {
        /**
         * @brief	Source city
         */
        public String citySource;

        /**
         * @brief	Destination city
         */
        public String cityDest;

        /**
         * @brief	Passage we are interested into is two-way?
         */
        public bool returnTicket;

        /**
         * @brief	Quantity of passages desired
         */
        public int quantity;

        /**
         * @brief	Max price desired to pay
         */
        public float maxPrice;

        /**
         * @brief
         */
        public int goingDay;

        /**
         * @brief
         */
        public int goingMonth;

        /**
         * @brief
         */
        public int goingYear;

        /**
         * @brief
         */
        public int returnDay;

        /**
         * @brief
         */
        public int returnMonth;

        /**
         * @brief
         */
        public int returnYear;

        /**
         * @brief   Default constructor
         * 
         * @param   _citySource     	: String
         * @param   _cityDest   		: String
         * @param   _quantity           : int
         * @param   _maxPrice           : float
         */
        public FlightTicketInterest(String _citySource,
                                    String _cityDest,
                                    int _goingDay,
                                    int _goingMonth,
                                    int _goingYear,
                                    bool _returnTicket,
                                    int _returnDay,
                                    int _returnMonth,
                                    int _returnYear,
                                    int _quantity,
                                    float _maxPrice)
        {
            this.citySource = _citySource;
            this.cityDest = _cityDest;
            this.returnTicket = _returnTicket;
            this.goingDay = _goingDay;
            this.goingMonth = _goingMonth;
            this.goingYear = _goingYear;
            this.returnDay = _returnDay;
            this.returnMonth = _returnMonth;
            this.returnYear = _returnYear;
            this.quantity = _quantity;
            this.maxPrice = _maxPrice;
        }
    }
}
