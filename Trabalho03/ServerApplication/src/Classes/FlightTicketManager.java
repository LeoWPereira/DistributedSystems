/**
 ******************************************************************************
 * @file    FlightTicket.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @brief	Class responsible for the flight ticket management
 */
public class FlightTicketManager implements Serializable 
{
    /**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = -9136165180232276846L;
	
	/**
	 * @brief	Array of Flight Tickets
	 */
	private ArrayList<FlightTicket> flightTicketList = new ArrayList<FlightTicket>();

    /** 
    * @brief	Get the flight ticket list
    * 
    * @return 	flight Ticket List
    *
    */
    public ArrayList<FlightTicket> getFlightTicketList()
    {
        return this.flightTicketList;
    }

    /** 
    * @brief	Insert a flight ticket into the database
    * 
    * @param 	flight Ticket
    */
    public void insertFlightTicket(FlightTicket flightTicket) 
    {
        this.flightTicketList.add(flightTicket);
    }
    
    /**
    * @brief	Default getter
    * 
    * @param 	index	: index of the array
    * 
    * @return 	flight Ticket
    */
    public FlightTicket getFlightTicket(int index)
    {
        return this.flightTicketList.get(index);
    }

    /** 
    * @brief	Remove a flight ticket from database
    * 
    * @param 	flightTicket	: Flight ticket to be removed
    */
    public void removeFlightTicket(FlightTicket flightTicket) 
    {
        this.flightTicketList.remove(flightTicket);
    }
    
    /** 
    * @brief	Remove a flight ticket from database by its index
    * 
    * @param 	index	: index of the array
    */
    public void removeFlightTicketByIndex(int index) 
    {
        this.flightTicketList.remove(index);
    }
    
    /**
    * @brief	Edit a price of a flight ticket by its index
    *
    * @param 	index	: index of the array
    * @param 	price	: new price value
    */
    public void editPrice(int index, float price)
    {
        (this.flightTicketList.get(index)).setPrice(price);
    }
    
    /**
    * @brief	Returns the flight ticket list size
    *
    * @return 	list Size
    */
    public int getFlightTicketListSize() 
    {
        return this.flightTicketList.size();
    }
}
