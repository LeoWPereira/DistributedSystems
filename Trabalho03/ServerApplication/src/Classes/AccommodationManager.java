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

import java.util.ArrayList;
import java.lang.String;

/**
 * Class responsible for the accomodations management
 */
 public class AccommodationManager 
 { 
    private static ArrayList<Accommodation> accommodationList = new ArrayList<Accommodation>();

    /** 
    * Get the accommodation list
    * @return accommodationList
    */
    public ArrayList<Accommodation> getAccommodationList()
    {
        return this.accommodationList;
    }

    /** 
    * Insert an accommodation into the database
    * @param accommodation
    */
    public void insertAccommodation(Accommodation accommodation) 
    {
        accommodationList.add(accommodation);
        System.out.println("A hospedagem " + accommodation.getAccommodationName() + " na cidade "+ accommodation.getCityName()+" foi inserida com sucesso!");
    }

    
    /** 
    * Removes an accommodation from the database
    * @param accommodation
    */
    public void removeAccommodation(Accommodation accommodation) 
    {
        this.accommodationList.remove(accommodation);
        System.out.println("A hospedagem " + accommodation.getAccommodationName() + " na cidade "+ accommodation.getCityName()+" foi removida com sucesso!");

    }

    /**
    * Get accommodation by its index
    * @param index
    * @return Accommodation
    */
    public Accommodation getAccommodation(int index)
    {
        return this.accommodationList.get(index);
    }
    
     /** 
        * Remove an accommodation by its index
        * @param index
        */
    public void removeAccommodationByIndex(int index) 
    {
        this.accommodationList.remove(index);
        System.out.println("Hospedagem de índice "+ index + " removida com sucesso!");

        return;
    }
    
    /**
    * Set a price for an accommodation found by its index
    *
    * @param index
    * @param price
    */
    public void editPrice(int index, float price)
    {
        (this.accommodationList.get(index)).setPrice(price);
    }
    
    /**
    *   Get the accommodation list size
    *@return sizeList
    */
    public int getAccommodationListSize() 
    {
        return this.accommodationList.size();
    }
    
    /** 
    * Search for an accommodation by a city's name
    * @param cityName
    * @return accommodationByCityName
    *
    */
    public ArrayList<Accommodation> searchAccommodationsByCityName(String cityName)
    {
        ArrayList<Accommodation> accommodationByCityName = new ArrayList<Accommodation>();
        Accommodation accommodation;

        for (int i = 0; i< this.accommodationList.size(); i++)
        {
            accommodation = accommodationList.get(i);
            if((accommodation.getCityName().compareToIgnoreCase(cityName) == 0))
            {
                accommodationByCityName.add(accommodation);
            }
        }

        return accommodationByCityName;
    }

    /** 
    * Search for an accommodation by its name
    * @param accommodationName
    * @return accommodationByName
    *
    */
    public ArrayList<Accommodation> procuraHospedagem(String accommodationName)
    {
        ArrayList<Accommodation> accommodationByName = new ArrayList<Accommodation>();
        Accommodation accommodation;
        
        for (int i = 0; i< this.accommodationList.size();i++)
        {
            accommodation = accommodationList.get(i);
            if((accommodation.getAccommodationName().compareToIgnoreCase(accommodationName) == 0))
            {
                accommodationByName.add(accommodation);
            }
        }

        return accommodationByName;
    }
    
 }
