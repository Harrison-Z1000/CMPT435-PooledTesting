public class BinarySearchTree {
    BSTNode root;
    int comparisons;

    //Constructor initializes the root of 
    //the tree and a comparisons counter
    public BinarySearchTree() {
        this.root = null;
        this.comparisons = 0;
    }

    //Call the insert node function and update the value of root
    public void insert(String item) {
        this.root = insertNode(this.root, item);
    }

    //Traverse through the BST and insert a new node 
    //when we reach an end without a node
    public BSTNode insertNode(BSTNode root, String key) {
        if (root == null) {
            root = new BSTNode(key);
            return root;
        } 
        else {
            if (key.compareToIgnoreCase(root.key) < 0) {
                System.out.print("L ");
                root.left = insertNode(root.left, key);
            }
            else {
                System.out.print("R ");
                root.right = insertNode(root.right, key);
            }
        }
        return root;
    }

    //Print out the entire binary search tree 
    //with an in-order traversal
    public void inOrderTraversal(BSTNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.key);
            inOrderTraversal(root.right);
        }
    }

    //Traverse through the BST and compare the item held in each node 
    //with the item we're searching for until we find a match
    public int lookupItem(BSTNode root, String wantedItem) {
        this.comparisons++;

        if (root.key == null) {
            return -1;
        }
        else {
            if (wantedItem.compareToIgnoreCase(root.key) < 0) {
                System.out.print("L ");
                lookupItem(root.left, wantedItem);
            }
            else if (wantedItem.compareToIgnoreCase(root.key) > 0) {
                System.out.print("R ");
                lookupItem(root.right, wantedItem);
            }
            else {
                //This means we reached the node with the item 
                //we're looking for so go to the end of the function
            }
        }

        return this.comparisons;
    }

}
