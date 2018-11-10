package com.pharmakarma.encrypt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteEncrypted {
	
    public void writeByte(byte[] encrypted, String outFile, boolean combo) throws IOException {
    	
            OutputStream outputStream = new FileOutputStream(outFile);
            {
            for (byte c : encrypted) {
            	if (combo) {
            		c++;
            	}
                outputStream.write(c);
            }
        }
    }
}
