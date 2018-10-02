/**
 ******************************************************************************
 * @file    AccommodationManager.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    01 de out de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @brief	Class responsible for the accommodations management
 */
 public class AccommodationManager implements Serializable
 { 
	 /**
	 * @brief
	 */
	private static final long serialVersionUID = -6901199176974082169L;
	/**
	  * @brief
	  */
    public ArrayList<Accommodation> accommodationList = new ArrayList<Accommodation>();

    /** 
    * @brief	Insert an accommodation into the database
    * 
    * @param 	accommodation
    */
    public void insertAccommodation(Accommodation accommodation) 
    {
        accommodationList.add(accommodation);
    }

    
    /** 
    * @brief	Removes an accommodation from the database
    * 
    * @param 	accommodation
    */
    public void removeAccommodation(Accommodation accommodation) 
    {
        this.accommodationList.remove(accommodation);
    }

    /**
    * @brief	Get accommodation by its index
    * 
    * @param 	index
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
    * @param 	index
    */
    public void removeAccommodationByIndex(int index) 
    {
        this.accommodationList.remove(index);
    }
    
    /**
    * @brief	Set a price for an accommodation found by its index
    *
    * @param 	index
    * @param 	price
    */
    public void editPrice(int index, 
    					  float price)
    {
        (this.accommodationList.get(index)).price = (price);
    }
    
    /**
    * @brief	Get the accommodation list size
    *
    * @return 	sizeList
    */
    public int getAccommodationListSize() 
    {
        return this.accommodationList.size();
    }
 }
