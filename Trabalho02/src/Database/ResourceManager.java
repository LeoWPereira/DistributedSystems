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
 * @brief	Class responsible to manage the resources availability.
 * 			This is the class that actually implements the Ricart & Agrawala algorithm
 */
public class ResourceManager 
{
    /**
     * @name    peerList
     * @brief	A general list of peers.
     * 			The 'PeerList' class is defined in 'PeerList.java' file
     */
    private PeerList peerList;

    /**
     * @name    qtyResources
     * @brief	Quantity of available resource in the application
     */
    private int qtyResources;

    /**
     * @name    requestTimestamp
     * @brief
     */
    private int requestTimestamp;

    /**
     * @name    ResourceManager
     * @brief	Default class Constructor
     * @param	_peerList		: List of peers to be added to this manager
     * @param	_qtyResources	: Quantity of resources available in the application
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
     * @brief	Set a specific status for a specific resource for a specific peer
     * @param	_peerId			: the peer unique ID
     * @param	_resourceId		: the resource ID
     * @param	_resourceStatus	: the resource status
     */
    public void setResourceStatusByPeerId(int	_peerId,
                                          int 	_resourceId,
                                          byte 	_resourceStatus,
                                          int   _timestamp) 
    {
        Peer peer = this.peerList.findPeerById(_peerId);
        
        Resource.Status resourceStatus = null;

        if(null != peer)
        {
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
        }

        peer.getResourceList().setResourceStatus(_resourceId, 
        										 resourceStatus);

        peer.getResourceList().setRequestTimestamp(_resourceId, 
                                                    _timestamp);
        
        return;
    }

    /**
     * @name    setStatusResponseByPeerId
     * @brief	Searches for a specific peer and set its status response
     * @param	_peerId			: the peer unique ID
     * @param	_statusResponse	: the current peer status response
     */
    public void setStatusResponseByPeerId(int		_peerId, 
    									  boolean	_statusResponse) 
    {
        Peer peer = this.peerList.findPeerById(_peerId);

        if(null != peer)
        {
        	peer.setStatusResponse(_statusResponse);
        }
        
        return;
    }

    /**
     * @name    checkPeersResponse
     * @brief	This method is responsible to mount a PeerList (as an array)
     * 			of peers that did NOT responded ones request.
     * @return 	A peerList array of unanswered peers. This is NEVER null, though.
     * 			One should always check its size to see if it is really empty
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
     * @brief	When requesting a resource, one of the process' thread
     * 			calls this method in order to get the resource status for each one
     * 			of the available peers.
     * 			The responsible to set or reset the 'resourceAvailable' attribute is
     * 			the process' second thread.
     * @param   _idResource	: the resource ID
     * @return	true in case of resource currently available
     * 			false otherwise
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
     * @brief	For the application to be able to work properly,
     * 			we need to clear all previous Peers Data before some methods
     * 			(e.g. request resource).
     * 			This method handles this.
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
