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
	 * @brief
	 */
	private static final long serialVersionUID = 3281860207276377509L;
	
	/**
	 * @brief
	 */
	private String 		source;
	
	/**
	 * @brief
	 */
	private String 		dest;
	
	/**
	 * @brief
	 */
	private Date		date;
	
	/**
	 * @brief
	 */
	private int			quantity;
	
	/**
	 * @brief
	 */
	private float		price;

    /**
     * @brief	Default Constructor
     * 
     * @param 	_origin			:
     * @param 	_destination	:
     * @param 	_date			:
     * @param 	_qnt			:
     * @param 	_price			:
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
	 * @brief
	 * 
	 * @return the source
	 */
	public String getSource() 
	{
		return source;
	}

	/**
	 * @brief
	 * 
	 * @param source the source to set
	 */
	public void setSource(String source) 
	{
		this.source = source;
	}

	/**
	 * @brief
	 * 
	 * @return the dest
	 */
	public String getDest() 
	{
		return dest;
	}

	/**
	 * @brief
	 * 
	 * @param dest the dest to set
	 */
	public void setDest(String dest) 
	{
		this.dest = dest;
	}

	/**
	 * @brief
	 * 
	 * @return the date
	 */
	public Date getDate() 
	{
		return date;
	}

	/**
	 * @brief
	 * 
	 * @param date the date to set
	 */
	public void setDate(Date date) 
	{
		this.date = date;
	}

	/**
	 * @brief
	 * 
	 * @return the quantity
	 */
	public int getQuantity() 
	{
		return quantity;
	}

	/**
	 * @brief
	 * 
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}

	/**
	 * @brief
	 * 
	 * @return the price
	 */
	public float getPrice() 
	{
		return price;
	}

	/**
	 * @brief
	 * 
	 * @param price the price to set
	 */
	public void setPrice(float price) 
	{
		this.price = price;
	}
}
