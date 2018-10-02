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
import java.util.ArrayList;

import Classes.Accommodation;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketManager;
import Classes.Packages;

/**
 * @brief   
 */
public interface ServerInterface  extends Remote
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
     * 
     * @return
     */
    public boolean buyPassage(FlightTicket	_ticket) throws RemoteException;
    
    /**
     * @brief	
     * 
     * @param	_hotel	:
     * 
     * @return
     */
    public boolean reserveHotel(Accommodation	_hotel) throws RemoteException;

    /**
     * @brief
     * 
     * @param   flightTicketGoing    :
     * @param   flightTicketReturn   :
     * @param   accommodation        :
     * 
     * @return
     */
    public ArrayList<Packages> searchPackages(FlightTicket    flightTicketGoing, 
                                             FlightTicket    flightTicketReturn, 
                                             Accommodation   accommodation)      throws RemoteException;

    /**
     * @brief   
     * 
     * @param   _package :
     * 
     * @return
     */
    public int buyPackage(Packages  _package) throws RemoteException;
    
    /**
     * @brief	
     * 
     * @param 	_ticket			:
     * @param   _ticketFrom     :
     * @param 	_quantity		:
     * @param 	_desiredPrice	:
     * @param 	_refCli			:
     */
    public void registerPassageInterest(FlightTicket	_ticketTo,
                                        FlightTicket    _ticketFrom,
    									int 			_quantity,
    									float 			_desiredPrice,
    									ClientInterface _refCli,
                                        String          _clientName)		throws RemoteException;
    
    /**
     * @brief	
     * 
     * @param 	_hotel			:
     * @param 	_quantity		:
     * @param 	_numberOfGuests	:
     * @param 	_desiredPrice	:
     * @param 	_refCli			:
     * @param   _clientName     :
     */
    public void registerHotelInterest(Accommodation		_hotel, 
    								  int               _quantity,
                                      int               _numberOfGuests,
    								  float 			_desiredPrice,
    								  ClientInterface	_refCli,
                                      String            _clientName) 		throws RemoteException;
}
