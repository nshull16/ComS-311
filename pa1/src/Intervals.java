/**
 * Class for an interval in a red black tree
 * @author Nathan Shull, Tyler Krueger
 */
import java.util.*;

public class Intervals {
    private RBTree rbtree;
    private ArrayList<IntervalCreator> intervalList;
    private int value;
    
    class IntervalCreator{
        private int id;
        private Node left;
        private Node right;
        
        IntervalCreator(int ID, Node leftN, Node rightN){
            id = ID;
            left = leftN;
            right = rightN;
        }
        
        public Node getLeft(){
            return left;
        }
        public Node getRight(){
            return right;
        }
        public int getID(){
            return id;
        }
    }
    
    public Intervals(){
        value = 1;
        intervalList = new ArrayList<IntervalCreator>();
        rbtree = new RBTree();
    }
    
    
    public void intervalInsert(int a, int b){
        Endpoint right = new Endpoint(b, -1);
        Endpoint left = new Endpoint(a, 1);
        Node leftNode = new Node(left);
        Node rightNode = new Node(right);
        this.rbtree.InsertNode(leftNode);
        this.rbtree.InsertNode(rightNode);
        intervalList.add(new IntervalCreator(value, leftNode, rightNode));
        value++;
    }
    
    
    public RBTree getRBTree(){
        return rbtree;
    }
    
    public int findPOM(){
        return rbtree.getRoot().getEmax().getValue();
    }
    
    public boolean intervalDelete(int intervalID){
        if(intervalID < 1 || intervalID > intervalList.size()) {
        	return false;
        }
        if(intervalList.get(intervalID - 1) == null) {
        	return false;
        }
        IntervalCreator incr = intervalList.get(intervalID - 1);
        this.rbtree.Delete(incr.getLeft());
        this.rbtree.Delete(incr.getRight());
        intervalList.set(intervalID - 1, null);
        return true;
        
        
    }
    
}
