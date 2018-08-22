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
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @name 	MultiCast_Manager
 * @brief
 * 
 *
 */
public class MultiCast_Manager extends Thread 
{
	/**
	 * @name	communicationPort
	 * @brief
	 */
	private int communicationPort;
	
	/**
	 * @name	communicationGroup
	 * @brief
	 */
	private InetAddress communicationGroup;
	
	/**
	 * @name	MulticastSocket
	 * @brief
	 */
	private MulticastSocket socket;
	
	/**
	 * @name	sizeOfBuffer
	 * @brief	Each message can store until 500 bytes, we are using way less than this
	 */
	private int sizeOfBuffer = 500;
	
	/**
	 * @name	connectionOK
	 * @brief
	 */
	private boolean connectionOK = false;
	
	/**
	 * @name	MultiCast_Manager
	 * @brief
	 * @param 	_communicationPort
	 * @param	_communcationGroup
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
	 * @name	run
	 * @brief
	 */
	public void run() 
	{
		DatagramPacket receivedPacket = null;
		
		byte[] buffer = null;
		byte[] receivedMessage = null;
				
		while(true)
		{
			try
			{
				buffer = new byte[sizeOfBuffer];
				
				receivedPacket = new DatagramPacket(buffer,
													buffer.length);
				
				socket.receive(receivedPacket);
				
				receivedMessage = receivedPacket.getData();
				
				//***************************************************
				// From this point on, decode the received message //
				//***************************************************
				
				if(SD_Message.Types.TEST.getByteValue() == receivedMessage[0])
				{
					this.testMultiCastSocket_Callback();
				}
				
				else
				{
					
				}
			}
			
			catch(IOException ex)
			{
				Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	/**
	 * @name
	 * @brief
	 * @param	_message
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
	
	/**
	 * @name	testMultiCastSocket_Callback
	 * @brief
	 */
	public void testMultiCastSocket_Callback()
	{
		connectionOK = true;
		
		return;
	}
	
	public boolean getConnectionStatus()
	{
		return this.connectionOK;
	}
}
