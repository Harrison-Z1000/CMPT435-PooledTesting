//Stack class is used to create and perform operations on stacks
public class Stack{
    public static Node top;

    //Constructor
    public Stack() {
        //Stack.top = null;
    }
    
    //Adds a node to the top of a stack
    public void Push(Node newNode) {
        Node oldTop = top;
        top = newNode;
        top.next = oldTop;

    }
    
    //Pops every node in a stack and puts them into an array
    //Return just char in the node
    public char Pop() {
        if (isEmpty()) {
            //Handle an underflow error
            throw new IllegalArgumentException("Warning! Cannot pop an empty stack!");
        } else {
            //Change top pointer to point to the node below the one currently on top 
            char poppedChar = top.character;
            top = top.next;
            return poppedChar;
        }
    }

    //Checks whether a stack is empty
    public boolean isEmpty() {
        if (top == null) {
            return true;
        }
        else {
            return false;
        }
    }

}
