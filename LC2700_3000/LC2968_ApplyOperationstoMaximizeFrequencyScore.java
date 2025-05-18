package LC2700_3000;
import java.util.*;
public class LC2968_ApplyOperationstoMaximizeFrequencyScore {
    /**
     * You are given a 0-indexed integer array nums and an integer k.
     *
     * You can perform the following operation on the array at most k times:
     *
     * Choose any index i from the array and increase or decrease nums[i] by 1.
     * The score of the final array is the frequency of the most frequent element in the array.
     *
     * Return the maximum score you can achieve.
     *
     * The frequency of an element is the number of occurences of that element in the array.
     *
     * Input: nums = [1,2,6,4], k = 3
     * Output: 3
     *
     * Input: nums = [1,4,4,2,4], k = 0
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= k <= 10^14
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int n = nums.length, res = 0;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        for (int i = 0, j = 0; i < n; i++) {
            int len = i - j + 1, mid = j + len / 2;
            long ld = 1L * nums[mid] * (mid - j) - (s[mid] - s[j]);
            long rd = (s[i + 1] - s[mid + 1]) - 1L * nums[mid] * (i - mid);
            if (ld + rd <= k) res = Math.max(res, len);
            else j++;
        }
        return res;
    }
}