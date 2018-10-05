/**
 ******************************************************************************
 * @file    AccommodationManager.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief	Class responsible for the accommodations management
 */
 public class AccommodationManager implements Serializable
 { 
	 /**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = -6901199176974082169L;

	/**
	  * @brief	Array of Accommodation
	  */
    public List<Accommodation> accommodationList = new ArrayList<Accommodation>();

    /** 
    * @brief	Insert an accommodation into the database
    * 
    * @param 	accommodation	:	accommodation instance to be added into array
    */
    public void insertAccommodation(Accommodation accommodation) 
    {
        this.accommodationList.add(accommodation);
    }
    
    /** 
    * @brief	Removes an accommodation from the database
    * 
    * @param 	accommodation	: accommodation instance to be removed from array
    */
    public void removeAccommodation(Accommodation accommodation) 
    {
        this.accommodationList.remove(accommodation);
    }

    /**
    * @brief	Get accommodation by its index
    * 
    * @param 	index	:	index of the array
    * 
    * @return 	Accommodation
    */
    public Accommodation getAccommodation(int index)
    {
        return this.accommodationList.get(index);
    }
    
    /** 
    * @brief	Remove an accommodation by its index
    * 
    * @param 	index	: index of the array
    */
    public void removeAccommodationByIndex(int index) 
    {
        this.accommodationList.remove(index);
    }
    
    /**
    * @brief	Set a price for an accommodation found by its index
    *
    * @param 	_index	:	index of the array
    * @param 	_price	:	new price value
    */
    public void editPrice(int 	_index,
    					  float	_price)
    {
        this.accommodationList.get(_index).price = _price;
    }
 }
