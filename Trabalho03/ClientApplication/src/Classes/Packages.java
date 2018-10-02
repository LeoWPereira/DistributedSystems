/**
 ******************************************************************************
 * @file    Packages.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.io.Serializable;
import java.util.Date;

/**
 * Class responsible for storing the package info
 */
public class Packages implements Serializable
{
    /**
	 * @brief
	 */
	private static final long serialVersionUID = 3281860207276377509L;
	
	/**
	 * @brief
	 */
	private FlightTicket flightTicketGoing;

	/**
	 * @brief
	 */
	private FlightTicket flightTicketReturn;
	
	/**
	 * @brief
	 */
	private Accommodation accommodation;
    /**
     * @brief	Default Constructor
     * 
     * @param 	_flightTicketGoing		:
     * @param 	_flightTicketReturn		:
     * @param 	_accommodation			:
     */   
    public Packages(FlightTicket 	_flightTicketGoing, 
					FlightTicket 	_flightTicketReturn, 
					Accommodation 	_accommodation)
    {
        setFlightTicketGoing(_flightTicketGoing);
        setFlightTicketReturn(_flightTicketReturn);
        setAccommodation(_accommodation);
    }

	/**
	 * @brief Default getter
	 * 
	 * @return flightTicketGoing
	 */
	public FlightTicket getFlightTicketGoing() 
	{
		return flightTicketGoing;
	}

	/**
	 * @brief
	 * 
	 * @param flightTicketGoing
	 */
	public void setFlightTicketGoing(FlightTicket _flightTicketGoing) 
	{
		this.flightTicketGoing = _flightTicketGoing;
	}

	/**
	 * @brief Default getter
	 * 
	 * @return flightTicketReturn
	 */
	public FlightTicket getFlightTicketReturn() 
	{
		return flightTicketReturn;
	}

	/**
	 * @brief
	 * 
	 * @param flightTicketReturn
	 */
	public void setFlightTicketReturn(FlightTicket _flightTicketReturn) 
	{
		this.flightTicketReturn = _flightTicketReturn;
	}

	/**
	 * @brief Default getter
	 * 
	 * @return accommodation
	 */
	public Accommodation getAccommodation() 
	{
		return accommodation;
	}

	/**
	 * @brief
	 * 
	 * @param accommodation
	 */
	public void setAccommodation(Accommodation _accommodation) 
	{
		this.accommodation = _accommodation;
	}

	/**
	 * @brief Get the total price of the package
	 * 
	 * @param totalPrice
	 */
	public float getTotalPrice() 
	{
		float ticketGoingPrice		= 0;
		float ticketReturnPrice 	= 0;
		float accommodationPrice	= 0;

		ticketGoingPrice = flightTicketGoing.getPrice();

		if(flightTicketReturn != null)
		{
			ticketReturnPrice = flightTicketReturn.getPrice();
		}

		accommodationPrice = accommodation.getPrice();

		return ticketGoingPrice + ticketReturnPrice + accommodationPrice;
	}
}
