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
	 * @brief
	 */
	private static final long serialVersionUID = 3281860207276377509L;
	
	/**
	 * @brief
	 */
	public String 			source;
	
	/**
	 * @brief
	 */
	public String 			dest;
	
	/**
	 * @brief
	 */
	public java.util.Date	date;
	
	/**
	 * @brief
	 */
	public int				quantity;
	
	/**
	 * @brief
	 */
	public float			price;

    /**
     * @brief	Default Constructor
     * 
     * @param 	_origin			:
     * @param 	_destination	:
     * @param 	_date			:
     * @param 	_qnt			:
     * @param 	_price			:
     */   
    public FlightTicket(String 	_origin, 
    					String 	_destination, 
    					Date 	_date, 
    					int 	_qnt,
    					float 	_price)
    {
        source 		= _origin;
        dest 		= _destination;
        date 		= _date;
        quantity	= _qnt;
        price 		= _price;
    }
}
