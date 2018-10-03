/**
 ******************************************************************************
 * @file    FlightTicketInterest.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    01 de out de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.util.Date;

/**
 * @brief   Class responsible for storing the flight ticket interest info from the clients
 */
public class FlightTicketInterest 
{
    /**
     * @brief
     */
	public FlightTicket		flightTicketTo;

    /**
     * @brief
     */
	public FlightTicket  	flightTicketFrom;

    /**
     * @brief
     */
	public boolean 			returnTicket;
    
    /**
     * @brief
     */
	public int 				quantity;
    
    /**
     * @brief
     */
	public float 			maxPrice;

    /**
     * @brief
     */
	public String 			clientName;

    /**
     * @brief   Default constructor
     * 
     * @param   _flightTicketTo     : FlightTicket
     * @param   _flightTicketFrom   : FlightTicket
     * @param   _quantity           : int
     * @param   _maxPrice           : float
     * @param   _refCli             : ClientInterface
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
     * @brief Return if a ticket is a return ticket (not one-way)
     * 
     * @param isReturn : boolean - True if it is a return ticket
     */
    public boolean isReturnTicket() 
    {
        return returnTicket;
    }
    
    /**
     * @brief   Get the source of the ticket interest
     * 
     * @return  source
     */   
    public String getSource()
    {
        return flightTicketTo.source;
    }

    /**
     * @brief   Get the destination of the ticket interest
     * 
     * @return  cityName
     */   
    public String getDest()
    {
        return flightTicketTo.dest;
    }

    /**
     * @brief   Get the source date from the source ticket
     * 
     * @return  sourceDate
     */ 
    public Date getSourceDate()
    {
        return flightTicketTo.date;
    }

    /**
     * @brief   Get the return date from the return ticket
     * 
     * @return  returnDate
     */ 
    public Date getReturnDate()
    {
        return flightTicketFrom.date;
    }
}
    
