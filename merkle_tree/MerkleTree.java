package merkle_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

import merkle_tree.exceptions.MerkleTreeException;
import crypto.Hash;
import winternitz.KeyPair;

public class MerkleTree {
	
	private int height;
	private List<KeyPair> leaves;
	private List<Node>[] merkleTree;

	private boolean correctSize(int size) {
		int aux = 1;
		do {
			aux <<= 1;
		} while(aux < size);
		if(aux == size)
			return true;
		else
			return false;
	}
	
	private int getHeight(int numLeaves) {
		if(numLeaves == 0)
			return 0;
		int aux = 1, exp = 0;
		while(aux < numLeaves) {
			aux <<= 1;
			exp++;
		}
		return exp + 1;
	}
	
	public MerkleTree(List<KeyPair> leaves) {
		height = getHeight(leaves.size());
		merkleTree = (List<Node>[]) new ArrayList[height];
		for(int i = 0; i < height; i++)
			merkleTree[i] = new ArrayList<Node>();
		if(!correctSize(leaves.size()))
			throw new MerkleTreeException("Tree being initialized with wrong number of key pairs");
		for(KeyPair kpair : leaves) {
			if(!kpair.isGenerated())
				throw new MerkleTreeException("Tree being initialized with empty key pair");
			else {
				Leaf leaf = new Leaf(leaves.indexOf(kpair), kpair);
				merkleTree[0].add(leaf);
			}
		}
		this.leaves = leaves;
	}
	
	public static void getNode(Leaf leaf, Stack<Node> stack) {
		Node node = leaf;
		while(!stack.isEmpty() && node.getHeight() == stack.peek().getHeight()) {
			Node top = stack.pop();
			node.setNode(node.height + 1, Node.getParentIndex(node), Hash.getHash(top.getAuth(), node.getAuth()));
		}
		stack.push(node);
	}
	
	private boolean hasNode(int height, int index) {
		try {
			if(merkleTree[height].get(index) != null)
				return true;
		}
		catch(Exception e) {
			return false;
		}
		return false;
	}
	
	private boolean hasNode(Node node) {
		try {
			if(merkleTree[node.getHeight()].get(node.getIndex()).getAuth() == node.getAuth())
				return true;
		}
		catch(Exception e) {
			return false;
		}
		return false;
	}
	
	public void add(Node node) {
		if(!hasNode(node.getHeight(), node.getIndex()))
			merkleTree[node.getHeight()].add(node);
	}
	
	private void printLine(int height, List<Node> nodes) {
		int hsize = 5; // [x,y]
		if(height > 9)
			hsize++;
		
		int nspace = 1;
		
		for(int i = 0; i <= height; i++)
			nspace *= 2;
		
		nspace--;
		
		for(int i = 0, index = 0; i < nodes.size(); index++) {
			if(index == 0) {
				for(int j = 0; j < nspace / 2; j++) {
					for(int k = 0; k < hsize; k++)
						System.out.print(" ");
				}
			}
			else {
				for(int j = 0; j < nspace; j++) {
					for(int k = 0; k < hsize; k++)
						System.out.print(" ");
				}
			}
			
			if(index == 5 && height > 0) 		// item from line above increase by 1
				hsize++;
			
			if(nodes.get(i).getIndex() == index) {
				nodes.get(i).print();
				i++;
			}
			else {
				int psize = (i > 9) ? hsize + 2 : hsize;
				for(int j = 0; j < psize; j++)
					System.out.print("*");
			}
		}
	}
	
	private void printVSpace(int vspace) {
		for(int j = 0; j <= vspace; j++)
			System.out.print('\n');
	}
	
	public void print() {
		//int hspace = 3; // vspace must be odd (for better formatting)
		int vspace = 1;
		for(int i = 0; i < height; i++) {
			if(i < 10)
				System.out.print("h = " + i + " - - -  ");
			else
				System.out.print("h = " + i + " - - - ");
			printLine(i, merkleTree[i]);
			printVSpace(vspace);
		}
	}
}
