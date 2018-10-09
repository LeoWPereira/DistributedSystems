using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TravelAgencyClient.Classes
{
    public class PackageInt
    {
        /**
     * @brief	Desired quantity
     */
        public int quantity;

        /**
         * @brief	Max price to pay
         */
        public float maxPrice;

        /**
         * @brief	Number of Guests
         */
        public int numberOfGuests;

        /**
         * @brief	Name of the client
         */
        public String citySource;

        /**
         * @brief	
         */
        public String cityDest;

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
        public bool isReturn;

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
         * @param   _citySource
         * @param   _cityDest
         * @param   _goingDay
         * @param   _goingMonth
         * @param   _goingYear
         * @param   _isReturn
         * @param   _returnDay
         * @param   _returnMonth
         * @param   _returnYear
         * @param   _returnTicket
         * @param   _quantity           : int
         * @param   _maxPrice           : float
         * @param   _numberOfGuests     : int
         */
        public PackageInt(String _citySource,
                               String _cityDest,
                               int _goingDay,
                               int _goingMonth,
                               int _goingYear,
                               bool _isReturn,
                               int _returnDay,
                               int _returnMonth,
                               int _returnYear,
                               int _quantity,
                               float _maxPrice,
                               int _numberOfGuests)
        {
            this.citySource = _citySource;
            this.cityDest = _cityDest;
            this.goingDay = _goingDay;
            this.goingMonth = _goingMonth;
            this.goingYear = _goingYear;
            this.isReturn = _isReturn;
            this.returnDay = _returnDay;
            this.returnMonth = _returnMonth;
            this.returnYear = _returnYear;

            this.quantity = _quantity;
            this.maxPrice = _maxPrice;
            this.numberOfGuests = _numberOfGuests;
        }
    }
}
