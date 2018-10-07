/**
 ******************************************************************************
 * @file    PackageInterest.java
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
 * @brief   Class responsible for storing the package interest info from the clients
 */
public class PackageInterest implements Serializable
{
    /**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = 8486142313026956054L;
    
    /**
     * @brief	Desired quantity
     */
    public int quantity;
    
    /**
     * @brief	Max price to pay
     */
    public float maxPrice;

    /**
     * @brief	Instance of Accommodation
     */
    public Accommodation accommodation;

    /**
     * @brief	Number of Guests
     */
    public int numberOfGuests;
    
    /**
     * @brief	Name of the client
     */
    public String citySource;
    
    /**
     * @brief	
     */
    public String cityDest;
    
    /**
     * @brief	
     */
    public int goingDay;
    /**
     * @brief	
     */
    public int goingMonth;
    
    /**
     * @brief	
     */
    public int goingYear;
    
    /**
     * @brief	
     */
    public boolean isReturn;
    
    /**
     * @brief	
     */
    public int returnDay;
    
    /**
     * @brief	
     */
    public int returnMonth;
    
    /**
     * @brief	
     */
    public int returnYear;

    /**
     * @brief   Default constructor
     * 
     * @param   _citySource
	 * @param   _cityDest
	 * @param   _goingDay
	 * @param   _goingMonth
	 * @param   _goingYear
	 * @param   _isReturn
	 * @param   _returnDay
	 * @param   _returnMonth
	 * @param   _returnYear
     * @param   _returnTicket
     * @param   _quantity           : int
     * @param   _maxPrice           : float
     * @param   _numberOfGuests     : int
     */   
    public PackageInterest(String 			_citySource,
				           String 			_cityDest,
				           int 				_goingDay,
				           int 				_goingMonth,
				           int 				_goingYear,
				           boolean 			_isReturn,
				           int 				_returnDay,
				           int 				_returnMonth,
				           int 				_returnYear,
                           int             _quantity, 
                           float           _maxPrice, 
                           int             _numberOfGuests) 
    {
        this.citySource 	= _citySource;
        this.cityDest 		= _cityDest;
        this.goingDay       = _goingDay;
        this.goingMonth 	= _goingMonth;
        this.goingYear 		= _goingYear;
        this.isReturn       = _isReturn;
        this.returnDay 		= _returnDay;
        this.returnMonth 	= _returnMonth;
        this.returnYear     = _returnYear;
        
        this.quantity 			= _quantity;
        this.maxPrice 			= _maxPrice;
        this.numberOfGuests     = _numberOfGuests;
    }
}
