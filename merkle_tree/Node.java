package merkle_tree;

public class Node {

	int height;
	int index;
	byte[] auth;
	
	public Node(int height, int index, byte[] auth) {
		this.height = height;
		this.index = index;
		this.auth = auth;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getIndex() {
		return index;
	}
	
	public byte[] getAuth() {
		return auth;
	}
	
	public void setNode(int height, int index, byte[] auth) {
		this.height = height;
		this.index = index;
		this.auth = auth;
	}
	
	public static int getParentIndex(Node n) {
		return n.getIndex() / 2;
	}
	
	void print() {
		System.out.print("[" + height + "," + index + "]");
	}
}
