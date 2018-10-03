/**
 ******************************************************************************
 * @file    ClientInterface.java
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
 * @brief	Interface responsible for defining every method of the client
 */
public interface ClientInterface extends Remote
{    
	/**
	 * @brief	Send a message from server to client
	 * 
	 * @param	value	: Received Message from Server
	 */
    public void eventPopUp(String value) throws RemoteException;
}
