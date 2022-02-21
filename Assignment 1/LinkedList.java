//LinkedList class is used to create linked lists
public class LinkedList {
    Node head;

    //Constructor
    public LinkedList() {
        this.head = null;
    }

    //Makes linked list out of string passed in
    public static LinkedList createLinkedList(String magicItem) {
        LinkedList listOfChars = new LinkedList();
        for (int j = 0; j < magicItem.length(); j++) {
            Node character = new Node(magicItem.charAt(j));

            //Set the node as the list's head if the list does not have a head yet
            if (listOfChars.head == null) {
                listOfChars.head = character;
            }
            //For rest of chars, go to last node in list 
            //and add new char node to its next pointer
            else {
                Node tail = listOfChars.head;
                while (tail.next != null) {
                    tail = tail.next;
                }
                tail.next = character;
            }
        }
        return listOfChars;
    }
}