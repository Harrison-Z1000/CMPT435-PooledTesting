//Node class is used for creating elements in linked lists, stacks, and queues
public class Node {
    char character;
	Node next;

    //Constructor initializes properties of new Node instances
    public Node(char character) {
        this.character = character;
        this.next = null;
    }

}