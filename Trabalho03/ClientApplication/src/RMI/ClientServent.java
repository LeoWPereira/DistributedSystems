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
	 * @brief	
	 * 
	 * @param	_serverReference	:
	 * @param	_name				:
	 */
	public ClientServent(ServerInterface 	_serverReference,
						 String				_name) throws RemoteException
    {
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
    	JOptionPane.showMessageDialog(null, "teste");
    }
}
