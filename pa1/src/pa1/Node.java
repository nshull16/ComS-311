package pa1;

public class Node {
	private int color; //0 represents red, 1 represents black
	private int value;
	private int maxvalue;
	private Endpoint endpoint;
	private Endpoint max;
	private Node parent;
	private Node left;
	private Node right;
	
	public Node(){
		color = 0; 
		value = 0;
		maxvalue = 0;
		endpoint = new Endpoint(0,0);
		max = new Endpoint(0,0);
		parent = null;
		left = null;
		right = null;
	}
	
	public Node(Endpoint endpoint){
		color = 0;
		value = 0;
		maxvalue = 0;
		this.endpoint = endpoint;
		max = endpoint;
		parent = null;
		left = null;
		right = null;
	}
	
	public int getColor(){
		return color;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public int getVal()
	{
		return value;
	}
	
	public void setVal(int value){
		this.value = value;
	}
	
	public int getMaxValue(){
		return maxvalue;
	}
	
	public void setMaxValue(int maxvalue){
		this.maxvalue = maxvalue;
	}
	
	public Endpoint getEndpoint(){
		return endpoint;
	}
	
	public void setEndpoint(Endpoint endpoint){
		this.endpoint = endpoint;
	}
	
	public Endpoint getMax(){
		return max;
	}
	
	public void setMax(Endpoint max){
		this.max = max;
	}
	
	public Node getParent(){
		return parent;
	}
	
	public void setParent(Node parent){
		this.parent = parent;
	}
	
	public Node getLeft(){
		return left;
	}
	
	public void setLeft(Node left){
		this.left = left;
	}
	
	public Node gerRight(){
		return right;
	}
	
	public void setRight(Node right){
		this.right = right;
	}
	
	
}
