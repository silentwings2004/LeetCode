package LC3001_3300;

public class LC3229_MinimumOperationstoMakeArrayEqualtoTarget {
    /**
     * You are given two positive integer arrays nums and target, of the same length.
     *
     * In a single operation, you can select any subarray of nums and increment or decrement each element within that
     * subarray by 1.
     *
     * Return the minimum number of operations required to make nums equal to the array target.
     *
     * Input: nums = [3,5,1,2], target = [4,6,2,4]
     *
     * Output: 2
     *
     * Input: nums = [1,3,2], target = [2,1,4]
     *
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length == target.length <= 10^5
     * 1 <= nums[i], target[i] <= 10^8
     * @param nums
     * @param target
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long minimumOperations(int[] nums, int[] target) {
        int n = nums.length;
        long res = 0, s = 0;
        for (int i = 0; i < n; i++) {
            long d = target[i] - nums[i];
            long x = 0;
            if (s > 0 && d > 0 || s < 0 && d < 0) {
                if (Math.abs(s) > Math.abs(d)) {
                    s = d;
                    continue;
                } else {
                    x = Math.abs(s - d);
                    s = d;
                }
            } else {
                x = Math.abs(d);
                s = d;
            }
            res += x;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long minimumOperations2(int[] nums, int[] target) {
        int n = nums.length;
        long res = Math.max(target[0] - nums[0], 0);
        for (int i = 1; i < n; i++) {
            res += Math.max((target[i] - nums[i]) - (target[i - 1] - nums[i - 1]), 0);
        }
        res += Math.max(-(target[n - 1] - nums[n - 1]), 0);
        return res;
    }
}
/**
 * 1. 通过差分数组，把子数组的操作，转化成两个位置的变化
 *    计算 target[i] - nums[i] 的差分数组
 *    分析这个差分数组所需要的操作次数
 * 2. target[i] - nums[i] 得到一个数组 a
 * 相当于我需要从一个全为 0 的数组，操作成数组 a
 * 3. 求 a 的差分数组，原来的操作 (把子数组都增加 / 减少) 转换成对差分数组的2个位置的增加 / 减少
 * 4. 从左到右遍历查分数组，去统计操作次数
 */