package Process;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

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
	 * @name
	 * @brief
	 */
	private static Crypto cryptography;
	
	/**
	 * @name	processID
	 * @brief	
	 */
	private byte processID = 0;

	/**
	 * @name	getProcessID
	 * @brief	
	 */
	public byte getProcessID() 
	{
		return processID;
	}

	/**
	 * @name	getProcessID
	 * @brief	
	 */
	public Crypto getCriptography() 
	{
		return cryptography;
	}
	
	/**
	 * @name	setProcessID
	 * @brief	
	 */
	public void setProcessID(byte processID) 
	{
		this.processID = processID;
	}
	
	/**
	 * @name	
	 * @brief
	 */
	public ProcessClass() throws Exception
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
	
	/**
	 * @name	configProcess
	 * @brief	
	 * @return
	 */
	public void configProcess()
	{
		Integer id = 0;
		
		System.out.println("\nDigite um ID para você:");
		System.out.println("Ele será verificado para ser único no Multi Cast");
		
		do
		{
			id = scanKeyboard.nextInt();
			
			// Verify process ID
			if(true)
			{
				System.out.println("ID configurado com êxito!");
				
				processID = id.byteValue();
				
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
