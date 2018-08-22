
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
import Security.Crypto;

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
	 * @name	multiCast
	 * @brief
	 */
	public static MultiCast_Manager multiCast;
	
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
	 * @name	minimumPeers
	 * @brief
	 */
	public static int minimumPeers = 3;
	
	/**
	 * @name	debugMode
	 * @brief
	 */
	public static boolean debugMode = false;

	/**
	 * @name
	 * @brief
	 */
	private static Crypto cryptography;
	
	/**
	 * @name	main
	 * @brief
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		configPort();
		
		configGroup();
		
		configMinimumPeersQuantity();
		
		multiCast = new MultiCast_Manager(communicationPort, 
						  				  communicationGroup,
						  				  debugMode);
		
		multiCast.start();
		
		if(testMultiCastSocket())
		{
			System.out.println("\nÓtimo. O Socket MultiCast foi configurado com êxito!\n");
			
			//subscribe
			
			// request public keys
			
			waitForPeers();
		}
		
		else
		{
			System.out.println("\nTimeout de configuração do Socket MultiCast. Favor tentar novamente!\n");
		}
		
    	testCryptography();
    	
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
	 * @name	configPort
	 * @brief	
	 * @return
	 */
	public static void configMinimumPeersQuantity()
	{
		int minPeers = 0;
		
		System.out.println("Quantos Peers devem se registrar antes da aplicação iniciar (O padrão é 3)?");
		System.out.println("Caso queira manter o padrão, digite 0");
		
		minPeers = scanKeyboard.nextInt();
		
		if(0 != minPeers)
		{
			minimumPeers = minPeers;
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
		
		System.out.println("\nMuito obrigado! Aguarde alguns instantes para que eu possa testar suas configurações");
	
		sd_message = new SD_Message(SD_Message.Types.TEST,
									null,
									null,
									debugMode);
		
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
	
	public static void waitForPeers() throws InterruptedException
	{
		int tempoRestante = 60;
		
		System.out.println("\nAguarde até " + minimumPeers + " peers se conectarem (Timeout de " + tempoRestante + " segundos)!\n");
		
		while(0 < tempoRestante)
		{
			TimeUnit.SECONDS.sleep(1);
			
			System.out.print(".");
			
			if(multiCast.getPeers().size() == minimumPeers)
			{
				break;
			}
			
			tempoRestante--;
		}
		
		return;
	}

	/**
	 * @name	testCryptography
	 * @brief	
	 * @return
	 * @throws Exception 
	 */
	public static void testCryptography() throws Exception
	{
		cryptography = new Crypto();
    		
		byte[] sign;
		// Gera assinatura para a mensagem
		sign = cryptography.generateSignature("Teste");

    	// verifica assinatura
    	System.out.println("Assinatura: " +
    		   (cryptography.verifySignature("Teste", sign, cryptography.getPublicKey()) ? "OK" : "NOK"));

    	// Criptografa mensagem
    	byte[] encryptedMsg = cryptography.encrypt("Mensagem Exemplo");
    	System.out.println(new String(encryptedMsg));
    	
		// Decriptografa mensagem
    	System.out.println(new String(cryptography.decrypt(cryptography.getPublicKey(), encryptedMsg)));
		
		return;
	}
}
