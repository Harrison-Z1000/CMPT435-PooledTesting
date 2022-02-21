import java.io.*;
import java.util.*;

public class Search {
    
    private static final String FILE_NAME = "magicitems.txt";
    public static final int ITEM_COUNT = 666;
    public static final int RANDOM_ITEM_COUNT = 42;
    public static final int HASH_TABLE_SIZE = 250;

    public static void main(String[] args) {
        //Read file items line-by-line into an array
        String[] arr = new String[ITEM_COUNT];
        try {
            File fileObj = new File(FILE_NAME);
            Scanner myReader = new Scanner(fileObj);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = myReader.nextLine();
                //Sanitize the string
                arr[i] = arr[i].replaceAll("[0-9+,_()/.]", "");
                arr[i] = arr[i].replaceAll("\\s", "");
                arr[i] = arr[i].replaceAll("'", "");
                arr[i] = arr[i].replaceAll("-", "");
                arr[i] = arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1).toLowerCase();
            }
            myReader.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("See error below.");
            ex.printStackTrace();
        }
        
        //Call function to randomize order of magic items 
        arr = shuffleItems(arr);
        
        //Create array to store random items
        String[] randomItemArr = new String[RANDOM_ITEM_COUNT];

        //Populate the array of random items with the first 42 items in the shuffled array
        for (int i = 0; i < RANDOM_ITEM_COUNT; i++) {
            randomItemArr[i] = arr[i];
        }

        //Print random item array for quality assurance
        System.out.println("Randomly selected items: ");
        for (int i = 0; i < randomItemArr.length; i++) {
            System.out.println(randomItemArr[i]);
        }

        //Sort array with merge sort before searching
        arr = mergeSort(arr);

        //Initialize total comparisons counter for linear search
        int ttlComparisonsLinear = 0;

        //Perform a linear search for each randomly selected item
        System.out.println("\nNumber of comparisons used for each linear search: ");
        for (int i = 0; i < randomItemArr.length; i++) {
            int comparisons = LinearSearch.ls(arr, randomItemArr[i]);
            ttlComparisonsLinear += comparisons;
            //Print the number of comparisons used for each search
            System.out.println(randomItemArr[i] + " - " + comparisons);
        }

        //Compute and print the average number of comparisons to 2 decimal places
        double avgComparisonsLinear = (double) ttlComparisonsLinear / randomItemArr.length;
        System.out.println("\nAverage number of comparisons used for linear search: " + String.format("%.2f", avgComparisonsLinear));

        //Initialize variables for binary search
        int ttlComparisonsBinary = 0;
        int startIndex = 0;
        //Comparisons counter is initialized outside of binarySearch method since 
        //it is a recursive method and will reset the value each iteration
        int initialComparisons = 0;

        //Perform a binary search for each randomly selected item
        System.out.println("\nNumber of comparisons used for each binary search: ");
        for (int i = 0; i < randomItemArr.length; i++) {
            int comparisons = BinarySearch.bs(arr, startIndex, ITEM_COUNT-1, randomItemArr[i], initialComparisons);
            ttlComparisonsBinary += comparisons;
            //Print the number of comparisons used for each search
            System.out.println(randomItemArr[i] + " - " + comparisons);
        }

        //Compute and print the average number of comparisons to 2 decimal places
        double avgComparisonsBinary = (double) ttlComparisonsBinary / randomItemArr.length;
        System.out.println("\nAverage number of comparisons used for binary search: " +  String.format("%.2f", avgComparisonsBinary));

        //Initialize hash table
        Node[] hashTable = new Node[HASH_TABLE_SIZE];

        //Load hash table with all magic items
        for (int i = 0; i < arr.length; i++) { 
            //Use the hash code function in the Hashing class to get 
            //the key/table index for the item we are loading
            int key = Hashing.makeHashCode(arr[i]);
            
            //Load each magic item into table using insert method
            hashTable = HashTable.insert(hashTable, key, arr[i]);
        }

        //Initialize total comparisons counter for retrieving from the hash table
        int ttlComparisonsHT = 0;

        System.out.println("\nNumber of comparisons used for retrieving items from hash table: ");
        for (int i = 0; i < randomItemArr.length; i++) {
            int comparisons = HashTable.retrieve(hashTable, randomItemArr[i]);
            ttlComparisonsHT += comparisons;
            
            //Print the number of comparisons used for each retrieval
            System.out.println(randomItemArr[i] + " - " + comparisons);
        }

        //Compute and print the average number of comparisons to 2 decimal places
        double avgComparisonsHT = (double) ttlComparisonsHT / randomItemArr.length;
        System.out.println("\nAverage number of comparisons used for retrieving items from hash table: " +  String.format("%.2f", avgComparisonsHT));
    }

    //Shuffle magic items with routine based on Knuth shuffle
    public static String[] shuffleItems(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //Create a "random" value
            int rand = i + (int) (Math.random() * (arr.length - i));
            
            //Swap the value at this index with the value at 
            //the index of the randomly generated number
            String item = arr[rand];
            arr[rand] = arr[i];
            arr[i] = item;
        }
        return arr;
    }

    //Sort magic items using merge sort
    public static String[] mergeSort(String[] arr) {
        if (arr.length >= 2) {
            String[] left = new String[arr.length / 2];
            String[] right = new String[arr.length - arr.length / 2];

            //Populate left array with values in first half of magic items array
            for (int i = 0; i < left.length; i++) {
                left[i] = arr[i];
            }

            //Populate right array with values in second half of magic items array
            for (int i = 0; i < right.length; i++) {
                right[i] = arr[i + arr.length / 2];
            }

            //Recursively divide arrays in half until the size of each is 1
            mergeSort(left);
            mergeSort(right);

            //Conquer by merging/sorting
            merge(arr, left, right);
        }
        return arr;
    }

    //Conquer the problem by merging the subarrays into a final, sorted array
    public static void merge(String[] arr, String[] left, String[] right) {
        int a = 0;
        int b = 0;
        for (int i = 0; i < arr.length; i++) {
            if (b >= right.length || (a < left.length && left[a].compareTo(right[b]) < 0)) {
                arr[i] = left[a];
                a++;
            } else {
                arr[i] = right[b];
                b++;
            }
        }
    }

}
