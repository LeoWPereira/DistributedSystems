/**
 ******************************************************************************
 * @file    AccommodationInterest.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    01 de out de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

/**
 * @brief   Class responsible for storing the accommodation interest info from the clients
 */
public class AccommodationInterest 
{
    /**
     * @brief
     */
    private Accommodation  accommodation;
    
    /**
     * @brief
     */
    private int quantity;
    
    /**
     * @brief
     */
    private int numberOfGuests;
    
    /**
     * @brief
     */
    private float maxPrice;

    /**
     * @brief
     */
    private String clientName;

    /**
     * @brief   Default constructor
     * 
     * @param   _accommodation      : Accomodation
     * @param   _quantity           : int
     * @param   _numberOfGuests     : int
     * @param   _maxPrice           : float
     * @param   _refCli             : ClientInterface
     * @param   _clientName         : String
     */   
    public AccommodationInterest(Accommodation   _accommodation, 
                                 int             _quantity, 
                                 int             _numberOfGuests, 
                                 float           _maxPrice,
                                 String          _clientName) 
    {
        this.accommodation  =   _accommodation;
        this.quantity       =   _quantity;
        this.numberOfGuests =   _numberOfGuests;
        this.maxPrice       =   _maxPrice;
        this.clientName     =   _clientName;
    }

    /**
     * @brief Default getter
     * 
     * @param accommodationName
     */
    public String getAccommodationName() 
    {
        return accommodation.getAccommodationName();
    }
    
    /**
     * @brief   Default getter
     * 
     * @return  cityName
     */   
    public String getAccommodationCityName()
    {
        return accommodation.getCityName();
    }
    
    /**
     * @brief   Default getter
     * 
     * @return  quantity
     */ 
    public int getQuantity()
    {
        return quantity;
    }
    
    /**
     * @brief   Default getter
     * 
     * @return  numberOfGuests
     */ 
    public float getNumberOfGuests()
    {
        return numberOfGuests;
    }

    /**
     * @brief Default getter
     * 
     * @return maxPrice
     */
    public float getMaxPrice() 
    {
        return maxPrice;
    }

    /**
     * @brief Default getter
     * 
     * @return  clientName
     */
    public String getClientName() 
    {
        return clientName;
    }
}
    
