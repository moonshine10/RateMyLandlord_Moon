package db.encription;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Key;

import org.junit.Test;

import Config.Keystore;

public class AEStest {

	@Test
	public void encrypt_decryptAESExampleTest() throws Exception {
		String pw1="12345";
		String key="770A8A65DA156D24EE2A093277530142";
		AESExample a= new AESExample(key);
		String enc_pw1=a.encrypt(pw1);
//		System.out.println(enc_pw1);
		
		assertNotEquals(enc_pw1,pw1);
		String fin=a.decrypt(enc_pw1);
//		System.out.println(fin);
		assertEquals(fin,pw1);
		
	}
	
	@Test
	public void encrypt_decryptAESTest() throws Exception {
		String pw1="123456789";
		String keystoreLocation= Keystore.keystoreLocation;
		String keystorePass=Keystore.keystorePass;
		String alias=Keystore.keystorealias;
		String keyPass=Keystore.keyPass;
		Key key=KeystoreUtil.getKeyFromKeystore(keystoreLocation,keystorePass,   alias,   keyPass);
		AES256 a= new AES256(key);
		String enc_pw1=a.encrypt(pw1);
		System.out.println(enc_pw1);
		assertNotEquals(enc_pw1,pw1);

		
		String fin=a.decrypt(enc_pw1);
		System.out.println(fin);
		assertEquals(fin,pw1);
		
	}


}
