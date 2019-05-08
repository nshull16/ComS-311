/**
 * Class representing an endpoint of a node in a red black tree
 * @author Nathan Shull, Tyler Krueger
 *
 */

public class Endpoint {
	private int value; //value of endpoint
	private int p; //value(1 or -1) to set endpoint as a left or right endpoint, respectively
	private Node node;
	
	public Endpoint(int value, int p){
		this.value = value;
		this.p = p;
		this.node = null;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public int getP(){
		return this.p;
	}
	
	public Node getNode() {
		return this.node;
	}
	
	public void setNode(Node n) {
		this.node = n;
	}
}

