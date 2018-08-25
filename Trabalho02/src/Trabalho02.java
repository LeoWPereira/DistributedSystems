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
import Communication.MultiCast_Manager;
import Communication.SD_Message;
import Process.ProcessClass;

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
	 * @name	process
	 * @brief
	 */
	public static ProcessClass process;
	
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
	 * @name	deltaTime
	 * @brief	value in seconds
	 */
	public static int deltaTime = 2;

	/**
	 * @name	qtyResources
	 * @brief
	 */
	public static int qtyResources = 2;
	
	/**
	 * @name	debugMode
	 * @brief
	 */
	public static boolean debugMode = true;
	
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
		
		process 	= new ProcessClass(qtyResources);
		
		multiCast 	= new MultiCast_Manager(process,
											communicationPort, 
						  				    communicationGroup,
						  				    debugMode);
		
		multiCast.start();
		
		if(testMultiCastSocket())
		{
			System.out.println("�timo. O Socket MultiCast foi configurado com �xito!\n");
			
			requestPeers();
			
			TimeUnit.SECONDS.sleep(deltaTime);
			
			process.configProcess();
			
			subscribePeer();

			//waitForPeers();

			// Rotina da aplica��o
			while(true)
			{
				int option = 0;

				System.out.println("Por favor, escolha uma da op��es a seguir:\n1 - Alocar recurso de n�mero 1\n2 - Alocar recurso de n�mero 2\n3 - Encerrar processo");
				option = scanKeyboard.nextInt();

				switch(option)
				{
					case 1:
						// Allocates resource 1
					break;

					case 2:
					{
						// Allocates resource 2

					}
					break;

					case 3:
					{
						// Exit application
						// Send unsubscribe message
						unsubscribePeer();
					}
					break;
				}
			}
		}
		
		else
		{
			System.out.println("\nTimeout de configura��o do Socket MultiCast. Favor tentar novamente!\n");
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
		
		System.out.println("Primeiro: Qual porta ser� utilizada para a comunica��o (O padr�o � 6689)?");
		System.out.println("Caso queira manter o padr�o, digite 0");
		
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
		
		System.out.println("\nSegundo: Qual ser� o endere�o IP utilizado pelo MultiCast (O padr�o � 224.0.0.10)?");
		System.out.println("Caso queira manter o padr�o, digite 0");
		
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
	 * @name	configMinimumPeersQuantity
	 * @brief	
	 * @return
	 */
	public static void configMinimumPeersQuantity()
	{
		int minPeers = 0;
		
		System.out.println("Quantos Peers devem se registrar antes da aplica��o iniciar (O padr�o � 3)?");
		System.out.println("Caso queira manter o padr�o, digite 0");
		
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
		
		System.out.println("\nMuito obrigado! Aguarde alguns instantes para que eu possa testar suas configura��es");
	
		sd_message = new SD_Message(SD_Message.Types.TEST,
									0,
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
	 * @name	requestPeers
	 * @brief	
	 */
	public static void requestPeers()
	{
		SD_Message sd_message;
		
		sd_message = new SD_Message(SD_Message.Types.REQUEST_PUBLIC_KEY, 
									0, 
									null);
		
		multiCast.sendMessage(sd_message.mountMessage());
		
		return;
	}

	/**
	 * @name	subscribePeer
	 * @brief	
	 */
	public static void subscribePeer()
	{
		SD_Message sd_message;
		
		sd_message = new SD_Message(SD_Message.Types.SUBSCRIBE, 
									process.getProcessID(), 
									process.getCriptography().getPublicKeyByte());
		
		multiCast.sendMessage(sd_message.mountMessage());
		
		return;
	}

	/**
	 * @name	unsubscribePeer
	 * @brief	
	 */
	public static void unsubscribePeer()
	{
		sendSignedMessage(SD_Message.Types.UNSUBSCRIBE, 
									process.getProcessID(), 
									null);

		System.out.println("Fechando aplica��o");	
		return;
	}

	/**
	 * @name 	sendSignedMessage
	 * @brief	Mounts a message, signs it and sends it
	 */
	public static void sendSignedMessage(SD_Message.Types _type, int _processId, byte[] _pubKey) 
	{
		byte[] signature;
		byte[] mountedMessage;

		SD_Message sd_message = new SD_Message(_type,
											_processId,
											_pubKey);

		mountedMessage = sd_message.mountMessage();

		signature = process.getCriptography().generateSignature(mountedMessage);

		multiCast.sendMessage(sd_message.appendSignature(mountedMessage, signature));
				
		return;
	}
	
	public static void waitForPeers() throws InterruptedException
	{
		int tempoRestante = 60;
		
		System.out.println("\nAguarde at� " + minimumPeers + " peers se conectarem (Timeout de " + tempoRestante + " segundos)!\n");
		
		while(0 < tempoRestante)
		{
			TimeUnit.SECONDS.sleep(1);
			
			System.out.print(".");
			
			if(process.getPeerList().getPeerListSize() == minimumPeers)
			{
				break;
			}
			
			tempoRestante--;
		}
		
		return;
	}
}
