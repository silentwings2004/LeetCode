package LC3601_3900;
import java.util.*;
public class LC3729_CountDistinctSubarraysDivisiblebyKinSortedArray {
    /**
     * You are given an integer array nums sorted in non-descending order and a positive integer k.
     *
     * A subarray of nums is good if the sum of its elements is divisible by k.
     *
     * Return an integer denoting the number of distinct good subarrays of nums.
     *
     * Subarrays are distinct if their sequences of values are. For example, there are 3 distinct subarrays in
     * [1, 1, 1], namely [1], [1, 1], and [1, 1, 1].
     *
     * Input: nums = [1,2,3], k = 3
     * Output: 3
     *
     * Input: nums = [2,2,2,2,2,2], k = 6
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * nums is sorted in non-descending order.
     * 1 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public long numGoodSubarrays(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        long tot = 0, s = 0;
        for (int x : nums) {
            s += x;
            int r = (int)(s % k);
            tot += map.getOrDefault(r, 0);
            map.put(r, map.getOrDefault(r, 0) + 1);
        }

        long cnt = 0;
        int n = nums.length, i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && nums[j] == nums[i]) j++;
            int len = j - i;
            long g = gcd(nums[i], k);
            long kp = k / g;
            if (len >= kp) {
                long m = len / kp;
                cnt += m * len - kp * m * (m + 1) / 2;
            }
            i = j;
        }
        return tot - cnt;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}