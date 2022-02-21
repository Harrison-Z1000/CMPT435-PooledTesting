//Used to hold items in the hash table and create linked lists
public class Node {
    String value;
    Node next;
 
    //Initialize properties of new Node instances
    public Node(String value) {
        this.value = value;
        this.next = null;
    }

}