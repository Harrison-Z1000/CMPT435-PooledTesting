import java.util.*;
import java.io.*;

public class SortArrays {

    public static final int ITEMS = 666;

    //Used to track number of string comparisons performed by different sorting algorithms
    public static int comparisons = 0;

    public static void main(String[] args) {
        
        //Read file items into an array
        String[] arr = new String[ITEMS];
        try {
            File fileObj = new File("magicitems.txt");
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

        //Call selection sort method
        selectionSort(shuffleItems(arr));

        //Call insertion sort method
        insertionSort(shuffleItems(arr));

        //Call merge sort method
        mergeSort(shuffleItems(arr));
        //Print the number of comparisons performed
        System.out.println("Number of Comparisons in Merge Sort: ");
        System.out.println(comparisons);
        comparisons = 0;

        //Call quick sort method
        quickSort(shuffleItems(arr), 0, arr.length - 1);
        //Print the number of comparisons performed
        System.out.println("Number of Comparisons in Quick Sort: ");
        System.out.println(comparisons);
        comparisons = 0;
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

    //Sort magic items using selection sort
    public static void selectionSort(String[] arr) {

        for (int j = 0; j < arr.length - 1; j++) {
            int min = j;
            //Look in the rest of the array for the string with the lowest lexicographic value 
            for (int k = j + 1; k < arr.length; k++) {
                //compareTo() returns a negative value if the first string is lexicographically less than the string argument
                int compareValue = arr[k].compareTo(arr[min]);
                comparisons++;
                if (compareValue < 0) {
                    //Set min to index of string with lower value
                    min = k;  
                }
            }

            //Swap item at index j with item with the new minimum lexicographic value
            String temp = arr[j];
            arr[j] = arr[min];
            arr[min] = temp;
        }

        //Print the number of comparisons performed
        System.out.println("Number of Comparisons in Selection Sort: ");
        System.out.println(comparisons);
        comparisons = 0;

    }

    //Sort magic items using insertion sort
    public static void insertionSort(String[] arr) {

        for (int i = 1; i < arr.length; ++i) {
            String key = arr[i];
            int j = i - 1;
    
            //Move strings that have higher lexicographic value than key one positions up
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                comparisons++;
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }

        //Print the number of comparisons performed
        System.out.println("Number of Comparisons in Insertion Sort: ");
        System.out.println(comparisons);
        comparisons = 0;

    }

    //Sort magic items using merge sort
    public static void mergeSort(String[] arr) {
        
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

    }

    //Conquer the problem by merging the subarrays into the final array in sorted order
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
            comparisons++;
        }

    }

    //Sort magic items using quick sort
    public static void quickSort(String[] arr, int left, int right) {
        //Initialize counters at either end of the array
        int i = left;
        int j = right;
        
        if (j - i >= 1) {
            //Set the pivot value to the 1st element in the array
            String pivot = arr[left];

            //Conquer problem by sorting the elements around the pivot value
            while (j > i) {
                
                //Increment the left counter until the current element has a value greater than the pivot,
                //the left counter reaches the right, or the right counter is farther ahead.
                while (arr[i].compareTo(pivot) <= 0 && i < right && j > i) {
                    comparisons++;
                    i++;
                }

                //Decrement the right counter until the current element has a value less than the pivot,
                //the right counter reaches the left, or the right counter is farther ahead.
                while (arr[j].compareTo(pivot) >= 0 && j > left && j >= i) {
                    comparisons++;
                    j--;
                }

                //Swap elements at left and right counters' current positions
                if (j > i) {
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            //Swap left boundary and pivot with last element in 
            //left partition once counters have crossed center
            String temp = arr[left];
            arr[left] = arr[j];
            arr[j] = temp;
            
            //Recursively sort the left and right partitions
            quickSort(arr, left, j - 1);
            quickSort(arr, j + 1, right);
        }
    }

}