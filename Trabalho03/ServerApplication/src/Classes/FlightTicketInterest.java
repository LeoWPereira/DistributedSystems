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

/**
 * @brief   Class responsible for storing the flight ticket interest info from the clients
 */
public class FlightTicketInterest 
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
    private int quantity;
    
    /**
     * @brief
     */
    private float maxPrice;

    /**
     * @brief
     */
    private ClientInferface refCli;

    /**
     * @brief   Default constructor
     * 
     * @param   _flightTicketTo     : FlightTicket
     * @param   _flightTicketFrom   : FlightTicket
     * @param   _quantity           : int
     * @param   _maxPrice           : float
     * @param   _refCli             : ClientInferface
     */   
    public FlightTicketInterest(FlightTicket    _flightTicketTo, 
                                FlightTicket    _flightTicketFrom, 
                                int             _quantity, 
                                float           _maxPrice, 
                                ClientInferface _refCli) 
    {
        this.flightTicketTo = _flightTicketTo;
        this.flightTicketFrom = _flightTicketFrom;
        this.quantity = _quantity;
        this.maxPrice = _maxPrice;
        this.refCli = refCli;
    }

    /**
     * @brief Return if the flight ticket interest has a return ticket
     * 
     * @param isReturn : boolean - True if it is a return ticket
     */
    public boolean interestIsReturnTicket() 
    {
        boolean isReturn = false;

        if(flightTicketFrom != null)
        {
            isReturn = true;
        }

        return isReturn;
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
    public float getSourceDate()
    {
        return flightTicketTo.getDate();
    }

    /**
     * @brief   Get the return date from the return ticket
     * 
     * @return  returnDate
     */ 
    public float getReturnDate()
    {
        return flightTicketFrom.getDate();
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
    public int getMaxPrice() 
    {
        return maxPrice;
    }

    /**
     * @brief Default getter
     * 
     * @return  refCli
     */
    public ClientInferface getClientInterface() 
    {
        return refCli;
    }
}
    
