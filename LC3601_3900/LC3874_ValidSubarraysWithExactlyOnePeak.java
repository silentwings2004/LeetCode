package LC3601_3900;
import java.util.*;
public class LC3874_ValidSubarraysWithExactlyOnePeak {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * An index i is a peak if:
     *
     * 0 < i < n - 1
     * nums[i] > nums[i - 1] and nums[i] > nums[i + 1]
     * A subarray [l, r] is valid if:
     *
     * It contains exactly one peak at index i from nums
     * i - l <= k and r - i <= k
     * Return an integer denoting the number of valid subarrays in nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,3,2], k = 1
     * Output: 4
     *
     * Input: nums = [4,3,5,1], k = 2
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long validSubarrays(int[] nums, int k) {
        int n = nums.length;
        List<Integer> q = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) q.add(i);
        }
        int m = q.size();
        long res = 0;
        for (int i = 0; i < m; i++) {
            int lb = -1, rb = n;
            int cur = q.get(i);
            if (i > 0) lb = q.get(i - 1);
            if (i + 1 < m) rb = q.get(i + 1);
            int l = Math.min(cur - lb, k + 1), r = Math.min(rb - cur, k + 1);
            res += 1L * l * r;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long validSubarrays2(int[] nums, int k) {
        int n = nums.length;
        int a = -1, b = -1;
        long res = 0;
        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                res += 1L * Math.min(a - b, k + 1) * Math.min(i - a, k + 1);
                b = a;
                a = i;
            }
        }
        return res + 1L * Math.min(a - b, k + 1) * Math.min(n - a, k + 1);
    }
}