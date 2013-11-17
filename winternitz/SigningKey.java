package winternitz;

import crypto.PRG;

public class SigningKey {

	boolean generated;
	byte[] data;
	
	public SigningKey() {
		generated = false;
	}
	
	public byte[] keyGen() {
		
		if(generated)
			return null;
		
		data = PRG.getRandom(null);
		return Winternitz.OWF(data);
	}
}
