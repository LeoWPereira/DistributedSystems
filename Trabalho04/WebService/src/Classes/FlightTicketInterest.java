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
     * @brief	Source city
     */
    public String citySource;

    /**
     * @brief	Destination city
     */
    public String  cityDest;

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
     * @brief
     */
    public int goingDay;
    
    /**
     * @brief
     */
    public int goingMonth;
    
    /**
     * @brief
     */
    public int goingYear;
    
    /**
     * @brief
     */
    public int returnDay;
    
    /**
     * @brief
     */
    public int returnMonth;
    
    /**
     * @brief
     */
    public int returnYear;

    /**
     * @brief   Default constructor
     * 
     * @param   _citySource     	: String
     * @param   _cityDest   		: String
     * @param   _quantity           : int
     * @param   _maxPrice           : float
     */   
    public FlightTicketInterest(String    		_citySource, 
    							String    		_cityDest, 
    							int    			_goingDay,
                                int    			_goingMonth,
                                int    			_goingYear,
                                boolean         _returnTicket,
                                int    			_returnDay,
                                int    			_returnMonth,
                                int    			_returnYear,
                                int             _quantity, 
                                float           _maxPrice) 
    {
        this.citySource 		= _citySource;
        this.cityDest 			= _cityDest;
        this.returnTicket       = _returnTicket;
        this.goingDay 			= _goingDay;
        this.goingMonth 		= _goingMonth;
        this.goingYear       	= _goingYear;
        this.returnDay 			= _returnDay;
        this.returnMonth 		= _returnMonth;
        this.returnYear       	= _returnYear;
        this.quantity 			= _quantity;
        this.maxPrice 			= _maxPrice;
    }
    
    /**
     * @brief   Get the source of the ticket interest
     * 
     * @return  source flight
     */   
    public String getSource()
    {
        return this.citySource;
    }

    /**
     * @brief   Get the destination of the ticket interest
     * 
     * @return  city Name
     */   
    public String getDest()
    {
        return this.cityDest;
    }

    /**
     * @brief   Get the source date from the source ticket
     * 
     * @return  source Date
     */ 
    public Date getSourceDate()
    {
    	Calendar calendar = Calendar.getInstance();
	    
		calendar.set(goingYear,
					 goingMonth - 1,
					 goingDay);
		
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
	    
		calendar.set(returnYear,
					 returnMonth - 1,
					 returnDay);
		
		return new java.sql.Date(calendar.getTime().getTime());
    }
}
