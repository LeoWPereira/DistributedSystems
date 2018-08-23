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
 * @brief
 * 
 *
 */
public class PeerList 
{
    /**
     * @name    peerList
     * @brief
     */
    private ArrayList<Peer> peerList = new ArrayList<Peer>();

    /**
     * @name    ListaPeer
     * @brief
     */
    public PeerList()
    {
    	return;
    }

    /**
     * @name    insertPeer
     * @param	_idPeer
     * @param	_publicKeyByte
     * @brief
     */
    public void insertPeer(int		_idPeer, 
    					   byte[]	_publicKeyByte) 
    {
        this.peerList.add(new Peer(_idPeer, 
        					  	   _publicKeyByte));
        
        System.out.println("Peer adicionado com ID " + _idPeer + " e chave publica " + _publicKeyByte + '\n');
        
        return;
    }

    /**
     * @name    removePeer
     * @brief
     * @param	_idPeer
     */
    public void removePeer(int	_idPeer) 
    {
        Peer peer = findPeerById(_idPeer);
        
        this.peerList.remove(peer);
        
        System.out.println("Peer com ID" + peer.getId() + "removido\n");

        return;
    }

    /**
     * @name    findPeerById
     * @brief
     * @param	_idPeer
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
        
        System.out.println("Peer nao foi encontrado.");
        
        return null;
    }
    
     /**
     * @name    getPublicKeyByte
     * @brief
     * @param	_idPeer
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
     * @brief
     */
    public int getPeerListSize() 
    {
        return this.peerList.size();
    }
}
