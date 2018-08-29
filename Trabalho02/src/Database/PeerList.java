/**
 ******************************************************************************
 * @file    PeerList.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Database;

import java.util.ArrayList;

/**
 * @name    PeerList
 * @brief	Class responsible to insert and remove peers from
 * 			a list of peers.
 * 			Initially it is null.
 */
public class PeerList 
{
    /**
     * @name    peerList
     * @brief	Simple array of peers.
     */
    private ArrayList<Peer> peerList = new ArrayList<Peer>();

    /**
     * @name    PeerList
     * @brief	Default constructor does not need to do anything
     */
    public PeerList()
    {
    	return;
    }

    /**
     * @name    insertPeer
     * @brief	If the peer is not already in the list, insert it.
     * 			Otherwise, do nothing.
     * @param	_idPeer			: The peer unique ID
     * @param	_publicKeyByte	: The peer public key
     * @param	_qtyResources	: Quantity of resources for this peer
     */
    public void insertPeer(int		_idPeer, 
    					   byte[]	_publicKeyByte,
                           int      _qtyResources) 
    {
    	if(null == this.findPeerById(_idPeer))
    	{
	        this.peerList.add(new Peer(_idPeer, 
	        					  	   _publicKeyByte,
	                                   _qtyResources));
	        
	        System.out.println("Peer adicionado com ID " + _idPeer + " e chave pública " + _publicKeyByte + '\n');
    	}
    	
        return;
    }
    
    /**
     * @name    insertPeer
     * @brief	Inserts a peer in the peer list
     * @param	_peer	: Peer to be added
     */
    public void insertPeer(Peer	_peer) 
    {
        this.peerList.add(new Peer(_peer.getId(), 
        					  	   _peer.getPublicKeyByte(),
                                   0));
    	
        return;
    }

    /**
     * @name    removePeer
     * @brief	Find peer and removes it from the list.
     * 			For safety, it only asks for the removal once
     * 			it has been found
     * @param	_idPeer	: the wanted peer unique ID
     */
    public void removePeer(int	_idPeer) 
    {
        Peer peer = findPeerById(_idPeer);
        
        if (peer != null) 
        {
        	this.peerList.remove(peer);
            
            System.out.println("Peer com ID " + peer.getId() + " removido da lista de Peers.\n");
        }

        return;
    }

    /**
     * @name    findPeerById
     * @brief	Look inside the peer List for the desired peer.
     * @param	_idPeer	: the wanted peer unique ID
     * @return	null if peer is not in the list
     * 			Peer class otherwise
     */
    public Peer findPeerById(int	_idPeer) 
    {
        for(int i = 0; i < this.peerList.size(); i++) 
        {
            if((this.peerList.get(i).getId() == _idPeer)) 
            {
                return this.peerList.get(i);
            }
        }
        
        return null;
    }
    
     /**
     * @name    getPublicKeyByte
     * @brief	Search for and returns the peer public key
     * @param	_idPeer	: the wanted peer unique ID
     * @return	null if peer not found
     * 			byte array if peer has been found
     */
    public byte[] getPublicKeyByte(int	_idPeer) 
    {
        Peer peer = findPeerById(_idPeer);
        
        if (peer != null) 
        {
            return peer.getPublicKeyByte();
        }
        
        return null;
    }

    /**
     * @name    getPeerListSize
     * @brief	Default getter
     */
    public int getPeerListSize() 
    {
        return this.peerList.size();
    }
    
    /**
     * @name    getPeerList
     * @brief	Default getter
     */
    public ArrayList<Peer> getPeerList() 
    {
        return this.peerList;
    }

    /**
     * @name    getPeerByIndex
     * @brief	Default getter
     */
    public Peer getPeerByIndex(int _index) 
    {
        return this.peerList.get(_index);
    }
}
