/**
 ******************************************************************************
 * @file    ServerServent.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import Classes.Accommodation;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketManager;
import Classes.FlightTicketInterest;
import Classes.AccommodationInterest;
import Classes.Packages;
import Database.DBConnection;
import Database.Controller.CtrlHotel;
import Database.Controller.CtrlPassages;

/**
 * @name    ServerServent
 * @brief   
 */
public class ServerServent extends UnicastRemoteObject implements ServerInterface
{
    /**
	 * @brief
	 */
	private static final long serialVersionUID = -8288164692733086382L;
	
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

	/**
	 * @brief	
	 */
	public ServerServent() throws RemoteException
    {
		ctrlPassages	= new CtrlPassages();
		
		ctrlHotel		= new CtrlHotel();

		listTicketInterest = new ArrayList<FlightTicketInterest>();
        listAccommodationInterest = new ArrayList<AccommodationInterest>();
    }

	@Override
	public synchronized void call(String 			clientName, 
					 			  ClientInterface	refCli) 		throws RemoteException 
	{
		JOptionPane.showMessageDialog(null, 
									  "Cliente " + clientName	+ " conectado!");
		
		//String message = "Conex�o realizada com sucesso";
		
        //refCli.eventPopUp(message);
	}

	@Override
	public synchronized FlightTicketManager searchPassages(String	_source, 
														   String 	_dest, 
														   Date 	_date) 	throws RemoteException 
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
	public synchronized AccommodationManager searchHotelByCity(String _city) throws RemoteException 
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
	public synchronized AccommodationManager searchHotelByName(String _hotel) throws RemoteException 
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
	public synchronized boolean buyPassage(FlightTicket _ticket) throws RemoteException 
	{
		boolean returnValue = false;
	    
		try 
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			int ticketsLeft = ctrlPassages.getQuantityLeft(_stm,
														   _ticket.getSource(),
														   _ticket.getDest(),
														   new java.sql.Date(_ticket.getDate().getTime()),
														   _ticket.getPrice());

			if(ticketsLeft >= _ticket.getQuantity())
			{
				ctrlPassages.updateQuantity(_stm,
										    _ticket.getSource(),
										    _ticket.getDest(),
										    new java.sql.Date(_ticket.getDate().getTime()),
										    _ticket.getPrice(),
										    ticketsLeft - _ticket.getQuantity());
				
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
	public synchronized boolean reserveHotel(Accommodation _hotel) throws RemoteException 
	{
		boolean returnValue = false;
	    
		try 
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);
			
			int roomsLeft = ctrlHotel.getQuantityLeft(_stm,
													  _hotel.getCityName(),
													  _hotel.getAccommodationName(),
													  _hotel.getPrice());

			if(roomsLeft >= _hotel.getQuantity())
			{
				ctrlHotel.updateQuantity(_stm,
										 _hotel.getCityName(),
										 _hotel.getAccommodationName(),
										 _hotel.getPrice(),
										 roomsLeft - _hotel.getQuantity());
				
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

	/**
     * @brief
     * 
     * @param   flightTicketGoing    :
     * @param   flightTicketReturn   :
     * @param   accommodation        :
     * 
     * @return
     */
    public ArrayList<Packages> searchPackages(FlightTicket   flightTicketGoing, 
                                              FlightTicket    flightTicketReturn, 
                                              Accommodation   accommodation)      throws RemoteException
    {
    	ArrayList<Packages> list = new ArrayList<Packages>();
    	FlightTicketManager listTicketGoing = new FlightTicketManager();
    	FlightTicketManager listTicketReturn = new FlightTicketManager();
    	AccommodationManager listAccommodation = new AccommodationManager();

    	FlightTicket flightTicketGoingFound;
    	FlightTicket flightTicketReturnFound;
    	Accommodation accommodationFound = null;
    	Packages pack;

    	Statement _stm;	    
		
		// First, search for the going ticket
		try 
		{
			_stm = DBConnection.configureDatabase(dbConnection);
			
			listTicketGoing = ctrlPassages.searchPassages(_stm,
											   flightTicketGoing.getSource(), 
											   flightTicketGoing.getDest(), 
											   new java.sql.Date(flightTicketGoing.getDate().getTime()));
			// Verifies if there is a return ticket
			if(flightTicketReturn != null)
			{
				// search for the return ticket					
				listTicketReturn = ctrlPassages.searchPassages(_stm,
												   flightTicketReturn.getSource(), 
												   flightTicketReturn.getDest(), 
												   new java.sql.Date(flightTicketReturn.getDate().getTime()));
			}
			
			// search for accommodation by city name
			listAccommodation = ctrlHotel.searchHotelByCity(_stm,
											   				accommodation.getCityName());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		// Fills a package for each combination of going ticket, return ticket and accommodation
		for(int i = 0; i < listTicketGoing.getFlightTicketListSize() ; i++)
		{
			flightTicketGoingFound = listTicketGoing.getFlightTicket(i);
			for(int j = 0; j < listAccommodation.getAccommodationListSize() ; j++)
			{
				accommodationFound = listAccommodation.getAccommodation(j);

				// If there is a return ticket
				if(flightTicketReturn != null)
				{
					for(int k = 0; k < listTicketReturn.getFlightTicketListSize() ; k++)
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
    public int buyPackage(Packages  _package) throws RemoteException
    {
    	int result = 0;
    	int goingTicketsLeft = 0;
    	int returnTicketsLeft = 0;
    	int roomsLeft = 0;
    	boolean isReturnTicket = false;
    	boolean returnTicketAvailability = true;

    	if(_package.getFlightTicketReturn() != null)
		{
			isReturnTicket = true;
		}

    	try 
		{
			Statement _stm = DBConnection.configureDatabase(dbConnection);

			goingTicketsLeft = ctrlPassages.getQuantityLeft(_stm,
														     _package.getFlightTicketGoing().getSource(),
														     _package.getFlightTicketGoing().getDest(),
														     new java.sql.Date(_package.getFlightTicketGoing().getDate().getTime()),
														     _package.getFlightTicketGoing().getPrice());

			if(isReturnTicket)
			{
				returnTicketsLeft = ctrlPassages.getQuantityLeft(_stm,
															     _package.getFlightTicketReturn().getSource(),
															     _package.getFlightTicketReturn().getDest(),
															     new java.sql.Date(_package.getFlightTicketReturn().getDate().getTime()),
															     _package.getFlightTicketReturn().getPrice());
				
				if(returnTicketsLeft <= _package.getFlightTicketReturn().getQuantity())
				{
					returnTicketAvailability = false;
				}
			}

			roomsLeft = ctrlHotel.getQuantityLeft(_stm,
												  _package.getAccommodation().getCityName(),
												  _package.getAccommodation().getAccommodationName(),
												  _package.getAccommodation().getPrice());

			// checks if there are enough going tickets
			if(goingTicketsLeft >= _package.getFlightTicketGoing().getQuantity() &&
			   returnTicketAvailability)
			{
				// checks if there are enough rooms left
				if(roomsLeft >= _package.getAccommodation().getQuantity())
				{
					// Finally, buy the package and update database
					ctrlPassages.updateQuantity(_stm,
										    	_package.getFlightTicketGoing().getSource(),
										    	_package.getFlightTicketGoing().getDest(),
										    	new java.sql.Date(_package.getFlightTicketGoing().getDate().getTime()),
										    	_package.getFlightTicketGoing().getPrice(),
										    	goingTicketsLeft - _package.getFlightTicketGoing().getQuantity());
					if(isReturnTicket)
					{
						ctrlPassages.updateQuantity(_stm,
											    	_package.getFlightTicketReturn().getSource(),
											    	_package.getFlightTicketReturn().getDest(),
											    	new java.sql.Date(_package.getFlightTicketReturn().getDate().getTime()),
											    	_package.getFlightTicketReturn().getPrice(),
											    	returnTicketsLeft - _package.getFlightTicketReturn().getQuantity());
					}

					ctrlHotel.updateQuantity(_stm,
										 	 _package.getAccommodation().getCityName(),
										 	 _package.getAccommodation().getAccommodationName(),
										 	 _package.getAccommodation().getPrice(),
										 	 roomsLeft - _package.getAccommodation().getQuantity());

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
	public synchronized void registerPassageInterest(FlightTicket    _ticketTo,
			                                         FlightTicket    _ticketFrom,
			                                         int             _quantity,
			                                         float           _desiredPrice,
			                                         ClientInterface _refCli,
			                                         String          _clientName) 		throws RemoteException 
	{
		boolean isReturnTicket = true;

		if(_ticketFrom == null)
		{
			isReturnTicket = false;
		}

        FlightTicketInterest ticketInterest = new FlightTicketInterest(_ticketTo,
        															   _ticketFrom, 
        															   isReturnTicket,
        															   _quantity, 
        															   _desiredPrice,
        															   _refCli,
        															   _clientName);

        this.listTicketInterest.add(ticketInterest);
        
        String message = "Registro de interesse realizado com sucesso";
		
        _refCli.eventPopUp(message);

        return;
	}

	@Override
	public synchronized void registerHotelInterest(Accommodation		_hotel, 
			    								   int               	_quantity,
			                                       int              	_numberOfGuests,
			    								   float 				_desiredPrice,
			    								   ClientInterface		_refCli,
			                                       String            	_clientName) 		throws RemoteException 
	{
		AccommodationInterest accommodationInterest = new AccommodationInterest(_hotel,
		        															   _quantity, 
		        															   _numberOfGuests,
		        															   _desiredPrice, 
		        															   _refCli,
		        															   _clientName);

        this.listAccommodationInterest.add(accommodationInterest);
        
        String message = "Registro de interesse realizado com sucesso";
		
        _refCli.eventPopUp(message);
	}

	public ArrayList<FlightTicketInterest> getTicketInterestList()
	{
		return listTicketInterest;
	}

	public ArrayList<AccommodationInterest> getAccommodationInterestList()
	{
		return listAccommodationInterest;
	}

	public void notifyTicketsInterests(FlightTicket flightTicket) throws RemoteException
	{
        FlightTicketInterest flightTicketInterest;
        // Creates a SimpleDateFormat to compare dates
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

        for (int i = 0; i < this.listTicketInterest.size(); i++) 
        {
            flightTicketInterest = listTicketInterest.get(i);
            boolean isReturnTicketInterest = flightTicketInterest.isReturnTicket();
            boolean goingTicketOk = false;

            if ((flightTicketInterest.getSource().compareToIgnoreCase(flightTicket.getSource()) == 0) 	&&
            	(flightTicketInterest.getDest().compareToIgnoreCase(flightTicket.getDest()) == 0) 		&&
            	fmt.format(flightTicketInterest.getSourceDate()).equals(fmt.format(flightTicket.getDate()))) 
            {
                // Lastly, verifies if the price is lower than what the client wants and if there are available the amount of passages of the interest
                if (flightTicket.getPrice() 	<= flightTicketInterest.getMaxPrice() &&
                	flightTicket.getQuantity() 	>= flightTicketInterest.getQuantity()) 
                {
                	// if is a return ticket interest, still has to find the other ticket interest (return)
                	if(isReturnTicketInterest)
                	{
                		goingTicketOk = true;
                	}
                	// Interest found
                	else
                	{
	                    String message = "Passagem de interesse: De " + flightTicket.getSource() + " para " + flightTicket.getDest() + 
	                    " no dia " + flightTicket.getDate() + " no pre�o " + flightTicket.getPrice();

	                    flightTicketInterest.getClientInterface().eventPopUp(message);

	                    listTicketInterest.remove(i);
	                }
                }
            }

            if(isReturnTicketInterest)
            {
            	// If this ticket was ok (going ticket), try to find the return ticket
            	if(goingTicketOk)
            	{
            		// Tries to find a going ticket in the database
                	FlightTicketManager list 					= new FlightTicketManager();
                	FlightTicket        flightTicketReturnFound = null;
                	boolean 			foundTicket 			= false;

					try 
					{
						Statement _stm = DBConnection.configureDatabase(dbConnection);
						
						list = ctrlPassages.searchPassages(_stm,
														   flightTicketInterest.getDest(), 
														   flightTicketInterest.getSource(), 
														   new java.sql.Date(flightTicketInterest.getReturnDate().getTime()));

						for (FlightTicket flightTicketReturn : list.getFlightTicketList())
						{
							if (flightTicketReturn.getPrice() 	 <= flightTicketInterest.getMaxPrice() &&
			                	flightTicketReturn.getQuantity() >= flightTicketInterest.getQuantity()) 
			                {
								flightTicketReturnFound = flightTicketReturn;
			                	foundTicket = true;
			                }
						}
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}


                	if(foundTicket)
                	{
                		String message = "Passagem de interesse de ida: De " + flightTicket.getSource() + " para " + flightTicket.getDest() + 
	                    				 " no dia " + flightTicket.getDate() + " no pre�o " + flightTicket.getPrice() +
                						 ". Passagem de interesse de volta: De " + flightTicketReturnFound.getSource() + " para " + flightTicketReturnFound.getDest() + 
	                    				 " no dia " + flightTicketReturnFound.getDate() + " no pre�o " + flightTicketReturnFound.getPrice();

	                    flightTicketInterest.getClientInterface().eventPopUp(message);

	                    listTicketInterest.remove(i);
                	}
            	}
            	// Verify if the registered ticket is an interest of a return ticket
            	else
            	{
            		boolean a = (flightTicketInterest.getDest().compareToIgnoreCase(flightTicket.getSource()) == 0);
            		boolean b = flightTicketInterest.getSource().compareToIgnoreCase(flightTicket.getDest()) == 0;
            		boolean c = fmt.format(flightTicketInterest.getReturnDate()).equals(fmt.format(flightTicket.getDate()));
            		
            		
	            	if ((flightTicketInterest.getDest().compareToIgnoreCase(flightTicket.getSource()) == 0) 	&&
	            		(flightTicketInterest.getSource().compareToIgnoreCase(flightTicket.getDest()) == 0) 		&&
	            		fmt.format(flightTicketInterest.getReturnDate()).equals(fmt.format(flightTicket.getDate()))) 
		            {
		                // Verifies if the price is lower than what the client wants and if there are available the amount of passages of the interest
		                if (flightTicket.getPrice() 	<= flightTicketInterest.getMaxPrice() &&
		                	flightTicket.getQuantity() 	>= flightTicketInterest.getQuantity()) 
		                {
		                	// Tries to find a going ticket in the database
		                	FlightTicketManager list 					= new FlightTicketManager();
		                	FlightTicket 		flightTicketGoingFound  = null;
		                	boolean 			foundTicket 			= false;
		
							try 
							{
								Statement _stm = DBConnection.configureDatabase(dbConnection);
								
								list = ctrlPassages.searchPassages(_stm,
																   flightTicketInterest.getSource(), 
																   flightTicketInterest.getDest(), 
																   new java.sql.Date(flightTicketInterest.getSourceDate().getTime()));

								for (FlightTicket flightTicketGoing : list.getFlightTicketList())
								{

									if (flightTicketGoing.getPrice() 	<= flightTicketInterest.getMaxPrice() &&
					                	flightTicketGoing.getQuantity() >= flightTicketInterest.getQuantity()) 
					                {
										flightTicketGoingFound = flightTicketGoing;
					                	foundTicket = true;
					                }
								}
							} 
							catch (SQLException e) 
							{
								e.printStackTrace();
							}

		                	if(foundTicket)
		                	{
		                		String message = "Passagem de interesse de ida: De " + flightTicketGoingFound.getSource() + " para " + flightTicketGoingFound.getDest() + 
			                    				 " no dia " + flightTicketGoingFound.getDate() + " no pre�o " + flightTicketGoingFound.getPrice() +
			                    				 "Passagem de interesse de volta: De " + flightTicket.getSource() + " para " + flightTicket.getDest() + 
	                    				 		 " no dia " + flightTicket.getDate() + " no pre�o " + flightTicket.getPrice();

	                    	    listTicketInterest.remove(i);
			                }
		                }
		            }

            	}
            }
        }
        return;
    }

    public void notifyAccommodationInterests(Accommodation accommodation) throws RemoteException
	{
        AccommodationInterest accommodationInterest;

        for (int i = 0; i < this.listAccommodationInterest.size(); i++) 
        {
            accommodationInterest = listAccommodationInterest.get(i);

            // if the interest has no city name, it was to a specific hotel
            if(accommodationInterest.getAccommodationCityName().isEmpty())
            {
            	if ((accommodationInterest.getAccommodationName().compareToIgnoreCase(accommodation.getAccommodationName()) == 0) 	&&
            		accommodation.getPrice() 				<= accommodationInterest.getMaxPrice() 									&&
                	accommodation.getQuantity() 			>= accommodationInterest.getQuantity() 									&&
                	accommodation.getMaxGuestsPerRoom() 	>= accommodationInterest.getNumberOfGuests()) 
            	{
                	// Interest found
                    String message = "Hospedagem de interesse de nome " + accommodation.getAccommodationName() + " dispon�vel no pre�o " + accommodation.getPrice();

                    accommodationInterest.getClientInterface().eventPopUp(message);

                    listAccommodationInterest.remove(i);
            	}
            }
            // search by its city name instead
            else
            {
            	if ((accommodationInterest.getAccommodationCityName().compareToIgnoreCase(accommodation.getCityName()) == 0) 	&&
            		accommodation.getPrice() 				<= accommodationInterest.getMaxPrice() 									&&
                	accommodation.getQuantity() 			>= accommodationInterest.getQuantity() 									&&
                	accommodation.getMaxGuestsPerRoom() 	>= accommodationInterest.getNumberOfGuests()) 
            	{
                	// Interest found
                    String message = "Hospedagem de interesse na cidade " + accommodation.getCityName() + " dispon�vel no pre�o " + accommodation.getPrice();

                    accommodationInterest.getClientInterface().eventPopUp(message);

                    listAccommodationInterest.remove(i);
            	}
            }
        }
        return;
    }
}
