
public class RBTree {
	/**
	 * Root node of the tree
	 */
	private Node root;
	
	/**
	 * Nil node
	 */
	private Node sentinel;
	
	/**
	 * Number of nodes in the RBT
	 */
	private int size;
	
	/**
	 * Constructor for a Red Black Tree
	 * Each instance of a Red Black Tree will have a root, sentinel, and size
	 */
	public RBTree() {
		root = new Node();
		sentinel = new Node();
		sentinel.setColor(1);
		sentinel.setMax(sentinel.getEndpoint());
		root = sentinel;
		root.setParent(sentinel);
		size = 0;
	}
	
	/**
	 * Method for getting the root node of a RBT
	 * @return the root of the Red Black Tree
	 */
	public Node getRoot() {
		return this.root;
	}
	
	/**
	 * Method for setting the root node in a RBT
	 * @param n A node object
	 */
	public void setRoot(Node n) {
		this.root = n;
	}
	
	/**
	 * Method for getting the sentinel or nil node for a RBT
	 * @return the nil node in the RBT
	 */
	public Node getNILNode() {
		return this.sentinel;
	}
	
	/**
	 * Method for getting the size or number of internal nodes in a RBT
	 * @return integer value of number of nodes in the RBT
	 */
	public int getSize() {
	
		return this.size;
	}
	
	/**
	 * Method for setting the number of internal nodes in a RBT
	 * @param size Number of nodes in the RBT
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Method for finding how many levels tall a RBT is
	 * @return Integer value representing how many levels tall a RBT is
	 */
	public int getHeight() {
		return this.root.getHeight();
	}
	
	/**
	 * Method for inserting a node into a RBT
	 * It will then call for node values to be updated and RBT to be fixed up
	 * @param newNode Node to be inserted
	 */
	public void RBInsert(Node newNode) {
		this.setSize(this.getSize() + 1);
		Node y = new Node();
		y = this.getNILNode();
		Node x = new Node();
		x = this.getRoot();
		
		while(x != this.getNILNode()) {
			y = x;
			if (newNode.getKey() < x.getKey()) {
				x = x.getLeft();
			}
			else if(newNode.getKey() == x.getKey()) {
				if (newNode.getP() >= x.getP()) {
					x = x.getLeft();
				}
				else{
					x = x.getRight();
				}
			}
			else{
				x = x.getRight();
			}
		}
		newNode.setParent(y);
		if (y == this.getNILNode()) {
			this.setRoot(newNode);
			newNode.setColor(1);
			newNode.setLeft(this.getNILNode());
			newNode.setRight(this.getNILNode());
			return;
		} else if (newNode.getKey() < y.getKey()) {
			y.setLeft(newNode);
		} else if (newNode.getKey() == y.getKey()) {
			if (newNode.getP() >= y.getP()) {
				y.setLeft(newNode);
			} else {
				y.setRight(newNode);
			}
		} else {
			y.setRight(newNode);
		}
		newNode.setLeft(this.getNILNode());
		newNode.setRight(this.getNILNode());
		updateNodeValues(newNode);
		RBInsertFixup(newNode);
	}
	
	/**
	 * Method for keeping the RBT true to all of the necessary properties for a RBT
	 * @param z Node to be fixed up
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
	 * Rotates the nodes around the node x to the left
	 * @param x Node used to determine where the rotation takes place
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
	 * Method for rotating nodes around node x to the right
	 * @param x Node used to determine where the rotation takes place
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
	 * This method is used for testing and it will traverse through the tree in order,
	 * and it will print out everything about the node, just so we can see our code is working correctly
	 * @param z is the root of the tree that we would like to see
	 */
	public void InOrderTraversal(Node z) {
		if (z == this.getNILNode()) {
			return;
		}
		InOrderTraversal(z.getLeft());
		System.out.print("Key: " + z.getKey() + " Color: " + z.getColor());
		if (z.getParent() == this.getNILNode())
			System.out.print(" Parent: Nil Node");
		else
			System.out.print(" Parent: " + z.getParent().getKey());
		if (z.getLeft() == this.getNILNode())
			System.out.print(" Left: Nil Node");
		else
			System.out.print(" Left: " + z.getLeft().getKey());
		if (z.getRight() == this.getNILNode())
			System.out.println(" Right: Nil Node");
		else
			System.out.println(" Right: " + z.getRight().getKey());
		System.out.print("\tP: " + z.getP() + " Val: " + z.getVal() + " Max Val: " + z.getMaxVal());
		if (z.getEmax() == this.getNILNode().getEndpoint())
			System.out.println(" Emax: Nil Node");
		else
			System.out.println(" Emax: " + z.getEmax().getValue());
		InOrderTraversal(z.getRight());
	}

	/**
	 * Method used for updating the emax, val, and maxval values of a node when a new node is inserted or deleted
	 * @param x Used as the starting node and travels up the tree through recursive calls
	 */
	private void updateNodeValues(Node x) {
		if(x == this.getNILNode()) {
			x.setVal(0);
			x.setMaxValue(0);
			x.setMax(this.getNILNode().getEmax());
			x.setHeight(0);
		} else {
			x.setVal(x.getLeft().getVal() + x.getP() + x.getRight().getVal());
			x.setMaxValue(Math.max(x.getLeft().getMaxVal(),
					Math.max(x.getLeft().getVal() + x.getP(), x.getLeft().getVal() + x.getP() + x.getRight().getMaxVal())));
			x.setHeight(Math.max(x.getLeft().getHeight(), x.getRight().getHeight()) + 1);
			if(x.getLeft().getEmax() != this.getNILNode().getEmax() && x.getMaxVal() == x.getLeft().getMaxVal()) {
				x.setMax(x.getLeft().getEmax());
			} else if (x.getMaxVal() == (x.getLeft().getVal() + x.getP())) {
				x.setMax(x.getEndpoint());
			} else if (x.getRight().getEmax() != this.getNILNode().getEmax() && x.getMaxVal() == (x.getLeft().getVal() + x.getP() + x.getRight().getMaxVal())) {
				x.setMax(x.getRight().getEmax());
			} else {
				x.setMax(this.getNILNode().getEndpoint());
			}
			updateNodeValues(x.getParent());
		}
	}
	
	/**
	 * Method for shifting a subtree into a specified node position
	 * @param u Position node
	 * @param v Node to get the subtree that will move
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
	 * Method used for deleting a node from an RBT
	 * It will fixup the tree and transplant nodes when needed
	 * @param z Node to be deleted
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
	 * Method for fixing the RBT after deletion so that it holds the properties of a RBT
	 * @param x Node to be fixed up
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
	
	/**
	 * Method for finding the minimum node in a subtree
	 * @param x Node to start at
	 * @return The leftmost Node starting from node x
	 */
	private Node Minimum(Node x) {
		Node z = x;
		while (z.getLeft() != this.getNILNode()) {
			z = z.getLeft();
		}
		return z;
	}
}
