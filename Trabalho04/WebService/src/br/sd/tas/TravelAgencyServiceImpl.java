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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jws.WebService;

import java.sql.Connection;
import java.sql.Statement;

import Classes.Accommodation;
import Classes.AccommodationInterest;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketInterest;
import Classes.FlightTicketManager;
import Classes.PackageInterest;
import Classes.Packages;
import Database.DBConnection;
import Database.Controller.CtrlHotel;
import Database.Controller.CtrlPassages;

@WebService(endpointInterface = "br.sd.tas.TravelAgencyService")
public class TravelAgencyServiceImpl implements TravelAgencyService
{
	private CtrlPassages ctrlPassages;
	
	private CtrlHotel ctrlHotel;

	private List<FlightTicketInterest> listTicketInterest;

	private List<AccommodationInterest> listAccommodationInterest;
	
	private List<PackageInterest> listPackageInterest;
	
	private static Connection	dbConnection;
	
	private static Statement	dbStatement;
	
	public TravelAgencyServiceImpl()
	{
		ctrlPassages				= new CtrlPassages();
		
		ctrlHotel					= new CtrlHotel();

		listTicketInterest 			= new ArrayList<FlightTicketInterest>();
		
        listAccommodationInterest	= new ArrayList<AccommodationInterest>();
        
        listPackageInterest 		= new ArrayList<PackageInterest>();
        
        try
        {
			dbStatement 			= DBConnection.configureDatabase(dbConnection);
		}
        catch (SQLException e)
        {
			e.printStackTrace();
		}
	}
	
	@Override
	public AccommodationManager loadDBHotels() throws RemoteException
	{
		AccommodationManager list = new AccommodationManager();
		
		try
		{
			list = ctrlHotel.loadDBHotels(dbStatement);
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
			list = ctrlPassages.loadDBPassages(dbStatement);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public boolean insertHotelEntry(String 		_city,
								    String 		_hotel,
								    int			_quantity,
								    int			_maxGuestsPerRoom,
								    float		_price) throws RemoteException
	{
		boolean success;
		
		try
		{
			ctrlHotel.insertEntry(dbStatement,
								  _city, 
								  _hotel, 
								  _quantity, 
								  _maxGuestsPerRoom, 
								  _price);
			
			success = true;
		}
		catch(SQLException e)
		{
			success = false;
		}
		
		return success;
	}
	
	@Override
	public boolean insertPassageEntry(String 		_source,
							          String 		_dest,
							          int			_dateDay,
						   	          int			_dateMonth,
						   	          int			_dateYear,
								      int			_quantity,
								      float			_price) throws RemoteException
	{
		boolean success;
		
		Calendar calendar = Calendar.getInstance();
	    
		calendar.set(_dateYear,
	    			 _dateMonth,
	    			 _dateDay);
	    
		try
		{
			ctrlPassages.insertEntry(dbStatement,
									 _source,
									 _dest, 
									 new java.sql.Date(calendar.getTime().getTime()), 
									 _quantity, 
									 _price);
			
			success = true;
		}
		catch(SQLException e)
		{
			success = false;
		}
		
		return success;
	}
	
	@Override
	public FlightTicketManager searchPassages(String	_source,
											  String 	_dest,
											  int		_dateDay,
									   	      int		_dateMonth,
									   	      int		_dateYear) throws RemoteException
	{
		FlightTicketManager list = new FlightTicketManager();
		
		Calendar calendar = Calendar.getInstance();
	    
		calendar.set(_dateYear,
	    			 _dateMonth - 1,
	    			 _dateDay);
		
		try
		{
			list = ctrlPassages.searchPassages(dbStatement,
											   _source, 
											   _dest, 
											   new java.sql.Date(calendar.getTime().getTime()));
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
			list = ctrlHotel.searchHotelByCity(dbStatement,
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
			list = ctrlHotel.searchHotelByHotel(dbStatement,
												_hotel);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public boolean buyPassage(String _source,
						      String _dest,
						      int    _day,
						      int 	 _month,
						      int 	 _year,
						      int 	 _quantity,
						      float  _price) throws RemoteException
	{
		boolean returnValue = false;
		
		Calendar calendar = Calendar.getInstance();
	    
		calendar.set(_year,
					 _month - 1,
	    			 _day);
	    
		try 
		{
			int ticketsLeft = ctrlPassages.getQuantityLeft(dbStatement,
														   _source,
														   _dest,
														   new java.sql.Date(calendar.getTime().getTime()),
														   _price);

			if(ticketsLeft >= _quantity)
			{
				ctrlPassages.updateQuantity(dbStatement,
											_source,
											_dest,
											new java.sql.Date(calendar.getTime().getTime()),
										    _price,
										    ticketsLeft - _quantity);
				
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
	public boolean reserveHotel(String _cityName,
								String _accommodationName,
								int    _quantity,
								float  _price) throws RemoteException
	{
		boolean returnValue = false;
	    
		try 
		{
			int roomsLeft = ctrlHotel.getQuantityLeft(dbStatement,
													  _cityName,
													  _accommodationName,
													  _price);

			if(roomsLeft >= _quantity)
			{
				ctrlHotel.updateQuantity(dbStatement,
										 _cityName,
										 _accommodationName,
										 _price,
										 roomsLeft - _quantity);
				
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
	public List<FlightTicketInterest> getTicketInterestList()
	{
		return this.listTicketInterest;
	}
	
	@Override
	public List<AccommodationInterest> getAccommodationInterestList()
	{
		return this.listAccommodationInterest;
	}
	
	@Override
	public List<PackageInterest> getPackageInterestList()
	{
		return this.listPackageInterest;
	}

	@Override
	public List<Packages> searchPackages(String _source,
									     String _dest,
									     int    _goingDay,
									     int 	_goingMonth,
									     int 	_goingYear,
									     boolean _isReturn,
									     int    _returnDay,
									     int 	_returnMonth,
									     int 	_returnYear) throws RemoteException
	{
		List<Packages> 			list 				= new ArrayList<Packages>();
    	FlightTicketManager 	listTicketGoing 	= new FlightTicketManager();
    	FlightTicketManager 	listTicketReturn 	= new FlightTicketManager();
    	AccommodationManager 	listAccommodation 	= new AccommodationManager();

    	FlightTicket 			flightTicketGoingFound;
    	FlightTicket 			flightTicketReturnFound;
    	Accommodation 			accommodationFound = null;
    	Packages 				pack;
		
		// First, search for the going ticket
		try 
		{		
			Calendar calendar = Calendar.getInstance();
		    
			calendar.set(_goingYear,
					     _goingMonth - 1,
					     _goingDay);
			
			listTicketGoing = ctrlPassages.searchPassages(dbStatement,
														  _source, 
														  _dest, 
														  new java.sql.Date(calendar.getTime().getTime()));
			
			// Verifies if there is a return ticket
			if(_isReturn)
			{
				calendar.set(_returnYear,
							 _returnMonth - 1,
							 _returnDay);
				
				// search for the return ticket					
				listTicketReturn = ctrlPassages.searchPassages(dbStatement,
															   _dest, 
															   _source, 
															   new java.sql.Date(calendar.getTime().getTime()));
			}
			
			// search for accommodation by city name
			listAccommodation = ctrlHotel.searchHotelByCity(dbStatement,
															_dest);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		// Fills a package for each combination of going ticket, return ticket and accommodation
		for(int i = 0; i < listTicketGoing.flightTicketList.size(); i++)
		{
			flightTicketGoingFound = listTicketGoing.getFlightTicket(i);
			
			for(int j = 0; j < listAccommodation.accommodationList.size(); j++)
			{
				accommodationFound = listAccommodation.getAccommodation(j);

				// If there is a return ticket
				if(_isReturn)
				{
					for(int k = 0; k < listTicketReturn.flightTicketList.size(); k++)
					{
						flightTicketReturnFound = listTicketReturn.getFlightTicket(k);

						pack = new Packages(flightTicketGoingFound,
										    flightTicketReturnFound,
										    accommodationFound);

						list.add(pack);
					}
				}
				else
				{
					flightTicketReturnFound = null;
					
					pack = new Packages(flightTicketGoingFound,
										flightTicketReturnFound,
										accommodationFound);

					list.add(pack);
				}
			}
		}
		
		return list;
	}

	@Override
	public int buyPackage(String  _source,
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
					      int 	  _quantity) throws RemoteException
	{
		int 	result 				= 0;
    	int 	goingTicketsLeft 	= 0;
    	int 	returnTicketsLeft 	= 0;
    	int 	roomsLeft 			= 0;
    	
    	boolean returnTicketAvailability 	= true;

    	try
		{
    		Calendar calendar = Calendar.getInstance();
		    
			calendar.set(_goingYear,
					     _goingMonth - 1,
					     _goingDay);
			
    		goingTicketsLeft = ctrlPassages.getQuantityLeft(dbStatement,
    														_source,
    														_dest,
    														new java.sql.Date(calendar.getTime().getTime()),
    														_goingTicketPrice);

			if(_isReturn)
			{
				calendar.set(_returnYear,
							 _returnMonth - 1,
							 _returnDay);
				
				returnTicketsLeft = ctrlPassages.getQuantityLeft(dbStatement,
																 _dest,
																 _source,
																 new java.sql.Date(calendar.getTime().getTime()),
															     _returnTicketPrice);
				
				if(returnTicketsLeft <= _quantity)
				{
					returnTicketAvailability = false;
				}
			}

			roomsLeft = ctrlHotel.getQuantityLeft(dbStatement,
												  _dest,
												  _hotelName,
												  _hotelPrice);

			// checks if there are enough going tickets
			if((goingTicketsLeft >= _quantity) &&
			   (returnTicketAvailability))
			{
				// checks if there are enough rooms left
				if(roomsLeft >= _quantity)
				{
					calendar.set(_goingYear,
						     _goingMonth - 1,
						     _goingDay);
					
					// Finally, buy the package and update database
					ctrlPassages.updateQuantity(dbStatement,
										    	_source,
										    	_dest,
										    	new java.sql.Date(calendar.getTime().getTime()),
										    	_goingTicketPrice,
										    	goingTicketsLeft - _quantity);
					
					if(_isReturn)
					{
						ctrlPassages.updateQuantity(dbStatement,
													_dest,
											    	_source,
											    	new java.sql.Date(calendar.getTime().getTime()),
											    	_returnTicketPrice,
											    	returnTicketsLeft - _quantity);
					}

					ctrlHotel.updateQuantity(dbStatement,
											 _dest,
										 	 _hotelName,
										 	 _hotelPrice,
										 	 roomsLeft - _quantity);

					// Succeded
					result = 1;
				}
				else
				{
					// Not enough rooms
					result = 3;
				}
			}
			else
			{
				// Not enough passages
				result = 2;
			}			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

    	return result;
	}

	@Override
	public void registerPackageInterest(FlightTicket	_ticketTo,
										FlightTicket 	_ticketFrom, 
										Accommodation 	_accommodation,
										int 			_quantity, 
										float 			_desiredPrice, 
										int 			_numberOfGuests, 
										String 			_clientName) throws RemoteException 
	{
	
	}

	@Override
	public void notifyTicketsInterests(FlightTicket flightTicket) throws RemoteException
	{
		
	}
	
	@Override
	public void notifyAccommodationInterests(Accommodation _accommodation) throws RemoteException 
	{
		
	}

	@Override
	public void notifyPackageInterests(FlightTicket _flightTicket) throws RemoteException
	{
		
	}

	@Override
	public void notifyPackageInterestsByAccommodation(Accommodation _accommodation) throws RemoteException
	{
		
	}

	@Override
	public void unregisterTicketInterestByFlightTicket(FlightTicketInterest _ticketInterest) throws RemoteException 
	{
		
	}

	@Override
	public void unregisterAccommodationInterest(AccommodationInterest _accommodationInterest) throws RemoteException 
	{
		
	}

	@Override
	public void unregisterPackageInterest(PackageInterest _packageInterest) throws RemoteException 
	{
		
	}
}