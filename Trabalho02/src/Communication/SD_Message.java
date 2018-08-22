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

/**
 * @name 	SD_Message
 * @brief
 * 
 *
 */
public class SD_Message 
{
	/**
	 * @name	debugMode
	 * @brief
	 */
	private boolean debugMode;
	
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
		REQUEST_RESOURCE(new Integer('Q'));
		
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
	private byte[] uniqueID;
	
	/**
	 * @name	data
	 * @brief
	 */
	private byte[] data;

	public SD_Message(Types  	_type,
					  byte[] 	_uniqueID,
					  byte[] 	_data,
					  boolean	_debugMode)
	{
		this.type		= _type;
		this.uniqueID	= _uniqueID;
		this.data 		= _data;
		
		this.debugMode	= _debugMode;
		
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
	public byte[] getUniqueID() 
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
	 * @brief
	 */
	public byte[] mountMessage()
	{
		byte[] mountedMessage = null;
		
		mountedMessage = new byte[]{this.getType().getByteValue()};
		
		return mountedMessage;
	}
}
