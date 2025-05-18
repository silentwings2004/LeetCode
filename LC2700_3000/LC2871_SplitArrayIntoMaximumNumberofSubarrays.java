package LC2700_3000;

public class LC2871_SplitArrayIntoMaximumNumberofSubarrays {
    /**
     * You are given an array nums consisting of non-negative integers.
     *
     * We define the score of subarray nums[l..r] such that l <= r as nums[l] AND nums[l + 1] AND ... AND nums[r] where
     * AND is the bitwise AND operation.
     *
     * Consider splitting the array into one or more subarrays such that the following conditions are satisfied:
     *
     * Each element of the array belongs to exactly one subarray.
     * The sum of scores of the subarrays is the minimum possible.
     * Return the maximum number of subarrays in a split that satisfies the conditions above.
     *
     * A subarray is a contiguous part of an array.
     *
     * Input: nums = [1,0,2,0,1,2]
     * Output: 3
     *
     * Input: nums = [5,7,1,3]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int maxSubarrays(int[] nums) {
        int n = nums.length, INF = (1 << 30) - 1;
        int t = INF;
        for (int i = 0; i < n; i++) t = t & nums[i];

        int s = INF, res = 0;
        for (int i = 0; i < n; i++) {
            s = s & nums[i];
            boolean flag = true;
            for (int j = 0; j < 30; j++) {
                int a = t >> j & 1, b = s >> j & 1;
                if (a == 1 && b == 1 || a == 0 && b == 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res++;
                s = INF;
            }
        }
        return res == 0 ? 1 : res;
    }

    // S2
    // time = O(n), space = O(1)
    public int maxSubarrays2(int[] nums) {
        int res = 0, a = -1;
        for (int x : nums) {
            a &= x;
            if (a == 0) {
                res++;
                a = -1;
            }
        }
        return Math.max(res, 1);
    }
}
/**
 * 1. 先满足分数之和尽量小
 * 假设整个数组的 AND > 0
 * 随着 AND 的数越来越多，AND 只会越来越小
 * 随着 OR 的数越来越多，OR 只会越来越大
 *
 * 真个数组的AND就是任意子数组 AND 的最小值
 * 假设整个数组的 AND 记作 a > 0
 * 如果分出了2个子数组 >= 2 * a > a
 * 此时只能分出一个数组，即 nums
 *
 * 2. 再满足分割出的子数组尽量多
 * 如果a = 0
 */