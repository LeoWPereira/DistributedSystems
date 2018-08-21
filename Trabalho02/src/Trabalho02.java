import java.io.*;
import java.security.*;
import javax.crypto.Cipher;

public class Trabalho02 
{
	public static void main(String[] args) 
	{
	    try {
	    	// Gera par de chaves a partir de função
	    	KeyPair keyPair = gerarParChaves();
	    	// Separa chave privada e pública
	    	PrivateKey priv = keyPair.getPrivate();
	    	PublicKey pub = keyPair.getPublic();
	    	// Armazena as chaves em array de bytes
	    	byte[] privByte = priv.getEncoded();
	    	byte[] pubByte = pub.getEncoded();
	    	
	    	// Assinatura digital
	    	byte[] byteExemplo = new byte[1024];
    	    String stringExemplo = "Exemplo";
    	    byteExemplo = stringExemplo.getBytes();
    	    // Cria instância de assinatura digital com SHA256
	    	Signature sign = Signature.getInstance("SHA256withRSA");
	    	// Inicializa com chave privata gerada
	    	sign.initSign(priv);
	    	// Utiliza o byte de exemplo para assinar
    	    sign.update(byteExemplo);
    	    byte[] signature = sign.sign();
    	    
    	    // Verificar assinatura digital
    	    Signature signVerify = Signature.getInstance("SHA256withRSA");
    	    signVerify.initVerify(pub);
    	    signVerify.update(byteExemplo); 	    
    	    System.out.println(": Signature " +
    	    		   (signVerify.verify(signature) ? "OK" : "NOK"));
    	    
    	    // Criptografa mensagem
            byte [] mensagemCriptografada = criptografar(priv, "Mensagem Exemplo");     
            System.out.println(new String(mensagemCriptografada));
            
            // Decriptografa mensagem
            byte[] mensagemDecriptografada = decriptografar(pub, mensagemCriptografada);                                 
            System.out.println(new String(mensagemDecriptografada));
	
	    } catch (Exception e) {
	        System.err.println("Caught exception " + e.toString());
	    }
		
		return;
	}
	
	public static KeyPair gerarParChaves() throws NoSuchAlgorithmException {
        final int keySize = 1024;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }
	
	 public static byte[] criptografar(PrivateKey privateKey, String message) throws Exception {
	        Cipher cipher = Cipher.getInstance("RSA");  
	        cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

	        return cipher.doFinal(message.getBytes());  
	    }
	    
	    public static byte[] decriptografar(PublicKey publicKey, byte [] encrypted) throws Exception {
	        Cipher cipher = Cipher.getInstance("RSA");  
	        cipher.init(Cipher.DECRYPT_MODE, publicKey);
	        
	        return cipher.doFinal(encrypted);
	    }
}
