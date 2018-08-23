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
	 * @brief
	 */
	private byte uniqueID;
	
	/**
	 * @name	data
	 * @brief
	 */
	private byte[] data;

	/**
	 * @name	SD_Message
	 * @param	_type
	 * @param	_uniqueID
	 * @param	_data
	 * @param	_debugMode
	 * @brief
	 */
	public SD_Message(Types  	_type,
					  byte 		_uniqueID,
					  byte[] 	_data)
	{
		this.type		= _type;
		this.uniqueID	= _uniqueID;
		this.data 		= _data;
		
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
	public byte getUniqueID() 
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
	 * @name	mountMessage
	 * @brief	Message structure:
	 * 			- Type (single byte)
	 * 			- Unique ID (single byte)
	 * 			- Data length
	 * 			- Data (with Data length size)
	 */
	public byte[] mountMessage()
	{
		// Byte version of data length
		Integer dataLength	= new Integer(0);
				
		if(null != this.data)
		{
			dataLength = this.data.length;
		}
		
        byte[] initMessage 	= new byte[]{this.type.getByteValue(), 
        								 this.uniqueID,
        								 dataLength.byteValue()};
        
        byte[] mountedMessage = append(initMessage, 
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
