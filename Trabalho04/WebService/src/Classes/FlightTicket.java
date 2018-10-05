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
import java.util.Calendar;

/**
 * Class responsible for storing the flight ticket info
 */
public class FlightTicket implements Serializable
{
    /**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = 3281860207276377509L;
	
	/**
	 * @brief	Source City
	 */
	public String 		source;
	
	/**
	 * @brief	Destination City
	 */
	public String 		dest;
	
	/**
	 * @brief
	 */
	public int 		dateDay;
	
	/**
	 * @brief
	 */
	public int 		dateMonth;
	
	/**
	 * @brief
	 */
	public int 		dateYear;
	
	/**
	 * @brief	Quantity of passages Left
	 */
	public int			quantity;
	
	/**
	 * @brief	Price of the passage
	 */
	public float		price;

    /**
     * @brief	Default Constructor
     * 
     * @param 	_origin			: Source City
     * @param 	_destination	: Destination City
     * @param 	_dateDay		: Date day of the passage
     * @param 	_dateMonth		: Date month of the passage
     * @param 	_dateYear		: Date year of the passage
     * @param 	_qnt			: Quantity of available passages
     * @param 	_price			: Price of the passage
     */   
    public FlightTicket(String 	_origin, 
    					String 	_destination,
    					int 	_dateDay,
    					int		_dateMonth,
    					int		_dateYear,
    					int 	_qnt,
    					float 	_price)
    {
    	this.source 	= _origin;
    	this.dest		= _destination;
    	this.dateDay	= _dateDay;
    	this.dateMonth	= _dateMonth;
    	this.dateYear	= _dateYear;
    	this.quantity	= _qnt;
    	this.price		= _price;
    }
    
    /**
     * @brief
     * 
     * @return
     */
    public java.sql.Date getSqlDate()
    {
    	Calendar calendar = Calendar.getInstance();
	    
		calendar.set(this.dateYear,
					 this.dateMonth,
					 this.dateDay);
		
		return new java.sql.Date(calendar.getTime().getTime());
    }
}
