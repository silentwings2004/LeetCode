package LC1801_2100;

public class LC1829_MaximumXORforEachQuery {
    /**
     * You are given a sorted array nums of n non-negative integers and an integer maximumBit. You want to perform the
     * following query n times:
     *
     * Find a non-negative integer k < 2maximumBit such that nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k
     * is maximized. k is the answer to the ith query.
     * Remove the last element from the current array nums.
     * Return an array answer, where answer[i] is the answer to the ith query.
     *
     * Input: nums = [0,1,1,3], maximumBit = 2
     * Output: [0,3,2,3]
     *
     * Input: nums = [2,3,4,7], maximumBit = 3
     * Output: [5,2,6,5]
     *
     * Input: nums = [0,1,2,2,5,7], maximumBit = 3
     * Output: [4,3,6,4,6,7]
     *
     * Constraints:
     *
     * nums.length == n
     * 1 <= n <= 10^5
     * 1 <= maximumBit <= 20
     * 0 <= nums[i] < 2maximumBit
     * nums is sorted in ascending order.
     * @param nums
     * @param maximumBit
     * @return
     */
    // S1
    // time = O(n * k), space = O(1)
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length, sum = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) sum ^= nums[i];
        for (int i = 0; i < n; i++) {
            if (i > 0) sum ^= nums[n - i];
            for (int j = 0; j < maximumBit; j++) {
                if ((sum >> j & 1) == 0) res[i] |= 1 << j;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int[] getMaximumXor2(int[] nums, int maximumBit) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0, sum = 0; i < n; i++) {
            sum ^= nums[i];
            res[n - 1 - i] = sum ^ (1 << maximumBit) - 1;
        }
        return res;
    }
}
/**
 * k = a[0] ^ a[1] ^ ... ^ a[t] ^ (2^m - 1)
 */