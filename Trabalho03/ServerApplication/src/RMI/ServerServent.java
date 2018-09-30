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

import javax.swing.JOptionPane;

import Classes.Accommodation;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketManager;
import Classes.FlightTicketInterest;
import Classes.AccommodationInterest;
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
		
		//String message = "Conexão realizada com sucesso";
		
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
		
		return returnValue;
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
	                    " no dia " + flightTicket.getDate() + " no preço " + flightTicket.getPrice();

	                    flightTicketInterest.getClientInterface().eventPopUp(message);
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
	                    				 " no dia " + flightTicket.getDate() + " no preço " + flightTicket.getPrice() +
                						 ". Passagem de interesse de volta: De " + flightTicketReturnFound.getSource() + " para " + flightTicketReturnFound.getDest() + 
	                    				 " no dia " + flightTicketReturnFound.getDate() + " no preço " + flightTicketReturnFound.getPrice();

	                    flightTicketInterest.getClientInterface().eventPopUp(message);
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
			                    				 " no dia " + flightTicketGoingFound.getDate() + " no preço " + flightTicketGoingFound.getPrice() +
		                						 "/nPassagem de interesse de volta: De " + flightTicket.getSource() + " para " + flightTicket.getDest() + 
			                    				 " no dia " + flightTicket.getDate() + " no preço " + flightTicket.getPrice();

			                    flightTicketInterest.getClientInterface().eventPopUp(message);
		                	}
		                }
		            }

            	}
            }
        }
        return;
    }
}
