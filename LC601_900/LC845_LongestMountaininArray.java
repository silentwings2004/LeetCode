package LC601_900;
import java.util.*;
public class LC845_LongestMountaininArray {
    /**
     * You may recall that an array arr is a mountain array if and only if:
     *
     * arr.length >= 3
     * There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
     * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
     * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
     * Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is
     * no mountain subarray.
     *
     * Input: arr = [2,1,4,7,3,2,5]
     * Output: 5
     *
     * Input: arr = [2,2,2]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^4
     * 0 <= arr[i] <= 10^4
     *
     * Follow up:
     *
     * Can you solve it using only one pass?
     * Can you solve it in O(1) space?
     * @param arr
     * @return
     */
    // time = O(n), space = O(n)
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int[] l = new int[n], r = new int[n];
        Arrays.fill(l, 1);
        Arrays.fill(r, 1);

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) l[i] = l[i - 1] + 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) r[i] = r[i + 1] + 1;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (l[i] > 1 && r[i] > 1) res = Math.max(res, l[i] + r[i] - 1);
        }
        return res;
    }
}