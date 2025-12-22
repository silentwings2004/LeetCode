package LC3601_3900;
import java.util.*;
public class LC3785_MinimumSwapstoAvoidForbiddenValues {
    /**
     * You are given two integer arrays, nums and forbidden, each of length n.
     *
     * You may perform the following operation any number of times (including zero):
     *
     * Choose two distinct indices i and j, and swap nums[i] with nums[j].
     * Return the minimum number of swaps required such that, for every index i, the value of nums[i] is not equal to
     * forbidden[i]. If no amount of swaps can ensure that every index avoids its forbidden value, return -1.
     *
     * Input: nums = [1,2,3], forbidden = [3,2,1]
     * Output: 1
     *
     * Input: nums = [4,6,6,5], forbidden = [4,6,5,5]
     * Output: 2
     *
     * Input: nums = [7,7], forbidden = [8,7]
     * Output: -1
     *
     * Input: nums = [1,2], forbidden = [2,1]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length == forbidden.length <= 10^5
     * 1 <= nums[i], forbidden[i] <= 10^9
     * @param nums
     * @param forbidden
     * @return
     */
    // time = O(n), space = O(n)
    public int minSwaps(int[] nums, int[] forbidden) {
        int n = nums.length;
        HashMap<Integer, Integer> cn = new HashMap<>();
        HashMap<Integer, Integer> cf = new HashMap<>();
        for (int x : nums) cn.put(x, cn.getOrDefault(x, 0) + 1);
        for (int x : forbidden) cf.put(x, cf.getOrDefault(x, 0) + 1);
        for (int k : cn.keySet()) {
            if (cn.get(k) + cf.getOrDefault(k, 0) > n) return -1;
        }
        int cnt = 0, mf = 0;
        HashMap<Integer, Integer> cb = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == forbidden[i]) {
                cb.put(nums[i], cb.getOrDefault(nums[i], 0) + 1);
                mf = Math.max(mf, cb.get(nums[i]));
                cnt++;
            }
        }
        return Math.max(mf, (cnt + 1) / 2);
    }
}
/**
 * 结论题
 * 1. 判断是否有解?
 * 超过 n 个 5，抽屉原理 => 某列一定有 2 个 5
 * 否则一定有解，可以用反证法
 * # 5 <= n
 * n - 1 + 2 = n + 1 > n => 矛盾
 * 1 1 1 2 2 3
 * 1 1 1 2 2 3
 * 怎么换？=> 最小操作次数
 * 首先统计下出现次数最多的数字
 * 如果其余的数字出现次数更多的话，操作次数取决于其余数字的个数
 * 反之，那么就由这个最多的数字个数
 */