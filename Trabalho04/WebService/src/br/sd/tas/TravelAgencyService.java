/**
 ******************************************************************************
 * @file    TravelAgencyService.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    01 de out de 2018
 * @brief	https://www.javaworld.com/article/3209654/java-language/web-services-in-java-se-part-1-tools-overview.html
 * 			https://www.javaworld.com/article/3215966/java-language/web-services-in-java-se-part-2-creating-soap-web-services.html
 * 			https://blogs.mulesoft.com/dev/howto/wrap-soap-web-service-around-a-database/
 ******************************************************************************
 */

package br.sd.tas;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

import Classes.Accommodation;
import Classes.AccommodationInterest;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketInterest;
import Classes.FlightTicketManager;

@WebService
public interface TravelAgencyService
{
	@WebMethod String hello(String	_txt);
	
	/**
	 * @brief	
	 * 
	 * @return	
	 * 
	 * @throws 	SQLException
	 */
	@WebMethod AccommodationManager loadDBHotels() throws RemoteException;
	
	/**
	 * @brief	
	 * 
	 * @return	
	 * 
	 * @throws 	SQLException
	 */
	@WebMethod FlightTicketManager loadDBPassages() throws RemoteException;
	
	/**
	 * @brief	
	 * 
	 * @param 	_city				:
	 * @param 	_hotel				:
	 * @param 	_quantity			:
	 * @param 	_maxGuestsPerRoom	:
	 * @param 	_price				:
	 * 
	 * @throws 	RemoteException
	 */
	@WebMethod void insertHotelEntry(String 		_city,
									 String 		_hotel,
									 int			_quantity,
									 int			_maxGuestsPerRoom,
									 float			_price) throws RemoteException;
	
	/**
	 * @brief	
	 * 
	 * @param 	_source				:
	 * @param 	_dest				:
	 * @param 	_date				:
	 * @param 	_quantity			:
	 * @param 	_price				:
	 * 
	 * @throws 	RemoteException
	 */
	@WebMethod void insertPassageEntry(String 		_source,
								       String 		_dest,
									   Date			_date,
									   int			_quantity,
									   float		_price) throws RemoteException;
	
	/**
    * @brief	
    * 
    * @param 	_source	:
    * @param 	_dest	:
    * @param 	_date	:
    * 
    * @return
    * 
    * @throws 	RemoteException
    */
   @WebMethod FlightTicketManager searchPassages(String	_source, 
											     String	_dest, 
											     Date 	_date) throws RemoteException;
   
   	/**
    * @brief	
    * 
    * @param 	_city	:
    * 
    * @return
    * 
    * @throws 	RemoteException
    */
   @WebMethod AccommodationManager searchHotelByCity(String _city) throws RemoteException;

   	/**
    * @brief	
    * 
    * @param 	_hotel	:
    * 
    * @return
    * 
    * @throws 	RemoteException
    */
   @WebMethod AccommodationManager searchHotelByName(String _hotel) throws RemoteException;

   	/**
    * @brief	
    * 
    * @param 	_ticket	:
    * 
    * @return
    * 
    * @throws 	RemoteException
    */
   @WebMethod boolean buyPassage(FlightTicket _ticket) throws RemoteException;

   	/**
    * @brief	
    * 
    * @param 	_hotel	:
    * 
    * @return
    * 
    * @throws 	RemoteException
    */
   @WebMethod boolean reserveHotel(Accommodation _hotel) throws RemoteException;

   	/**
    * @brief	
    * 
    * @param 	_ticketTo		:
    * @param 	_ticketFrom		:
    * @param 	_quantity		:
    * @param 	_desiredPrice	:
    * @param 	_clientName		:
    * 
    * @throws 	RemoteException
    */
   @WebMethod  void registerPassageInterest(FlightTicket    _ticketTo,
									        FlightTicket    _ticketFrom,
									        int             _quantity,
									        float           _desiredPrice,
									        String          _clientName) 		throws RemoteException;

   	/**
    * @brief	
    * 
    * @param 	_hotel			:
    * @param 	_quantity		:
    * @param 	_numberOfGuests	:
    * @param 	_desiredPrice	:
    * @param 	_clientName		:
    * 
    * @throws 	RemoteException
    */
   @WebMethod void registerHotelInterest(Accommodation		_hotel, 
									     int               	_quantity,
									     int              	_numberOfGuests,
									     float 				_desiredPrice,
									     String            	_clientName) 		throws RemoteException;

   	/**
    * @brief	
    * 
    * @return	
    */
   @WebMethod public ArrayList<FlightTicketInterest> getTicketInterestList();

   	/**
    * @brief	
    * 
    * @return	
    */
   @WebMethod  ArrayList<AccommodationInterest> getAccommodationInterestList();

   	/**
    * @brief	
    * 
    * @param 	_flightTicket	:
    * 
    * @throws 	RemoteException
    */
   @WebMethod void notifyTicketsInterests(FlightTicket _flightTicket) throws RemoteException;
}
