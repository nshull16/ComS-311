/**
 * Class for an interval in a red black tree
 * @author Nathan Shull, Tyler Krueger
 */
import java.util.*;

public class Intervals {
	RBTree rbtree;

    private List<Endpoint[]> intervalList;

	public Intervals() {
		rbtree = new RBTree();
		intervalList = new ArrayList<Endpoint[]>();
	}

	public void intervalInsert(int a, int b) {
		Endpoint left = new Endpoint(a, 1);
		Node x = new Node(left);
		Endpoint right = new Endpoint(b, -1);
		Node y = new Node(right);
		Endpoint[] newInterval = { left, right };
		intervalList.add(newInterval);
		this.getRBTree().RBInsert(x);
		this.getRBTree().RBInsert(y);
	}


	public boolean intervalDelete(int intervalID) {
        if (intervalID < 1 || intervalID > intervalList.size()) {
            return false;
        }
        if (intervalList.get(intervalID - 1) == null) {
            return false;
        }
        Endpoint[] e = intervalList.get(intervalID - 1);

        this.getRBTree().RBDeletion(e[0].getNode());
        this.getRBTree().RBDeletion(e[1].getNode());
        intervalList.set(intervalID - 1, null);
        return true;
	}

	public int findPOM() {
        return rbtree.getRoot().getEmax().getValue();
	}

	public RBTree getRBTree() {
		return rbtree;
	}
	
    public List<Endpoint[]> getList() {
		return this.intervalList;
	}
}