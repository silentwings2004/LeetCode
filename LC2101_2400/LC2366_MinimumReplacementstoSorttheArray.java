package LC2101_2400;

public class LC2366_MinimumReplacementstoSorttheArray {
    /**
     * You are given a 0-indexed integer array nums. In one operation you can replace any element of the array with any
     * two elements that sum to it.
     *
     * For example, consider nums = [5,6,7]. In one operation, we can replace nums[1] with 2 and 4 and convert nums to
     * [5,2,4,7].
     * Return the minimum number of operations to make an array that is sorted in non-decreasing order.
     *
     * Input: nums = [3,9,3]
     * Output: 2
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long minimumReplacement(int[] nums) {
        int n = nums.length, last = nums[n - 1];
        long res = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= last) last = nums[i];
            else {
                int k = (nums[i] + last - 1) / last; // a/b 上取整
                res += k - 1; // 一共分成k段，每多一段需要1次操作
                last = nums[i] / k;
            }
        }
        return res;
    }
}
/**
 * 从后往前考虑，最后一个数越大越好
 * 假设考虑到i，都考虑完了
 * 考虑第i-1个数 a
 * 1. 数值越大越好
 * if a <= b => a
 * if a > b -> 切分，满足升序，第一个数越大越好
 * 切的数量越少越好
 * a = a1 + a2 + ... + ak
 * a1 <= a2 <= ...<= ak <= b
 * a1 大
 * k 小
 * a和k如果可以同时取到最优 -> 贪心
 * 如果不能同时取到最优 -> dp (枚举)
 * 假设k固定 -> a1什么时候可以取到最大值呢？ 越平均，最小值最大  a / k
 * ai <= aj - 2  => ai + 1 <= aj - 1  => max(ai + 1, aj - 1) 变小
 * 在最优解里，所有值尽量均匀，差值不超过1
 * 如果能整除，所有数都是 a / k
 * 如果有余数，那就让最后几个数 + 1
 * 第一个数就是 a1max = a / k
 * a1最大值和k负相关，a1越大，k越小 => k取最小值的时候，a1恰好取最大值，所以这2个参数是相关的
 * => 让k最小即可。
 * 每一份最多拆成b => 最少拆 a/b 上取整次 => k >= [a/b] => k = [a/b]
 * a/k <= b
 * (1) 如果能整除的话， a/k = a/(a/b) = b
 * (2) 如果不能取整的话，a/k < b
 * 从后往前扫描一遍就可以了
 * [a/b] = (a+b-1)/b
 */