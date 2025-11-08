package LC3601_3900;
import java.util.*;
public class LC3738_LongestNonDecreasingSubarrayAfterReplacingatMostOneElement {
    /**
     * You are given an integer array nums.
     *
     * You are allowed to replace at most one element in the array with any other integer value of your choice.
     *
     * Return the length of the longest non-decreasing subarray that can be obtained after performing at most one
     * replacement.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * An array is said to be non-decreasing if each element is greater than or equal to its previous one (if it exists).
     *
     * Input: nums = [1,2,3,1,2]
     * Output: 4
     *
     * Input: nums = [2,2,2,2,2]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] >= nums[j - 1]) j++;
            q.add(new int[]{i, j - 1});
            i = j - 1;
        }
        int m = q.size();
        if (m == 1) return n;

        int res = 0;
        for (int i = 1; i < m; i++) {
            int[] a = q.get(i - 1), b = q.get(i);
            int len1 = a[1] - a[0] + 1, len2 = b[1] - b[0] + 1;
            res = Math.max(res, Math.max(len1 + 1, len2 + 1));
            if (a[1] == 0 || nums[a[1] - 1] <= nums[b[0]]) res = Math.max(res, len1 + len2);
            if (b[0] == n - 1 || nums[a[1]] <= nums[b[0] + 1]) res = Math.max(res, len1 + len2);
        }
        return res;
    }
}