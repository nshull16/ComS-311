package pa1;

public class RBTree {
	private Node root;
	private int size;
	private Node nil;
	
	public RBTree(){
		root = new Node();
		size = 0;
	}
	
	public Node getRoot(){
		return this.root;
	}
	
	public void setRoot(Node node){
		this.root = node;
	}
	
	public int getSize(){
		return this.size;
	}

}
