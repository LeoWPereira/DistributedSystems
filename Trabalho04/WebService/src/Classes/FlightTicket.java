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
import java.util.Date;

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
	 * @brief	Date of the FLight
	 */
	public Date		date;
	
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
     * @param 	_date			: Date of the passage
     * @param 	_qnt			: Quantity of available passages
     * @param 	_price			: Price of the passage
     */   
    public FlightTicket(String 	_origin, 
    					String 	_destination, 
    					Date 	_date, 
    					int 	_qnt,
    					float 	_price)
    {
    	this.source 	= _origin;
    	this.dest		= _destination;
    	this.date		= _date;
    	this.quantity	= _qnt;
    	this.price		= _price;
    }
}
