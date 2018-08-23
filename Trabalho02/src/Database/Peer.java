/**
 ******************************************************************************
 * @file    Peer.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Database;

/**
 * @name    Peer
 * @brief
 * 
 *
 */
public class Peer 
{
    /**
     * @name    id
     * @brief
     */
    private int id;
    
    /**
     * @name    publicKeyByte
     * @brief
     */
    private byte[] publicKeyByte;

    /**
     * @name    Peer
     * @brief
     * @param	_id
     * @param	_publicKeyByte
     */
    public Peer(int		_id, 
    			byte[] 	_publicKeyByte) 
    {
        this.id = _id;
        
        this.publicKeyByte = _publicKeyByte;
        
        return;
    }

    /**
     * @name    getId
     * @brief   
     * @return
     */
    public int getId() 
    {
    	return this.id;
	}  

    /**
     * @name    getPublicKeyByte
     * @brief   
     * @return
     */
    public byte[] getPublicKeyByte()
    {
    	return publicKeyByte;
    }
}
