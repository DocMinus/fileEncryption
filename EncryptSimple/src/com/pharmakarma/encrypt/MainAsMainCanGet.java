package com.pharmakarma.encrypt;

/**
 * Simple encryption and decryption system based on some public example
 * The originator is unfortunately unknown, most likely open-source though
 * Note that this system isn't fool proof against 
 * for example mid meaning man in the middle attacks
 * Thus just for testing the concept, nothing one would seriously want to use.
 * 
 *  @author Alex M at Pharmakarma
 *
 */
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MainAsMainCanGet {

	public static void main(String[] args) throws Exception {
		//since this is a short app, majority contained here in main

		//add file manually here, e.g. a csv file at ~/knimeData/encryptExample/unencrypted.csv
		String filePath = System.getProperty("user.home") + File.separator + "knimeData" + File.separator + "encryptExample" + File.separator;
		//Read file to encrypt (no catch/throw included here); add more Lists for additional files
		List<String> inputLines = Files.readAllLines(Paths.get(filePath + "unencrypted.csv"));
		
		System.out.print("Reading file(s) and encrypting!....\n"); 
		{
			KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
	        SecretKey myDesKey = keygenerator.generateKey();
	        Cipher desCipher;
	        desCipher = Cipher.getInstance("DES");
	        
	        //convert to byte stream
	        Serializer serious = new Serializer();
	  
	        byte[] text2 = serious.serialize(inputLines);
	        byte[] textKey = serious.serialize(myDesKey);
	        
	        //encrypt
	        desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
	        byte[] text2Encrypted = desCipher.doFinal(text2);
	      
	        //write encrypted and generated key to files
	        WriteEncrypted wE = new WriteEncrypted();
	        wE.writeByte(text2Encrypted, filePath + "encrypted", false);
	        wE.writeByte(textKey, filePath + ".key", true); //storing they key, pseudoencrypted
     	}
        System.out.print("Encryption done!\n");        
         
        //to check, read back the files
        System.out.print("Reading back and decrpyting...!\n"); 
        ReadEncrypted rE = new ReadEncrypted();
        byte[] text2Read = rE.readByte(filePath + "encrypted", false); //read encrypted file
        byte[] keyRead = rE.readByte(filePath + ".key", true); //retrieve key, pseudoencryption
        Serializer serious2 = new Serializer();
        
        //decrypt
        KeyGenerator keygenerator2 = KeyGenerator.getInstance("DES");
        SecretKey inKey = (SecretKey) serious2.deserialize(keyRead);
        Cipher desCipher2;
        desCipher2 = Cipher.getInstance("DES");
        desCipher2.init(Cipher.DECRYPT_MODE, inKey);
   
        byte[] text2Decrypted = desCipher2.doFinal(text2Read);

        
        //Convert back from byte mode to intended object                
        List<String> decryptedLine = (List<String>) serious2.deserialize(text2Decrypted); 
        //just to check
        System.out.println(decryptedLine);
        System.out.print("All done!\n");  
	}

}
