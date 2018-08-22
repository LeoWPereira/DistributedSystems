package Communication;
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
		TEST(new Integer('T'));
		
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
	 * @name	publicKey
	 * @brief
	 */
	private byte[] publicKey;
	
	/**
	 * @name	data
	 * @brief
	 */
	private byte[] data;

	/**
	 * @name	type
	 * @brief
	 */
	private Types type;
	
	public SD_Message(byte[] _publicKey,
					  Types  _type,
					  byte[] _data)
	{
		this.publicKey	= _publicKey;
		this.type		= _type;
		this.data 		= _data;
		
		return;
	}
	
	/**
	 * @name	getPublicKey
	 * @brief
	 */
	public byte[] getPublicKey() 
	{
		return publicKey;
	}

	/**
	 * @name	getData
	 * @brief
	 */
	public byte[] getData() 
	{
		return data;
	}

	/**
	 * @name	getType
	 * @brief
	 */
	public Types getType() 
	{
		return type;
	}
	
	public byte[] mountMessage()
	{
		byte[] mountedMessage = null;
		
		mountedMessage = new byte[]{this.getType().getByteValue()};
		
		return mountedMessage;
	}
}
