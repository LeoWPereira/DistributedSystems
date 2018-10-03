/**
 ******************************************************************************
 * @file    FlightTicket.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package Classes;

import java.io.Serializable;
import java.util.Date;

/**
 * Class responsible for storing the flight ticket info
 */
public class FlightTicket implements Serializable
{
    /**
	 * @brief	Unique Version ID from Class
	 */
	private static final long serialVersionUID = 3281860207276377509L;
	
	/**
	 * @brief	Source City
	 */
	private String 		source;
	
	/**
	 * @brief	Destination City
	 */
	private String 		dest;
	
	/**
	 * @brief	Date of the FLight
	 */
	private Date		date;
	
	/**
	 * @brief	Quantity of passages Left
	 */
	private int			quantity;
	
	/**
	 * @brief	Price of the passage
	 */
	private float		price;

    /**
     * @brief	Default Constructor
     * 
     * @param 	_origin			: Source City
     * @param 	_destination	: Destination City
     * @param 	_date			: Date of the passage
     * @param 	_qnt			: Quantity of available passages
     * @param 	_price			: Price of the passage
     */   
    public FlightTicket(String 	_origin, 
    					String 	_destination, 
    					Date 	_date, 
    					int 	_qnt,
    					float 	_price)
    {
        setSource(_origin);
        setDest(_destination);
        setDate(_date);
        setQuantity(_qnt);
        setPrice(_price);
    }

	/**
	 * @brief	Default getter
	 * 
	 * @return 	the source city
	 */
	public String getSource() 
	{
		return this.source;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	source	: the source city
	 */
	public void setSource(String source) 
	{
		this.source = source;
	}

	/**
	 * @brief	Default getter
	 * 
	 * @return 	the destination city
	 */
	public String getDest() 
	{
		return this.dest;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	dest	: the destination city
	 */
	public void setDest(String dest) 
	{
		this.dest = dest;
	}

	/**
	 * @brief	Default getter
	 * 
	 * @return 	the passage date
	 */
	public Date getDate() 
	{
		return date;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	date	: the date of the passage
	 */
	public void setDate(Date date) 
	{
		this.date = date;
	}

	/**
	 * @brief	Default getter
	 * 
	 * @return 	the quantity of passages left
	 */
	public int getQuantity() 
	{
		return this.quantity;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	quantity	: the quantity of passages left
	 */
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}

	/**
	 * @brief	Default getter
	 * 
	 * @return 	the price of the passage
	 */
	public float getPrice() 
	{
		return this.price;
	}

	/**
	 * @brief	Default setter
	 * 
	 * @param 	price	: the price of the passage
	 */
	public void setPrice(float price) 
	{
		this.price = price;
	}
}
