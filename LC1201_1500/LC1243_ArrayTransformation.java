package LC1201_1500;
import java.util.*;
public class LC1243_ArrayTransformation {
    /**
     * Given an initial array arr, every day you produce a new array using the array of the previous day.
     *
     * On the i-th day, you do the following operations on the array of day i-1 to produce the array of day i:
     *
     * If an element is smaller than both its left neighbor and its right neighbor, then this element is incremented.
     * If an element is bigger than both its left neighbor and its right neighbor, then this element is decremented.
     * The first and last elements never change.
     * After some days, the array does not change. Return that final array.
     *
     * Input: arr = [6,2,3,4]
     * Output: [6,3,3,4]
     *
     * Input: arr = [1,6,3,4,3,5]
     * Output: [1,4,4,4,4,5]
     *
     * Constraints:
     *
     * 3 <= arr.length <= 100
     * 1 <= arr[i] <= 100
     * @param arr
     * @return
     */
    // time = O(n), space = O(n)
    public List<Integer> transformArray(int[] arr) {
        int n = arr.length;
        while (true) {
            int[] b = arr.clone();
            for (int i = 1; i + 1 < n; i++) {
                if (arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) b[i]++;
                if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) b[i]--;
            }
            if (Arrays.equals(arr, b)) break;
            arr = b.clone();
        }
        List<Integer> res = new ArrayList<>();
        for (int x : arr) res.add(x);
        return res;
    }
}