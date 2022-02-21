//Class that contains methods for manipulating hash tables 
public class HashTable {
    
    //Adds a node to a slot in the hash table
    public static Node[] insert(Node[] hashTable, int key, String magicItem) {
        //Create a new node using magic item passed in
        Node item = new Node(magicItem);
        if (hashTable[key] == null) {
            //If a slot in the table is null, then the new node 
            //will be the first one in the slot
            hashTable[key] = item;
        }
        else {
            //If a slot in the table already has a node, then go to the last node 
            //in the linked list and add the new node to its next pointer
            Node currentNode = hashTable[key];
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = item;
        }
        return hashTable;
    }

    //Determine how many comparisons are needed to retrieve an item in the hash table
    public static int retrieve(Node[] hashTable, String magicItem) {
        //Since every get is one compare, 
        //the comparisons counter starts at 1
        int comparisons = 1;

        //Use the hash code function in the Hashing class to get 
        //the key/table index for the string we are searching for
        int key = Hashing.makeHashCode(magicItem);

        if (hashTable[key] == null) {
            //Tells user in case the calculated key indexes into an empty slot
            System.out.println("ERROR: No magic item in this slot of the hash table");
            return -1;
        }
        else {
            //If a slot in the table has at least one node, 
            //then iterate through the linked list until we reach 
            //the one with the item we are searching for
            Node currentNode = hashTable[key];
            while (currentNode.next != null) {
                comparisons++;
                if (currentNode.value.equalsIgnoreCase(magicItem)) {
                    break;
                }
                currentNode = currentNode.next;
            }
            return comparisons;
        }
    }
    
}