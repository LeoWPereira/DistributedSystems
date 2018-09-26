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

package Database;

import java.util.ArrayList;
import java.lang.String;


/**
 * Class responsible for the flight ticket management
 */
public class FlightTicketManager 
{
    private static ArrayList<FlightTicket> flightTicketList = new ArrayList<FlightTicket>();

    /** 
    * Get the flight ticket list
    * @return flightTicketList
    *
    */
    public ArrayList<FlightTicket> getFlightTicketList()
    {
        return this.flightTicketList;
    }

    /** 
    * Insert a flight ticket into the database
    * @param flightTicket
    */
    public void insertFlightTicket(FlightTicket flightTicket) 
    {
        flightTicketList.add(flightTicket);
        System.out.println("A passagem de origem "+ flightTicket.getOrigin()+" e "+ flightTicket.getDestination() + " foi inserida com sucesso!");
    }
    

    /**
    * Default getter
    * @param index
    * @return flightTicket
    */
    public FlightTicket getFlightTicket(int index)
    {
        return this.flightTicketList.get(index);
    }

    /** 
        * Remove a flight ticket from database
        * @param flightTicket
        */
    public void removeFlightTicket(FlightTicket flightTicket) 
    {
        this.flightTicketList.remove(flightTicket);
        System.out.println("A passagem de origem "+ flightTicket.getOrigin()+" e "+ flightTicket.getDestination() + " foi removida com sucesso!");

    }
    
    /** 
        * Remove a flight ticket from database by its index
        * @param index
        */
    public void removeFlightTicketByIndex(int index) 
    {
        this.flightTicketList.remove(index);
        System.out.println("A passagem de  "+ index + " foi removida com sucesso!");

    }
    
    /**
    * Edit a price of a flight ticket by its index
    *
    * @param index
    * @param price
    */
    public void editPrice(int index, float price)
    {
        (this.flightTicketList.get(index)).setPrice(price);
    }
    
    /**
    * Returns the flight ticket list size
    *@return listSize
    */
    public int getFlightTicketListSize() 
    {
        return this.flightTicketList.size();
    }
    
    /**
    * Returns a flight ticket list by its origin, destination and date
    *
    * @param origin
    * @param destination
    * @param date
    * @return customList
    *
    */
    public ArrayList<FlightTicket> procuraPassagemParaConsulta(String origin, String destination, String date) {
        ArrayList<FlightTicket> customList = new ArrayList<FlightTicket>();
        FlightTicket flightTicket;

        for (int i = 0; i < this.flightTicketList.size(); i++) 
        {
            flightTicket = flightTicketList.get(i);
            if ((flightTicket.getOrigin().compareToIgnoreCase(origin) == 0) && flightTicket.getDestination().compareToIgnoreCase(destination) == 0 
                && flightTicket.getDate().compareToIgnoreCase(date) == 0) 
            {
                customList.add(flightTicket);
            }
        }

        return customList;
    }   
}

