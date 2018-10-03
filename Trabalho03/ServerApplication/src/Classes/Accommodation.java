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
	private String 	cityName;
    
	/**
	 * @brief
	 */
	private String 	accommodationName;
    
	/**
	 * @brief
	 */
	private int 	quantity;
	
	/**
	 * @brief
	 */
	private int 	maxGuestsPerRoom;
	
	/**
	 * @brief
	 */
	private float 	price;
    
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
    
    /**
     * @brief Default setter
     * 
     * @param _price	: float
     */
    public void setPrice(float _price) 
    {
        price = _price;
    }
    
    /**
     * @brief	Default getter
     * 
     * @return 	cityName
     */   
    public String getCityName()
    {
        return cityName;
    }
    
    /**
     * @brief	Default getter
     * 
     * @return 	accommodationName
     */ 
    public String getAccommodationName()
    {
        return accommodationName;
    }
    
    /**
     * @brief	Default getter
     * 
     * @return 	price
     */ 
    public float getPrice()
    {
        return price;
    }

	/**
	 * @brief
	 * 
	 * @return the quantity
	 */
	public int getQuantity() 
	{
		return quantity;
	}

	/**
	 * @brief
	 * 
	 * @param _quantity	: the quantity to set
	 */
	public void setQuantity(int _quantity)
	{
		this.quantity = _quantity;
	}

	/**
	 * @brief
	 * 
	 * @return	the maxGuestsPerRoom
	 */
	public int getMaxGuestsPerRoom() 
	{
		return maxGuestsPerRoom;
	}

	/**
	 * @brief
	 * 
	 * @param 	_maxGuestsPerRoom	: the maxGuestsPerRoom to set
	 */
	public void setMaxGuestsPerRoom(int _maxGuestsPerRoom) 
	{
		maxGuestsPerRoom = _maxGuestsPerRoom;
	}
    
   
}