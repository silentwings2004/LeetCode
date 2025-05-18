package LC301_600;
import java.util.*;
public class LC368_LargestDivisibleSubset {
    /**
     * Given a set of distinct positive integers nums, return the largest subset answer such that every pair
     * (answer[i], answer[j]) of elements in this subset satisfies:
     *
     * answer[i] % answer[j] == 0, or
     * answer[j] % answer[i] == 0
     * If there are multiple solutions, return any of them.
     *
     * Input: nums = [1,2,3]
     * Output: [1,2]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 2 * 10^9
     * All the integers in nums are unique.
     * @param nums
     * @return
     */
    // S1: DP
    // time = O(n^2), space = O(n)
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, maxv = 0, t = 0;
        int[] f = new int[n], path = new int[n];
        Arrays.fill(f, 1);
        Arrays.fill(path, -1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (f[i] < f[j] + 1) {
                        f[i] = f[j] + 1;
                        path[i] = j;
                    }
                }
            }
            if (maxv < f[i]) {
                maxv = f[i];
                t = i;
            }
        }

        List<Integer> res = new LinkedList<>();
        while (t != -1) {
            res.add(0, nums[t]);
            t = path[t];
        }
        return res;
    }
}
/**
 * 2 3 4 9 36
 * 36接在3后面
 * 36也可以接在9后面
 * 找subset，从小到大排一些
 * 选定4作为第二大元素后，4和36之间就不能选了
 * 基本型II dp -> 今天的状态跟前面某个状态有关，找个最优的状态
 *
 * 只要看相邻2个数能否整除即可
 * f(i) = max(f(i), f(i) + 1)
 * 求方案，倒推
 *
 * dp 选或不选: 相邻无关问题，代表题目为 背包
 * 枚举选哪个: 适用于相邻相关的题目，代表题目为 LIS
 */