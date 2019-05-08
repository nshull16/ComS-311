/**
 * Class to represent a single node within a red black tree
 * @author Nathan Shull, Tyler Krueger
 *
 */
public class Node {
	/**
	 * Color value of red and black nodes, 0 and 1, respectively
	 */
	private int color; //0 represents red, 1 represents black
	
	/**
	 * Value field of a node
	 */
	private int val;
	
	/**
	 * Maximum sum of p values from a node and the subtrees within it
	 */
	private int maxVal;
	
	/**
	 * Endpoint for the given node
	 */
	private Endpoint endpoint;
	
	/**
	 * The endpoint with the maximum overlap
	 */
	private Endpoint emax;
	
	/**
	 * Parent node of the node
	 */
	private Node parent;
	
	/**
	 * Left child of the node
	 */
	private Node left;
	
	/**
	 * Right child of the node
	 */
	private Node right;
	private int height;
	
	/**
	 * Constructor for a node object. All nodes have the following properties:
	 * color, value, maxvalue, endpoint, point of maximum overlap, parent node, left node, right node
	 */
	public Node(){
		color = 0; 
		val = 0;
		maxVal = 0;
		parent = null;
		left = null;
		right = null;
		height = 0;
		endpoint = new Endpoint(0,0);
		this.endpoint.setNode(this);
		emax = new Endpoint(0,0);
	}
	
	/**
	 * Constructor for a node object, that takes in an endpoint
	 * @param endpoint Endpoint object to be associated for this instance of a node
	 */
	public Node(Endpoint endpoint){
		color = 0;
		val = 0;
		maxVal = 0;
		parent = null;
		left = null;
		right = null;
		height = 0;
		this.endpoint = endpoint;
		this.endpoint.setNode(this);
		emax = endpoint;
	}
	
	/**
	 * Gets the color of the node
	 * @return the color of the value
	 */
	public int getColor(){
		return color;
	}
	
	/**
	 * Sets the color value of the node
	 * @param color Integer value to represent the color of the node
	 */
	public void setColor(int color){
		this.color = color;
	}
	
	/**
	 * Gets the value of the node
	 * @return The value of the node
	 */
	public int getVal()
	{
		return val;
	}
	
	/**
	 * Sets the value of the node
	 * @param val Integer value to set the value of the node to
	 */
	public void setVal(int value){
		this.val = value;
	}
	
	/**
	 * Gets the maxValue of the node
	 * @return the maxValue of the node
	 */
	public int getMaxVal(){
		return maxVal;
	}
	
	/**
	 * Sets the maxValue of the node
	 * @param maxValue the maxValue the node should be set to
	 */
	public void setMaxValue(int maxvalue){
		this.maxVal = maxvalue;
	}
	
	/**
	 * Gets the endpoint that is associated with the node object
	 * @return The endpoint associated with the node object
	 */
	public Endpoint getEndpoint(){
		return endpoint;
	}
	
	/**
	 * Sets the endpoint that is associated with the node object
	 * @param endPoint The endpoint to be associated with the node object
	 */
	public void setEndpoint(Endpoint endpoint){
		this.endpoint = endpoint;
		this.endpoint.setNode(this);
	}
	
	/**
	 * Gets the max value of the node
	 * @return the max value of the node
	 */
	public Endpoint getEmax(){
		return emax;
	}
	
	/**
	 * Sets the max value of the node
	 * @param max The endpoint to be used for calculating max
	 */
	public void setMax(Endpoint max){
		this.emax = max;
	}
	
	/**
	 * Gets the parent of the current node
	 * @return the parent of the current node
	 */
	public Node getParent(){
		return parent;
	}
	
	/**
	 * Sets the parent of the current node
	 * @param parent Node to be set as the parent
	 */
	public void setParent(Node parent){
		this.parent = parent;
	}
	
	/**
	 * Gets the left child node of the current node
	 * @return the left child node of the current node
	 */
	public Node getLeft(){
		return left;
	}
	
	/**
	 * Sets the left child node of the current node
	 * @param left the node that should be made the left child node
	 */
	public void setLeft(Node left){
		this.left = left;
	}
	
	/**
	 * Gets the right child node of the current node
	 * @return the right child node of the current node
	 */
	public Node getRight(){
		return right;
	}
	
	/**
	 * Sets the right child node of the current node
	 * @param right the node that should be made the right child node
	 */
	public void setRight(Node right){
		this.right = right;
	}
	
	/**
	 * Gets the p value of the node
	 * @return the p value of the endpoint of the node
	 */
	public int getP(){
		return endpoint.getP();
	}
	
	/**
	 * Gets the key of the current node
	 * @return the key of the endpoints value
	 */
	public int getKey(){
		return endpoint.getValue();
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
}
