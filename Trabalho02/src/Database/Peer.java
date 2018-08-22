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

import java.security.PrivateKey;

/**
 * @name    Peer
 * @brief
 * 
 *
 */
public class Peer {

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
     */
    public Peer( int id, byte[] publicKeyByte) {
        this.id = id;
        this.publicKeyByte = publicKeyByte;
    }

    /**
     * @name    getId
     * @brief   
     * @return
     */
    public int getId() {return id;}  

    /**
     * @name    getPublicKeyByte
     * @brief   
     * @return
     */
    public byte[] getPublicKeyByte(){return publicKeyByte;}
}
