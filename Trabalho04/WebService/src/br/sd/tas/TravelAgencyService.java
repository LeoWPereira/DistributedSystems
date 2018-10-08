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

import Classes.AccommodationInterest;
import Classes.AccommodationManager;
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
    * @param 	_source
	* @param 	_dest
	* @param 	_day
	* @param 	_month
	* @param 	_year
	* @param 	_quantity
	* @param 	_price
    * 
    * @return
    * 
    * @throws 	RemoteException
    */
   @WebMethod boolean buyPassage(String _source,
							     String _dest,
							     int    _day,
							     int 	 _month,
							     int 	 _year,
							     int 	 _quantity,
							     float  _price) throws RemoteException;

   	/**
    * @brief	
    * 
    * @param 	_cityName
	* @param 	_accommodationName
	* @param 	_quantity
	* @param 	_price
    * 
    * @return
    * 
    * @throws 	RemoteException
    */
   @WebMethod boolean reserveHotel(String _cityName,
								   String _accommodationName,
								   int    _quantity,
								   float  _price) throws RemoteException;

   /**
    * @brief
    * 
    * @param   _source        :
    * @param   _dest   		  :
    * @param   _goingDay      :
    * @param   _goingMonth    :
    * @param   _goingYear     :
    * @param   _isReturn      :
    * @param   _returnDay     :
    * @param   _returnMonth   :
    * @param   _returnYear    :
    * 
    * @return
    */
   @WebMethod List<Packages> searchPackages(String _source,
										     String _dest,
										     int    _goingDay,
										     int 	_goingMonth,
										     int 	_goingYear,
										     boolean _isReturn,
										     int    _returnDay,
										     int 	_returnMonth,
										     int 	_returnYear) throws RemoteException;
   
   /**
    * @brief   
    * 
    * @param   _source,
	* @param   _dest,
	* @param   _hotelName,
	* @param   _goingDay,
	* @param   _goingMonth,
	* @param   _goingYear,
	* @param   _isReturn,
	* @param   _returnDay,
	* @param   _returnMonth,
	* @param   _returnYear,
	* @param   _goingTicketPrice,
	* @param   _returnTicketPrice,
	* @param   _hotelPrice,
	* @param   _quantity,
    * 
    * @return result int 	: 1 - Success
    *						  2 - Not enough passages
    *						  3 - Not enough rooms
    */
   @WebMethod public int buyPackage(String  _source,
								    String  _dest,
								    String  _hotelName,
								    int     _goingDay,
								    int 	  _goingMonth,
								    int 	  _goingYear,
								    boolean _isReturn,
								    int     _returnDay,
								    int 	  _returnMonth,
								    int 	  _returnYear,
								    float	  _goingTicketPrice,
								    float	  _returnTicketPrice,
								    float   _hotelPrice,
								    int 	  _quantity) throws RemoteException;
   
   	/**
    * @brief	
    * 
    * @param 	_citySource
	* @param 	_cityDest
	* @param 	_goingDay
	* @param 	_goingMonth
	* @param 	_goingYear
	* @param 	_returnTicket
	* @param 	_returnDay
	* @param 	_returnMonth
	* @param 	_returnYear
    * @param 	_quantity		:
    * @param 	_desiredPrice	:
    * 
    * @throws 	RemoteException
    */
   @WebMethod  String registerPassageInterest(String    		_citySource, 
											  String    		_cityDest, 
											  int    			_goingDay,
								              int    			_goingMonth,
								              int    			_goingYear,
								              boolean         	_returnTicket,
								              int    			_returnDay,
								              int    			_returnMonth,
								              int    			_returnYear,
											  int 				_quantity,
											  float 			_desiredPrice) throws RemoteException;
   
   /**
    * @brief	
    * 
    * @param 	_cityName		:
    * @param 	_quantity		:
    * @param 	_numberOfGuests	:
    * @param 	_desiredPrice	:
    * 
    * @throws 	RemoteException
    */
   @WebMethod String registerHotelInterestByCity(String		_cityName, 
									     	     int      	_quantity,
									     	     int      	_numberOfGuests,
									     	     float 		_desiredPrice) 		throws RemoteException;
   
   /**
    * @brief	
    * 
    * @param 	_hotelName		:
    * @param 	_quantity		:
    * @param 	_numberOfGuests	:
    * @param 	_desiredPrice	:
    * 
    * @throws 	RemoteException
    */
   @WebMethod String registerHotelInterestByHotel(String	_hotelName, 
									     	      int       _quantity,
									     	      int       _numberOfGuests,
									     	      float 	_desiredPrice) 		throws RemoteException;

   /**
    * @brief   
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
    * @param   _quantity       :
    * @param   _desiredPrice   :
    * @param   _numberOfGuests :
    * @param   _clientName     :
    */
   @WebMethod String registerPackageInterest(String 	_citySource,
								             String 	_cityDest,
								             int 		_goingDay,
								             int 		_goingMonth,
								             int 		_goingYear,
								             boolean	_isReturn,
								             int 		_returnDay,
								             int 		_returnMonth,
								             int 		_returnYear,
										     int 		_quantity, 
										     float 		_desiredPrice, 
										     int 		_numberOfGuests) throws RemoteException ;
   
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
}
