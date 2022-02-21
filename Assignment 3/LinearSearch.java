public class LinearSearch {

    //Determine how many comparisons are needed to find 
    //the randomly selected item in the array using linear search
    public static int ls(String arr[], String item) {
        //Track number of comparisons used for each search
        int comparisons = 0;
        
        //Compare the randomly selected item to every item in the array until a match is found
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == item) {
                break;
            }
        }
        return comparisons;
    }
    
}
