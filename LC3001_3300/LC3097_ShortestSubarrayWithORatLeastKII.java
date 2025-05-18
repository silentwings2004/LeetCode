package LC3001_3300;

public class LC3097_ShortestSubarrayWithORatLeastKII {
    /**
     * You are given an array nums of non-negative integers and an integer k.
     *
     * An array is called special if the bitwise OR of all of its elements is at least k.
     *
     * Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.
     *
     * Input: nums = [1,2,3], k = 2
     * Output: 1
     *
     * Input: nums = [2,1,8], k = 10
     * Output: 3
     *
     * Input: nums = [1,2], k = 0
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 10^5
     * 0 <= nums[i] <= 10^9
     * 0 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    int[] b;
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length, res = n + 1;
        b = new int[30];
        for (int i = 0, j = 0; i < n; i++) {
            add(nums[i], 1);
            while (j <= i && get() >= k) {
                res = Math.min(res, i - j + 1);
                add(nums[j++], -1);
            }
        }
        return res == n + 1 ? -1 : res;
    }

    private void add(int x, int c) {
        for (int i = 0; i < 30; i++) {
            if ((x >> i & 1) == 1) b[i] += c;
        }
    }

    private int get() {
        int res = 0;
        for (int i = 0; i < 30; i++) {
            if (b[i] > 0) res |= 1 << i;
        }
        return res;
    }
}
/**
 * 0 | 0 = 0
 * 1 | 0 = 1
 * 1 | 1 = 1
 * 2^29 < 10^9 < 2^30
 * 1. 枚举以i为右端点的子数组
 * OR 的性质：
 * 以i为右端点的子数组的 OR，至多只有 O(log max(nums)) 个不通知 31
 * 从以i位右端点的子数组
 * 到以i+1为右端点的子数组
 * 增量式地计算子数组的 OR
 * 维护子数组 OR 以及对应的子数组的左端点的最大值
 */