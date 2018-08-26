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
	 * @name	setProcessID
	 * @brief	
	 */
	public void setProcessID(int processID) 
	{
		this.processID = processID;
	}
	
	/**
	 * @name	ProcessClass
	 * @brief
	 */
	public ProcessClass(int qtyResources) throws Exception
	{
		this.cryptography 	= new Crypto();
		
		this.peerList		= new PeerList();

		this.resourceList   = new ResourceList();

		addResources(qtyResources);
		
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

	/**
	 * @name	addResources
	 * @brief	
	 * @param	__qtyResources
	 */
	public void addResources(int	_qtyResources)
	{
		// Add resources to this process list
		for(int i = 1; i <= _qtyResources; i++)
		{
			this.resourceList.insertResource(i);
		}
		
		return;
	}
}
