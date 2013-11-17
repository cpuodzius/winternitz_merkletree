package winternitz;

public class KeyPair {
	
	private boolean generated;
	private VerifyingKey pkey;
	private SigningKey skey;
	
	public KeyPair() {
		skey = new SigningKey();
		pkey = new VerifyingKey();
		generated = false;
	}

	public VerifyingKey getPublicKey() {
		return pkey;
	}
	
	public void keyGen() {
		pkey.keyGen(skey);
		generated = true;
	}
	
	public boolean isGenerated() {
		return generated;
	}
}
