package db.encription;

import java.io.InputStream;
import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;




public class AES256 {

	private static final String Algo="AES";
	byte[] key;
	
	public AES256(Key key){
		this.key=key.getEncoded();
	}
	
	
	public String encrypt(String data)throws Exception{
		Cipher c=Cipher.getInstance(Algo);
		c.init(Cipher.ENCRYPT_MODE, generateSecKey());
		byte[]encVal=c.doFinal(data.getBytes());
		String encryptedValue=new BASE64Encoder().encode(encVal);
		return encryptedValue;

	}
	
	
	public String decrypt(String data)throws Exception{
		Cipher c=Cipher.getInstance(Algo);
		c.init(Cipher.DECRYPT_MODE, generateSecKey());
		byte[] dv=new BASE64Decoder().decodeBuffer(data);
		byte[] decVal=c.doFinal(dv);
		String FinalDecValue=new String(decVal);
		return FinalDecValue;

	}
	
	
	public SecretKeySpec generateSecKey(){

		SecretKeySpec Seckey= new SecretKeySpec(key,Algo);
		return Seckey;
	}
	
}
