/**
 ******************************************************************************
 * @file    Accommodation.java
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
 * @brief	Class responsible for storing the accommodation info
 */
public class Accommodation implements Serializable
{    
    /**
	 * @brief
	 */
	private static final long serialVersionUID = -5955676390015753503L;
	
	/**
	 * @brief
	 */
	public String 	cityName;
    
	/**
	 * @brief
	 */
	public String 	accommodationName;
    
	/**
	 * @brief
	 */
	public int 	quantity;
	
	/**
	 * @brief
	 */
	public int 	maxGuestsPerRoom;
	
	/**
	 * @brief
	 */
	public float 	price;
    
	/**
	 * @brief	Default constructor
	 * 
	 * @param 	_cityName 			: String
	 * @param 	_accommodationName 	: String
	 * @param 	_quantity 			: int
	 * @param 	_price 				: float
	 */    
    public Accommodation(String 	_cityName, 
    					 String 	_accommodationName,
    					 int		_quantity,
    					 int		_maxGuests,
    					 float 		_price)
    {
        cityName 			= _cityName;
        accommodationName	= _accommodationName;
        quantity			= _quantity;
        maxGuestsPerRoom	= _maxGuests;
        price 				= _price;
    }
}