/**
 ******************************************************************************
 * @file    Package.java
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
 * Class responsible for storing the package info
 */
public class Packages implements Serializable
{
    /**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = 3281860207276377509L;
	
	/**
	 * @brief	Instance of FLight Ticket
	 */
	public FlightTicket flightTicketGoing;

	/**
	 * @brief	Instance of FLight Ticket
	 */
	public FlightTicket flightTicketReturn;
	
	/**
	 * @brief	Instance of Accommodation
	 */
	public Accommodation accommodation;
	
    /**
     * @brief	Default Constructor
     * 
     * @param 	_flightTicketGoing		: Instance of FLight Ticket
     * @param 	_flightTicketReturn		: Instance of FLight Ticket
     * @param 	_accommodation			: Instance of Accommodation
     */   
    public Packages(FlightTicket 	_flightTicketGoing, 
					FlightTicket 	_flightTicketReturn, 
					Accommodation 	_accommodation)
    {
    	this.flightTicketGoing	= _flightTicketGoing;
    	this.flightTicketReturn	= _flightTicketReturn;
    	this.accommodation		= _accommodation;
    }

	/**
	 * @brief 	Get the total price of the package
	 * 
	 * @param 	total Price
	 */
	public float getTotalPrice() 
	{
		float ticketGoingPrice		= 0;
		float ticketReturnPrice 	= 0;
		float accommodationPrice	= 0;

		ticketGoingPrice = this.flightTicketGoing.price;

		if(this.flightTicketReturn != null)
		{
			ticketReturnPrice = this.flightTicketReturn.price;
		}

		accommodationPrice = this.accommodation.price;

		return ticketGoingPrice + ticketReturnPrice + accommodationPrice;
	}
}
