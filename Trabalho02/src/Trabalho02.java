/**
 ******************************************************************************
 * @file   	Trabalho02.java
 * @author	Leonardo Winter Pereira
 * @author 	Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief	Main File of the Project.
 * 			This is the caller and the main executor of one of the thread for
 * 			every process.
 ******************************************************************************
 */

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import Communication.MultiCast_Manager;
import Communication.SD_Message;
import Database.Peer;
import Database.PeerList;
import Database.Resource;
import Process.ProcessClass;

/**
 * @name 	Trabalho02
 * @brief	Class responsible for declaring constants, variables and every
 * 			static member of the project
 */
public class Trabalho02 
{
	/**
	 * @name	scanKeyboard
	 * @brief	The scanKeyboard is responsible for reading the console 
	 * 			in look for final users responses
	 */
	private static Scanner scanKeyboard = new Scanner(System.in);
	
	/**
	 * @name	multiCast
	 * @brief	the multiCast is a static member responsible for the
	 * 			management of the entire MultiCast solution
	 */
	public static MultiCast_Manager multiCast;
	
	/**
	 * @name	process
	 * @brief	A process is the manager of each single peer in the
	 * 			solution. It stores its id, cryptographic information 
	 * 			and the list of peers he knows.
	 */
	public static ProcessClass process;
	
	/**
	 * @name	communicationPort
	 * @brief	As a static member of the solution, the communicationPort
	 * 			stores the desired port to be used for the multiCast.
	 * 			As default, the value 6689 is defined. But it can be changed
	 * 			in runtime by the user
	 */
	public static int communicationPort = 6689;
	
	/**
	 * @name	communicationGroup
	 * @brief	As a static member of the solution, the communicationGroup
	 * 			stores the desired IP address to be used for the multiCast.
	 * 			As default, the value 224.0.0.10 is defined. But it can be changed
	 * 			in runtime by the user
	 */
	public static String communicationGroup = "224.0.0.10";
	
	/**
	 * @name	minimumPeers
	 * @brief	As a static member of the solution, the minimumPeers
	 * 			stores the integer value of how much Peers must be initially 
	 * 			connected for the application to be started.
	 * 			As default, the value 3 is defined. But it can be changed
	 * 			in runtime by the user.
	 */
	public static int minimumPeers = 3;
	
	/**
	 * @name	maximumWaitTime
	 * @brief	in addition for the rules written by the teacher, we decided 
	 * 			to add a maximum time that the peers wait for other peers to 
	 * 			be connected. After this time, the application starts.
	 */
	public static int maximumWaitTime = 60;
	
	/**
	 * @name	deltaTime
	 * @brief	value in seconds representing the max time
	 * 			that one Peer is going to wait for other peers
	 * 			answers.
	 * 			After this time, it will consider that the corresponding
	 * 			peer is offline and must be removed for the peer List.
	 */
	public static int deltaTime = 2;

	/**
	 * @name	qtyResources
	 * @brief	As a static member of the solution, the qtyResources
	 * 			stores the desired number of resources to be available
	 * 			in the solution.
	 * 			As written in the document 'spec.pdf', the default value 
	 * 			of 2 is used.
	 */
	public static int qtyResources = 2;
	
	/**
	 * @name	debugMode
	 * @brief	Enable this if you want the have a few more
	 * 			debug messages printed in the terminal.
	 * 			It may be used to enable test-only features!
	 */
	public static boolean debugMode = false;
	
	/**
	 * @name	main
	 * @brief	The main function is the core of the application
	 * 			To facilitate reading, it only calls several methods
	 * @param 	args : default void main parameter
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
						  				    minimumPeers,
						  				    debugMode);
		
		multiCast.start();
		
		if(testMultiCastSocket())
		{
			System.out.println("Ótimo. O Socket MultiCast foi configurado com êxito!\n");
			
			requestPeers();
			
			process.configProcess();
			
			subscribePeer();

			waitForPeers();

			runAppRoutine();
		}
		
		else
		{
			System.out.println("\nTimeout de configuração do Socket MultiCast. Favor reiniciar a aplicação e tentar novamente!\n");
		}
		
		return;
	}
	
	/**
	 * @name	configPort
	 * @brief	Function responsible for asking the user for the desired
	 * 			communication port.
	 * 			It has an option to use the default value
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
	 * @brief	Function responsible for asking the user for the desired 
	 * 			communication IP address.
	 * 			It has an option to use the default value
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
	 * @brief	Function responsible for asking the user for the desired 
	 * 			number of peers to be available prior the the main application 
	 * 			execution starts.
	 * 			It has an option to use the default value
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
	 * @brief	Function responsible to send a branch new TEST
	 * 			message.
	 * 			By sending the process_id as 0 to SD_Message, other 
	 * 			processes do NOT consider this as a valid peers wanting 
	 * 			to subscribe to the multiCast.
	 * 			Thereat its only purpose is to guarantee the peers has correct
	 * 			settings for the multiCast
	 * @return	returns the 
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
	public static void requestPeers() throws InterruptedException
	{
		SD_Message sd_message;
		
		sd_message = new SD_Message(SD_Message.Types.REQUEST_PUBLIC_KEY, 
									0, 
									null);
		
		multiCast.sendMessage(sd_message.mountMessage());
		
		TimeUnit.SECONDS.sleep(deltaTime);
		
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
									null,
									0);

		multiCast.stop();
		
		System.out.println("Fechando aplicação");
		
		return;
	}

	/**
	 * @name 	requestResource
	 * @brief	Request resource of ID _resourceId
	 */
	public static void requestResource(int _resourceId) 
	{
		if(process.getResourceList().getResourceStatus(_resourceId) == Resource.Status.RELEASED)
		{
			byte[] resourceIdBytes;
			int msgTimespamp;

			// Before requesting a resource, clear all responses received before
			process.getResourceManager().clearPreviousPeerData();

			resourceIdBytes = ByteBuffer.allocate(4).putInt(_resourceId).array();

			// Store timestamp into the resourceManager class
			msgTimespamp = Calendar.getInstance().get(Calendar.MILLISECOND);

			process.getResourceManager().setRequestTimestamp(msgTimespamp);

			// Set the designated resource to the wanted state
			process.getResourceList().setResourceStatus(_resourceId, 
														Resource.Status.WANTED);

			// Send signed message with the same timestamp
			multiCast.sendSignedMessage(SD_Message.Types.REQUEST_RESOURCE, 
										process.getProcessID(), 
										resourceIdBytes,
										msgTimespamp);

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
		int tempoRestante = maximumWaitTime;
		
		System.out.println("\nAguarde até " + minimumPeers + " peers se conectarem (Timeout de " + tempoRestante + " segundos)!\n");
		
		while(0 < tempoRestante)
		{
			TimeUnit.SECONDS.sleep(1);
			
			System.out.print(".");
			
			if((process.getPeerList().getPeerListSize() == (minimumPeers - 1)) || (multiCast.getInitialSetOK()))
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
		PeerList unansweredPeerList = new PeerList();
		
		int remainingTime = deltaTime;
		
		boolean receivedAllReplies = false;
		
		System.out.println("\nAguardando até " + remainingTime + " segundos pelas respostas\n");
		
		while(remainingTime != 0 && !receivedAllReplies)
		{
			unansweredPeerList = process.getResourceManager().checkPeersResponse();
			
			if(0 == unansweredPeerList.getPeerListSize())
			{
				receivedAllReplies = true;
				
				break;
			}
			
			TimeUnit.SECONDS.sleep(1);
			
			System.out.print(".");
			
			remainingTime--;
		}

		if(receivedAllReplies)
		{
			// TEST CONCURRENCE
			// Change the first peer to the wanted state
			process.getPeerList().getPeerByIndex(0).getResourceList().setResourceStatus(_resourceId, Resource.Status.WANTED);
			// Change its timestamp request for the amount for the chosen test
			process.getPeerList().getPeerByIndex(0).getResourceList().setRequestTimestamp(_resourceId, process.getResourceManager().getRequestTimestamp() - 1);
			
			
			// Checks if the resource is available
			if (process.getResourceManager().checkResourceAvailability(_resourceId, process.getProcessID()))
			{
				// Allocate resource
				process.getResourceList().setResourceStatus(_resourceId, 
															Resource.Status.HELD);
				
				System.out.println("Recurso " + _resourceId + " alocado com sucesso.");
			}
			else
			{
				System.out.println("O recurso " + _resourceId + " já está sendo alocado por outro Peer.");
				// Set resource to released state
				process.getResourceList().setResourceStatus(_resourceId, 
															Resource.Status.RELEASED);
			}
		}
		
		// Peer Fail - should remove the peer in question
		else
		{
			for(Peer peer : unansweredPeerList.getPeerList())
			{
				System.out.println("Falha no Peer " + peer.getId());
				
				process.getPeerList().removePeer(peer.getId());
			}

			// Set resource to released state
			process.getResourceList().setResourceStatus(_resourceId, 
														Resource.Status.RELEASED);
		}
		
		return;
	}

	/**
	 * @name 	releaseResource
	 * @brief	Release resource of ID _resourceId
	 */
	public static void releaseResource(int _resourceId) 
	{
		Resource.Status resourceStatus;

		resourceStatus = process.getResourceList().getResourceStatus(_resourceId);

		if(Resource.Status.HELD == resourceStatus)
		{
			process.getResourceList().setResourceStatus(_resourceId, 
														Resource.Status.RELEASED);
			
			System.out.println("Recurso " + _resourceId + " liberado com sucesso.");
		}
		
		else
		{
			System.out.println("Recurso " + _resourceId + " não se encontra alocado.");
		}
				
		return;
	}
	
	/**
	 * @name
	 * @brief
	 */
	public static void runAppRoutine()
	{
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
						releaseResource(option);
					}

					break;

				case 3:
					process.getResourceList().listAvailableResources();
					
					break;
					
				case 4:
					// First, we should deallocate every resource the peer is using
					for(int i = 1; i <= process.getResourceList().getResourceListSize(); i++)
					{
						releaseResource(i);
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
}
