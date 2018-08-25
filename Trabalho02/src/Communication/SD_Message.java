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

/**
 * @name 	SD_Message
 * @brief
 * 
 *
 */
public class SD_Message 
{
	/**
	 * @name	Types
	 * @brief
	 */
	public enum Types
	{
		TEST(new Integer('T')),
		SUBSCRIBE(new Integer('S')),
		UNSUBSCRIBE(new Integer('U')),
		REPLY_PUBLIC_KEY(new Integer('R')),
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
	 * @brief
	 */
	private Types type;
	
	/**
	 * @name	uniqueID
	 * @brief	4 bytes Unique ID
	 */
	private int uniqueID;
	
	/**
	 * @name	data
	 * @brief	
	 */
	private byte[] data;
	
	/**
	 * @name	dataLength
	 * @brief	4 bytes data Length
	 */
	private int dataLength;

	/**
	 * @name	SD_Message
	 * @param	_type
	 * @param	_uniqueID
	 * @param	_data
	 * @param	_debugMode
	 * @brief
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
	
	public SD_Message()
	{
		return;
	}

	/**
	 * @name	getType
	 * @brief
	 */
	public Types getType() 
	{
		return this.type;
	}
	
	/**
	 * @name	getUniqueID
	 * @brief
	 */
	public int getUniqueID() 
	{
		return this.uniqueID;
	}
	
	/**
	 * @name	getData
	 * @brief
	 */
	public byte[] getData() 
	{
		return this.data;
	}
	
	/**
	 * @name	getDataLength
	 * @brief
	 */
	public int getDataLength() 
	{
		return this.dataLength;
	}
	
	/**
	 * @name	mountMessage
	 * @brief	Message structure:
	 * 			- Type (single byte)
	 * 			- Unique ID (single byte)
	 * 			- Data length
	 * 			- Data (with Data length size)
	 */
	public byte[] mountMessage()
	{
        byte[] initMessage 	= new byte[]{this.type.getByteValue()};
        
        byte[] mountedMessage = append(initMessage,
        							   ByteBuffer.allocate(4).putInt(this.uniqueID).array());
        
        mountedMessage = append(mountedMessage, 
        					    ByteBuffer.allocate(4).putInt(this.dataLength).array());
        
        mountedMessage = append(mountedMessage, 
        					    this.data);

        System.out.println("Tipo: " + this.type.getByteValue() + "\nUnique ID: " + this.uniqueID + "\nData Length: " + dataLength + "\nData: " + this.data);
        
		return mountedMessage;
	}
	
	/**
	 * @name	demountMessage
	 * @brief	Message structure:
	 * 			- Type (single byte)
	 * 			- Unique ID (single byte)
	 * 			- Data length
	 * 			- Data (with Data length size)
	 * @param 	_message
	 */
	public void demountMessage(byte[]	_message)
	{
		System.out.println("Estou em demountMessage: size = " + _message.length);
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

		else if (SD_Message.Types.REQUEST_RESOURCE.getByteValue() == _message[0]) 
		{
			this.type = SD_Message.Types.REQUEST_RESOURCE;
		}
		
		else if (SD_Message.Types.REQUEST_PUBLIC_KEY.getByteValue() == _message[0]) 
		{
			this.type = SD_Message.Types.REQUEST_PUBLIC_KEY;
		}
		
		//*************
		// Unique ID //
		//*************
		
		this.uniqueID = _message[1] << 24 | (_message[2] & 0xff) << 16 | (_message[3] & 0xff) << 8 | (_message[4] & 0xff);
		
		//***************
		// Data Length //
		//***************
		
		this.dataLength = _message[5] << 24 | (_message[6] & 0xff) << 16 | (_message[7] & 0xff) << 8 | (_message[8] & 0xff);
		
		//********
		// Data //
		//********
		
		this.data = Arrays.copyOfRange(_message, 
									   9, 
									   (9 + this.dataLength));
		
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
}
