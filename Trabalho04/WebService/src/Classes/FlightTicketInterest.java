/**
 ******************************************************************************
 * @file    FlightTicketInterest.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @brief   Class responsible for storing the flight ticket interest info from the clients
 */
public class FlightTicketInterest implements Serializable
{
	/**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = -1430226376074837554L;

	/**
     * @brief	Instance of flight ticket
     */
    public FlightTicket  flightTicketTo;

    /**
     * @brief	Instance of flight ticket
     */
    public FlightTicket  flightTicketFrom;

    /**
     * @brief	Passage we are interested into is two-way?
     */
    public boolean returnTicket;
    
    /**
     * @brief	Quantity of passages desired
     */
    public int quantity;
    
    /**
     * @brief	Max price desired to pay
     */
    public float maxPrice;

    /**
     * @brief	Name of the client
     */
    public String clientName;

    /**
     * @brief   Default constructor
     * 
     * @param   _flightTicketTo     : FlightTicket
     * @param   _flightTicketFrom   : FlightTicket
     * @param   _quantity           : int
     * @param   _maxPrice           : float
     * @param   _clientName         : String
     */   
    public FlightTicketInterest(FlightTicket    _flightTicketTo, 
                                FlightTicket    _flightTicketFrom, 
                                boolean         _returnTicket,
                                int             _quantity, 
                                float           _maxPrice, 
                                String          _clientName) 
    {
        this.flightTicketTo 	= _flightTicketTo;
        this.flightTicketFrom 	= _flightTicketFrom;
        this.returnTicket       = _returnTicket;
        this.quantity 			= _quantity;
        this.maxPrice 			= _maxPrice;
        this.clientName         = _clientName;
    }
    
    /**
     * @brief   Get the source of the ticket interest
     * 
     * @return  source flight
     */   
    public String getSource()
    {
        return this.flightTicketTo.source;
    }

    /**
     * @brief   Get the destination of the ticket interest
     * 
     * @return  city Name
     */   
    public String getDest()
    {
        return this.flightTicketTo.dest;
    }

    /**
     * @brief   Get the source date from the source ticket
     * 
     * @return  source Date
     */ 
    public Date getSourceDate()
    {
    	Calendar calendar = Calendar.getInstance();
	    
		calendar.set(this.flightTicketTo.dateYear,
					 this.flightTicketTo.dateMonth,
					 this.flightTicketTo.dateDay);
		
		return new java.sql.Date(calendar.getTime().getTime());
    }

    /**
     * @brief   Get the return date from the return ticket
     * 
     * @return  return Date
     */ 
    public Date getReturnDate()
    {
    	Calendar calendar = Calendar.getInstance();
	    
		calendar.set(this.flightTicketFrom.dateYear,
					 this.flightTicketFrom.dateMonth,
					 this.flightTicketFrom.dateDay);
		
		return new java.sql.Date(calendar.getTime().getTime());
    }
}
