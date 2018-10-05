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
import java.sql.SQLException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import Classes.Accommodation;
import Classes.AccommodationInterest;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketInterest;
import Classes.FlightTicketManager;
import Classes.PackageInterest;
import Classes.Packages;

@WebService
public interface TravelAgencyService
{
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
	 * @return	True in case of success
	 * 
	 * @throws 	RemoteException
	 */
	@WebMethod boolean insertHotelEntry(String 		_city,
									    String 		_hotel,
									    int			_quantity,
									    int			_maxGuestsPerRoom,
									    float		_price) throws RemoteException;
	
	/**
	 * @brief	
	 * 
	 * @param 	_source				:
	 * @param 	_dest				:
	 * @param 	_dateDay			:
	 * @param 	_dateMonth			:
	 * @param 	_dateYear			:
	 * @param 	_quantity			:
	 * @param 	_price				:
	 * 
	 * @return	True in case of success
	 * 
	 * @throws 	RemoteException
	 */
	@WebMethod boolean insertPassageEntry(String 		_source,
							   	          String 		_dest,
							   	          int			_dateDay,
							   	          int			_dateMonth,
							   	          int			_dateYear,
									      int			_quantity,
									      float			_price) throws RemoteException;
	
	/**
    * @brief	
    * 
    * @param 	_source		:
    * @param 	_dest		:
    * @param 	_dateDay	:
	* @param 	_dateMonth	:
	* @param 	_dateYear	:
    * 
    * @return
    * 
    * @throws 	RemoteException
    */
   @WebMethod FlightTicketManager searchPassages(String	_source, 
											     String	_dest, 
											     int	_dateDay,
									   	         int	_dateMonth,
									   	         int	_dateYear) throws RemoteException;
   
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
    * @param   flightTicketGoing    :
    * @param   flightTicketReturn   :
    * @param   accommodation        :
    * 
    * @return
    */
   @WebMethod List<Packages> searchPackages(FlightTicket	_flightTicketGoing, 
                                            FlightTicket    _flightTicketReturn, 
                                            Accommodation   _accommodation)      throws RemoteException;
   
   /**
    * @brief   
    * 
    * @param   _package 	: Package
    * 
    * @return result int 	: 1 - Success
    *						  2 - Not enough passages
    *						  3 - Not enough rooms
    *						  4 - Guests exceeded
    */
   @WebMethod int buyPackage(Packages	_package) throws RemoteException;
   
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
    * @param   _ticketTo       :
    * @param   _ticketFrom     :
    * @param   _accommodation  :
    * @param   _quantity       :
    * @param   _desiredPrice   :
    * @param   _numberOfGuests :
    * @param   _clientName     :
    */
   @WebMethod void registerPackageInterest(FlightTicket    _ticketTo,
           								   FlightTicket    _ticketFrom,
           								   Accommodation   _accommodation,
           								   int             _quantity,
           								   float           _desiredPrice,
           								   int 			 _numberOfGuests,
           								   String          _clientName) 		throws RemoteException;
   
   	/**
    * @brief	
    * 
    * @return	
    */
   @WebMethod List<FlightTicketInterest> getTicketInterestList();

   	/**
    * @brief	
    * 
    * @return	
    */
   @WebMethod List<AccommodationInterest> getAccommodationInterestList();

   /**
    * @brief	
    * 
    * @return	
    */
   @WebMethod List<PackageInterest> getPackageInterestList();
   
   	/**
    * @brief	
    * 
    * @param 	_flightTicket	:
    * 
    * @throws 	RemoteException
    */
   @WebMethod void notifyTicketsInterests(FlightTicket _flightTicket) throws RemoteException;
   
   /**
    * @brief	
    * 
    * @param 	_accommodation	:
    * 
    * @throws 	RemoteException
    */
   @WebMethod void notifyAccommodationInterests(Accommodation _accommodation) throws RemoteException;
   
   /**
    * @brief	
    * 
    * @param 	_flightTicket	:
    * 
    * @throws 	RemoteException
    */
   @WebMethod void notifyPackageInterests(FlightTicket _flightTicket) throws RemoteException;
   
   /**
    * @brief	
    * 
    * @param 	_accommodation	:
    * 
    * @throws 	RemoteException
    */
   @WebMethod void notifyPackageInterestsByAccommodation(Accommodation _accommodation) throws RemoteException;
   
   /**
	 * @brief 	Remove ticket interest from local array and from the server
	 * 
	 * @param	_ticketInterest	:
	 */
   @WebMethod void unregisterTicketInterestByFlightTicket(FlightTicketInterest _ticketInterest)  throws RemoteException;
   
   /**
    * @brief 	Remove accommodation interest from local array and from the server
    * 
    * @param	_accommodationInterest	:
    */
   @WebMethod void unregisterAccommodationInterest(AccommodationInterest _accommodationInterest)  throws RemoteException;
   
   /**
    * @brief 	Remove package interest from local array and from the server
    * 
    * @param	_packageInterest	:
    */
   @WebMethod void unregisterPackageInterest(PackageInterest _packageInterest) throws RemoteException;
}
