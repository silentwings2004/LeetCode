package LC1201_1500;
import java.util.*;
public class LC1218_LongestArithmeticSubsequenceofGivenDifference {
    /**
     * Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which
     * is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
     *
     * A subsequence is a sequence that can be derived from arr by deleting some or no elements without changing the
     * order of the remaining elements.
     *
     * Input: arr = [1,2,3,4], difference = 1
     * Output: 4
     *
     * Input: arr = [1,3,5,7], difference = 1
     * Output: 1
     *
     * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^5
     * -10^4 <= arr[i], difference <= 10^4
     * @param arr
     * @param difference
     * @return
     */
    // time = O(n), space = O(n)
    public int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int x : arr) {
            if (map.containsKey(x - difference)) map.put(x, map.get(x - difference) + 1);
            else map.put(x, 1);
            res = Math.max(res, map.get(x));
        }
        return res;
    }
}