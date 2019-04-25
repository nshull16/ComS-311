/**
 * Class for an interval in a red black tree
 * @author Nathan Shull, Tyler Krueger
 */
import java.util.*;

public class Intervals {
    private RBTree rbtree;
    private List<Endpoint[]> intervalList;
    
    public Intervals(){
        intervalList = new ArrayList<Endpoint[]>();
        rbtree = new RBTree();
    }
    
    
    public void intervalInsert(int a, int b){
        Endpoint right = new Endpoint(b, -1);
        Endpoint left = new Endpoint(a, 1);
        Node leftNode = new Node(left);
        Node rightNode = new Node(right);
        Endpoint[] newInterval = {left, right};
        intervalList.add(newInterval);
        this.rbtree.InsertNode(leftNode);
        this.rbtree.InsertNode(rightNode);

    }
    
    
    public RBTree getRBTree(){
        return rbtree;
    }
    
    public int findPOM(){
        return rbtree.getRoot().getEmax().getValue();
    }
    
    public List<Endpoint[]> getList(){
    	return this.intervalList;
    }
    
    public boolean intervalDelete(int intervalID){
        if(intervalID < 1 || intervalID > intervalList.size()) {
        	return false;
        }
        if(intervalList.get(intervalID - 1) == null) {
        	return false;
        }
        Endpoint[] e = intervalList.get(intervalID - 1);
        this.rbtree.Delete(e[0].getNode());
        this.rbtree.Delete(e[0].getNode());
        intervalList.set(intervalID - 1, null);
        return true;
        
        
    }
    
}
