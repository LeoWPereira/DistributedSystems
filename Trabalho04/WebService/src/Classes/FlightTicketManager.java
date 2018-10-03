/**
 ******************************************************************************
 * @file    FlightTicket.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    01 de out de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief	Class responsible for the flight ticket management
 */
public class FlightTicketManager implements Serializable 
{
    /**
	 * @brief
	 */
	private static final long serialVersionUID = -9136165180232276846L;
	
	/**
	 * @brief
	 */
	public List<FlightTicket> flightTicketList = new ArrayList<FlightTicket>();

    /** 
    * @brief	Get the flight ticket list
    * 
    * @return 	flightTicketList
    *
    */
    public List<FlightTicket> getFlightTicketList()
    {
        return this.flightTicketList;
    }

    /** 
    * @brief	Insert a flight ticket into the database
    * 
    * @param 	flightTicket
    */
    public void insertFlightTicket(FlightTicket flightTicket) 
    {
        flightTicketList.add(flightTicket);
    }
    
    /**
    * @brief	Default getter
    * 
    * @param 	index	:
    * 
    * @return 	flightTicket
    */
    public FlightTicket getFlightTicket(int index)
    {
        return this.flightTicketList.get(index);
    }

    /** 
    * @brief	Remove a flight ticket from database
    * 
    * @param 	flightTicket	:
    */
    public void removeFlightTicket(FlightTicket flightTicket) 
    {
        this.flightTicketList.remove(flightTicket);
    }
    
    /** 
    * @brief	Remove a flight ticket from database by its index
    * 
    * @param 	index	:
    */
    public void removeFlightTicketByIndex(int index) 
    {
        this.flightTicketList.remove(index);
    }
    
    /**
    * @brief	Edit a price of a flight ticket by its index
    *
    * @param 	index	:
    * @param 	price	:
    */
    public void editPrice(int index, float price)
    {
        (this.flightTicketList.get(index)).price = price;
    }
    
    /**
    * @brief	Returns the flight ticket list size
    *
    * @return 	listSize	:
    */
    public int getFlightTicketListSize() 
    {
        return this.flightTicketList.size();
    }
}
