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

import javax.swing.JOptionPane;

import Classes.Accommodation;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketManager;
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
    }

	@Override
	public synchronized void call(String 			clientName, 
					 			  ClientInterface	refCli) 		throws RemoteException 
	{
		JOptionPane.showMessageDialog(null, 
									  "Cliente " + clientName	+ " conectado!");
		
		String message = new StringBuilder().append("Conexão realizada com sucesso").toString();
		
        refCli.eventPopUp(message);
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
		return null;
	}

	@Override
	public synchronized AccommodationManager searchHotelByName(String _hotel) throws RemoteException 
	{
		return null;
	}

	@Override
	public synchronized void buyPassage(FlightTicket _ticket) throws RemoteException 
	{

	}

	@Override
	public synchronized void reserveHotel(Accommodation _hotel) throws RemoteException 
	{

	}

	@Override
	public synchronized void registerPassageInterest(FlightTicket		_ticket, 
													 Date 				_until, 
													 float 				_desiredPrice, 
													 ClientInterface	_refCli) 		throws RemoteException 
	{

	}

	@Override
	public synchronized void registerHotelInterest(Accommodation	_hotel, 
												   Date 			_until, 
												   Date 			_checkin, 
												   Date 			_checkout,
												   float 			_desiredPrice, 
												   ClientInterface 	_refCli) 		throws RemoteException 
	{

	}
}
