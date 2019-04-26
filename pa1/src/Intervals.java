/**
 * Class for an interval in a red black tree
 * @author Nathan Shull, Tyler Krueger
 */
import java.util.ArrayList;
import java.util.List;

public class Intervals {
    private RBTree rbtree;
    private List<Endpoint[]> intervalList;
    
    public Intervals(){
        intervalList = new ArrayList<Endpoint[]>();
        rbTree = new RBTree();
    }
    
    
    public void intervalInsert(int a, int b){
		Endpoint left = new Endpoint(a, 1);
		Node x = new Node(left);
		Endpoint right = new Endpoint(b, -1);
		Node y = new Node(right);
		Endpoint[] newInterval = { left, right };
		intervalList.add(newInterval);
		this.getRBTree().RBInsert(x);
		this.getRBTree().RBInsert(y);

    }
    
    
    public RBTree getRBTree(){
        return rbTree;
    }
    
    public int findPOM(){
        return rbTree.getRoot().getEmax().getValue();
    }
    
    public List<Endpoint[]> getList(){
    	return this.intervalList;
    }
    
	public boolean intervalDelete(int intervalID) {
        if (intervalID < 1 || intervalID > intervalList.size()) {
            System.out.println("Invalid interval ID please try again");
            return false;
        }
        if (intervalList.get(intervalID - 1) == null) {
            System.out.println("Interval has already been deleted");
            return false;
        }
        Endpoint[] e = intervalList.get(intervalID - 1);

        this.getRBTree().RBDeletion(e[0].getNode());
        this.getRBTree().RBDeletion(e[1].getNode());
        intervalList.set(intervalID - 1, null);
        return true;
	}
    
}
