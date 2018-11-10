package com.pharmakarma.encrypt;

/*
 * This class converts any object (for example lists)
 * to byte[] for the purpose of encryption/decryption
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    public byte[] serialize(Object obj) throws IOException {
    	// make byte[] array
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(obj);
            }
            return b.toByteArray();
        }
    }

    public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
    	//back to the original object (a cast might be necessary)
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return o.readObject();
            }
        }
    }

}