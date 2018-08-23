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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.Arrays.copyOfRange;

import Process.ProcessClass;

/**
 * @name MultiCast_Manager
 * @brief
 * 
 *
 */
public class MultiCast_Manager extends Thread 
{
	/**
	 * @name	peers
	 * @brief	Hash Map with the key as a uniqueID (as byte[])
	 * 			and a value as the public Key (also as a byte[])
	 */
	private Map<byte[], byte[]> peers;
	
	/**
	 * @name communicationPort
	 * @brief
	 */
	private int communicationPort;

	/**
	 * @name communicationGroup
	 * @brief
	 */
	private InetAddress communicationGroup;

	/**
	 * @name MulticastSocket
	 * @brief
	 */
	private MulticastSocket socket;

	/**
	 * @name 	process
	 * @brief
	 */
	private ProcessClass process;
	
	/**
	 * @name sizeOfBuffer
	 * @brief Each message can store until 500 bytes, we are using way less than
	 *        this
	 */
	private int sizeOfBuffer = 500;

	/**
	 * @name connectionOK
	 * @brief
	 */
	private boolean connectionOK = false;
	
	/**
	 * @name	debugMode
	 * @brief
	 */
	private boolean debugMode;

	/**
	 * @name MultiCast_Manager
	 * @brief
	 * @param _communicationPort
	 * @param _communcationGroup
	 */
	public MultiCast_Manager(ProcessClass	_process,
							 int 			_communicationPort, 
							 String			_communcationGroup,
							 boolean		_debugMode) 
	{
		this.process			= _process;
		
		this.peers				= new HashMap<byte[], byte[]>();
		
		this.communicationPort 	= _communicationPort;

		this.debugMode			= _debugMode;
		
		try
		{
			this.communicationGroup = InetAddress.getByName(_communcationGroup);

			socket = new MulticastSocket(_communicationPort);

			socket.joinGroup(this.communicationGroup);
		}

		catch (SocketException e) 
		{
			System.out.println("Socket: " + e.getMessage());
		}

		catch (IOException e) 
		{
			System.out.println("IO: " + e.getMessage());
		}

		return;
	}

	/**
	 * @name 	getConnectionStatus
	 * @brief
	 */
	public boolean getConnectionStatus() 
	{
		return this.connectionOK;
	}
	
	/**
	 * @name 	getPeers
	 * @brief
	 */
	public Map<byte[], byte[]> getPeers()
	{
		return this.peers;
	}

	/**
	 * @name run
	 * @brief
	 */
	public void run() 
	{
		DatagramPacket receivedPacket = null;

		byte[] buffer = null;
		byte[] receivedMessage = null;

		while (true) 
		{
			try 
			{
				buffer = new byte[sizeOfBuffer];

				receivedPacket = new DatagramPacket(buffer, 
													buffer.length);

				socket.receive(receivedPacket);

				receivedMessage = receivedPacket.getData();

				// **************************************************
				// From this point on, decode the received message //
				// **************************************************

				if (SD_Message.Types.TEST.getByteValue() == receivedMessage[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("Mensagem Recebida do tipo TEST");
					}
					
					this.testMultiCastSocket_Callback();
				}

				else if (SD_Message.Types.SUBSCRIBE.getByteValue() == receivedMessage[0]) 
				{
					this.subscribe_Callback();
				}

				else if (SD_Message.Types.UNSUBSCRIBE.getByteValue() == receivedMessage[0]) 
				{
					this.unsubscribe_Callback();
				}

				else if (SD_Message.Types.REPLY_PUBLIC_KEY.getByteValue() == receivedMessage[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("Mensagem Recebida do tipo REPLY_PUBLIC_KEY");
					}
					
					this.replyPublicKey_Callback(receivedMessage);
				}

				else if (SD_Message.Types.REQUEST_RESOURCE.getByteValue() == receivedMessage[0]) 
				{
					this.requestResource_Callback();
				}
				
				else if (SD_Message.Types.REQUEST_PUBLIC_KEY.getByteValue() == receivedMessage[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("Mensagem Recebida do tipo REQUEST_PUBLIC_KEY");
					}
					
					this.requestPublicKey_Callback(receivedMessage);
				}

				else 
				{
					System.out.println("Tipo de Mensagem não reconhecido!");
				}
			}

			catch (IOException ex) 
			{
				Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
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

	/**
	 * @name	testMultiCastSocket_Callback
	 * @brief
	 */
	public void testMultiCastSocket_Callback()
	{	
		connectionOK = true;
		
		return;
	}

	/**
	 * @name	subscribe_Callback
	 * @brief
	 */
	public void subscribe_Callback()
	{
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(true)
		{
			// Ignore message
		}
		
		else
		{
			
		}
				
		return;
	}

	/**
	 * @name unsubscribe_Callback
	 * @brief
	 */
	public void unsubscribe_Callback() 
	{
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(true)
		{
			// Ignore message
		}
		
		else
		{
			
		}
		
		return;
	}

	/**
	 * @name replyPublicKey_Callback
	 * @brief
	 */
	public void replyPublicKey_Callback(byte[] _message) 
	{
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		int msgType;
		int uniqueID;
		byte[] data;
		boolean equal;
		
		if(true)
		{
			msgType = (int) _message[0];
			uniqueID = (int) _message[1];
			data = copyOfRange(_message, 2, (_message.length - 1));
			
			System.out.println("tipo:" + msgType + " id: " + uniqueID + " data: " + new String(data)+ "\n");
			
			equal = Arrays.equals(this.process.getCriptography().getPublicKeyByte(), data);
			
			System.out.println("equal:" + equal + "\n");
		}
		
		else
		{
			
		}
		
		return;
	}

	/**
	 * @name requestResource_Callback
	 * @brief
	 */
	public void requestResource_Callback() 
	{
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(true)
		{
			// Ignore message
		}
		
		else
		{
			
		}
				
		return;
	}
	
	/**
	 * @name requestPublicKey_Callback
	 * @brief
	 */
	public void requestPublicKey_Callback(byte[] _message) 
	{
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(0 != this.process.getProcessID())
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		
		else
		{
			SD_Message sd_message = new SD_Message(SD_Message.Types.REPLY_PUBLIC_KEY,
												   this.process.getProcessID(),
												   this.process.getCriptography().getPublicKeyByte(),
												   debugMode);
			
			sendMessage(sd_message.mountMessage());
		}
				
		return;
	}
}
