/**
 ******************************************************************************
 * @file    TravelAgencyServiceImpl.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    01 de out de 2018
 * @brief
 ******************************************************************************
 */

package br.sd.tas;

import java.rmi.RemoteException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.jws.WebService;

import java.sql.Connection;
import java.sql.Statement;

import Classes.Accommodation;
import Classes.AccommodationInterest;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketInterest;
import Classes.FlightTicketManager;
import Database.DBConnection;
import Database.Controller.CtrlHotel;
import Database.Controller.CtrlPassages;

@WebService(endpointInterface = "br.sd.tas.TravelAgencyService")
public class TravelAgencyServiceImpl implements TravelAgencyService
{
	/**
	 * @brief
	 */
	private CtrlPassages ctrlPassages;
	
	/**
	 * @brief
	 */
	private CtrlHotel ctrlHotel;

	/**
	 * @brief
	 */
	private ArrayList<FlightTicketInterest> listTicketInterest;

	/**
	 * @brief
	 */
	private ArrayList<AccommodationInterest> listAccommodationInterest;
	
	/**
	 * @brief	Member holding every info about the connection to the DB
	 */
	private static Connection	dbConnection;
	
	public TravelAgencyServiceImpl()
	{
		ctrlPassages				= new CtrlPassages();
		
		ctrlHotel					= new CtrlHotel();

		listTicketInterest 			= new ArrayList<FlightTicketInterest>();
		
        listAccommodationInterest	= new ArrayList<AccommodationInterest>();
	}
	
	@Override
	public String hello(String	_txt)
	{
		return _txt;
	}
	
	@Override
	public AccommodationManager loadDBHotels() throws RemoteException
	{
		AccommodationManager list = new AccommodationManager();
		
		try
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			list = ctrlHotel.loadDBHotels(_stm);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public FlightTicketManager loadDBPassages() throws RemoteException
	{
		FlightTicketManager list = new FlightTicketManager();
		
		try
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			list = ctrlPassages.loadDBPassages(_stm);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public void insertHotelEntry(String 		_city,
								 String 		_hotel,
								 int			_quantity,
								 int			_maxGuestsPerRoom,
								 float			_price) throws RemoteException
	{
		try
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			ctrlHotel.insertEntry(_stm,
								  _city, 
								  _hotel, 
								  _quantity, 
								  _maxGuestsPerRoom, 
								  _price);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertPassageEntry(String 		_source,
							       String 		_dest,
								   Date			_date,
								   int			_quantity,
								   float		_price) throws RemoteException
	{
		try
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			ctrlPassages.insertEntry(_stm,
									 _source,
									 _dest, 
									 _date, 
									 _quantity, 
									 _price);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public FlightTicketManager searchPassages(String	_source,
											  String 	_dest,
											  Date 		_date) throws RemoteException
	{
		FlightTicketManager list = new FlightTicketManager();
		
		try 
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			list = ctrlPassages.searchPassages(_stm,
											   _source, 
											   _dest, 
											   _date);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public AccommodationManager searchHotelByCity(String _city) throws RemoteException
	{
		AccommodationManager list = new AccommodationManager();
		
		try 
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			list = ctrlHotel.searchHotelByCity(_stm,
											   _city);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public AccommodationManager searchHotelByName(String _hotel) throws RemoteException
	{
		AccommodationManager list = new AccommodationManager();
		
		try 
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			list = ctrlHotel.searchHotelByHotel(_stm,
												_hotel);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public boolean buyPassage(FlightTicket _ticket) throws RemoteException
	{
		boolean returnValue = false;
	    
		try 
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			int ticketsLeft = ctrlPassages.getQuantityLeft(_stm,
														   _ticket.source,
														   _ticket.dest,
														   new java.sql.Date(_ticket.date.getTime()),
														   _ticket.price);

			if(ticketsLeft >= _ticket.quantity)
			{
				ctrlPassages.updateQuantity(_stm,
										    _ticket.source,
										    _ticket.dest,
										    new java.sql.Date(_ticket.date.getTime()),
										    _ticket.price,
										    ticketsLeft - _ticket.quantity);
				
				returnValue = true;
			}
			else
			{
				returnValue = false;
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return returnValue;
	}
	
	@Override
	public boolean reserveHotel(Accommodation _hotel) throws RemoteException
	{
		boolean returnValue = false;
	    
		try 
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			int roomsLeft = ctrlHotel.getQuantityLeft(_stm,
													  _hotel.cityName,
													  _hotel.accommodationName,
													  _hotel.price);

			if(roomsLeft >= _hotel.quantity)
			{
				ctrlHotel.updateQuantity(_stm,
										 _hotel.cityName,
										 _hotel.accommodationName,
										 _hotel.price,
										 roomsLeft - _hotel.quantity);
				
				returnValue = true;
			}
			else
			{
				returnValue = false;
			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return returnValue;
	}
	
	@Override
	public void registerPassageInterest(FlightTicket	_ticketTo,
										FlightTicket 	_ticketFrom,
										int 			_quantity,
										float 			_desiredPrice, 
										String 			_clientName) throws RemoteException
	{
		boolean _isReturnTicket = true;

		if(_ticketFrom == null)
		{
			_isReturnTicket = false;
		}

        FlightTicketInterest ticketInterest = new FlightTicketInterest(_ticketTo,
        															   _ticketFrom, 
        															   _isReturnTicket,
        															   _quantity, 
        															   _desiredPrice,
        															   _clientName);

        this.listTicketInterest.add(ticketInterest);
	}
	
	@Override
	public void registerHotelInterest(Accommodation	_hotel, 
									  int 			_quantity,
									  int 			_numberOfGuests,
									  float 		_desiredPrice,
									  String 		_clientName) throws RemoteException
	{
		AccommodationInterest accommodationInterest = new AccommodationInterest(_hotel,
																			    _quantity, 
																			    _numberOfGuests,
																			    _desiredPrice,
																			    _clientName);

		this.listAccommodationInterest.add(accommodationInterest);
	}
	
	@Override
	public ArrayList<FlightTicketInterest> getTicketInterestList()
	{
		return listTicketInterest;
	}
	
	@Override
	public ArrayList<AccommodationInterest> getAccommodationInterestList()
	{
		return listAccommodationInterest;
	}
	
	@Override
	public void notifyTicketsInterests(FlightTicket flightTicket) throws RemoteException
	{
		// TODO Auto-generated method stub
	}
}