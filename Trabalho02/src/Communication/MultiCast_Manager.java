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
import java.nio.ByteBuffer;
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
	 * @name 	communicationPort
	 * @brief
	 */
	private int communicationPort;

	/**
	 * @name 	communicationGroup
	 * @brief
	 */
	private InetAddress communicationGroup;

	/**
	 * @name 	MulticastSocket
	 * @brief
	 */
	private MulticastSocket socket;

	/**
	 * @name 	process
	 * @brief
	 */
	private ProcessClass process;
	
	/**
	 * @name 	sizeOfBuffer
	 * @brief 	Each message can store until 1024 bytes, we are using way less than
	 *        	this
	 */
	private int sizeOfBuffer = 1024;

	/**
	 * @name 	connectionOK
	 * @brief
	 */
	private boolean connectionOK = false;
	
	/**
	 * @name 	initialSetOK
	 * @brief
	 */
	private boolean initialSetOK = false;
	
	/**
	 * @name 	minimumPeers
	 * @brief
	 */
	private int minimumPeers;
	
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
							 int 			_minimumPeers,
							 boolean		_debugMode) 
	{
		this.process			= _process;
		
		this.communicationPort 	= _communicationPort;

		this.minimumPeers		= _minimumPeers;
		
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
	 * @name 	getInitialSetOK
	 * @brief
	 */
	public boolean getInitialSetOK() 
	{
		return this.initialSetOK;
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
					this.testMultiCastSocket_Callback(buffer);
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
	 * @param	_message
	 */
	public void testMultiCastSocket_Callback(byte[]	_message)
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
		
		sd_message.demountMessage(_message, 
								  false);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		else
		{
			System.out.println("\nMensagem Recebida do tipo SUBSCRIBE");
			
			this.process.getPeerList().insertPeer(sd_message.getUniqueID(), 
												  sd_message.getData(), 
												  this.process.getQtyResources());
			
			if(process.getPeerList().getPeerListSize() == (this.minimumPeers - 1))
			{
				this.initialSetOK = true;
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
		
		sd_message.demountMessage(_message, 
								  true);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
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
			catch(Exception e)
			{
				
			}	
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
		
		sd_message.demountMessage(_message, 
								  false);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		
		else
		{
			System.out.println("\nMensagem Recebida do tipo REPLY_PUBLIC_KEY");

			this.process.getPeerList().insertPeer(sd_message.getUniqueID(), 
												  sd_message.getData(), 
												  this.process.getQtyResources());
			
			if((process.getPeerList().getPeerListSize() == (this.minimumPeers - 1)) || (sd_message.getData()[sd_message.getDataLength() - 1] == '1'))
			{
				this.initialSetOK = true;
			}
		}
		
		return;
	}

	/**
	 * @name 	replyResourceStatus_Callback
	 * @brief
	 * @param	_message
	 */
	public void replyResourceStatus_Callback(byte[]	_message) 
	{
		SD_Message sd_message = new SD_Message();
		
		sd_message.demountMessage(_message, 
								  true);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		
		else
		{
			System.out.println("\nMensagem Recebida do tipo REPLY_RESOURCE_STATUS");
			
			byte[] data = sd_message.getData();
			
			int resourceId = data[0] << 24 | (data[1] & 0xff) << 16 | (data[2] & 0xff) << 8 | (data[3] & 0xff);
			
			int peerId = sd_message.getUniqueID();
			
			byte resourceStatus = data[4];

			this.process.getResourceManager().setResourceStatusByPeerId(peerId, 
																		resourceId, 
																		resourceStatus);
			
			// Message received, response is ok
			this.process.getResourceManager().setStatusResponseByPeerId(peerId, 
																		true);
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
		
		sd_message.demountMessage(_message, 
								  true);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		
		else
		{
			System.out.println("\nMensagem Recebida do tipo REQUEST_RESOURCE");
			
			try
			{
				// Verify if the signature is correct
				if(this.process.getCriptography().verifySignature(sd_message, this.process.getPeerList().getPublicKeyByte(sd_message.getUniqueID())))
				{
					byte[] data = sd_message.getData();
					
					int resourceId = data[0] << 24 | (data[1] & 0xff) << 16 | (data[2] & 0xff) << 8 | (data[3] & 0xff);
					
					byte[] resourceStatus = new byte[1];
					
					resourceStatus[0] = this.process.getResourceList().getResourceStatus(resourceId).getByteValue();

					// Mount message
					data = new byte[5];
					
					data = ByteBuffer.allocate(4).putInt(resourceId).array();
					
					data = sd_message.append(data, resourceStatus);

					sendSignedMessage(SD_Message.Types.REPLY_RESOURCE_STATUS,
									  process.getProcessID(), 
									  data);
				}
			}
			catch(Exception e)
			{
				
			}
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
		
		sd_message.demountMessage(_message, 
								  false);
		
		//*****************************************
		// Check if the message sender is myself //
		//*****************************************
		
		if(checkIfSenderIsMyself(sd_message.getUniqueID()))
		{
			if(debugMode)
			{
				System.out.println("Sou eu enviando, não preciso fazer nada!");
			}
		}
		
		else
		{
			System.out.println("\nMensagem Recebida do tipo REQUEST_PUBLIC_KEY");
			
			byte[] data;
			
			if(this.getInitialSetOK())
			{
				data = new byte[]{'1'};
			}
			
			else
			{
				data = new byte[]{'0'};
			}
			
			sd_message = new SD_Message(SD_Message.Types.REPLY_PUBLIC_KEY,
										this.process.getProcessID(),
										sd_message.append(this.process.getCriptography().getPublicKeyByte(),
														  data));
		
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
	public void sendSignedMessage(SD_Message.Types	_type, 
								  int 				_processId, 
								  byte[] 			_data) 
	{
		byte[] signature;
		byte[] mountedMessage;

		SD_Message sd_message = new SD_Message(_type,
											   _processId,
											   _data);

		mountedMessage = sd_message.mountMessage();

		signature = process.getCriptography().generateSignature(mountedMessage);

		sendMessage(sd_message.appendSignature(mountedMessage, 
											   signature));
				
		return;
	}
}
