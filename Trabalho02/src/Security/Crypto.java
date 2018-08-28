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
 * @name	Crypto
 * @brief
 */
public class Crypto 
{    
	private final int keySize = 1024;
	
    /**
     * @name	keyPair
     * @brief
     */
    private KeyPair keyPair;
    
    /**
     * @name	privKey
     * @brief
     */
    private PrivateKey privKey;
    
    /**
     * @name	pubKey
     * @brief
     */
    private PublicKey pubKey;

    /**
     * @name	privKeyByte
     * @brief
     */
    private byte[] privKeyByte;

    /**
     * @name	pubKeyByte
     * @brief
     */
    private byte[] pubKeyByte;
    
    /**
     * @name	Crypto
     * @brief
     */
    public Crypto() throws NoSuchAlgorithmException 
    {
        generateKeyPair();
        
        return;
    }
    
    /**
     * @name	getPublicKeyByte
     * @brief   
     * @return
     */
    public byte[] getPublicKeyByte() 
    {
        return this.pubKeyByte;
    }

    /**
     * @name    getPrivateKeyByte
     * @brief   
     * @return
     */
    public byte[] getPrivateKeyByte() 
    {
        return this.privKeyByte;
    }

    /**
     * @name    getPublicKey
     * @brief   
     * @return
     */
    public PublicKey getPublicKey() 
    {
        return this.pubKey;
    }

    /**
     * @name    getPrivateKey
     * @brief   
     * @return
     */
    public PrivateKey getPrivateKey() 
    {
        return this.privKey;
    }

    /**
     * @name    generateKeyPair
     * @brief   
     * @return
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
     * @brief   
     * @return
     */
    public KeyPair initializeKeyPair() throws NoSuchAlgorithmException 
    {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        
        keyPairGenerator.initialize(this.keySize);      
        
        return keyPairGenerator.genKeyPair();
    }
    
    /**
     * @name    encrypt
     * @brief   
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
     * @brief  	
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
     * @brief   
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
     * @brief   
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
     * @brief 
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
