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

import javax.swing.JOptionPane;

/**
 * @brief	Class responsible for  
 */
public class ClientServent extends UnicastRemoteObject implements ClientInterface
{
	/**
	 * @brief	Generated Serial Version ID
	 */
	private static final long serialVersionUID = -6179845856072812728L;

	/**
	 * @brief Name of the user
	 */
	private String name;

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
     * @brief Server RMI object
     */
    private ServerInterface serverRMI;

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

        serverRMI = _serverReference;

        serverRMI.call(_name,
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
    
    /**
	 * @brief Remove ticket interest from local array and from the server
	 * 
	 */
    public void removeTicketInterest(FlightTicketInterest ticketInterest)
    {
        FlightTicketInterest interest;

    	for(int i = 0; i < listTicketInterest.size(); i++)
    	{
            interest = listTicketInterest.get(i);

    		if((ticketInterest.getSource().compareToIgnoreCase(interest.getSource()) == 0)   &&
              (ticketInterest.getDest().compareToIgnoreCase(interest.getDest()) == 0)       &&
              (ticketInterest.isReturnTicket() == interest.isReturnTicket()                 &&
              (ticketInterest.getMaxPrice() == interest.getMaxPrice())))
    		{
    			listTicketInterest.remove(i);

                try {
					serverRMI.unregisterTicketInterest(interest);
				} 
                catch (RemoteException e) 
                {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	return;
    }

    /**
     * @brief Remove accommodation interest from local array and from the server
     * 
     */
    public void removeAccommodationInterest(AccommodationInterest accommodationInterest)
    {
        AccommodationInterest interest;

        for(int i = 0; i < listAccommodationInterest.size(); i++)
        {
            interest = listAccommodationInterest.get(i);

            // checks if the interest is by its city name
            if(accommodationInterest.getAccommodationName().isEmpty())
            {
                if((accommodationInterest.getAccommodationCityName().compareToIgnoreCase(interest.getAccommodationCityName()) == 0)   &&
                  (accommodationInterest.getMaxPrice() == interest.getMaxPrice()))
                {
                    listAccommodationInterest.remove(i);

                    try 
                    {
						serverRMI.unregisterAccommodationInterest(interest);
					} 
                    catch (RemoteException e) 
                    {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
            else
            {

                if((accommodationInterest.getAccommodationName().compareToIgnoreCase(interest.getAccommodationName()) == 0)   &&
                  (accommodationInterest.getMaxPrice() == interest.getMaxPrice()))
                {
                    listAccommodationInterest.remove(i);

                    try 
                    {
						serverRMI.unregisterAccommodationInterest(interest);
					} 
                    catch (RemoteException e) 
                    {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        }
        return;
    }

    /**
     * @brief Remove package interest from local array and from the server
     * 
     */
    public void removePackageInterest(PackageInterest packageInterest)
    {
        PackageInterest interest;

        for(int i = 0; i < listPackageInterest.size(); i++)
        {
            interest = listPackageInterest.get(i);

            if((packageInterest.getSource().compareToIgnoreCase(interest.getSource()) == 0)   &&
              (packageInterest.getDest().compareToIgnoreCase(interest.getDest()) == 0)       &&
              (packageInterest.isReturnTicket() == interest.isReturnTicket()                 &&
              (packageInterest.getMaxPrice() == interest.getMaxPrice())))
            {
                listPackageInterest.remove(i);

                try 
                {
					serverRMI.unregisterPackageInterest(interest);
				} 
                catch (RemoteException e) 
                {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        return;
    }
}
