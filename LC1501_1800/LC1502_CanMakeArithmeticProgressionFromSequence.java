package LC1501_1800;
import java.util.*;
public class LC1502_CanMakeArithmeticProgressionFromSequence {
    /**
     * A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements
     * is the same.
     *
     * Given an array of numbers arr, return true if the array can be rearranged to form an arithmetic progression.
     * Otherwise, return false.
     *
     * Input: arr = [3,5,1]
     * Output: true
     *
     * Input: arr = [1,2,4]
     * Output: false
     *
     * Constraints:
     *
     * 2 <= arr.length <= 1000
     * -10^6 <= arr[i] <= 10^6
     * @param arr
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, t = arr[1] - arr[0];
        for (int i = 2; i < n; i++) {
            if (arr[i] - arr[i - 1] != t) return false;
        }
        return true;
    }
}