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

import java.util.Date;

import RMI.ClientInterface;

/**
 * @brief   Class responsible for storing the flight ticket interest info from the clients
 */
public class FlightTicketInterest implements Serializable
{
    /**
     * @brief	Instance of flight ticket
     */
    private FlightTicket  flightTicketTo;

    /**
     * @brief	Instance of flight ticket
     */
    private FlightTicket  flightTicketFrom;

    /**
     * @brief	Passage we are interested into is two-way?
     */
    private boolean returnTicket;
    
    /**
     * @brief	Quantity of passages desired
     */
    private int quantity;
    
    /**
     * @brief	Max price desired to pay
     */
    private float maxPrice;

    /**
     * @brief	Reference to Client
     */
    private ClientInterface refCli;

    /**
     * @brief	Name of the client
     */
    private String clientName;

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
                                ClientInterface _refCli,
                                String          _clientName) 
    {
        this.flightTicketTo 	= _flightTicketTo;
        this.flightTicketFrom 	= _flightTicketFrom;
        this.returnTicket       = _returnTicket;
        this.quantity 			= _quantity;
        this.maxPrice 			= _maxPrice;
        this.refCli 			= _refCli;
        this.clientName         = _clientName;
    }

    /**
     * @brief	Return if a ticket is a return ticket (not one-way)
     * 
     * @param	isReturn	: boolean - True if it is a return ticket
     */
    public boolean isReturnTicket() 
    {
        return this.returnTicket;
    }
    
    /**
     * @brief   Get the source of the ticket interest
     * 
     * @return  source flight
     */   
    public String getSource()
    {
        return this.flightTicketTo.getSource();
    }

    /**
     * @brief   Get the destination of the ticket interest
     * 
     * @return  city Name
     */   
    public String getDest()
    {
        return this.flightTicketTo.getDest();
    }

    /**
     * @brief   Get the source date from the source ticket
     * 
     * @return  source Date
     */ 
    public Date getSourceDate()
    {
        return this.flightTicketTo.getDate();
    }

    /**
     * @brief   Get the return date from the return ticket
     * 
     * @return  return Date
     */ 
    public Date getReturnDate()
    {
        return this.flightTicketFrom.getDate();
    }
    
    /**
     * @brief   Default getter
     * 
     * @return  quantity
     */ 
    public int getQuantity()
    {
        return this.quantity;
    }

    /**
     * @brief	Default getter
     * 
     * @return	maxPrice
     */
    public float getMaxPrice() 
    {
        return this.maxPrice;
    }

    /**
     * @brief 	Default getter
     * 
     * @return  reference of the Client
     */
    public ClientInterface getClientInterface() 
    {
        return this.refCli;
    }

    /**
     * @brief 	Default getter
     * 
     * @return  client Name
     */
    public String getClientName() 
    {
        return this.clientName;
    }
}
