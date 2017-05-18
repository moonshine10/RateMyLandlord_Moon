package db.encription;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;




public class AESExample {

	private static final String Algo="AES";
	private byte[] keyValue;
	
	public AESExample(String key){
		keyValue=key.getBytes();
	}
	
	public String encrypt(String data)throws Exception{
		Cipher c=Cipher.getInstance(Algo);
		SecretKeySpec sks=new SecretKeySpec(keyValue,Algo);
		c.init(Cipher.ENCRYPT_MODE, sks);
		byte[]encVal=c.doFinal(data.getBytes());
		String encryptedValue=new BASE64Encoder().encode(encVal);
		return encryptedValue;

	}
	
	
	public String decrypt(String data)throws Exception{
		Cipher c=Cipher.getInstance(Algo);
		SecretKeySpec sks=new SecretKeySpec(keyValue,Algo);
		c.init(Cipher.DECRYPT_MODE, sks);
		byte[] dv=new BASE64Decoder().decodeBuffer(data);
		byte[] decVal=c.doFinal(dv);
		String FinalDecValue=new String(decVal);
		return FinalDecValue;

	}
	
}
