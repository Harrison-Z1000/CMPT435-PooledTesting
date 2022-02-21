//Queue class is used to create and perform operations on queues
public class Queue {
    static Node head; 
    static Node tail;

    //Constructor
    public Queue() {
        //Queue.head = null;
        //Queue.tail = null;
    }

    //Adds a node to the end of the queue
    public void Enqueue(Node newNode) {
        if (isEmpty()) {
            head = newNode;
        }
        else {
            tail.next = newNode;
        }
        tail = newNode;
    }
    
    //Removes the node at the front of the queue and
    //returns its character value
    public char Dequeue() {
        if (isEmpty()) {
            //Handle an underflow error
            throw new IllegalArgumentException("Warning! Cannot dequeue an empty queue!");
        } 
        else {
            //Change head pointer to point to the next node
            char dequeuedChar = head.character;
            head = head.next;
            if (isEmpty()) {
                tail = null;
            }
            return dequeuedChar;
        }
    }

    //Checks whether a queue is empty
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        else {
            return false;
        }
    }
}
