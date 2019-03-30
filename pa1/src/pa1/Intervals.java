package pa1;

import java.util.*;

public class Intervals {
    private RBTree rbtree;
    private ArrayList<IntervalCreator> intervalList;
    private int value;
    private int idTracker = 0;
    
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
    
    
    void intervalInsert(int a, int b){
        Endpoint right = new Endpoint(b, -1);
        Endpoint left = new Endpoint(a, 1);
        Node leftNode = new Node(left);
        Node rightNode = new Node(right);
        rbtree.InsertNode(leftNode);
        rbtree.InsertNode(rightNode);
        intervalList.add(new IntervalCreator(value, leftNode, rightNode));
        value++;
    }
    
    public RBTree getRBTree(){
        return rbtree;
    }
    
    public int findPOM(){
        return rbtree.getRoot().getEndpoint().getValue();
    }
    
    public boolean intervalDelete(int intervalID){
        return false;
    }
    
}
