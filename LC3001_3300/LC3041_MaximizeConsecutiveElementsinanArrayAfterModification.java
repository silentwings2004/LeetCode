package LC3001_3300;
import java.util.*;
public class LC3041_MaximizeConsecutiveElementsinanArrayAfterModification {
    /**
     * You are given a 0-indexed array nums consisting of positive integers.
     *
     * Initially, you can increase the value of any element in the array by at most 1.
     *
     * After that, you need to select one or more elements from the final array such that those elements are consecutive
     * when sorted in increasing order. For example, the elements [3, 4, 5] are consecutive while [3, 4, 6] and
     * [1, 1, 2, 3] are not.
     * Return the maximum number of elements that you can select.
     *
     * Input: nums = [2,1,5,1,1]
     * Output: 3
     *
     * Input: nums = [1,4,7,10]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int maxSelectedElements(int[] nums) {
        int n = nums.length, res = 0;
        Arrays.sort(nums);
        int[][] f = new int[n][2];
        HashMap<Integer, Integer> hm1 = new HashMap<>();
        HashMap<Integer, Integer> hm2 = new HashMap<>();

        for (int i = 0; i < n; i++) {
            f[i][0] = f[i][1] = 1;
            int x = nums[i];
            if (hm1.containsKey(x - 1)) f[i][0] = Math.max(f[i][0], f[hm1.get(x - 1)][0] + 1);
            if (hm2.containsKey(x - 1)) f[i][0] = Math.max(f[i][0], f[hm2.get(x - 1)][1] + 1);
            if (hm1.containsKey(x)) f[i][1] = Math.max(f[i][1], f[hm1.get(x)][0] + 1);
            if (hm2.containsKey(x)) f[i][1] = Math.max(f[i][1], f[hm2.get(x)][1] + 1);
            if (!hm1.containsKey(x) || f[i][0] > f[hm1.get(x)][0]) hm1.put(x, i);
            if (!hm2.containsKey(x + 1) || f[i][1] > f[hm2.get(x + 1)][1]) hm2.put(x + 1, i);
            res = Math.max(res, Math.max(f[i][0], f[i][1]));
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int maxSelectedElements2(int[] nums) {
        Arrays.sort(nums);
        HashMap<Integer, Integer> f = new HashMap<>();
        for (int x : nums) {
            f.put(x + 1, f.getOrDefault(x, 0) + 1);
            f.put(x, f.getOrDefault(x - 1, 0) + 1);
        }
        int res = 0;
        for (int v : f.values()) res = Math.max(res, v);
        return res;
    }
}
/**
 * 为什么可以排序？
 * 选出来的排序后的序列是 b
 * b[i] + 1 = b[i+1]
 * 操作前，是否会出现b[i] > b[i+1]?
 * 不会出现，因为 b[i] > b[i+1] 的时候不会出现操作后 b[i] + 1 = b[i+1]
 *
 * 子序列dp的思考套路：
 * 1. 01 背包: 选的子序列的相邻元素无关 [相邻无关]
 * 2. LIS  选的子序列的相邻元素时相关的 [相邻相关]
 * O(n^2)  f[i]  nums[i]  找 j < i 且 nums[j] < nums[i]
 * 从这样的 f[j] 转移过来取 max
 * dfs(i)
 *    nums[j] = nums[i] + 1
 *    dfs(j) + 1
 *    nums[j] = nums[i] + 1
 *    二分找右边第一个 nums[i] + 1
 *    从而用 O(logn) 时间找到转移来源
 * dfs(i, add1)
 * 二分找右边第一个 nums[i] + add1 或者 nums[i] + add1 + 1
 * 分别递归到dfs(j, 1)                dfs(j, 0)
 * j 是二分得到的下标
 */