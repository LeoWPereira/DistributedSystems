/**
 ******************************************************************************
 * @file    ServerInterface.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package RMI;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;

import Classes.Accommodation;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketManager;
 

/**
 * @brief   
 */
public interface ServerInterface extends Remote
{
	/**
	 * @brief	
	 * 
	 * @param 	clientName
	 * @param 	refCli
	 */
 	public void call(String 			clientName,
 				  	 ClientInterface	refCli) throws RemoteException;
   
 	/**
 	 * @brief
 	 * 
 	 * @param 	_source	:
 	 * @param 	_dest	:
 	 * @param 	_date	:
 	 * 
 	 * @return
 	 */
    public FlightTicketManager searchPassages(String	_source,
    										  String 	_dest,
    										  Date 		_date) 		throws RemoteException;
    
    /**
     * @brief
     * 
     * @param 	_city	:
     * 
     * @return	
     */
    public AccommodationManager searchHotelByCity(String _city) throws RemoteException;
    
    /**
     * @brief
     * 
     * @param 	_hotel	:
     * 
     * @return	
     */
    public AccommodationManager searchHotelByName(String _hotel) throws RemoteException;
    
    /**
     * @brief	
     * 
     * @param	_ticket	:
     */
    public void buyPassage(FlightTicket	_ticket) throws RemoteException;
    
    /**
     * @brief	
     * 
     * @param	_hotel	:
     */
    public void reserveHotel(Accommodation	_hotel) throws RemoteException;
    
    /**
     * @brief	
     * 
     * @param 	_ticket			:
     * @param 	_until			:
     * @param 	_desiredPrice	:
     * @param 	_refCli			:
     */
    public void registerPassageInterest(FlightTicket	_ticket,
    									Date 			_until,
    									float 			_desiredPrice,
    									ClientInterface _refCli)		throws RemoteException;
    
    /**
     * @brief	
     * 
     * @param 	_hotel			:
     * @param 	_until			:
     * @param 	_checkin		:
     * @param 	_checkout		:
     * @param 	_desiredPrice	:
     * @param 	_refCli			:
     */
    public void registerHotelInterest(Accommodation		_hotel, 
    								  Date 				_until, 
    								  Date				_checkin, 
    								  Date				_checkout,
    								  float 			_desiredPrice,
    								  ClientInterface	_refCli) 		throws RemoteException;
}
