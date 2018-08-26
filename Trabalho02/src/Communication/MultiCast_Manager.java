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

import Database.Resource;
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
					this.testMultiCastSocket_Callback();
				}

				else if (SD_Message.Types.SUBSCRIBE.getByteValue() == buffer[0]) 
				{
					this.subscribe_Callback(buffer);
				}

				else if (SD_Message.Types.UNSUBSCRIBE.getByteValue() == buffer[0]) 
				{					
					this.unsubscribe_Callback(buffer);
				}

				else if (SD_Message.Types.REPLY_PUBLIC_KEY.getByteValue() == buffer[0]) 
				{
					this.replyPublicKey_Callback(buffer);
				}

				else if (SD_Message.Types.REPLY_RESOURCE_STATUS.getByteValue() == buffer[0]) 
				{
					if(this.debugMode)
					{
						System.out.println("\nMensagem Recebida do tipo REPLY_RESOURCE_STATUS");
					}
					
					this.replyResourceStatus_Callback(buffer);
				}

				else if (SD_Message.Types.REQUEST_RESOURCE.getByteValue() == buffer[0]) 
				{
					this.requestResource_Callback(buffer);
				}
				
				else if (SD_Message.Types.REQUEST_PUBLIC_KEY.getByteValue() == buffer[0]) 
				{
					this.requestPublicKey_Callback(buffer);
				}

				else 
				{
					System.out.println("\nTipo de Mensagem n�o reconhecido!");
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
		if(!this.connectionOK)
		{
			if(this.debugMode)
			{
				System.out.println("\nMensagem Recebida do tipo TEST");
			}
			
			this.connectionOK = true;
		}
		
		return;
	}

	/**
	 * @name	subscribe_Callback
	 * @brief
	 * @param	_message
	 */
	public void subscribe_Callback(byte[]	_message)
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, false);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, n�o preciso fazer nada!");
			}
		}
		else
		{
			System.out.println("\nMensagem Recebida do tipo SUBSCRIBE");
			
			// Checks if the peer is already in the list
			if(null == this.process.getPeerList().findPeerById(sd_message.getUniqueID()))
			{
				this.process.getPeerList().insertPeer(sd_message.getUniqueID(), sd_message.getData());
			}
		}
				
		return;
	}

	/**
	 * @name 	unsubscribe_Callback
	 * @brief
	 * @param	_message
	 */
	public void unsubscribe_Callback(byte[]	_message) 
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, true);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, n�o preciso fazer nada!");
			}
		}
		
		else
		{
			System.out.println("\nMensagem Recebida do tipo UNSUBSCRIBE");
			
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
	 * @param	_message
	 */
	public void replyPublicKey_Callback(byte[]	_message) 
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, false);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, n�o preciso fazer nada!");
			}
		}
		
		else
		{
			System.out.println("\nMensagem Recebida do tipo REPLY_PUBLIC_KEY");

			this.process.getPeerList().insertPeer(sd_message.getUniqueID(), sd_message.getData());
		}
		
		return;
	}

	/**
	 * @name 	replyResourceStatus_Callback
	 * @brief
	 */
	public void replyResourceStatus_Callback(byte[]	_message) 
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
				System.out.println("Sou eu enviando, n�o preciso fazer nada!");
			}
		}
		
		else
		{
			byte[] data = sd_message.getData();
			int resourceId = data[0] << 24 | (data[1] & 0xff) << 16 | (data[2] & 0xff) << 8 | (data[3] & 0xff);
			int peerId = sd_message.getUniqueID();
			byte resourceStatus = data[4];

			this.process.getResourceManager().setResourceStatusByPeerId(peerId, resourceId, resourceStatus);
		}
		
		return;
	}

	/**
	 * @name 	requestResource_Callback
	 * @brief
	 * @param	_message
	 */
	public void requestResource_Callback(byte[]	_message) 
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, true);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, n�o preciso fazer nada!");
			}
		}
		
		else
		{
			try
			{
				// Verify if the signature is correct
				if(this.process.getCriptography().verifySignature(sd_message, this.process.getPeerList().getPublicKeyByte(sd_message.getUniqueID())))
				{
					byte[] data = sd_message.getData();
					int resourceId = data[0] << 24 | (data[1] & 0xff) << 16 | (data[2] & 0xff) << 8 | (data[3] & 0xff);
					byte[] resourceStatus = new byte[0];
					resourceStatus[0] = this.process.getResourceList().getResourceStatus(resourceId).getByteValue();

					sendSignedMessage(SD_Message.Types.REPLY_RESOURCE_STATUS,
								process.getProcessID(), 
								resourceStatus);
				}
			}
			catch(Exception e){}	
			System.out.println("\nMensagem Recebida do tipo REQUEST_RESOURCE");
		}
				
		return;
	}
	
	/**
	 * @name 	requestPublicKey_Callback
	 * @brief
	 * @param	_message
	 */
	public void requestPublicKey_Callback(byte[]	_message) 
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, false);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, n�o preciso fazer nada!");
			}
		}
		
		else
		{
			System.out.println("\nMensagem Recebida do tipo REQUEST_PUBLIC_KEY");
			
			sd_message = new SD_Message(SD_Message.Types.REPLY_PUBLIC_KEY,
										this.process.getProcessID(),
										this.process.getCriptography().getPublicKeyByte());
		
			sendMessage(sd_message.mountMessage());
		}
				
		return;
	}
	
	/**
	 * @name	checkIfSenderIsMyself
	 * @brief
	 * @param 	_messageUniqueID
	 * @return
	 */
	public boolean checkIfSenderIsMyself(int	_messageUniqueID)
	{
		return _messageUniqueID == this.process.getProcessID();
	}

	/**
	 * @name 	sendSignedMessage
	 * @brief	Mounts a message, signs it and sends it
	 */
	public void sendSignedMessage(SD_Message.Types _type, int _processId, byte[] _data) 
	{
		byte[] signature;
		byte[] mountedMessage;

		SD_Message sd_message = new SD_Message(_type,
											_processId,
											_data);

		mountedMessage = sd_message.mountMessage();

		signature = process.getCriptography().generateSignature(mountedMessage);

		sendMessage(sd_message.appendSignature(mountedMessage, signature));
				
		return;
	}
}
