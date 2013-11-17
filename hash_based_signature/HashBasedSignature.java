package hash_based_signature;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import merkle_tree.Leaf;
import merkle_tree.MerkleTree;
import merkle_tree.Node;
import winternitz.KeyPair;

public class HashBasedSignature {
	
	private static void printStack(Stack<Node> stack) {
		System.out.print("{ ");
		for(Node node : stack) {
			System.out.print("[" + node.getHeight() + "," + node.getIndex() + "] ");
		}
		System.out.print("}\n");
	}

	static boolean HashTreeConstructionTest() {
		final int numKeys = 16;
		List<KeyPair> leaves = new ArrayList<KeyPair>();
		for(int i = 0; i < numKeys; i++) {
			KeyPair kp = new KeyPair();
			kp.keyGen();
			leaves.add(kp);
		}
		MerkleTree mt = new MerkleTree(leaves);
		Stack<Node> stack = new Stack<Node>();
 		for(int i = 0; i < leaves.size(); i++) {
			Node node = new Leaf(i, leaves.get(i));
			MerkleTree.getNode((Leaf) node, stack);
			mt.add(node);
			printStack(stack);
		}
		mt.print();
		return true;
	}
	
	public static void main(String[] args) {
		HashTreeConstructionTest();
		System.out.println("All tests successfully accomplished");
	}

}
