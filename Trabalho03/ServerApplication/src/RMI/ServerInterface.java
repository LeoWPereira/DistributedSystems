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
import Classes.AccommodationInterest;
import Classes.AccommodationManager;
import Classes.FlightTicket;
import Classes.FlightTicketInterest;
import Classes.FlightTicketManager;
import Classes.PackageInterest;
import Classes.Packages;
 

/**
 * @brief	This class is responsible for defining the
 * 			the methods available in the server side
 */
public interface ServerInterface extends Remote
{
	/**
	 * @brief	First communication between client and server
	 * 
	 * @param 	clientName	: String with the Client Name
	 * @param 	refCli		: Reference to the client
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
     * @brief	Register Interest in a certain Passage
     * 
     * @param 	_ticket			: Instance of a Ticket
     * @param   _ticketFrom     : Instance of a Ticket
     * @param 	_quantity		: Quantity of desired tickets
     * @param 	_desiredPrice	: Desired max price
     * @param 	_refCli			: Reference to the client
     * @param   _clientName     : Client name
     */
    public void registerPassageInterest(FlightTicket    _ticketTo,
                                        FlightTicket    _ticketFrom,
                                        int             _quantity,
                                        float           _desiredPrice,
                                        ClientInterface _refCli,
                                        String          _clientName)        throws RemoteException;
    
    /**
     * @brief	Register Interest in a certain Accommodation
     * 
     * @param 	_hotel			: Instance of a Accommodation
     * @param 	_quantity		: Quantity of desired rooms
     * @param 	_numberOfGuests	: Number of Guests
     * @param 	_desiredPrice	: Desired Max PRice
     * @param 	_refCli			: Reference to the client
     * @param   _clientName     : Client name
     */
    public void registerHotelInterest(Accommodation     _hotel, 
                                      int               _quantity,
                                      int               _numberOfGuests,
                                      float             _desiredPrice,
                                      ClientInterface   _refCli,
                                      String            _clientName)        throws RemoteException;

    /**
     * @brief   Register Interest in a certain Package
     * 
     * @param   _ticketTo       : Instance of a Ticket
     * @param   _ticketFrom     : Instance of a Ticket
     * @param   _accommodation  : Instance of an Accommodation
     * @param   _quantity       : Quantity of desired tickets
     * @param   _desiredPrice   : Desired Max Price
     * @param   _numberOfGuests : Number of Desired Guests
     * @param   _refCli         : Reference to the client
     * @param   _clientName     : Client Name
     */
    public void registerPackageInterest(FlightTicket    _ticketTo,
                                        FlightTicket    _ticketFrom,
                                        Accommodation   _accommodation,
                                        int             _quantity,
                                        float           _desiredPrice,
                                        int             _numberOfGuests,
                                        ClientInterface _refCli,
                                        String          _clientName)       throws RemoteException;

    /**
     * @brief   Cancel a registration in a Flight Ticket
     * 
     * @param   interest	: Instance of a Flight Ticket
     */
    public void unregisterTicketInterest(FlightTicketInterest interest)  throws RemoteException;

    /**
     * @brief   Cancel a registration in an Accommodation
     * 
     * @param   interest	: Instance of an Accommodation
     */
    public void unregisterAccommodationInterest(AccommodationInterest interest)  throws RemoteException;

    /**
     * @brief   Cancel a registration in a Package
     * 
     * @param   interest	: Instance of a Package
     */
    public void unregisterPackageInterest(PackageInterest interest)  throws RemoteException;
}
