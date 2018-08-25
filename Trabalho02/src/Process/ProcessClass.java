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
	public ProcessClass() throws Exception
	{
		this.cryptography 	= new Crypto();
		
		this.peerList		= new PeerList();
		
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
			if(true)
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
