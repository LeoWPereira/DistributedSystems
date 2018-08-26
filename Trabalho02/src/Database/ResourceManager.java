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

import java.util.ArrayList;

import Database.Resource.Status;

/**
 * @name    ResourceManager
 * @brief
 * 
 *
 */
public class ResourceManager 
{
    /**
     * @name    peerStatusList
     * @brief
     */
    private ArrayList<PeerStatus> peerStatusList = new ArrayList<PeerStatus>();

    /**
     * @name    qtyResources
     * @brief
     */
    private int qtyResources;

    /**
     * @name    ResourceManager
     * @brief
     */
    public ResourceManager(PeerList _peerList,
                        int _qtyResources)
    {
        this.qtyResources = _qtyResources;

        // Add a peerStatus for each peer in the list   
        for(int i = 0; i < _peerList.getPeerListSize(); i++)
        {
            peerStatusList.add(new PeerStatus(_peerList.getPeerByIndex(i).getId(), _qtyResources));
        }

    	return;
    }

    /**
     * @name    setResourceStatusByPeerId
     * @brief
     * @return 
     */
    public void setResourceStatusByPeerId(int _peerId,
                                        int _resourceId,
                                        byte _resourceStatus) 
    {
        PeerStatus peerStatus = findPeerStatusById(_peerId);
        Resource.Status resourceStatus = null;

        if (Resource.Status.FREE.getByteValue() == _resourceStatus) 
        {
            resourceStatus = Resource.Status.FREE;
        }

        else if (Resource.Status.HELD.getByteValue() == _resourceStatus) 
        {
            resourceStatus = Resource.Status.HELD;
        }

        peerStatus.getResourceList().setResourceStatus(_resourceId, resourceStatus);
        
        return;
    }

    /**
     * @name    findPeerStatusById
     * @brief
     * @param   _idPeerStatus
     */
    public PeerStatus findPeerStatusById(int _idPeerStatus) 
    {
        for(int i = 0; i < this.peerStatusList.size(); i++) 
        {
            if((this.peerStatusList.get(i).getPeerId() == _idPeerStatus)) 
            {
                return this.peerStatusList.get(i);
            }
        }
        
        return null;
    }

    /**
     * @name    checkPeersResponse
     * @brief
     * @return 
     */
    public boolean checkPeersResponse() 
    {
        boolean receivedAllReplies = true;

        for(int i = 0; i < this.peerStatusList.size(); i++)
        {
            if(this.peerStatusList.get(i).getStatusResponse())
            {
                receivedAllReplies = false;
            }
        }
        
        return receivedAllReplies;
    }

    /**
     * @name    removePeerStatus
     * @brief
     * @param	_idPeerStatus
     */
    public void removePeerStatus(int	_idPeerStatus) 
    {
        PeerStatus peerStatus = findPeerStatusById(_idPeerStatus);
        
        this.peerStatusList.remove(peerStatus);

        return;
    }

    /**
     * @name    checkResourceAvailability
     * @brief
     * @param   _idResource
     * @return 
     */
    public boolean checkResourceAvailability(int _idResource) 
    {
        boolean resourceAvailable = true;

        for(int i = 0; i < this.peerStatusList.size(); i++)
        {
            if(Resource.Status.HELD == this.peerStatusList.get(i).getResourceList().getResourceStatus(_idResource))
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
        for(int i = 0; i < this.peerStatusList.size(); i++)
        {
            // Clear all resources status
            for(int k = 1; k <= this.qtyResources; k++)
            {
                this.peerStatusList.get(i).getResourceList().setResourceStatus(k, Resource.Status.FREE);
            }

            // Set response status to false
            this.peerStatusList.get(i).setStatusResponse(false);
        }
        
        return;
    }
}
