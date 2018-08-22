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
	 * @name	main
	 * @brief
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		System.out.println("Bem-vinda professora Ana Cristina!");
		
		System.out.println("\nVamos começar o trabalho 02?\n");
		
		configPort();
		
		configGroup();
		
		if(testMultiCastSocket())
		{
			
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
		boolean returnValue = false;
		
		int tempoRestante = 2;
		
		multiCast = new MultiCast_Manager(communicationPort, 
										  communicationGroup);
		
		multiCast.start();
		
		System.out.println("\nMuito obrigado! Aguarde alguns instantes para que eu possa testar suas configurações");
		
		while(0 < tempoRestante)
		{
			TimeUnit.SECONDS.sleep(1);
			
			System.out.println(".\n");
			
			tempoRestante--;
		}
		
		
		
		return returnValue;
	}
}
