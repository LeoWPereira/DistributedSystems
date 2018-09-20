/**
 ******************************************************************************
 * @file    Client.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package RMI;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @name    Client
 * @brief   
 */
public class Client 
{
    public static void main(String[] args)throws RemoteException  
    {
        try 
        {
            Registry nameServiceReference = LocateRegistry.getRegistry();
            try 
            {
                ServerInterface serverReference = (ServerInterface) nameServiceReference.lookup("Server");
                ClientServent clientServent = new ClientServent(serverReference);
                //serverReference.call("client", clientServent);
            } 
            catch (NotBoundException ex)
            {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (AccessException ex)
            {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        } 
        catch (RemoteException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
