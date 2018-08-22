/**
 ******************************************************************************
 * @file   	MultiCast_Manager.java
 * @author	Leonardo Winter Pereira
 * @author 	Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @name MultiCast_Manager
 * @brief
 * 
 *
 */
public class MultiCast_Manager extends Thread 
{
	/**
	 * @name
	 * @brief
	 */
	private int communicationPort;
	
	/**
	 * @name
	 * @brief
	 */
	private InetAddress communicationGroup;
	
	/**
	 * @name
	 * @brief
	 */
	private MulticastSocket socket;
	
	/**
	 * @name
	 * @brief
	 */
	public MultiCast_Manager(int _communicationPort,
							 String _communcationGroup)
	{
		this.communicationPort 	= _communicationPort;
		
		try
		{
			this.communicationGroup = InetAddress.getByName(_communcationGroup);
			
			socket					= new MulticastSocket(_communicationPort);
			
			socket.joinGroup(this.communicationGroup);
		}
		
		catch(SocketException e)
		{
			System.out.println("Socket: " + e.getMessage());
		}
		
		catch(IOException e)
		{
			System.out.println("IO: " + e.getMessage());
		}
		
		return;
	}

	/**
	 * @name
	 * @brief
	 */
	public void run() 
	{
		return;
	}

	/**
	 * @name
	 * @brief
	 * @param _message
	 * @return
	 */
	public boolean sendMessage(byte[] _message) 
	{
		boolean returnValue = false;

		DatagramPacket messageOut = new DatagramPacket(_message, 
													   _message.length, 
													   this.communicationGroup, 
													   this.communicationPort);

		try 
		{
			this.socket.send(messageOut);
			
			returnValue = true;
		}
		
		catch (IOException ex) 
		{
			Logger.getLogger(MultiCast_Manager.class.getName()).log(Level.SEVERE, 
																	null, 
																	ex);
		}

		return returnValue;
	}
}
