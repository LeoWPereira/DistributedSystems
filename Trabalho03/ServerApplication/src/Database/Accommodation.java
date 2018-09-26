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

package Database;

import java.io.Serializable;

/**
 * Class responsible for storing the accommodation info
 */
public class Accommodation implements Serializable
{    
    private String cityName;
    private String accommodationName;
    private float price;
    
/**
 * Default constructor
 * 
 * @param cityName String
 * @param accommodationName String
 * @param price float
 */    
    public Accommodation(String cityName, String accommodationName, float price)
    {
        this.cityName = cityName;
        this.accommodationName = accommodationName;
        this.price = price;
    }
    
    /**
     * Default setter
     * 
     * @param price float
     */
    public void setPrice(float price) 
    {
        this.price = price;
    }
    
    /**
     * Default getter
     * @return cityName
     * 
     */   
    public String getCityName()
    {
        return this.cityName;
    }
    
    /**
     * Default getter
     * @return accommodationName
     * 
     */ 
    public String getAccommodationName()
    {
        return this.accommodationName;
    }
    
    /**
     * Default getter
     * @return price
     * 
     */ 
    public float getPrice()
    {
        return this.price;
    }
    
   
}