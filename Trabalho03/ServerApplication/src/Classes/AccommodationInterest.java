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

/**
 * @brief   Class responsible for storing the accommodation interest info from the clients
 */
public class AccommodationInterest 
{
    /**
     * @brief
     */
    private Accomodation  accommodation;
    
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
    private String checkinDate;

    /**
     * @brief
     */
    private String checkoutDate;

    /**
     * @brief
     */
    private ClientInferface refCli;

    /**
     * @brief   Default constructor
     * 
     * @param   _accommodation      : Accomodation
     * @param   _quantity           : int
     * @param   _numberOfGuests     : int
     * @param   _maxPrice           : float
     * @param   _checkinDate        : String
     * @param   _checkoutDate       : String
     * @param   _refCli             : ClientInferface
     */   
    public AccommodationInterest(Accomodation   _accommodation, 
                                int             _quantity, 
                                int             _numberOfGuests, 
                                float           _maxPrice, 
                                String          _checkinDate, 
                                String          _checkoutDate, 
                                ClientInferface _refCli) 
    {
        this.accommodation = _accommodation;
        this.quantity = _quantity;
        this.numberOfGuests = _numberOfGuests;
        this.maxPrice = _maxPrice;
        this.checkinDate = preco;
        this.refCli = refCli;
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
        return accommodation.getAccommodationCityName();
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
    public int getMaxPrice() 
    {
        return maxPrice;
    }

    /**
     * @brief Default getter
     * 
     * @param checkinDate
     */
    public void getCheckinDate()
    {
        return checkinDate;
    }


    /**
     * @brief Default getter
     * 
     * @param checkoutDate
     */
    public void getCheckoutDate()
    {
        return checkoutDate;
    }

    /**
     * @brief Default getter
     * 
     * @return  refCli
     */
    public ClientInferface getClientInterface() 
    {
        return refCli;
    }
}
    
