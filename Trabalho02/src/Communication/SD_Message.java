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
	 * @brief
	 */
	public byte[] mountMessage()
	{
		// Monta mensagem - Tipo de mensagem, ID Unico
        byte[] initMessage = new byte[]{type.getByteValue(), uniqueID};     
        // Dados
        byte[] mountedMessage = append(initMessage, data);
		
		return mountedMessage;
	}

	/**
	 * @name	append
	 * @brief	Função não autoral para cópia de array de bytes
	 */
	public static final byte[] append(final byte[]... arrays) 
	{
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (arrays != null) {
            for (final byte[] array : arrays) {
                if (array != null) {
                    out.write(array, 0, array.length);
                }
            }
        }
        return out.toByteArray();
    }
}
