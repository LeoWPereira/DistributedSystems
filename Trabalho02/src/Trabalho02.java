
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
	
	public static int deltaTime = 2;
	
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
		
		process 	= new ProcessClass();
		
		multiCast 	= new MultiCast_Manager(process,
											communicationPort, 
						  				    communicationGroup,
						  				    debugMode);
		
		multiCast.start();
		
		if(testMultiCastSocket())
		{
			System.out.println("\nÓtimo. O Socket MultiCast foi configurado com êxito!\n");
			
			requestPeers();
			
			TimeUnit.SECONDS.sleep(deltaTime);
			
			process.configProcess();
			
			//subscribe
			
			waitForPeers();
		}
		
		else
		{
			System.out.println("\nTimeout de configuração do Socket MultiCast. Favor tentar novamente!\n");
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
	 * @name	configMinimumPeersQuantity
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
									(byte)0,
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
	
	/**
	 * @name	requestPeers
	 * @brief	
	 */
	public static void requestPeers()
	{
		SD_Message sd_message;
		
		sd_message = new SD_Message(SD_Message.Types.REQUEST_PUBLIC_KEY, 
									(byte)0, 
									null, 
									debugMode);
		
		multiCast.sendMessage(sd_message.mountMessage());
		
		return;
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
}
