package LC2700_3000;
import java.util.*;
public class LC2963_CounttheNumberofGoodPartitions {
    /**
     * You are given a 0-indexed array nums consisting of positive integers.
     *
     * A partition of an array into one or more contiguous subarrays is called good if no two subarrays contain the same
     * number.
     *
     * Return the total number of good partitions of nums.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Input: nums = [1,2,3,4]
     * Output: 8
     *
     * Input: nums = [1,1,1,1]
     * Output: 1
     *
     * Input: nums = [1,2,1,3]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int numberOfGoodPartitions(int[] nums) {
        long mod = (long)(1e9 + 7);
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) map.put(nums[i], i);
        for (int i = 0, r = 0; i < n; i++) {
            r = Math.max(r, map.get(nums[i]));
            if (r == i) {
                if (res == 0) res = 1;
                else res = res * 2 % mod;
            }
        }
        return (int)res;
    }
}
/**
 * 1.相同数字一定要在同一个子数组中
 * 2.推论：对于一个数字，找到它最左边的下标 p 和最右边的下标 q，那么 [p,q]是不可能分割的
 * 3.LC56 区间合并
 * 3 | 1 2 1 2 | 4 4
 * 枚举分割线 取还是不取 => 2^(m - 1)
 *
 * 算法：
 * 1.维护每个元素最后一次出现的下标
 * 2. 再次遍历数组，去合并区间
 */