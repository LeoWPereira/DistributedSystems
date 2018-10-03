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
    public Accommodation	accommodation;
    
    /**
     * @brief
     */
    public int 				quantity;
    
    /**
     * @brief
     */
    public int 				numberOfGuests;
    
    /**
     * @brief
     */
    public float 			maxPrice;

    /**
     * @brief
     */
    public String 			clientName;

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
        return accommodation.accommodationName;
    }
    
    /**
     * @brief   Default getter
     * 
     * @return  cityName
     */   
    public String getAccommodationCityName()
    {
        return accommodation.cityName;
    }
}
    
