package LC1201_1500;
import java.util.*;
public class LC1356_SortIntegersbyTheNumberof1Bits {
    /**
     * You are given an integer array arr. Sort the integers in the array in ascending order by the number of 1's in
     * their binary representation and in case of two or more integers have the same number of 1's you have to sort them
     * in ascending order.
     *
     * Return the array after sorting it.
     *
     * Input: arr = [0,1,2,3,4,5,6,7,8]
     * Output: [0,1,2,4,8,3,5,6,7]
     *
     * Input: arr = [1024,512,256,128,64,32,16,8,4,2,1]
     * Output: [1,2,4,8,16,32,64,128,256,512,1024]
     *
     * Constraints:
     *
     * 1 <= arr.length <= 500
     * 0 <= arr[i] <= 10^4
     * @param arr
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] w = new Integer[n];
        for (int i = 0; i < n; i++) w[i] = arr[i];
        Arrays.sort(w, (o1, o2) -> Integer.bitCount(o1) != Integer.bitCount(o2) ? Integer.bitCount(o1) - Integer.bitCount(o2) : o1 - o2);
        for (int i = 0; i < n; i++) arr[i] = w[i];
        return arr;
    }
}