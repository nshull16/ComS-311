package pa1;

public class RBTree {
	private Node root;
	private int size;
	private Node nil;
	
	public RBTree(){
		root = new Node();
		size = 0;
		nil = new Node();
		nil.setColor(1);
		root = nil;
		root.setParent(nil);
		root.setEmax(nil.getEndpoint());
	}
	
	public Node getRoot(){
		return this.root;
	}
	
	public Node getNILNode(){
		return this.nil;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int getHeight(){
		if (root == nil){
			return 0;
		}
		else{
			return getHeight(root);
		}
	}
	
	public void setRoot(Node node){
		this.root = node;
	}
	
	
	public void LeftRotate(Node x){
		Node y = new Node();
		y = x.getRight();
		x.setRight(y.getLeft());
		if(this.getNILNode != y.getLeft()){
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent() == this.getNILNode()){
			this.setRoot(y);
		}
		else if(x == x.getParent().getLeft()){
			x.getParent().setLeft(y);
		}
		else{
			x.getParent().setRight(y);
		}
		y.setLeft(x);
		x.setParent(y);
	}
	
	public void RightRotate(Node x){
		Node y = new Node();
		y = x.getLeft();
		x.setLeft(y.getRight());
		if(y.getRight() != this.getNILNode()){
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent() == this.getNILNode()){
			this.setRoot(y);
		}
		else if(x == x.getParent().getLeft()){
			x.getParent().setLeft(y);
		}
		else{
			x.getParent().setRight(y);
		}
		y.setRight(x);
		x.setParent(y);
	}


	
	public void setSize(int size){
		this.size = size;
	}
	


}
