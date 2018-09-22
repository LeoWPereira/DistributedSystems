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
	 * @brief	
	 * 
	 * @param	serverReference	-
	 */
	public ClientServent(ServerInterface serverReference) throws RemoteException
    {
        serverReference.call("client", this);
    }
    
	/**
	 * @brief
	 * 
	 * @param	value	-
	 */
    @Override
    public void echo(String value) throws RemoteException 
    {
        System.out.println(value);
    }
}
