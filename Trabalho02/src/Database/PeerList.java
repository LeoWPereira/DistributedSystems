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
    private static ArrayList<Peer> peerList = new ArrayList<Peer>();

    /**
     * @name    ListaPeer
     * @brief
     */
    public PeerList() {}

    /**
     * @name    insertPeer
     * @brief
     */
    public void insertPeer(int idPeer, byte[] publicKeyByte) 
    {
        peerList.add(new Peer(idPeer, publicKeyByte));
        System.out.println("Peer adicionado com ID " + idPeer + " e chave pública " + publicKeyByte + '\n');
    }

    /**
     * @name    removePeer
     * @brief
     */
    public void removePeer(int idPeer) 
    {
        Peer peer = findPeerById(idPeer);
        this.peerList.remove(peer);
        System.out.println("Peer com ID" + peer.getId() + "removido.");

    }

    /**
     * @name    findPeerById
     * @brief
     */
    public Peer findPeerById(int idPeer) {
        for (int i = 0; i < this.peerList.size(); i++) {
            if ((this.peerList.get(i).getId() == idPeer)) {
                return this.peerList.get(i);
            }
        }
        System.out.println("Peer não foi encontrado");
        return null;
    }
    
     /**
     * @name    getPublicKeyByte
     * @brief
     */
    public byte[] getPublicKeyByte(int idPeer) {
        Peer peer = findPeerById(idPeer);
        if (peer != null) {
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
