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

import java.io.Serializable;

/**
 * Class responsible for storing the flight ticket info
 */
public class FlightTicket implements Serializable
{
    private String date;
    private String origin;
    private String destination;
    private float price;

    /**
     * Default Constructor
     * 
     * @param origin
     * @param destination
     * @param date
     * @param price
     */   
    public FlightTicket(String origin, String destination, String date, float price)
    {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.price = price;
    }
    
    /**
     * Default setter.
     * 
     * @param price
     */
     public void setPrice(float price) 
     {
        this.price = price;
    }
    
     /**
      * Default getter
      * @return date
      */
    public String getDate()
    {
        return this.date;
    }
    
    /**
     * Default getter
     * @return origin
     */
    public String getOrigin()
    {
        return this.origin;
    }
    
    /**
     * Default getter
     * @return destination
     */
    public String getDestination()
    {
        return this.destination;
    }
    
    /**
     * Default getter
     * @return price
     */
    public float getPrice()
    {
        return price;
    }
}
