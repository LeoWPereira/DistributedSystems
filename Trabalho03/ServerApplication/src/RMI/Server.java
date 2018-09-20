/**
 ******************************************************************************
 * @file    Server.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    19 de set de 2018
 * @brief
 ******************************************************************************
 */

package RMI;


import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * @name    Server
 * @brief   
 */
public class Server 
{    
    public static void main(String[] args) 
    {
        try 
        {
            Registry nameServiceReference = LocateRegistry.createRegistry(1099);
            ServerServent serverServent = new ServerServent();
            try 
            {
                nameServiceReference.bind("Server", serverServent);
            } 
            catch (AlreadyBoundException | AccessException ex) 
            {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } 
        catch (RemoteException ex) 
        {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
