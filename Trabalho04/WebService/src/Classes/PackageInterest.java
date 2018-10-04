/**
 ******************************************************************************
 * @file    PackageInterest.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.io.Serializable;

/**
 * @brief   Class responsible for storing the package interest info from the clients
 */
public class PackageInterest implements Serializable
{
    /**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = 8486142313026956054L;

	/**
     * @brief	Instance of Flight Ticket
     */
    public FlightTicket  flightTicketTo;

    /**
     * @brief	Instance of Flight Ticket
     */
    public FlightTicket  flightTicketFrom;

    /**
     * @brief	The flight we are interested into is a return ticket?
     */
    public boolean returnTicket;
    
    /**
     * @brief	Desired quantity
     */
    public int quantity;
    
    /**
     * @brief	Max price to pay
     */
    public float maxPrice;

    /**
     * @brief	Instance of Accommodation
     */
    public Accommodation accommodation;

    /**
     * @brief	Number of Guests
     */
    public int numberOfGuests;
    
    /**
     * @brief	Name of the client
     */
    public String clientName;

    /**
     * @brief   Default constructor
     * 
     * @param   _flightTicketTo     : FlightTicket
     * @param   _flightTicketFrom   : FlightTicket
     * @param   _accommodation      : Accommodation
     * @param   _quantity           : int
     * @param   _maxPrice           : float
     * @param   _numberOfGuests     : int
     * @param   _clientName         : String
     */   
    public PackageInterest(FlightTicket    _flightTicketTo, 
                           FlightTicket    _flightTicketFrom, 
                           Accommodation   _accommodation,
                           boolean         _returnTicket,
                           int             _quantity, 
                           float           _maxPrice, 
                           int             _numberOfGuests,
                           String          _clientName) 
    {
        this.flightTicketTo 	= _flightTicketTo;
        this.flightTicketFrom 	= _flightTicketFrom;
        this.accommodation      = _accommodation;
        this.returnTicket       = _returnTicket;
        this.quantity 			= _quantity;
        this.maxPrice 			= _maxPrice;
        this.numberOfGuests     = _numberOfGuests;
        this.clientName         = _clientName;
    }

    /**
     * @brief   Get the accommodation name from the accommodation
     * 
     * @return  accommodation Name
     */ 
    public String getAccommodationName()
    {
        return this.accommodation.accommodationName;
    }

    /**
     * @brief   Get the accommodation city name from the accommodation
     * 
     * @return  accommodation City Name
     */ 
    public String getAccommodationCityName()
    {
        return this.accommodation.cityName;
    }
}
