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
     * @name    resourceList
     * @brief
     */
    private ResourceList resourceList;

    /**
     * @name    statusResponse
     * @brief
     */
    private boolean statusResponse;

    /**
     * @name    Peer
     * @brief
     * @param	_id
     * @param	_publicKeyByte
     */
    public Peer(int		_id, 
    			byte[] 	_publicKeyByte,
                int     _qtyResources) 
    {
        this.id = _id;
        
        this.publicKeyByte = _publicKeyByte;
        
        this.resourceList = new ResourceList(_qtyResources);

        this.statusResponse = false;
        
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

    /**
     * @name    getResourceList
     * @brief   
     * @return
     */
    public ResourceList getResourceList()
    {
        return this.resourceList;
    }

    /**
     * @name    getStatusResponse
     * @brief   
     * @return
     */
    public boolean getStatusResponse() 
    {
        return this.statusResponse;
    }

    /**
     * @name    setStatusResponse
     * @brief   
     * @return
     */
    public void setStatusResponse(boolean _statusResponse) 
    {
        this.statusResponse = _statusResponse;

        return;
    }  
}
