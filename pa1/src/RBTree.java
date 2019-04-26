
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
	private Node sentinel;
	
	/**
	 * Constructor for a red black tree. Each red black tree will have a root node, nil node, and size
	 */
	public RBTree() {
		root = new Node();
		sentinel = new Node();
		sentinel.setColor(1);
		sentinel.setEmax(sentinel.getEndpoint());
		root = sentinel;
		root.setParent(sentinel);
		size = 0;
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
		return this.sentinel;
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
		return this.root.getHeight();
	}
	
	/**
	 * Sets the root node of a red black tree
	 * @param n a Node object
	 */
	public void setRoot(Node node){
		this.root = node;
	}
	
	
	/**
	 * Method to insert a node into a red black tree, and subsequently update the tree
	 * @param newNode the new node being inserted into the red black tree
	 */
	public void RBInsert(Node z) {
		this.setSize(this.getSize() + 1);
		Node y = new Node();
		y = this.getNILNode();
		Node x = new Node();
		x = this.getRoot();
		
		while(x != this.getNILNode()) {
			y = x;
			if (z.getKey() < x.getKey()) {
				x = x.getLeft();
			} else if (z.getKey() == x.getKey()) {
				if (z.getP() >= x.getP()) {
					x = x.getLeft();
				} else {
					x = x.getRight();
				}
			} else {
				x = x.getRight();
			}
		}
		z.setParent(y);
		if (y == this.getNILNode()) {
			this.setRoot(z);
			z.setColor(1);
			z.setLeft(this.getNILNode());
			z.setRight(this.getNILNode());
			return;
		} else if (z.getKey() < y.getKey()) {
			y.setLeft(z);
		} else if (z.getKey() == y.getKey()) {
			if (z.getP() >= y.getP()) {
				y.setLeft(z);
			} else {
				y.setRight(z);
			}
		} else {
			y.setRight(z);
		}
		z.setLeft(this.getNILNode());
		z.setRight(this.getNILNode());
		updateNodeValues(z);
		RBInsertFixup(z);
	}
	
	/**
	 * Method to keep the red black tree in check with the required rules/properties
	 * @param newNode node that needs to be checked, and maybe fixed
	 */
	public void RBInsertFixup(Node z) {
		while (z.getParent().getColor() == 0) {
			if (z.getParent() == z.getParent().getParent().getLeft()) {
				Node y = new Node();
				y = z.getParent().getParent().getRight();
				if (y.getColor() == 0) {
					z.getParent().setColor(1);
					y.setColor(1);
					z.getParent().getParent().setColor(0);
					z = z.getParent().getParent();
				}
				else {
					if (z == z.getParent().getRight()) {
						z = z.getParent();
						LeftRotate(z);
					}
					z.getParent().setColor(1);
					z.getParent().getParent().setColor(0);
					RightRotate(z.getParent().getParent());
				}
			}
			else {
				Node y = new Node();
				y = z.getParent().getParent().getLeft();
				if (y.getColor() == 0) {
					z.getParent().setColor(1);
					y.setColor(1);
					z.getParent().getParent().setColor(0);
					z = z.getParent().getParent();
				}
				else {
					if (z == z.getParent().getLeft()) {
						z = z.getParent();
						RightRotate(z);
					}
					z.getParent().setColor(1);
					z.getParent().getParent().setColor(0);
					LeftRotate(z.getParent().getParent());
				}
			}
		}
		this.getRoot().setColor(1);
	}
	
	/**
	 * Method to rotate nodes to the left, around a given node
	 * @param x node to specify where the rotation will occur
	 */
	public void LeftRotate(Node x) {
		Node y = new Node();
		y = x.getRight();
		x.setRight(y.getLeft());
		if (y.getLeft() != this.getNILNode()) {
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == this.getNILNode()) {
			this.setRoot(y);
		}
		else if (x == x.getParent().getLeft()) {
			x.getParent().setLeft(y);
		}
		else { 
			x.getParent().setRight(y);
		}
		y.setLeft(x);
		x.setParent(y);
		updateNodeValues(x);
	}
	
	/**
	 * Method to rotate nodes to the right, around a given node
	 * @param x node to specify where the roation will occur
	 */
	public void RightRotate(Node x) {
		Node y = new Node();
		y = x.getLeft();
		x.setLeft(y.getRight());
		if (y.getRight() != this.getNILNode()) {
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		if (x.getParent() == this.getNILNode()) {
			this.setRoot(y);
		}
		else if (x == x.getParent().getLeft()) {
			x.getParent().setLeft(y);
		}
		else {
			x.getParent().setRight(y);
		}
		y.setRight(x);
		x.setParent(y);
		updateNodeValues(x);
	}
	
	/**
	 * Method to update the endpoint max, value, and max value. This will be neccessary when a node is inserted or deleted
	 * @param x Node where the update checks will start, and will traverse up the tree from there
	 */
	private void updateNodeValues(Node x) {
		if(x == this.getNILNode()) {
			x.setVal(0);
			x.setMaxVal(0);
			x.setEmax(this.getNILNode().getEmax());
			x.setHeight(0);
		} else {
			x.setVal(x.getLeft().getVal() + x.getP() + x.getRight().getVal());
			x.setMaxVal(Math.max(x.getLeft().getMaxVal(),
					Math.max(x.getLeft().getVal() + x.getP(), x.getLeft().getVal() + x.getP() + x.getRight().getMaxVal())));
			x.setHeight(Math.max(x.getLeft().getHeight(), x.getRight().getHeight()) + 1);
			if(x.getLeft().getEmax() != this.getNILNode().getEmax() && x.getMaxVal() == x.getLeft().getMaxVal()) {
				x.setEmax(x.getLeft().getEmax());
			} else if (x.getMaxVal() == (x.getLeft().getVal() + x.getP())) {
				x.setEmax(x.getEndpoint());
			} else if (x.getRight().getEmax() != this.getNILNode().getEmax() && x.getMaxVal() == (x.getLeft().getVal() + x.getP() + x.getRight().getMaxVal())) {
				x.setEmax(x.getRight().getEmax());
			} else {
				x.setEmax(this.getNILNode().getEndpoint());
			}
			updateNodeValues(x.getParent());
		}
	}
	
	/**
	 * Method to find the smallest node in a tree
	 * @param x Starting node
	 * @return the farthest left node within the subtree of x
	 */
	private Node Minimum(Node x){
		Node z = x;
		while(z.getLeft() != this.getNILNode()){
			z = z.getLeft();
		}
		return z;
	}
	
	/**
	 * Method to move a subtree into a new position
	 * @param y where the new subtree will be rooted
	 * @param z Node representing the subtree to be moved
	 */
	private void RBTransplant(Node u, Node v) {
		if (u.getParent() == this.getNILNode()) {
			this.root = v;
		}
		else if (u == u.getParent().getLeft()) {
			u.getParent().setLeft(v);
		}
		else {
			u.getParent().setRight(v);
		}
		v.setParent(u.getParent());
	}
	
	/**
	 * Method to delete a node from a red black tree, followed by updating the tree
	 * @param z Node that should be deleted
	 */
	public void RBDeletion(Node z) {
		Node y = z;
		Node x;
		Node updateNode;
		this.setSize(this.getSize() - 1);
		int yOC = y.getColor();
		if (z.getLeft() == this.getNILNode()) {
			x = z.getRight();
			RBTransplant(z, z.getRight());
			if (x == this.getNILNode()) {
				updateNode = z.getParent();
			} else {
				updateNode = x;
			}
		}
		else if (z.getRight() == this.getNILNode()) {
			x = z.getLeft();
			RBTransplant(z, z.getLeft());
			updateNode = x;
		}
		else {
			y = Minimum(z.getRight());
			yOC = y.getColor();
			x = y.getRight();
			if (y.getParent() == z) {
				x.setParent(y);
			}
			else {
				RBTransplant(y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			RBTransplant(z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
			y.setColor(z.getColor());
			updateNode = x;
		}
		updateNodeValues(updateNode);
		if (yOC == 1) {
			RBDeleteFixup(x);
		}
	}
	
	/**
	 * Method to keep the red black tree in check with the required rules/properties
	 * @param x node that needs to be checked, and maybe fixed
	 */
	private void RBDeleteFixup(Node x) {
		while (x != this.getRoot() && x.getColor() == 1) {
			if (x == x.getParent().getLeft()) {
				Node w = x.getParent().getRight();
				if (w.getColor() == 0) {
					w.setColor(1);
					x.getParent().setColor(0);
					LeftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if (w.getLeft().getColor() == 1 && w.getRight().getColor() == 1) {
					w.setColor(0);
					x = x.getParent();
				}
				else {
					if (w.getRight().getColor() == 1) {
						w.getLeft().setColor(1);
						w.setColor(0);
						RightRotate(w);
						w = x.getParent().getRight();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(1);
					w.getRight().setColor(1);
					LeftRotate(x.getParent());
					x = this.getRoot();
				}
			}
			else {
				Node w = x.getParent().getLeft();
				if (w.getColor() == 0) {
					w.setColor(1);
					x.getParent().setColor(0);
					RightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if (w.getLeft().getColor() == 1 && w.getRight().getColor() == 1) {
					w.setColor(0);
					x = x.getParent();
				}
				else {
					if (w.getLeft().getColor() == 1) {
						w.getRight().setColor(1);
						w.setColor(0);
						LeftRotate(w);
						w = x.getParent().getLeft();
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(1);
					w.getLeft().setColor(1);
					RightRotate(x.getParent());
					x = this.getRoot();
				}
			}
		}
		x.setColor(1);
	}


	
	


}
