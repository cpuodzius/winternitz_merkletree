package merkle_tree;

import winternitz.KeyPair;

public class Leaf extends Node {

	KeyPair kpair;
	public Leaf(int index, KeyPair keyPair) {
		super(0, index,  keyPair.getPublicKey().getBytes());
		kpair = keyPair;
	}

}
