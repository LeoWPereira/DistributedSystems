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
import java.util.List;

import javax.jws.WebService;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Date;

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
								      Date			_date,
								      int			_quantity,
								      float			_price) throws RemoteException
	{
		boolean success;
		
		try
		{
			ctrlPassages.insertEntry(dbStatement,
									 _source,
									 _dest, 
									 _date, 
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
											  Date 		_date) throws RemoteException
	{
		FlightTicketManager list = new FlightTicketManager();
		
		try 
		{
			list = ctrlPassages.searchPassages(dbStatement,
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
	public boolean buyPassage(FlightTicket _ticket) throws RemoteException
	{
		boolean returnValue = false;
	    
		try 
		{
			int ticketsLeft = ctrlPassages.getQuantityLeft(dbStatement,
														   _ticket.source,
														   _ticket.dest,
														   new java.sql.Date(_ticket.date.getTime()),
														   _ticket.price);

			if(ticketsLeft >= _ticket.quantity)
			{
				ctrlPassages.updateQuantity(dbStatement,
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
			int roomsLeft = ctrlHotel.getQuantityLeft(dbStatement,
													  _hotel.cityName,
													  _hotel.accommodationName,
													  _hotel.price);

			if(roomsLeft >= _hotel.quantity)
			{
				ctrlHotel.updateQuantity(dbStatement,
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
	public List<Packages> searchPackages(FlightTicket	_flightTicketGoing,
										 FlightTicket 	_flightTicketReturn,
										 Accommodation 	_accommodation) throws RemoteException
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
			listTicketGoing = ctrlPassages.searchPassages(dbStatement,
											   			  _flightTicketGoing.source, 
											   			  _flightTicketGoing.dest, 
											   			  new java.sql.Date(_flightTicketGoing.date.getTime()));
			
			// Verifies if there is a return ticket
			if(_flightTicketReturn != null)
			{
				// search for the return ticket					
				listTicketReturn = ctrlPassages.searchPassages(dbStatement,
												   			   _flightTicketReturn.source, 
												   			   _flightTicketReturn.dest, 
												   			   new java.sql.Date(_flightTicketReturn.date.getTime()));
			}
			
			// search for accommodation by city name
			listAccommodation = ctrlHotel.searchHotelByCity(dbStatement,
											   				_accommodation.cityName);
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
				if(_flightTicketReturn != null)
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
	public int buyPackage(Packages _package) throws RemoteException
	{
		int 	result 				= 0;
    	int 	goingTicketsLeft 	= 0;
    	int 	returnTicketsLeft 	= 0;
    	int 	roomsLeft 			= 0;
    	
    	boolean	isReturnTicket 				= false;
    	boolean returnTicketAvailability 	= true;

    	if(_package.flightTicketReturn != null)
		{
			isReturnTicket = true;
		}

    	try
		{
    		goingTicketsLeft = ctrlPassages.getQuantityLeft(dbStatement,
														    _package.flightTicketGoing.source,
														    _package.flightTicketGoing.dest,
														    new java.sql.Date(_package.flightTicketGoing.date.getTime()),
														    _package.flightTicketGoing.price);

			if(isReturnTicket)
			{
				returnTicketsLeft = ctrlPassages.getQuantityLeft(dbStatement,
															     _package.flightTicketReturn.source,
															     _package.flightTicketReturn.dest,
															     new java.sql.Date(_package.flightTicketReturn.date.getTime()),
															     _package.flightTicketReturn.price);
				
				if(returnTicketsLeft <= _package.flightTicketReturn.quantity)
				{
					returnTicketAvailability = false;
				}
			}

			roomsLeft = ctrlHotel.getQuantityLeft(dbStatement,
												  _package.accommodation.cityName,
												  _package.accommodation.accommodationName,
												  _package.accommodation.price);

			// checks if there are enough going tickets
			if((goingTicketsLeft >= _package.flightTicketGoing.quantity) &&
			   (returnTicketAvailability))
			{
				// checks if there are enough rooms left
				if(roomsLeft >= _package.accommodation.quantity)
				{
					// Finally, buy the package and update database
					ctrlPassages.updateQuantity(dbStatement,
										    	_package.flightTicketGoing.source,
										    	_package.flightTicketGoing.dest,
										    	new java.sql.Date(_package.flightTicketGoing.date.getTime()),
										    	_package.flightTicketGoing.price,
										    	goingTicketsLeft - _package.flightTicketGoing.quantity);
					
					if(isReturnTicket)
					{
						ctrlPassages.updateQuantity(dbStatement,
											    	_package.flightTicketReturn.source,
											    	_package.flightTicketReturn.dest,
											    	new java.sql.Date(_package.flightTicketReturn.date.getTime()),
											    	_package.flightTicketReturn.price,
											    	returnTicketsLeft - _package.flightTicketReturn.quantity);
					}

					ctrlHotel.updateQuantity(dbStatement,
										 	 _package.accommodation.cityName,
										 	 _package.accommodation.accommodationName,
										 	 _package.accommodation.price,
										 	 roomsLeft - _package.accommodation.quantity);

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