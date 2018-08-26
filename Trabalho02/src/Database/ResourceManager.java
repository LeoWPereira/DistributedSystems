/**
 ******************************************************************************
 * @file    ResourceManager.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Database;

/**
 * @name    ResourceManager
 * @brief
 * 
 *
 */
public class ResourceManager 
{
    /**
     * @name    peerList
     * @brief
     */
    private PeerList peerList;

    /**
     * @name    qtyResources
     * @brief
     */
    private int qtyResources;

    /**
     * @name    ResourceManager
     * @brief
     * @param	_peerList
     * @param	_qtyResources
     */
    public ResourceManager(PeerList	_peerList,
                           int 		_qtyResources)
    {
        this.peerList = _peerList;

        this.qtyResources = _qtyResources;

    	return;
    }

    /**
     * @name    setResourceStatusByPeerId
     * @brief
     * @param	_peerId
     * @param	_resourceId
     * @param	_resourceStatus 
     */
    public void setResourceStatusByPeerId(int	_peerId,
                                          int 	_resourceId,
                                          byte 	_resourceStatus) 
    {
        Peer peer = this.peerList.findPeerById(_peerId);
        
        Resource.Status resourceStatus = null;

        if(Resource.Status.FREE.getByteValue() == _resourceStatus) 
        {
            resourceStatus = Resource.Status.FREE;
        }

        else if(Resource.Status.HELD.getByteValue() == _resourceStatus) 
        {
            resourceStatus = Resource.Status.HELD;
        }

        peer.getResourceList().setResourceStatus(_resourceId, 
        										 resourceStatus);
        
        return;
    }

    /**
     * @name    setStatusResponseByPeerId
     * @brief
     * @return 
     */
    public void setStatusResponseByPeerId(int _peerId, boolean _statusResponse) 
    {
        Peer peer = this.peerList.findPeerById(_peerId);

        peer.setStatusResponse(_statusResponse);
        
        return;
    }

    /**
     * @name    checkPeersResponse
     * @brief
     * @return 
     */
    public boolean checkPeersResponse() 
    {
        boolean receivedAllReplies = true;
     
        boolean peerStatusResponse;

        for(int i = 0; i < this.peerList.getPeerListSize(); i++)
        {
            peerStatusResponse = this.peerList.getPeerByIndex(i).getStatusResponse();
         
            if(!peerStatusResponse)
            {
                receivedAllReplies = false;
            }
        }
        
        return receivedAllReplies;
    }

    /**
     * @name    checkResourceAvailability
     * @brief
     * @param   _idResource
     * @return 
     */
    public boolean checkResourceAvailability(int	_idResource) 
    {
        boolean resourceAvailable = true;

        for(int i = 0; i < this.peerList.getPeerListSize(); i++)
        {
            if(Resource.Status.HELD == this.peerList.getPeerByIndex(i).getResourceList().getResourceStatus(_idResource))
            {
                resourceAvailable = false;
            }
        }
        
        return resourceAvailable;
    }

    /**
     * @name    clearPreviousPeerData
     * @brief
     */
    public void clearPreviousPeerData() 
    {
        for(int i = 0; i < this.peerList.getPeerListSize(); i++)
        {
            // Clear all resources status
            for(int k = 1; k <= this.qtyResources; k++)
            {
                this.peerList.getPeerByIndex(i).getResourceList().setResourceStatus(k, Resource.Status.FREE);
            }

            // Set response status to false
            this.peerList.getPeerByIndex(i).setStatusResponse(false);
        }
        
        return;
    }
}
