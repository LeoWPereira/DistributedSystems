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

import java.nio.ByteBuffer;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import Communication.MultiCast_Manager;
import Communication.SD_Message;
import Database.Resource;
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
	public static boolean debugMode = false;
	
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
			System.out.println("Ótimo. O Socket MultiCast foi configurado com êxito!\n");
			
			requestPeers();
			
			TimeUnit.SECONDS.sleep(deltaTime);
			
			process.configProcess();
			
			subscribePeer();

			//waitForPeers();

			// App Routine
			while(true)
			{
				int option = 0;

				System.out.println("Por favor, escolha uma das opções a seguir:\n1 - Alocar recurso\n2 - Liberar recurso\n3 - Listar Recursos Disponíveis\n4 - Encerrar processo");

				option = scanKeyboard.nextInt();

				switch(option)
				{
					case 1:
						System.out.println("Qual recurso deseja alocar? (" + process.getResourceList().getResourceListSize() + " disponíveis) -- Digite 0 para voltar\n");
						
						option = scanKeyboard.nextInt();

						if(0 != option)
						{
							requestResource(option);
						}

						break;

					case 2:
						System.out.println("Qual recurso deseja desalocar? (" + process.getResourceList().getResourceListSize() + " disponíveis) -- Digite 0 para voltar\n");
						
						option = scanKeyboard.nextInt();
						
						if(0 != option)
						{
							freeResource(option);
						}

						break;

					case 3:
						process.getResourceList().listAvailableResources();
						
						break;
						
					case 4:
						// First, we should deallocate every resource the peer is using
						for(int i = 1; i <= process.getResourceList().getResourceListSize(); i++)
						{
							freeResource(i);
						}
						
						// Send unsubscribe message
						unsubscribePeer();
						
						// Exit application
						return;	
					
					default:
						System.out.println("Opção não definida. Escolha novamente:");
						
						break;
				}
			}
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
		multiCast.sendSignedMessage(SD_Message.Types.UNSUBSCRIBE,
									process.getProcessID(), 
									null);

		System.out.println("Fechando aplicação");
		
		return;
	}

	/**
	 * @name 	requestResource
	 * @brief	Request resource of ID _resourceId
	 */
	public static void requestResource(int _resourceId) 
	{
		if(process.getResourceList().getResourceStatus(_resourceId) == Resource.Status.FREE)
		{
			byte[] resourceIdBytes;

			// Before requesting a resource, clear all responses received before
			process.getResourceManager().clearPreviousPeerData();

			resourceIdBytes = ByteBuffer.allocate(4).putInt(_resourceId).array();

			multiCast.sendSignedMessage(SD_Message.Types.REQUEST_RESOURCE, 
										process.getProcessID(), 
										resourceIdBytes);

			try 
			{
				waitForReplies(_resourceId);
			} 
			
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		else
		{
			System.out.println("Recurso já está sendo utilizado ou já foi requisitado!");
		}
				
		return;
	}
	
	/**
	 * @name	waitForPeers
	 * @brief	
	 * @throws 	InterruptedException
	 */
	public static void waitForPeers() throws InterruptedException
	{
		int tempoRestante = 60;
		
		System.out.println("\nAguarde até " + minimumPeers + " peers se conectarem (Timeout de " + tempoRestante + " segundos)!\n");
		
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

	/**
	 * @name	waitForReplies
	 * @brief
	 * @param 	_resourceId
	 * @throws 	InterruptedException
	 */
	public static void waitForReplies(int	_resourceId) throws InterruptedException
	{
		int remainingTime = deltaTime;
		
		boolean receivedAllReplies = false;
		
		System.out.println("\nAguardando até " + remainingTime + " segundos pelas respostas\n");
		
		while(remainingTime != 0 && !receivedAllReplies)
		{
			receivedAllReplies = process.getResourceManager().checkPeersResponse();
			
			if(receivedAllReplies)
			{
				break;
			}
			
			TimeUnit.SECONDS.sleep(1);
			
			System.out.print(".");
			
			remainingTime--;
		}

		if(receivedAllReplies)
		{
			// Checks if the resource is available
			if (process.getResourceManager().checkResourceAvailability(_resourceId))
			{
				// Allocate resource
				process.getResourceList().setResourceStatus(_resourceId, 
															Resource.Status.HELD);
				
				System.out.println("Recurso " + _resourceId + " alocado com sucesso.");
			}
			else
			{
				System.out.println("O recurso " + _resourceId + " já está sendo alocado por outro Peer.");
			}
		}
		else
		{
			
		}
		
		return;
	}

	/**
	 * @name 	freeResource
	 * @brief	Free resource of ID _resourceId
	 */
	public static void freeResource(int _resourceId) 
	{
		Resource.Status resourceStatus;

		resourceStatus = process.getResourceList().getResourceStatus(_resourceId);

		if(Resource.Status.HELD == resourceStatus)
		{
			process.getResourceList().setResourceStatus(_resourceId, 
														Resource.Status.FREE);
			
			System.out.println("Recurso " + _resourceId + " liberado com sucesso.");
		}
		
		else
		{
			System.out.println("Recurso " + _resourceId + " não se encontra alocado.");
		}
				
		return;
	}
}
