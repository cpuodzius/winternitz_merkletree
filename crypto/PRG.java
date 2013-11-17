package crypto;

public class PRG {

	// Stub for pseudo-random number generator
	public static byte[] getRandom(byte[] seed) {
		if(seed == null)
			return new byte[]{0};
		return seed;
	}
}
