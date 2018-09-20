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
 

/**
 * @name    ServerInterface
 * @brief   
 */
public interface ServerInterface extends Remote
{
 	public void call(String 			clientName,
 				  	 ClientInterface	refCli) throws RemoteException;
   
         
}
