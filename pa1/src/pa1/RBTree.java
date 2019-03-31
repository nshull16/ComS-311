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
	
	public void InsertNode(Node newNode){
		Node y = new Node();
		y = this.getNILNode();
		Node x = new Node();
		x = this.getRoot();
		this.setSize(this.getSize()+1);
		
		while(x != this.getNILNode){
			y = x;
			if(newNode.getKey() < x.getKey()){
				x = x.getLeft();
			}
			else{
				x = x.getRight();
			}
		}
		newNode.setParent(y);
		if(y == this.getNILNode()){
			this.setRoot(newNode);
			newNode.setLeft(this.getNILNode());
			newNode.setRight(this.getNILNode());
			newNode.setColor(1);
			return;
		}
		else if (newNode.getKey() < newNode.getKey()){
			y.setLeft(newNode);
		}
		else{
			y.setRight(newNode);
		}
		newNode.setLeft(this.getNILNode());
		newNode.setRight(this.getNILNode());
		InsertNodeFixup(newNode);
	}
	
	public void InsertNodeFixup(Node newNode){
		while(newNode.getParent().getColor() == 0){
			if(newNode.getParent() == newNode.getParent().getParent().getLeft()){
				Node y = new Node();
				y = newNode.getParent().getParent().getRight();
				if(y.getColor() == 0){
					newNode.getParent().setColor(1);
					y.setColor(1);
					newNode.getParent.getParent.setColor(0);
					newNode = newNode.getParent().getParent();
				}
				else{
					if(newNode == newNode.getParent.getRight()){
						newNode = newNode.getParent();
						LeftRotate(newNode);
					}
					newNode.getParent().setColor(1);
					newNode.getParent().getParent().setColor(0);
					RightRotate(newNode.getParent().getParent());
				}
			}
			else{
				Node y = new Node();
				y = newNode.getParent().getParent().getLeft();
				if(y.getColor() == 0){
					newNode.getParent().setColor(1);
					y.setColor(1);
					newNode.getParent().getParent().setColor(0);
					newNode = newNode.getParent().getParent();
				}
				else{
					if(newNode == newNode.getParent().getLeft()){
						newNode = newNode.getParent();
						RightRotate(newNode);
					}
					newNode.getParent().setColor(1);
					newNode.getParent().getParent().setColor(0);
					LeftRotate(newNode.getParent().getParent());
				}
			}
		}
		this.getRoot().setColor(1);
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
