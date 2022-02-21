import java.util.*;
import java.io.*;

public class PalindromeChecker {

    public static final int ITEMS = 666;
    public static void main(String[] args) {
        //Develop and test a singly linked list
        final LinkedList listOfChars = LinkedList.createLinkedList("greatsword");
        
        //Read file items into an array
        String[] arr = new String[ITEMS];
        try {
            File fileObj = new File("magicitems.txt");
            Scanner myReader = new Scanner(fileObj);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = myReader.nextLine();
            }
            myReader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("See error below.");
            ex.printStackTrace();
        }

        System.out.println("The following magic items are palindromes: ");
        for (int i = 0; i < arr.length; i++) {
            String magicItem = arr[i];

            //Sanitize the string
            magicItem = magicItem.replaceAll("[0-9+,_()/.]", "");
            magicItem = magicItem.replaceAll("\\s", "");
            magicItem = magicItem.replaceAll("'", "");
            magicItem = magicItem.replaceAll("-", "");
            magicItem = magicItem.toLowerCase();

            if (isPalindrome(magicItem)) {
                //Print the magic item if it passes the palindrome check
                System.out.println(magicItem);
            }
        }
    }

    //Creates a stack and queue and determines if magic item is a palindrome
    public static boolean isPalindrome(String magicItem) {

        //Initialize a stack and queue
        Stack stackOfChars = new Stack();
        Queue queueOfChars = new Queue();

        //For every character in magic item, push and enqueue it
        for (int i = 0; i < magicItem.length(); i++) {
            Node n = new Node(magicItem.charAt(i));
            stackOfChars.Push(n);
            queueOfChars.Enqueue(n);
        }

        //Removes each character from the stack and queue and compares them one at a time
        for (int i = 0; i < magicItem.length(); i++) {
            char sChar = stackOfChars.Pop();
            char qChar = queueOfChars.Dequeue();

            //Checks if the magic item is a palindrome
            if(sChar != qChar) {
                
                //Clears the stack and the queue before returning to main method
                for (int j = i; j < magicItem.length() - 1; j++)
				{
					stackOfChars.Pop();
					queueOfChars.Dequeue();
				}
                return false;
            }
        }
        return true;
    }
}
