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
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @name    ClientServent
 * @brief   
 */
public class ClientServent extends UnicastRemoteObject implements ClientInterface
{
    public ClientServent(ServerInterface serverReference) throws RemoteException
    {
        serverReference.call("client", this);
    }
    
    @Override
    public void echo(String value) throws RemoteException 
    {
        System.out.println(value);
    }
}
