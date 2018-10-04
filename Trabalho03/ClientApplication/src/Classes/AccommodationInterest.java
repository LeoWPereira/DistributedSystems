/**
 ******************************************************************************
 * @file    AccommodationInterest.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.io.Serializable;

import RMI.ClientInterface;

/**
 * @brief   Class responsible for storing the accommodation interest info from the clients
 */
public class AccommodationInterest implements Serializable
{
    /**
     * @brief	Member containing an instance of Accommodation
     */
    private Accommodation  accommodation;
    
    /**
     * @brief	Quantity of room one is interested
     */
    private int quantity;
    
    /**
     * @brief	Number of guests interested (in this interest event)
     */
    private int numberOfGuests;
    
    /**
     * @brief	Max desired price to pay
     */
    private float maxPrice;

    /**
     * @brief	Reference to a Client
     */
    private ClientInterface refCli;

    /**
     * @brief	Client Name
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
                                 ClientInterface _refCli,
                                 String          _clientName) 
    {
        this.accommodation  =   _accommodation;
        this.quantity       =   _quantity;
        this.numberOfGuests =   _numberOfGuests;
        this.maxPrice       =   _maxPrice;
        this.refCli         =   _refCli;
        this.clientName     =   _clientName;
    }

    /**
     * @brief	Default getter
     * 
     * @param 	Accommodation Name
     */
    public String getAccommodationName() 
    {
        return this.accommodation.getAccommodationName();
    }
    
    /**
     * @brief   Default getter
     * 
     * @return  City Name
     */   
    public String getAccommodationCityName()
    {
        return this.accommodation.getCityName();
    }
    
    /**
     * @brief   Default getter
     * 
     * @return  Quantity
     */ 
    public int getQuantity()
    {
        return this.quantity;
    }
    
    /**
     * @brief   Default getter
     * 
     * @return  Number Of Guests
     */ 
    public int getNumberOfGuests()
    {
        return this.numberOfGuests;
    }

    /**
     * @brief 	Default getter
     * 
     * @return 	Max Price
     */
    public float getMaxPrice() 
    {
        return this.maxPrice;
    }

    /**
     * @brief 	Default getter
     * 
     * @return  Client reference
     */
    public ClientInterface getClientInterface() 
    {
        return this.refCli;
    }

    /**
     * @brief 	Default getter
     * 
     * @return	Client Name
     */
    public String getClientName() 
    {
        return this.clientName;
    }
}
