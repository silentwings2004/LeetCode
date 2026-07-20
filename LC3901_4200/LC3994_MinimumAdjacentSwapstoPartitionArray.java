package LC3901_4200;

public class LC3994_MinimumAdjacentSwapstoPartitionArray {
    /**
     * You are given an integer array nums and two integers a and b such that a < b.
     *
     * An array is called good if it can be split into three contiguous parts, in this order, such that:
     *
     * Every element in the first part is less than a.
     * Every element in the second part is in the range [a, b] inclusive.
     * Every element in the third part is greater than b.
     * Any of the three parts may be empty.
     *
     * In one adjacent swap, you may swap two neighboring elements of nums.
     *
     * Return the minimum number of adjacent swaps required to make nums good. Since the answer may be very large,
     * return it modulo 10^9 + 7.
     *
     * Input: nums = [1,3,2,4,5,6], a = 3, b = 4
     * Output: 1
     *
     * Input: nums = [9,7,5,3], a = 4, b = 8
     * Output: 5
     *
     * Input: nums = [3,7,5,9], a = 4, b = 8
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 1 <= a < b <= 10^9
     * @param nums
     * @param a
     * @param b
     * @return
     */
    // time = O(n), space = O(1)
    public int minAdjacentSwaps(int[] nums, int a, int b) {
        long mod = (long)(1e9 + 7), res = 0;
        int cnt1 = 0, cnt2 = 0;
        for (int x : nums) {
            if (x < a) res += cnt1 + cnt2; // x 视作 0
            else if (x <= b) { // x 视作 1
                res += cnt2;
                cnt1++;
            } else cnt2++; // x 视作 2
        }
        return (int)(res % mod);
    }
}
/**
 * 结论题：求逆序对个数
 * 1. 交换次数的下界 >= 逆序对个数 I
 * 2. 主要存在一对相邻元素不是有序的 => 交换，恰好减少一对逆序对 => 恰好执行逆序对次数 I
 * 值域树状数组 => 前缀和
 */