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
 * @brief	
 */
public class ProcessClass 
{
	/**
	 * @name	scanKeyboard
	 * @brief
	 */
	private Scanner scanKeyboard = new Scanner(System.in);

	/**
	 * @name	processID
	 * @brief	
	 */
	private int processID = 0;
	
	/**
	 * @name
	 * @brief
	 */
	private Crypto cryptography;

	/**
	 * @name	peerList
	 * @brief
	 */
	private PeerList peerList;

	/**
	 * @name	resourceList
	 * @brief
	 */
	private ResourceList resourceList;

	/**
	 * @name	qtyResources
	 * @brief
	 */
	private int qtyResources;

	/**
	 * @name	resourceManager
	 * @brief
	 */
	private ResourceManager resourceManager;
	
	/**
	 * @name	getProcessID
	 * @brief	
	 */
	public int getProcessID() 
	{
		return processID;
	}

	/**
	 * @name	getProcessID
	 * @brief	
	 */
	public Crypto getCriptography() 
	{
		return this.cryptography;
	}
	
	/**
	 * @name	getPeerList
	 * @brief	
	 */
	public PeerList getPeerList() 
	{
		return this.peerList;
	}

	/**
	 * @name	getResourceList
	 * @brief	
	 */
	public ResourceList getResourceList() 
	{
		return this.resourceList;
	}

	/**
	 * @name	getResourceManager
	 * @brief	
	 */
	public ResourceManager getResourceManager() 
	{
		return this.resourceManager;
	}
	
	/**
	 * @name	setProcessID
	 * @brief	
	 */
	public void setProcessID(int processID) 
	{
		this.processID = processID;
	}

	/**
	 * @name	getQtyResources
	 * @brief	
	 */
	public int getQtyResources() 
	{
		return this.qtyResources;
	}
	
	/**
	 * @name	ProcessClass
	 * @brief
	 * @param	_qtyResources
	 */
	public ProcessClass(int _qtyResources) throws Exception
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
	 * @brief	
	 * @return
	 */
	public void configProcess()
	{
		int id = 0;
		
		System.out.println("\nDigite um ID para você (Sujeito à verificação - único no grupo):");
		
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
