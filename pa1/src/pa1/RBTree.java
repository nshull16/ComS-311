package pa1;
/**
 * Class that creates and maintains a red black tree
 * @author Nathan Shull, Tyler Krueger
 *
 */
public class RBTree {
	/**
	 * Root node
	 */
	private Node root;
	
	/**
	 * Size of the red black tree, number of nodes
	 */
	private int size;
	
	/**
	 * Nil node
	 */
	private Node nil;
	
	/**
	 * Constructor for a red black tree. Each red black tree will have a root node, nil node, and size
	 */
	public RBTree(){
		root = new Node();
		size = 0;
		nil = new Node();
		nil.setColor(1);
		root = nil;
		root.setParent(nil);
		root.setEmax(nil.getEndpoint());
	}
	
	/**
	 * Gets the root node of a red black tree
	 * @return the root of the red black tree
	 */
	public Node getRoot(){
		return this.root;
	}
	
	/**
	 * Gets the nil node of a red black tree
	 * @return the nil node of a red black tree
	 */
	public Node getNILNode(){
		return this.nil;
	}
	
	/**
	 * Gets the number of internal nodes of a red black tree
	 * @return number of nodes of a red black tree, as an integer
	 */
	public int getSize(){
		return this.size;
	}
	
	/**
	 * Sets the number of internal nodes of a red black tree
	 * @return the nuber of nodes of a red black tree
	 */
	public void setSize(int size){
		this.size = size;
	}
	
	/**
	 * Gets the number of levels of a red black tree
	 * 
	 * @return the number of levels of a red black tree, as an integer
	 */
	public int getHeight(){
		if (root == nil){
			return 0;
		}
		else{
			return getHeight(root);
		}
	}
	
	/**
	 * Sets the root node of a red black tree
	 * @param n a Node object
	 */
	public void setRoot(Node node){
		this.root = node;
	}
	
	/**
	 * Finds how many levels tall a red black tree is, from Node n
	 * @param n the height being found
	 * @return the height of the tree from n, as an integer
	 */
	private int getHeight(Node n){
		if (n == nil){
			return 0;
		}
		else{
			int leftHeight = getHeight(n.getLeft()) + 1;
			int rightHeight = getHeight(n.getRight()) + 1;
			if(leftHeight > rightHeight) {
				return leftHeight;
			}
			return rightHeight;
		}
	}
	
	/**
	 * Method to insert a node into a red black tree, and subsequently update the tree
	 * @param newNode the new node being inserted into the red black tree
	 */
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
		update(newNode);
		InsertNodeFixup(newNode);
	}
	
	/**
	 * Method to keep the red black tree in check with the required rules/properties
	 * @param newNode node that needs to be checked, and maybe fixed
	 */
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
	
	/**
	 * Method to rotate nodes to the left, around a given node
	 * @param x node to specify where the rotation will occur
	 */
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
		update(x);
	}
	
	/**
	 * Method to rotate nodes to the right, around a given node
	 * @param x node to specify where the roation will occur
	 */
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
		update(x);
	}
	
	/**
	 * Method to update the endpoint max, value, and max value. This will be neccessary when a node is inserted or deleted
	 * @param x Node where the update checks will start, and will traverse up the tree from there
	 */
	private void update(Node x){
		if(x == this.getNILNode()){
			x.setVal(0);
			x.setMaxValue(0);
			x.setMax(this.getNILNode().getMax());
		}
		else{
			x.setVal(x.getLeft().getVal() + x.getP() + x.getRight().getVal());
			x.setMaxValue(Math.max(x.getLeft().getMaxValue(), Math.max(x.getLeft().getVal() + x.getP(), x.getLeft().getVal() + x.getP() + x.getRight().getMax())));
			if(x.getLeft().getMax() != this.getNILNode().getMax() && x.getMaxValue() == x.getLeft().getMaxValue()){
				x.setMax(x.getLeft().getMax());
			}
			else if(x.getMaxValue() == (x.getLeft().getVal() + x.getP()){
				x.setMax(x.getEndpoint());
			}
			else if(x.getRight().getMax() != this.getNILNode().getMax() && x.getMaxValue() == (x.getLeft().getVal() + x.getP() + x.getRight().getMaxValue())){
				x.setMax(x.getRight().getMax());
			}
			else{
				x.setMax(this.getNILNode().getEndpoint());
			}
			update(x.getParent());
		}
	}
	
	/**
	 * Method to find the smallest node in a tree
	 * @param x Starting node
	 * @return the furthest left node within the subtree of x
	 */
	private Node Minimum(Node x){
		Node z = x;
		while(z.getLeft() != this.getNILNode()){
			z = z.getLeft();
		}
		return z;
	}
	
	/**
	 * 
	 */
	public Node Search(Node x, Node k){
		if(x == this.getNILNode() || k.getEndpoint() == x.getEndpoint()){
			return x;
		}
		if(k.getKey() <= x.getKey()){
			return Search(x.getLeft(), k);
		}
		else{
			return Search(x.getRight(), k);
		}
	}
	
	/**
	 * Method to move a subtree into a new position
	 * @param y where the new subtree will be rooted
	 * @param z Node representing the subtree to be moved
	 */
	public void Transplant(Node y, Node z){
		if(y.getParent() == this.getNILNode()){
			this.root = z;
		}
		else if(y == y.getParent().getLeft()){
			y.getParent().setLeft(z);
		}
		else{
			y.getParent().setRight(z);
		}
		z.setParent(y.getParent());
	}
	
	/**
	 * Method to delete a node from a red black tree, followed by updating the tree
	 * @param z Node that should be deleted
	 */
	public void Delete(Node z){
		Node y = z;
		Node x;
		int ycolor = y.getColor();
		if(z.getLeft() == this.getNILNode()){
			x = z.getRight();
			Transplant(z, z.getRight());
			update(x.getParent());
		}
		else if(z.getRight() == this.getNILNode()){
			x = z.getLeft();
			Transplant(z, z.getLeft());
			update(x.getParent());
		}
		else{
			y = Minimum(z.getRight());
			ycolor = y.getColor();
			x = y.getRight();
			if(y.getParent() == z) {
				x.setParent(y);
			}
			else{
				Transplant(y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			Transplant(z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
			y.setColor(z.getColor());
			update(x);
			if(ycolor == 1){
				DeleteFixup(x);
			}
		}
	}
	
	/**
	 * Method to keep the red black tree in check with the required rules/properties
	 * @param x node that needs to be checked, and maybe fixed
	 */
	public void DeleteFixup(Node x){
		while (x != this.getNILNode() && x.getColor() == 1){
			if(x == x.getParent().getLeft()){
				Node y = x.getParent().getRight();
				if(y.getColor() == 0){
					y.setColor(1);
					x.getParent().setColor(0);
					LeftRotate(x.getParent());
					y = x.getParent().getRight()
				}
				if(y.getLeft().getColor() == 1 && y.getRight().getColor() == 1){
					y.setColor(0);
					x = x.getParent();
				}
				else{
					if(y.getRight().getColor() == 1){
						y.getLeft().setColor(1);
						y.setColor(0);
						RightRotate(y);
						y = x.getParent().getRight():
					}
					y.setColor(x.getParent().getColor());
					x.getParent().setColor(1);
					y.getRight().setColor(1);
					LeftRotate(x.getParent());
					x = this.getRoot();
				}
			}
			else{
				Node y = x.getParent().getLeft();
				if(y.getColor() == 0){
					y.setColor(1);
					x.getParent().setColor(0);
					RightRotate(x.getParent());
					y = x.getParent().getLeft()
				}
				if(y.getLeft().getColor() == 1 && y.getRight().getColor() == 1){
					y.setColor(0);
					x = x.getParent();
				}
				else{
					if(y.getLeft().getColor() == 1){
						y.getRightt().setColor(1);
						y.setColor(0);
						LefttRotate(y);
						y = x.getParent().getLeft():
					}
					y.setColor(x.getParent().getColor());
					x.getParent().setColor(1);
					y.getLeftt().setColor(1);
					RightRotate(x.getParent());
					x = this.getRoot();
				}
			}
		}
		x.setColor(1);
	}


	
	


}
