//This class is used to create nodes that store 
//the names of magic items in a binary search tree
public class BSTNode {
    String key;
    BSTNode left;
    BSTNode right;

    public BSTNode(String item) {
        this.key = item;
        this.left = null;
        this.right = null;
    }
}
