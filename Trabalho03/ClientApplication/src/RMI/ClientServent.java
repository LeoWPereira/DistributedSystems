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

public class ClientServent extends UnicastRemoteObject implements ClientInterface
{
	private static final long serialVersionUID = -6179845856072812728L;

	private String name;

	private ArrayList<FlightTicketInterest> listTicketInterest;

	private ArrayList<AccommodationInterest> listAccommodationInterest;

	private ArrayList<PackageInterest> listPackageInterest;

    private ServerInterface serverRMI;

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

    public String getClientName()
    {
    	return this.name;
    }

    public void addFlightTicketInterest(FlightTicketInterest ticketInterest)
    {
    	listTicketInterest.add(ticketInterest);
    }

    public void addAccommodationInterest(AccommodationInterest accommodationInterest)
    {
    	listAccommodationInterest.add(accommodationInterest);
    }

    public void addPackageInterest(PackageInterest packageInterest)
    {
    	listPackageInterest.add(packageInterest);
    }

    public ArrayList<FlightTicketInterest> getTicketInterestList()
    {
    	return this.listTicketInterest;
    }

    public ArrayList<AccommodationInterest> getAccommodationInterestList()
    {
    	return this.listAccommodationInterest;
    }

    public ArrayList<PackageInterest> getPackageInterestList()
    {
    	return this.listPackageInterest;
    }
    
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

                try 
                {
					serverRMI.unregisterTicketInterest(interest);
				} 
                catch (RemoteException e) 
                {
					e.printStackTrace();
				}
    		}
    	}
    }

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
						e.printStackTrace();
					}
                }
            }
        }
    }

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
					e.printStackTrace();
				}
            }
        }
        return;
    }
}
