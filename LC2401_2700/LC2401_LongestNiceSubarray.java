package LC2401_2700;

public class LC2401_LongestNiceSubarray {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in
     * the subarray is equal to 0.
     *
     * Return the length of the longest nice subarray.
     *
     * A subarray is a contiguous part of an array.
     *
     * Note that subarrays of length 1 are always considered nice.
     *
     * Input: nums = [1,3,8,48,10]
     * Output: 3
     *
     * Input: nums = [3,1,5,11,13]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1: Two Pointers
    // time = O(n), space = O(1)
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length, res = 0, count = 0, j = 0;
        for (int i = 0; i < n; i++) {
            while (j < n && (count & nums[j]) == 0) count += nums[j++];
            res = Math.max(res, j - i);
            count -= nums[i];
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int longestNiceSubarray2(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0, j = 0, state = 0; i < n; i++) {
            while ((state & nums[i]) != 0) state ^= nums[j++];
            state |= nums[i];
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // S3
    // time = O(nlogn), space = O(1)
    public int longestNiceSubarray3(int[] nums) {
        int n = nums.length, j = 0, total = 0, res = 0;
        int[] cnt = new int[32];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < 31; k++) {
                if ((nums[i] >> k & 1) == 1) {
                    cnt[k]++;
                    if (cnt[k] > 1) total++;
                }
            }

            while (total > 0) {
                for (int k = 0; k < 31; k++) {
                    if ((nums[j] >> k & 1) == 1) {
                        cnt[k]--;
                        if (cnt[k] == 1) total--;
                    }
                }
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // S4
    // time = O(n), space = O(n)
    public int longestNiceSubarray4(int[] nums) {
        int n = nums.length, res = 1;
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++) presum[i] = presum[i - 1] + Integer.bitCount(nums[i - 1]);

        long sum = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < n) {
                sum += nums[j];
                if (Long.bitCount(sum) == presum[j + 1] - presum[i]) res = Math.max(res, j - i + 1);
                else break;
                j++;
            }
            if (j == n) break;
            while (i < j && Long.bitCount(sum) != presum[j + 1] - presum[i]) sum -= nums[i++];
            i--;
            j++;
        }
        return res;
    }
}
/**
 * 每1位上最多只能有1个1 => 双指针
 * x x x [x x x x] x..
 *        i        j
 * if (count & nums[j]) == 0
 *      count += nums[j]
 * else {
 *     count -= nums[i];
 * }
 */