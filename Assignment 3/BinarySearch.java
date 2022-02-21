public class BinarySearch {

    //Determine how many comparisons are needed to find 
    //the randomly selected item in the array using binary search
    public static int bs(String arr[], int start, int stop, String item, int comparisons) {
        while (stop >= start) {
            //Calculate the midpoint in the array and use floor rounding for non-whole numbers
            int mid = (int)((start + stop) / 2);
 
            comparisons++;

            if (arr[mid] == item) {
                //If element at the current mid matches the item, return comparisons used in the search
                return comparisons;
            }
            if (arr[mid].compareTo(item) > 0) {
                //If the item comes before the current mid, 
                //then search the subarray on the left
                stop = mid - 1;
            }
            else {
                //If the item comes after the current mid, 
                //then search the subarray on the right
                start = mid + 1;
            }
        }
        return 0;
    }
    
}