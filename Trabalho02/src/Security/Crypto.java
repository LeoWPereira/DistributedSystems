/**
 ******************************************************************************
 * @file    Crypto.java
 * @author  Leonardo Winter Pereira
 * @author  Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Security;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import Communication.SD_Message;

/**
 * @name    Crypto
 * @brief   Class responsible for managing keys and encryption.
 *          Mostly used for generating/verifying and
 *          encrypting/decrypting messages.
*/
public class Crypto 
{    
    /**
     * @name    keySize
     * @brief   Size of the keys - Used while generating keys.
     */
	private final int keySize = 1024;
	
    /**
     * @name	keyPair
     * @brief   Structure containing both the PublicKey and PrivateKey.
     */
    private KeyPair keyPair;
    
    /**
     * @name	privKey
     * @brief   Private Key used for generating message signatures.
     */
    private PrivateKey privKey;
    
    /**
     * @name	pubKey
     * @brief   Public Key used for sharing it to other peers.
     *          This key is responsible for verifying signatures
     *          generated using the Private Key.
     */
    private PublicKey pubKey;

    /**
     * @name	privKeyByte
     * @brief   Private Key in bytes used for generating message signatures.
     */
    private byte[] privKeyByte;

    /**
     * @name	pubKeyByte
     * @brief   Public Key in bytes used for sharing it to other peers.
     *          This key is responsible for verifying signatures
     *          generated using the Private Key.
     */
    private byte[] pubKeyByte;
    
    /**
     * @name	Crypto
     * @brief    Default Class Constructor
     *           Automatically generates a key pair.
     */
    public Crypto() throws NoSuchAlgorithmException 
    {
        generateKeyPair();
        
        return;
    }
    
    /**
     * @name	getPublicKeyByte
     * @brief   Default Getter
     */
    public byte[] getPublicKeyByte() 
    {
        return this.pubKeyByte;
    }

    /**
     * @name    getPrivateKeyByte
     * @brief   Default Getter
     */
    public byte[] getPrivateKeyByte() 
    {
        return this.privKeyByte;
    }

    /**
     * @name    getPublicKey
     * @brief   Default Getter
     */
    public PublicKey getPublicKey() 
    {
        return this.pubKey;
    }

    /**
     * @name    getPrivateKey
     * @brief   Default Getter
     */
    public PrivateKey getPrivateKey() 
    {
        return this.privKey;
    }

    /**
     * @name    generateKeyPair
     * @brief   Method for generating a key pair for the Crypto object.   
     * @throws NoSuchAlgorithmException 
     */
    public void generateKeyPair() throws NoSuchAlgorithmException
    {
    	this.keyPair = initializeKeyPair();
        
        // Get the private and public keys
    	this.privKey	= keyPair.getPrivate();
    	this.pubKey 	= keyPair.getPublic();
        
        // Get the byte[] array from the keys
    	this.privKeyByte	= privKey.getEncoded();
    	this.pubKeyByte 	= pubKey.getEncoded();
        
        return;
    }

    /**
     * @name    initializeKeyPair
     * @brief   Method for initializing a key pair.
     *          The RSA Algorithm is used for creating a instance of
     *          the KeyPairGenerator. Also, the keySize variable defines
     *          the size of the keys.
     */
    public KeyPair initializeKeyPair() throws NoSuchAlgorithmException 
    {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        
        keyPairGenerator.initialize(this.keySize);      
        
        return keyPairGenerator.genKeyPair();
    }
    
    /**
     * @name    encrypt
     * @brief   Encrypt method used for encrypting a message
     *          using the RSA Algorithm and this object private key.
     * @param	_message
     * @return
     */
    public byte[] encrypt(String	_message) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("RSA");  
        
        cipher.init(Cipher.ENCRYPT_MODE, 
        			this.privKey);  

        return cipher.doFinal(_message.getBytes());  
    }

    /**
     * @name    encryptByte
     * @brief  	Encrypt method used for encrypting a message in bytes
     *          using the RSA Algorithm and this object private key.
     * @param	_messageByte 
     * @return
     */
    public byte[] encryptByte(byte[]	_messageByte) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("RSA");  
        
        cipher.init(Cipher.ENCRYPT_MODE, 
        			this.privKey);  

        return cipher.doFinal(_messageByte);  
    }
    
    /**
     * @name    decrypt
     * @brief   Decrypt method used for decrypting a message in bytes
     *          using the RSA Algorithm and a public key.
     * @param	_publicKey
     * @param	_encrypted
     * @return
     */
    public byte[] decrypt(PublicKey	_publicKey, 
    					  byte[] 	_encrypted) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("RSA");  
        
        cipher.init(Cipher.DECRYPT_MODE, 
        			_publicKey);
        
        return cipher.doFinal(_encrypted);
    }

    /**
     * @name    generateSignature
     * @brief   Method used for generating a signature of a given
     *          message in bytes. The signature is generated using
     *          SHA256 with the RSA Algorithm.
     * @param	_byteToBeSigned
     * @return
     */
    public byte[] generateSignature(byte[] _byteToBeSigned)
    {
        byte[] byteToBeSigned 	= _byteToBeSigned;
        byte[] signature		= null;
        Signature sign;
        
        // Digital Signature
        try 
        {
            sign = Signature.getInstance("SHA256withRSA");
            
            sign.initSign(this.privKey);

            sign.update(byteToBeSigned);
            
            signature = sign.sign();
        } 
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return signature;
    }

    /**
     * @name    verifySignature
     * @brief   Verify a signature by using a given PublicKey in bytes (_pubKeyByte).
    *           The signature to be verified is inside the SD_Message _message, as 
    *           well as the message itself.
     * @param	_message
     * @param	_pubKeyByte
     * @return
     * @throws Exception 
     */
    public boolean verifySignature(SD_Message  	_message, 
    							   byte[]	   	_pubKeyByte) throws Exception
    {
        boolean signatureVerification = false;
        
        byte[] signedByte;
        
        Signature signVerify = null;

        // Convert string to byte[]
        signedByte = _message.mountMessage();

        // Convert byte array to public key
        KeyFactory kf = KeyFactory.getInstance("RSA"); 
        
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(_pubKeyByte);
        
        PublicKey _pubKey = kf.generatePublic(publicKeySpec);

        // Verify Digital Signature
        try 
        {
            signVerify = Signature.getInstance("SHA256withRSA");
            
            signVerify.initVerify(_pubKey);
            
            signVerify.update(signedByte);      
    
            signatureVerification = signVerify.verify(_message.getSignature());
        }
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return signatureVerification;
    }
}
