/**
 ******************************************************************************
 * @file   	SD_Message.java
 * @author	Leonardo Winter Pereira
 * @author 	Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Communication;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @name 	SD_Message
 * @brief	Class responsible for defining what is a message in this application.
 * 			It has an enumerator (see Types) that defines every possible message.
 * 			This Class also defines two REALLY important methods, mount() and demount().
 */
public class SD_Message 
{
	/**
	 * @name	Types
	 * @brief	Enumerator responsible for defining the values for every possible
	 * 			message.
	 * 			This is only a fancy way of describing what the application can actually do.
	 */
	public enum Types
	{
		TEST(new Integer('T')),
		SUBSCRIBE(new Integer('S')),
		UNSUBSCRIBE(new Integer('U')),
		REPLY_PUBLIC_KEY(new Integer('R')),
		REPLY_RESOURCE_STATUS(new Integer('C')),
		REQUEST_RESOURCE(new Integer('Q')),
		REQUEST_PUBLIC_KEY(new Integer('K'));
		
		private final byte byteValue;
		
		private Types(Integer _integerValue)
		{
			this.byteValue = _integerValue.byteValue();
			
			return;
		}
		
		public byte getByteValue()
		{
			return this.byteValue;
		}
	}

	/**
	 * @name	type
	 * @brief	It stores privately, the type of the current
	 * 			sent / received message
	 */
	private Types type;
	
	/**
	 * @name	timestamp
	 * @brief	Message Timestamp in milliseconds
	 */
	private int timestamp = 0;
	
	/**
	 * @name	uniqueID
	 * @brief	4 bytes representing the peer Unique ID (sender of the message).
	 */
	private int uniqueID;
	
	/**
	 * @name	data
	 * @brief	The message data field. It is different for every kind of message
	 */
	private byte[] data;
	
	/**
	 * @name	dataLength
	 * @brief	4 bytes data Length
	 */
	private int dataLength;

	/**
	 * @name	signature
	 * @brief	The message signature, used to guarantee the 
	 * 			correctness of the sender.
	 */
	private byte[] signature;
	
	/**
	 * @name	signatureLength
	 * @brief	4 bytes signature Length
	 */
	private int signatureLength;

	/**
	 * @name	SD_Message
	 * @brief	Class Constructor
	 * 			It is used when sending / receiving a message
	 * @param	_type		: The type of the message
	 * @param	_uniqueID	: The sender unique ID
	 * @param	_data		: the data (different for each kind of message)
	 */
	public SD_Message(Types  	_type,
					  int 		_uniqueID,
					  byte[] 	_data)
	{
		this.type		= _type;
		this.uniqueID	= _uniqueID;
		this.data 		= _data;
		
		this.dataLength	= 0;
		
		if(null != this.data)
		{
			dataLength = this.data.length;
		}
		
		return;
	}
	
	/**
	 * @name	SD_Message
	 * @brief	Default Class Constructor
	 */
	public SD_Message()
	{
		return;
	}

	/**
	 * @name	getType
	 * @brief	Default Getter
	 */
	public Types getType() 
	{
		return this.type;
	}
	
	/**
	 * @name	getTimestamp
	 * @brief	Default Getter
	 */
	public int getTimestamp() 
	{
		return this.timestamp;
	}
	
	/**
	 * @name	getUniqueID
	 * @brief	Default Getter
	 */
	public int getUniqueID() 
	{
		return this.uniqueID;
	}
	
	/**
	 * @name	getData
	 * @brief	Default Getter
	 */
	public byte[] getData() 
	{
		return this.data;
	}
	
	/**
	 * @name	getDataLength
	 * @brief	Default Getter
	 */
	public int getDataLength() 
	{
		return this.dataLength;
	}

	/**
	 * @name	getSignature
	 * @brief	Default Getter
	 */
	public byte[] getSignature() 
	{
		return this.signature;
	}
	
	/**
	 * @name	getSignatureLength
	 * @brief	Default Getter
	 */
	public int getSignatureLength() 
	{
		return this.signatureLength;
	}

	/**
	 * @name	setTimestamp
	 * @brief	Default Setter
	 */
	public void setTimestamp(int _timestamp) 
	{
		this.timestamp = _timestamp;

		return;
	}
	
	/**
	 * @name	mountMessage
	 * @brief	Message structure:
	 * 			- Type (single byte)
	 * 			- Unique ID (single byte)
	 * 			- Data length
	 * 			- Data (with Data length size)
	 * @return	A byte array containing the message ready to be sent
	 * 			using the multiCast Manager Class
	 */
	public byte[] mountMessage()
	{
		byte[] initMessage 	= new byte[]{this.type.getByteValue()};
		
		byte[] mountedMessage;
			
		if(this.timestamp == 0)
		{
			mountedMessage = append(initMessage,
        							ByteBuffer.allocate(4).putInt(Calendar.getInstance().get(Calendar.MILLISECOND)).array());
		}
		else
		{
			mountedMessage = append(initMessage,
					   				ByteBuffer.allocate(4).putInt(this.timestamp).array());
		}
		
        mountedMessage = append(mountedMessage, 
			    				ByteBuffer.allocate(4).putInt(this.uniqueID).array());
        
        mountedMessage = append(mountedMessage, 
        					    ByteBuffer.allocate(4).putInt(this.dataLength).array());
        
        mountedMessage = append(mountedMessage, 
        					    this.data);
        
		return mountedMessage;
	}
	
	/**
	 * @name	demountMessage
	 * @brief	Message structure:
	 * 			- Type (single byte)
	 * 			- Unique ID (single byte)
	 * 			- Data length
	 * 			- Data (with Data length size)
	 * @param 	_message		: The message to be demounted
	 *			_messagedSigned	: For sent messages containing signature, this parameter should be true
	 */
	public void demountMessage(byte[]	_message, 
							   boolean 	_messagedSigned)
	{
		//***************
		// Decode Type //
		//***************
		
		if (SD_Message.Types.TEST.getByteValue() == _message[0]) 
		{
			this.type = SD_Message.Types.TEST;
		}

		else if (SD_Message.Types.SUBSCRIBE.getByteValue() == _message[0]) 
		{
			this.type = SD_Message.Types.SUBSCRIBE;
		}

		else if (SD_Message.Types.UNSUBSCRIBE.getByteValue() == _message[0]) 
		{
			this.type = SD_Message.Types.UNSUBSCRIBE;
		}

		else if (SD_Message.Types.REPLY_PUBLIC_KEY.getByteValue() == _message[0]) 
		{
			this.type = SD_Message.Types.REPLY_PUBLIC_KEY;
		}

		else if (SD_Message.Types.REPLY_RESOURCE_STATUS.getByteValue() == _message[0]) 
		{
			this.type = SD_Message.Types.REPLY_RESOURCE_STATUS;
		}

		else if (SD_Message.Types.REQUEST_RESOURCE.getByteValue() == _message[0]) 
		{
			this.type = SD_Message.Types.REQUEST_RESOURCE;
		}
		
		else if (SD_Message.Types.REQUEST_PUBLIC_KEY.getByteValue() == _message[0]) 
		{
			this.type = SD_Message.Types.REQUEST_PUBLIC_KEY;
		}
		
		//*************
		// Timestamp //
		//*************
		
		this.timestamp = _message[1] << 24 | (_message[2] & 0xff) << 16 | (_message[3] & 0xff) << 8 | (_message[4] & 0xff);
		
		//*************
		// Unique ID //
		//*************
		
		this.uniqueID = _message[5] << 24 | (_message[6] & 0xff) << 16 | (_message[7] & 0xff) << 8 | (_message[8] & 0xff);
		
		//***************
		// Data Length //
		//***************
		
		this.dataLength = _message[9] << 24 | (_message[10] & 0xff) << 16 | (_message[11] & 0xff) << 8 | (_message[12] & 0xff);
		
		//********
		// Data //
		//********
		
		this.data = Arrays.copyOfRange(_message, 
									   13, 
									   (13 + this.dataLength));

		if(_messagedSigned)
		{
			int firstSignatureByte = 13 + this.dataLength;
			
			//********************
			// Signature Length //
			//********************
			
			this.signatureLength = _message[firstSignatureByte] << 24 | (_message[firstSignatureByte + 1] & 0xff) << 16 | 
								   (_message[firstSignatureByte + 2] & 0xff) << 8 | (_message[firstSignatureByte + 3] & 0xff);
			
			//*************
			// Signature //
			//*************
			
			this.signature = Arrays.copyOfRange(_message, 
										   		firstSignatureByte + 4, 
										   		(firstSignatureByte + 4 + this.signatureLength));
		}	
		
		return;
	}

	/**
	 * @name	append
	 * @brief	We do NOT own this method
	 */
	public final byte[] append(final byte[]... _arrays) 
	{
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        if(_arrays != null) 
        {
            for(final byte[] array : _arrays) 
            {
                if (array != null) 
                {
                    out.write(array, 
                    		0, 
                    		array.length);
                }
            }
        }
        
        return out.toByteArray();
    }

    /**
	 * @name	appendSignature
	 * @brief	Uses the append method to append the signature to the
	 * 			previously mounted message (passed as a parameter)
	 * @param	_message	: The message
	 * @param	_signature	: The signature
	 */
	public byte[] appendSignature(byte[]	_message, 
								  byte[] 	_signature)
	{
		byte[] signedMessage = _message;

		//*******************
		// Signature Length *
		//*******************
		
		this.signatureLength = _signature.length;

        signedMessage = append(signedMessage, 
        					   ByteBuffer.allocate(4).putInt(this.signatureLength).array());

        //************
		// Signature *
		//************
        
		this.signature = _signature;
        
        signedMessage = append(signedMessage, 
        					   this.signature);
        
		return signedMessage;
	}
}
