/**
 ******************************************************************************
 * @file    ClientServent.java
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
import java.util.ArrayList;

import Classes.AccommodationInterest;
import Classes.FlightTicketInterest;
import Classes.PackageInterest;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @brief   
 */
public class ClientServent extends UnicastRemoteObject implements ClientInterface
{
    /**
	 * @brief Generated Serial Version ID
	 */
	private static final long serialVersionUID = 5470551739044150069L;

	/**
	 * @brief Name of the user
	 */
	private static String name;

	/**
	 * @brief Local list of flight ticket interests
	 */
	private ArrayList<FlightTicketInterest> listTicketInterest;

	/**
	 * @brief Local list of flight ticket interests
	 */
	private ArrayList<AccommodationInterest> listAccommodationInterest;

	/**
	 * @brief Local list of flight ticket interests
	 */
	private ArrayList<PackageInterest> listPackageInterest;

	/**
	 * @brief	
	 * 
	 * @param	_serverReference	:
	 * @param	_name				:
	 */
	public ClientServent(ServerInterface 	_serverReference,
						 String				_name) throws RemoteException
    {
    	this.name = _name;

    	listTicketInterest = new ArrayList<FlightTicketInterest>();

    	listAccommodationInterest = new ArrayList<AccommodationInterest>();

    	listPackageInterest = new ArrayList<PackageInterest>();

        _serverReference.call(_name,
        					  this);
    }
    
	/**
	 * @brief
	 * 
	 * @param	value	:
	 */
    @Override
    public void eventPopUp(String value) throws RemoteException 
    {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() 
    	{
	        public void run() 
	        {
	           JOptionPane.showMessageDialog(null, value);
	        }
    	});
    }

    /**
	 * @brief Default getter
	 * 
	 */
    public String getClientName()
    {
    	return this.name;
    }

    /**
	 * @brief Adds a flight ticket interest into the local array list
	 * 
	 */
    public void addFlightTicketInterest(FlightTicketInterest ticketInterest)
    {
    	listTicketInterest.add(ticketInterest);

    	return;
    }

    /**
	 * @brief Adds an accommodation interest into the local array list
	 * 
	 */
    public void addAccommodationInterest(AccommodationInterest accommodationInterest)
    {
    	listAccommodationInterest.add(accommodationInterest);
    	
    	return;
    }

    /**
	 * @brief Adds a package interest into the local array list
	 * 
	 */
    public void addPackageInterest(PackageInterest packageInterest)
    {
    	listPackageInterest.add(packageInterest);
    	
    	return;
    }

    /**
	 * @brief Default getter
	 * 
	 */
    public ArrayList<FlightTicketInterest> getTicketInterestList()
    {
    	return this.listTicketInterest;
    }

    /**
	 * @brief Default getter
	 * 
	 */
    public ArrayList<AccommodationInterest> getAccommodationInterestList()
    {
    	return this.listAccommodationInterest;
    }

    /**
	 * @brief Default getter
	 * 
	 */
    public ArrayList<PackageInterest> getPackageInterestList()
    {
    	return this.listPackageInterest;
    }
}
