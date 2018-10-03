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
	private String 	cityName;
    
	/**
	 * @brief	Name of the destination accommodation
	 */
	private String 	accommodationName;
    
	/**
	 * @brief	Quantity of rooms left in the Accommodation
	 */
	private int 	quantity;
	
	/**
	 * @brief	Max number of guests per room
	 */
	private int 	maxGuestsPerRoom;
	
	/**
	 * @brief	Price of the room
	 */
	private float 	price;
    
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
        cityName 			= _cityName;
        accommodationName	= _accommodationName;
        quantity			= _quantity;
        maxGuestsPerRoom	= _maxGuests;
        price 				= _price;
    }
    
    /**
     * @brief	Default setter
     * 
     * @param 	_price	: float
     */
    public void setPrice(float _price) 
    {
        this.price = _price;
    }
    
    /**
     * @brief	Default getter
     * 
     * @return 	String containing the City Name
     */   
    public String getCityName()
    {
        return cityName;
    }
    
    /**
     * @brief	Default getter
     * 
     * @return 	String containing the Accommodation Name
     */ 
    public String getAccommodationName()
    {
        return accommodationName;
    }

    /**
     * @brief	Default setter
     * 
     * @return 	_accommodationName	: String containing the accommodation name
     */ 
    public void setAccommodationName(String _accommodationName)
    {
        this.accommodationName = _accommodationName;
    }

    /**
     * @brief	Default setter
     * 
     * @return 	_cityName	:	String containing the City name
     */   
    public void setCityName(String _cityName)
    {
        this.cityName = _cityName;
    }
    
    /**
     * @brief	Default getter
     * 
     * @return 	Float value containing the price for each room
     */ 
    public float getPrice()
    {
        return price;
    }

	/**
	 * @brief	Default getter
	 * 
	 * @return 	the quantity of available rooms
	 */
	public int getQuantity() 
	{
		return quantity;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	_quantity	: the quantity of rooms left
	 */
	public void setQuantity(int _quantity)
	{
		this.quantity = _quantity;
	}

	/**
	 * @brief	Default getter
	 * 
	 * @return	the maximum number of Guests Per Room
	 */
	public int getMaxGuestsPerRoom() 
	{
		return maxGuestsPerRoom;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	_maxGuestsPerRoom	: the maxGuestsPerRoom to set
	 */
	public void setMaxGuestsPerRoom(int _maxGuestsPerRoom) 
	{
		this.maxGuestsPerRoom = _maxGuestsPerRoom;
	}
}