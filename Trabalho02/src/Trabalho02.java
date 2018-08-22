
/**
 ******************************************************************************
 * @file   	Trabalho02.java
 * @author	Leonardo Winter Pereira
 * @author 	Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.security.*;
import javax.crypto.Cipher;

import Communication.MultiCast_Manager;
import Communication.SD_Message;

/**
 * @name 	Trabalho02
 * @brief
 * 
 *
 */
public class Trabalho02 
{
	/**
	 * @name	scanKeyboard
	 * @brief
	 */
	private static Scanner scanKeyboard = new Scanner(System.in);
	
	/**
	 * @name	communicationPort
	 * @brief
	 */
	public static int communicationPort = 6689;
	
	/**
	 * @name	communicationGroup
	 * @brief
	 */
	public static String communicationGroup = "224.0.0.10";
	
	/**
	 * @name	multiCast
	 * @brief
	 */
	public static MultiCast_Manager multiCast;

	/**
	 * @name
	 * @brief
	 */
	public static KeyPair keyPair;
	
	/**
	 * @name
	 * @brief
	 */
	public static PrivateKey priv;
	
	/**
	 * @name
	 * @brief
	 */
	public static PublicKey pub;

	/**
	 * @name
	 * @brief
	 */
	public static byte[] privByte;

	/**
	 * @name
	 * @brief
	 */
	public static byte[] pubByte;

	/**
	 * @name
	 * @brief
	 */
	public static byte[] signature;
	
	/**
	 * @name	main
	 * @brief
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		configPort();
		
		configGroup();
		
		if(testMultiCastSocket())
		{
			System.out.println("\nÓtimo. O Socket MultiCast foi configurado com êxito!\n");
		}
		
		else
		{
			System.out.println("\nTimeout de configuração do Socket MultiCast. Favor tentar novamente!\n");
		}
		
    	try 
    	{
    		// Gera par de chaves
			generateKeyPair();
			
			// Gera assinatura para a mensagem
	    	signature = generateSignature("Teste");

	    	// verifica assinatura
	    	System.out.println("Assinatura: " +
	    		   (verifySignature("Teste", signature, pub) ? "OK" : "NOK"));
		} 
    	catch (NoSuchAlgorithmException e1) 
    	{
			e1.printStackTrace();
		}
	    
	    
        try 
        {
        	// Criptografa mensagem
            byte[] encryptedMsg = encrypt(priv, "Mensagem Exemplo");
            System.out.println(new String(encryptedMsg));
			
			// Decriptografa mensagem
	        byte[] decryptedMsg = decrypt(pub, encryptedMsg);                                 
	        System.out.println(new String(decryptedMsg));
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}     
        
		return;
	}
	
	/**
	 * @name	configPort
	 * @brief	
	 * @return
	 */
	public static void configPort()
	{
		int port = 0;
		
		System.out.println("Primeiro: Qual porta será utilizada para a comunicação (O padrão é 6689)?");
		System.out.println("Caso queira manter o padrão, digite 0");
		
		port = scanKeyboard.nextInt();
		
		if(0 != port)
		{
			communicationPort = port;
		}
		
		return;
	}
	
	/**
	 * @name	configGroup
	 * @brief	
	 * @return
	 */
	public static void configGroup()
	{
		boolean validOption = false;
		
		String group = "";
		
		System.out.println("\nSegundo: Qual será o endereço IP utilizado pelo MultiCast (O padrão é 224.0.0.10)?");
		System.out.println("Caso queira manter o padrão, digite 0");
		
		while(!validOption)
		{
			group = scanKeyboard.nextLine();
			
			if((!group.equals("")) || (group.equals("0")))
			{
				if(!group.equals("0"))
				{
					communicationGroup = group;
				}
				
				validOption = true;
			}
		}
		
		return;
	}

	/**
	 * @name	testMultiCastSocket
	 * @brief	
	 * @return
	 */
	public static boolean testMultiCastSocket() throws InterruptedException
	{
		SD_Message sd_message;
		
		int tempoRestante = 3;
		
		multiCast = new MultiCast_Manager(communicationPort, 
										  communicationGroup);
		
		multiCast.start();
		
		System.out.println("\nMuito obrigado! Aguarde alguns instantes para que eu possa testar suas configurações");
	
		sd_message = new SD_Message(null,
									SD_Message.Types.TEST,
									null);
		
		multiCast.sendMessage(sd_message.mountMessage());
	
		while(0 < tempoRestante)
		{
			TimeUnit.SECONDS.sleep(1);
			
			System.out.println(".\n");
			
			if(multiCast.getConnectionStatus())
			{
				return true;
			}
			
			tempoRestante--;
		}
		
		return false;
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
    	priv = keyPair.getPrivate();
    	pub = keyPair.getPublic();
    	// Armazena as chaves em array de bytes
    	privByte = priv.getEncoded();
    	pubByte = pub.getEncoded();
		
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
	public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception 
	{
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

        return cipher.doFinal(message.getBytes());  
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
	    	sign.initSign(priv);
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
