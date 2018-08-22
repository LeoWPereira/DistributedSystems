
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

import java.security.InvalidAlgorithmParameterException;
import java.security.*;
import javax.crypto.Cipher;

/*
* Classe responsável por gerar e armazenas a chave pública e privada de cada processo.
* Além disso, realiza operações de criptografia e assinatura digital.
 */

/**
 * @name
 * @brief
 */
public class Crypto {
    
    /**
     * @name
     * @brief
     */
    private static KeyPair keyPair;
    
    /**
     * @name
     * @brief
     */
    private static PrivateKey privKey;
    
    /**
     * @name
     * @brief
     */
    private static PublicKey pubKey;

    /**
     * @name
     * @brief
     */
    private static byte[] privKeyByte;

    /**
     * @name
     * @brief
     */
    private static byte[] pubKeyByte;
    
    /**
     * @name
     * @brief
     */
    public Crypto() throws NoSuchAlgorithmException {
        // Gera par de chaves
        generateKeyPair();
    }
    
    /**
     * @name    
     * @brief   
     * @return
     */
    public byte[] getPublicKeyByte() {
        return pubKeyByte;
    }

    /**
     * @name    
     * @brief   
     * @return
     */
    public byte[] getPrivateKeyByte() {
        return privKeyByte;
    }

    /**
     * @name    
     * @brief   
     * @return
     */
    public PublicKey getPublicKey() {
        return this.pubKey;
    }

    /**
     * @name    
     * @brief   
     * @return
     */
    public PrivateKey getPrivateKey() {
        return this.privKey;
    }

    /**
     * @name    
     * @brief   
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public static void generateKeyPair() throws NoSuchAlgorithmException
    {
        // Inicializa par de chaves
        keyPair = initializeKeyPair();
        // Separa chave privada e pública
        privKey = keyPair.getPrivate();
        pubKey = keyPair.getPublic();
        // Armazena as chaves em array de bytes
        privKeyByte = privKey.getEncoded();
        pubKeyByte = pubKey.getEncoded();
        
        return;
    }

    /**
     * @name    
     * @brief   
     * @return
     */
    public static KeyPair initializeKeyPair() throws NoSuchAlgorithmException 
    {
        final int keySize = 1024;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }
    
    /**
     * @name    
     * @brief   
     * @return
     */
    public static byte[] encrypt(String message) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privKey);  

        return cipher.doFinal(message.getBytes());  
    }

    /**
     * @name    
     * @brief   
     * @return
     */
    public static byte[] encryptByte(byte[] messageByte) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privKey);  

        return cipher.doFinal(messageByte);  
    }
    
    /**
     * @name    
     * @brief   
     * @return
     */
    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        
        return cipher.doFinal(encrypted);
    }

    /**
     * @name    
     * @brief   
     * @return
     */
    public static byte[] generateSignature(String stringToBeSigned)
    {
        byte[] byteToBeSigned = new byte[1024];
        Signature sign;
        byte[] signature = null;

        // Convert string to byte[]
        byteToBeSigned = stringToBeSigned.getBytes();
        
        // Assinatura digital
        try 
        {
            // Cria instância de assinatura digital com SHA256
            sign = Signature.getInstance("SHA256withRSA");
            // Inicializa com chave privata gerada
            sign.initSign(privKey);
            // Utiliza o byte de exemplo para assinar
            sign.update(byteToBeSigned);
            signature = sign.sign();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return signature;
    }

    /**
     * @name    
     * @brief   
     * @return
     */
    public static boolean verifySignature(String signedString, byte[] sign, PublicKey pubKey)
    {
        boolean signatureVerification = false;
        byte[] signedByte;  
        Signature signVerify = null;

        // Convert string to byte[]
        signedByte = signedString.getBytes();

        // Verificar assinatura digital
        try 
        {
            signVerify = Signature.getInstance("SHA256withRSA");
            signVerify.initVerify(pubKey);
            signVerify.update(signedByte);      
    
            // Verifica se a assinatura esta correta
            signatureVerification = signVerify.verify(sign);
        
            signVerify = Signature.getInstance("SHA256withRSA");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return signatureVerification;
    }
}
