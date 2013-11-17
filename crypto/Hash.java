package crypto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import crypto.exceptions.HashException;

public class Hash {

	// Stub for has function
	public static byte[] getHash(byte[]... message) {
		ByteArrayOutputStream digest = new ByteArrayOutputStream();
		for(int i = 0; i < message.length; i++) {
			try {
				digest.write(message[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new HashException("Hash input message failed");
			}
 		}
		return digest.toByteArray();
	}
}
