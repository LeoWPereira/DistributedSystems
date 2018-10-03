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

import java.util.Date;

import RMI.ClientInterface;

/**
 * @brief   Class responsible for storing the package interest info from the clients
 */
public class PackageInterest implements Serializable
{
    /**
     * @brief	Instance of Flight Ticket
     */
    private FlightTicket  flightTicketTo;

    /**
     * @brief	Instance of Flight Ticket
     */
    private FlightTicket  flightTicketFrom;

    /**
     * @brief	The flight we are interested into is a return ticket?
     */
    private boolean returnTicket;
    
    /**
     * @brief	Desired quantity
     */
    private int quantity;
    
    /**
     * @brief	Max price to pay
     */
    private float maxPrice;

    /**
     * @brief	Reference to Client
     */
    private ClientInterface refCli;

    /**
     * @brief	Instance of Accommodation
     */
    private Accommodation accommodation;

    /**
     * @brief	Number of Guests
     */
    private int numberOfGuests;
    
    /**
     * @brief	Name of the client
     */
    private String clientName;

    /**
     * @brief   Default constructor
     * 
     * @param   _flightTicketTo     : FlightTicket
     * @param   _flightTicketFrom   : FlightTicket
     * @param   _accommodation      : Accommodation
     * @param   _quantity           : int
     * @param   _maxPrice           : float
     * @param   _numberOfGuests     : int
     * @param   _refCli             : ClientInterface
     * @param   _clientName         : String
     */   
    public PackageInterest(FlightTicket    _flightTicketTo, 
                           FlightTicket    _flightTicketFrom, 
                           Accommodation   _accommodation,
                           boolean         _returnTicket,
                           int             _quantity, 
                           float           _maxPrice, 
                           int             _numberOfGuests,
                           ClientInterface _refCli,
                           String          _clientName) 
    {
        this.flightTicketTo 	= _flightTicketTo;
        this.flightTicketFrom 	= _flightTicketFrom;
        this.accommodation      = _accommodation;
        this.returnTicket       = _returnTicket;
        this.quantity 			= _quantity;
        this.maxPrice 			= _maxPrice;
        this.numberOfGuests     = _numberOfGuests;
        this.refCli 			= _refCli;
        this.clientName         = _clientName;
    }

    /**
     * @brief	Return if a ticket is a return ticket (not one-way)
     * 
     * @param 	boolean	: True if it is a return ticket
     */
    public boolean isReturnTicket() 
    {
        return this.returnTicket;
    }
    
    /**
     * @brief   Get the source of the ticket interest
     * 
     * @return  source flight ticket
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
     * @brief   Get the accommodation name from the accommodation
     * 
     * @return  accommodation Name
     */ 
    public String getAccommodationName()
    {
        return this.accommodation.getAccommodationName();
    }

    /**
     * @brief   Get the accommodation city name from the accommodation
     * 
     * @return  accommodation City Name
     */ 
    public String getAccommodationCityName()
    {
        return this.accommodation.getCityName();
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
     * @brief 	Default getter
     * 
     * @return 	max Price
     */
    public float getMaxPrice() 
    {
        return this.maxPrice;
    }

    /**
     * @brief 	Default getter
     * 
     * @return 	number Of Guests
     */
    public int getNumberOfGuests() 
    {
        return this.numberOfGuests;
    }

    /**
     * @brief 	Default getter
     * 
     * @return  reference of the client
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
