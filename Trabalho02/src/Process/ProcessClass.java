/**
 ******************************************************************************
 * @file   	ProcessClass.java
 * @author	Leonardo Winter Pereira
 * @author 	Luis Felipe Mazzuchetti Ortiz
 * @version v1.0
 * @date    16 de ago de 2018
 * @brief
 ******************************************************************************
 */

package Process;

import java.util.Scanner;

import Database.PeerList;
import Database.ResourceList;
import Database.ResourceManager;
import Security.Crypto;

/**
 * @name	Process
 * @brief	The main class used for the peer.
 * 			It stores every needed information, such as:
 * 				- Process ID
 * 				- Cryptography informations
 * 				- List of connected peers
 * 				- List of available resources
 */
public class ProcessClass 
{
	/**
	 * @name	scanKeyboard
	 * @brief	The scanKeyboard is responsible for reading the console 
	 * 			in look for final users responses
	 */
	private Scanner scanKeyboard = new Scanner(System.in);

	/**
	 * @name	processID
	 * @brief	The process unique ID.
	 * 			This is the most important attribute of the class, 
	 * 			alongside the cryptography attribute.
	 */
	private int processID = 0;
	
	/**
	 * @name	cryptography
	 * @brief	Stores cryptography informations,
	 * 			such as public key, private key...
	 * 			It is important to note that this attribute is only
	 * 			visible to the current process.
	 * 			Other peers cryptography informations are available inside
	 * 			peerList member.
	 */
	private Crypto cryptography;

	/**
	 * @name	peerList
	 * @brief	Stores the information of other connected peers.
	 */
	private PeerList peerList;

	/**
	 * @name	resourceList
	 * @brief	List of available resources
	 */
	private ResourceList resourceList;

	/**
	 * @name	qtyResources
	 * @brief	Quantity of available resources
	 */
	private int qtyResources;

	/**
	 * @name	resourceManager
	 * @brief	The responsible for the usage of Ricart & Agrawala algorithm.
	 * 			This private attribute manages the resources statuses and the allocation
	 * 			of them.
	 */
	private ResourceManager resourceManager;
	
	/**
	 * @name	getProcessID
	 * @brief	Default Getter
	 */
	public int getProcessID() 
	{
		return processID;
	}

	/**
	 * @name	getProcessID
	 * @brief	Default Getter
	 */
	public Crypto getCriptography() 
	{
		return this.cryptography;
	}
	
	/**
	 * @name	getPeerList
	 * @brief	Default Getter
	 */
	public PeerList getPeerList() 
	{
		return this.peerList;
	}

	/**
	 * @name	getResourceList
	 * @brief	Default Getter
	 */
	public ResourceList getResourceList() 
	{
		return this.resourceList;
	}

	/**
	 * @name	getResourceManager
	 * @brief	Default Getter
	 */
	public ResourceManager getResourceManager() 
	{
		return this.resourceManager;
	}
	
	/**
	 * @name	setProcessID
	 * @brief	Default Setter
	 */
	public void setProcessID(int processID) 
	{
		this.processID = processID;
	}

	/**
	 * @name	getQtyResources
	 * @brief	Default Getter
	 */
	public int getQtyResources() 
	{
		return this.qtyResources;
	}
	
	/**
	 * @name	ProcessClass
	 * @brief	Default Class Constructor
	 * @param	_qtyResources	: Quantity of resources of the application
	 */
	public ProcessClass(int	_qtyResources) throws Exception
	{
		this.cryptography 	 = new Crypto();
		
		this.peerList		 = new PeerList();

		this.qtyResources   = _qtyResources;

		this.resourceList    = new ResourceList(this.qtyResources);

		this.resourceManager = new ResourceManager(this.peerList, this.qtyResources);
		
		return;
	}
	
	/**
	 * @name	configProcess
	 * @brief	Just a fancy method to ask the user for the process ID
	 * 			It then checks in its list of peers for some peer with the exact
	 * 			same ID.
	 */
	public void configProcess()
	{
		int id = 0;
		
		System.out.println("\nDigite um ID para você:");
		System.out.println("Ele será verificado para ser único no Multi Cast");
		
		do
		{
			id = scanKeyboard.nextInt();
			
			// Verify process ID
			if(null == this.peerList.findPeerById(id))
			{
				System.out.println("ID configurado com êxito!");
				
				processID = id;
				
				break;
			}
			
			else
			{
				System.out.println("ID já utilizado, escolha outro:");
			}
		} while(true);
		
		return;
	}
}
