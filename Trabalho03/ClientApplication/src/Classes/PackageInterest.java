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
import java.util.Date;

import RMI.ClientInterface;

/**
 * @brief   Class responsible for storing the package interest info from the clients
 */
public class PackageInterest 
{
    /**
     * @brief
     */
    private FlightTicket  flightTicketTo;

    /**
     * @brief
     */
    private FlightTicket  flightTicketFrom;

    /**
     * @brief
     */
    private boolean returnTicket;
    
    /**
     * @brief
     */
    private int quantity;
    
    /**
     * @brief
     */
    private float maxPrice;

    /**
     * @brief
     */
    private ClientInterface refCli;

    /**
     * @brief
     */
    private Accommodation accommodation;

    /**
     * @brief
     */
    private int numberOfGuests;
    
    /**
     * @brief
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
        return flightTicketTo.getSource();
    }

    /**
     * @brief   Get the destination of the ticket interest
     * 
     * @return  cityName
     */   
    public String getDest()
    {
        return flightTicketTo.getDest();
    }

    /**
     * @brief   Get the source date from the source ticket
     * 
     * @return  sourceDate
     */ 
    public Date getSourceDate()
    {
        return flightTicketTo.getDate();
    }

    /**
     * @brief   Get the return date from the return ticket
     * 
     * @return  returnDate
     */ 
    public Date getReturnDate()
    {
        return flightTicketFrom.getDate();
    }

    /**
     * @brief   Get the accommodation name from the accommodation
     * 
     * @return  accommodationName
     */ 
    public String getAccommodationName()
    {
        return accommodation.getAccommodationName();
    }

    /**
     * @brief   Get the accommodation city name from the accommodation
     * 
     * @return  accommodationCityName
     */ 
    public String getAccommodationCityName()
    {
        return accommodation.getCityName();
    }
    
    /**
     * @brief   Default getter
     * 
     * @return  quantity
     */ 
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * @brief Default getter
     * 
     * @return maxPrice
     */
    public float getMaxPrice() 
    {
        return maxPrice;
    }

    /**
     * @brief Default getter
     * 
     * @return numberOfGuests
     */
    public int getNumberOfGuests() 
    {
        return numberOfGuests;
    }

    /**
     * @brief Default getter
     * 
     * @return  refCli
     */
    public ClientInterface getClientInterface() 
    {
        return refCli;
    }

    /**
     * @brief Default getter
     * 
     * @return  clientName
     */
    public String getClientName() 
    {
        return clientName;
    }
}
    
