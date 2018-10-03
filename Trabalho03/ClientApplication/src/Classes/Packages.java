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
	private FlightTicket flightTicketGoing;

	/**
	 * @brief	Instance of FLight Ticket
	 */
	private FlightTicket flightTicketReturn;
	
	/**
	 * @brief	Instance of Accommodation
	 */
	private Accommodation accommodation;
	
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
        setFlightTicketGoing(_flightTicketGoing);
        setFlightTicketReturn(_flightTicketReturn);
        setAccommodation(_accommodation);
    }

	/**
	 * @brief 	Default getter
	 * 
	 * @return 	flight Ticket Going
	 */
	public FlightTicket getFlightTicketGoing() 
	{
		return this.flightTicketGoing;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	_flightTicketGoing	: Instance of FLight Ticket
	 */
	public void setFlightTicketGoing(FlightTicket _flightTicketGoing) 
	{
		this.flightTicketGoing = _flightTicketGoing;
	}

	/**
	 * @brief 	Default getter
	 * 
	 * @return 	flight Ticket Return
	 */
	public FlightTicket getFlightTicketReturn() 
	{
		return this.flightTicketReturn;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	_flightTicketReturn	: Instance of FLight Ticket
	 */
	public void setFlightTicketReturn(FlightTicket _flightTicketReturn) 
	{
		this.flightTicketReturn = _flightTicketReturn;
	}

	/**
	 * @brief 	Default getter
	 * 
	 * @return 	Accommodation
	 */
	public Accommodation getAccommodation() 
	{
		return this.accommodation;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	_accommodation	: Instance of Accommodation
	 */
	public void setAccommodation(Accommodation _accommodation) 
	{
		this.accommodation = _accommodation;
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

		ticketGoingPrice = this.flightTicketGoing.getPrice();

		if(this.flightTicketReturn != null)
		{
			ticketReturnPrice = this.flightTicketReturn.getPrice();
		}

		accommodationPrice = this.accommodation.getPrice();

		return ticketGoingPrice + ticketReturnPrice + accommodationPrice;
	}
}
