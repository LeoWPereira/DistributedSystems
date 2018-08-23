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
import javax.crypto.Cipher;

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
     * @param	_stringToBeSigned
     * @return
     */
    public byte[] generateSignature(String _stringToBeSigned)
    {
        byte[] byteToBeSigned 	= new byte[1024];
        byte[] signature		= null;
        
        Signature sign;
        
        // Convert string to byte[]
        byteToBeSigned = _stringToBeSigned.getBytes();
        
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
     * @param	_signedString
     * @param	_sign
     * @param	_pubKey
     * @return
     */
    public boolean verifySignature(String		_signedString, 
    							   byte[] 		_sign, 
    							   PublicKey	_pubKey)
    {
        boolean signatureVerification = false;
        
        byte[] signedByte;
        
        Signature signVerify = null;

        // Convert string to byte[]
        signedByte = _signedString.getBytes();

        // Verify Digital Signature
        try 
        {
            signVerify = Signature.getInstance("SHA256withRSA");
            
            signVerify.initVerify(this.pubKey);
            
            signVerify.update(signedByte);      
    
            signatureVerification = signVerify.verify(_sign);
        
            signVerify = Signature.getInstance("SHA256withRSA");
        }
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return signatureVerification;
    }
}
