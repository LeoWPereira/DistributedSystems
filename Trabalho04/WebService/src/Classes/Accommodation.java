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
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = -5955676390015753503L;
	
	/**
	 * @brief	Name of the destination city
	 */
	public	String 	cityName;
    
	/**
	 * @brief	Name of the destination accommodation
	 */
	public	String 	accommodationName;
    
	/**
	 * @brief	Quantity of rooms left in the Accommodation
	 */
	public	int 	quantity;
	
	/**
	 * @brief	Max number of guests per room
	 */
	public 	int 	maxGuestsPerRoom;
	
	/**
	 * @brief	Price of the room
	 */
	public 	float 	price;
    
	/**
	 * @brief	Default constructor
	 * 
	 * @param 	_cityName 			: Name of the City
	 * @param 	_accommodationName 	: Name of the Hotel
	 * @param 	_quantity 			: Quantity of rooms left
	 * @param	_maxGuests			: Number of guests per room
	 * @param 	_price 				: Price of each room
	 */    
    public Accommodation(String 	_cityName, 
    					 String 	_accommodationName,
    					 int		_quantity,
    					 int		_maxGuests,
    					 float 		_price)
    {
        this.cityName 			= _cityName;
        this.accommodationName	= _accommodationName;
        this.quantity			= _quantity;
        this.maxGuestsPerRoom	= _maxGuests;
        this.price 				= _price;
    }
}