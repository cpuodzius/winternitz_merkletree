package winternitz;

public class VerifyingKey {

	private byte[] data;
	
	public byte[] getBytes() {
		return data;
	}
	
	public void keyGen(SigningKey skey) {
		data = skey.keyGen();
	}
	
}
