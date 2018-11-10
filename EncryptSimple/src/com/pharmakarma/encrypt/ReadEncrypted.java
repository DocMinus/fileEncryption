package com.pharmakarma.encrypt;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ReadEncrypted {
	
    public byte[] readByte(String inFile, boolean check) throws IOException {
    	
            InputStream inputStream = new FileInputStream(inFile);
            
            BufferedInputStream in = new BufferedInputStream(inputStream, 1024*100);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] dataBuffer = new byte[1024 * 32];
            int size = 0;
            while ((size = in.read(dataBuffer)) != -1) {
                out.write(dataBuffer, 0, size);
            }
            byte[] bytes = out.toByteArray();
            if (check) {
            	for (int x = 0; x < bytes.length; x++) { // this is for the key
            		bytes[x]--;
            	}
            }
            return bytes;
        }

    }

