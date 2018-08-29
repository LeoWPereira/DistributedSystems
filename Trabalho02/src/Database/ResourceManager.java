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
     * @name    requestTimestamp
     * @brief
     */
    private int requestTimestamp;

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
                                          byte 	_resourceStatus,
                                          int   _timestamp) 
    {
        Peer peer = this.peerList.findPeerById(_peerId);
        
        Resource.Status resourceStatus = null;

        if(Resource.Status.RELEASED.getByteValue() == _resourceStatus) 
        {
            resourceStatus = Resource.Status.RELEASED;
        }

        else if(Resource.Status.HELD.getByteValue() == _resourceStatus) 
        {
            resourceStatus = Resource.Status.HELD;
        }
        
        else if(Resource.Status.WANTED.getByteValue() == _resourceStatus) 
        {
            resourceStatus = Resource.Status.WANTED;
        }

        peer.getResourceList().setResourceStatus(_resourceId, 
        										 resourceStatus);

        peer.getResourceList().setRequestTimestamp(_resourceId, 
                                                    _timestamp);
        
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
    public PeerList checkPeersResponse() 
    {
    	PeerList unansweredPeerList = new PeerList();
    
        boolean peerStatusResponse;

        for(int i = 0; i < this.peerList.getPeerListSize(); i++)
        {
            peerStatusResponse = this.peerList.getPeerByIndex(i).getStatusResponse();
         
            if(!peerStatusResponse)
            {
            	unansweredPeerList.insertPeer(this.peerList.getPeerByIndex(i));
            }
        }
        
        return unansweredPeerList;
    }

    /**
     * @name    checkResourceAvailability
     * @brief
     * @param   _idResource
     * @return 
     */
    public boolean checkResourceAvailability(int	_idResource,
                                             int    _processId) 
    {
        boolean resourceAvailable = true;

        for(int i = 0; i < this.peerList.getPeerListSize(); i++)
        {
            // Checks if the resource is HELD by another process
            if(Resource.Status.HELD == this.peerList.getPeerByIndex(i).getResourceList().getResourceStatus(_idResource))
            {
                resourceAvailable = false;
            }
            // Also checks if another process want to allocate this resource
            else if(Resource.Status.WANTED == this.peerList.getPeerByIndex(i).getResourceList().getResourceStatus(_idResource))
            {
                // Check if the requests timestamps are lower or equal
                if(this.requestTimestamp > this.peerList.getPeerByIndex(i).getResourceList().getResquestTimestamp(_idResource))
                {
                    // Another process requested first
                    resourceAvailable = false;

                    this.peerList.getPeerByIndex(i).getResourceList().setResourceStatus(_idResource, Resource.Status.RELEASED);
                }
                else if(this.requestTimestamp == this.peerList.getPeerByIndex(i).getResourceList().getResquestTimestamp(_idResource))
                {
                    // Check the process ID
                    if(_processId > this.peerList.getPeerByIndex(i).getId())
                    {
                        // The other process ID is higher, it has the priority
                        resourceAvailable = false;

                        this.peerList.getPeerByIndex(i).getResourceList().setResourceStatus(_idResource, Resource.Status.RELEASED);

                    }

                }
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
                this.peerList.getPeerByIndex(i).getResourceList().setResourceStatus(k, Resource.Status.RELEASED);
                this.peerList.getPeerByIndex(i).getResourceList().setRequestTimestamp(k, 0);
            }

            // Set response status to false
            this.peerList.getPeerByIndex(i).setStatusResponse(false);
        }
        
        return;
    }

    /**
     * @name    getRequestTimestamp
     * @brief
     */
    public int getRequestTimestamp() 
    {
        return this.requestTimestamp;
    }

    /**
     * @name    setRequestTimestamp
     * @brief
     */
    public void setRequestTimestamp(int _requestTimestamp) 
    {
        this.requestTimestamp = _requestTimestamp;
        
        return;
    }
}
