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
	 * @brief Each message can store until 1024 bytes, we are using way less than
	 *        this
	 */
	private int sizeOfBuffer = 1024;

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
	 * @name run
	 * @brief
	 */
	public void run() 
	{
		DatagramPacket receivedPacket = null;

		while (true) 
		{
			try 
			{
				byte[] buffer = new byte[sizeOfBuffer];
				
				receivedPacket = new DatagramPacket(buffer, 
													buffer.length);

				socket.receive(receivedPacket);

				buffer = receivedPacket.getData();
								
				// **************************************************
				// From this point on, decode the received message //
				// **************************************************

				if (SD_Message.Types.TEST.getByteValue() == buffer[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("\nMensagem Recebida do tipo TEST");
					}
					
					this.testMultiCastSocket_Callback();
				}

				else if (SD_Message.Types.SUBSCRIBE.getByteValue() == buffer[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("\nMensagem Recebida do tipo SUBSCRIBE");
					}
					
					this.subscribe_Callback(buffer);
				}

				else if (SD_Message.Types.UNSUBSCRIBE.getByteValue() == buffer[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("\nMensagem Recebida do tipo UNSUBSCRIBE");
					}
					
					this.unsubscribe_Callback(buffer);
				}

				else if (SD_Message.Types.REPLY_PUBLIC_KEY.getByteValue() == buffer[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("\nMensagem Recebida do tipo REPLY_PUBLIC_KEY");
					}
					
					this.replyPublicKey_Callback(buffer);
				}

				else if (SD_Message.Types.REQUEST_RESOURCE.getByteValue() == buffer[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("\nMensagem Recebida do tipo REQUEST_RESOURCE");
					}
					
					this.requestResource_Callback(buffer);
				}
				
				else if (SD_Message.Types.REQUEST_PUBLIC_KEY.getByteValue() == buffer[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("\nMensagem Recebida do tipo REQUEST_PUBLIC_KEY");
					}
					
					this.requestPublicKey_Callback(buffer);
				}

				else 
				{
					System.out.println("\nTipo de Mensagem não reconhecido!");
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
	 * @brief	This method is only used at the start of the application
	 * 			What it does is, whenever it receives a TEST message, it will 
	 * 			set its private connectionOK member.
	 * 			This way, the tester method can only look to this member and see if 
	 * 			the process is correctly connected to the server
	 */
	public void testMultiCastSocket_Callback()
	{	
		this.connectionOK = true;
		
		return;
	}

	/**
	 * @name	subscribe_Callback
	 * @brief
	 */
	public void subscribe_Callback(byte[]	_message)
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, false);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(sd_message.getUniqueID() != this.process.getProcessID())
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		else
		{
			// Checks if the peer is already in the list
			if(this.process.getPeerList().findPeerById(sd_message.getUniqueID()) == null)
			{
				this.process.getPeerList().insertPeer(sd_message.getUniqueID(), sd_message.getData());
			}
		}
				
		return;
	}

	/**
	 * @name 	unsubscribe_Callback
	 * @brief
	 */
	public void unsubscribe_Callback(byte[]	_message) 
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, true);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(sd_message.getUniqueID() != this.process.getProcessID())
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		
		else
		{
			try
			{
				// Verify if the signature is correct
				if(this.process.getCriptography().verifySignature(sd_message, this.process.getPeerList().getPublicKeyByte(sd_message.getUniqueID())))
				{
					// remove peer from this process peer list
					this.process.getPeerList().removePeer(sd_message.getUniqueID());
				}
			}
			catch(Exception e){}	
		}
		
		return;
	}

	/**
	 * @name 	replyPublicKey_Callback
	 * @brief
	 */
	public void replyPublicKey_Callback(byte[]	_message) 
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, false);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(sd_message.getUniqueID() != this.process.getProcessID())
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		
		else
		{
			this.process.getPeerList().insertPeer(sd_message.getUniqueID(), sd_message.getData());
		}
		
		return;
	}

	/**
	 * @name 	requestResource_Callback
	 * @brief
	 */
	public void requestResource_Callback(byte[]	_message) 
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
	 * @name 	requestPublicKey_Callback
	 * @brief
	 */
	public void requestPublicKey_Callback(byte[]	_message) 
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, false);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(sd_message.getUniqueID() != this.process.getProcessID())
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		
		else
		{
			sd_message = new SD_Message(SD_Message.Types.REPLY_PUBLIC_KEY,
										this.process.getProcessID(),
										this.process.getCriptography().getPublicKeyByte());
		
			sendMessage(sd_message.mountMessage());
		}
				
		return;
	}
}
