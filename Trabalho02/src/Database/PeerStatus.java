/**
 ******************************************************************************
 * @file    PeerStatus.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Database;

/**
 * @name    PeerStatus
 * @brief
 * 
 *
 */
public class PeerStatus 
{
    /**
     * @name    peerId
     * @brief
     */
    private int peerId;
    
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
     * @name    PeerStatus
     * @brief
     * @param	_id
     * @param   _qtyResources
     */
    public PeerStatus(int	_peerId,
                    int _qtyResources) 
    {
        this.peerId = _peerId;
        
        // Add resources to this list
        for(int i = 1; i <= _qtyResources; i++)
        {
            this.resourceList.insertResource(i);
        }

        statusResponse = false;
        
        return;
    }

    /**
     * @name    getPeerId
     * @brief   
     * @return
     */
    public int getPeerId() 
    {
    	return this.peerId;
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
