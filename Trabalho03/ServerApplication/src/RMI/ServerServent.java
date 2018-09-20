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

/**
 * @name    ServerServent
 * @brief   
 */
public class ServerServent extends UnicastRemoteObject implements ServerInterface
{
    public ServerServent() throws RemoteException
    {
    }
    
    @Override
    public void call(String 			clientName, 
    				 ClientInterface 	refCli) throws RemoteException
    {
    	System.out.print("Teste");
        refCli.echo(clientName);
    }
    
}
