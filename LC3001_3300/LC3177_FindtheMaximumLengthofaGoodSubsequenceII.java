package LC3001_3300;
import java.util.*;
public class LC3177_FindtheMaximumLengthofaGoodSubsequenceII {
    /**
     * You are given an integer array nums and a non-negative integer k. A sequence of integers seq is called good if
     * there are at most k indices i in the range [0, seq.length - 2] such that seq[i] != seq[i + 1].
     *
     * Return the maximum possible length of a good subsequence of nums.
     *
     * Input: nums = [1,2,1,1,3], k = 2
     * Output: 4
     *
     * Input: nums = [1,2,3,4,5,1], k = 0
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^3
     * 1 <= nums[i] <= 10^9
     * 0 <= k <= min(50, nums.length)
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n * k * logn), space = O(n * k)
    public int maximumLength(int[] nums, int k) {
        int n = nums.length, res = 1;
        int[][] f = new int[n][k + 1];
        for (int i = 0; i < n; i++) f[i][0] = 1;

        HashMap<Integer, Integer>[] map = new HashMap[k + 1];
        TreeSet<int[]>[] set = new TreeSet[k + 1];
        for (int i = 0; i <= k; i++) {
            map[i] = new HashMap<>();
            set[i] = new TreeSet<>((o1, o2) -> o2[0] - o1[0]);
        }
        map[0].put(nums[0], 0);
        set[0].add(new int[]{1, nums[0]});

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                f[i][j] = 1;

                if (map[j].containsKey(nums[i])) {
                    int idx = map[j].get(nums[i]);
                    f[i][j] = Math.max(f[i][j], f[idx][j] + 1);
                    set[j].remove(new int[]{f[idx][j], nums[i]});
                }
                map[j].put(nums[i], i);

                if (j > 0 && set[j - 1].size() > 0) {
                    int[] t = set[j - 1].first();
                    if (t[1] != nums[i]) f[i][j] = Math.max(f[i][j], t[0] + 1);
                    else if (set[j - 1].size() > 1) {
                        set[j - 1].remove(t);
                        f[i][j] = Math.max(f[i][j], set[j - 1].first()[0] + 1);
                        set[j - 1].add(t);
                    }
                }
                set[j].add(new int[]{f[i][j], nums[i]});
                res = Math.max(res, f[i][j]);
            }
        }
        return res;
    }

    // S2
    // time = O(n * k), space = O(k)
    public int maximumLength2(int[] nums, int k) {
        HashMap<Integer, int[]> map = new HashMap<>();
        int[] f = new int[k + 2];
        for (int x : nums) {
            int[] g = new int[k + 1];
            if (!map.containsKey(x)) map.put(x, g);
            else g = map.get(x);
            for (int i = k; i >= 0; i--) {
                g[i] = Math.max(g[i], f[i]) + 1;
                f[i + 1] = Math.max(f[i + 1], g[i]);
            }
        }
        return f[k + 1];
    }

    // S3
    // time = O(n * k), space = O(n * k)
    public int maximumLength3(int[] nums, int k) {
        int n = nums.length;
        int[][] f = new int[n][k + 1];
        HashMap<Integer, Integer>[] hm = new HashMap[k + 1];
        int[] mx = new int[k + 1];
        for (int i = 0; i <= k; i++) hm[i] = new HashMap<>();

        int res = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                int t = 1;
                if (hm[j].containsKey(nums[i])) {
                    t = Math.max(t, hm[j].get(nums[i]) + 1);
                }
                if (j > 0) {
                    t = Math.max(t, mx[j - 1] + 1);
                }
                f[i][j] = t;
                res = Math.max(res, t);
            }
            for (int j = 0; j <= k; j++) {
                hm[j].put(nums[i], f[i][j]);
                mx[j] = Math.max(mx[j], f[i][j]);
            }
        }
        return res;
    }
}
/**
 * 子序列 dp 的两种模型：
 * 相邻相关 LIS
 * 相邻无关 0-1 背包
 *
 * LIS
 * dfs(i) 表示以 nums[i] 结尾的 LIS 长度
 * 转移：枚举 j < i, 如果 nums[j] < nums[i]，就从 dfs(j) + 1 转移过来
 *
 * dfs(i, j) 表示以 nums[i] 结尾的，有至多 j 对相邻元素的最长子序列的长度
 * 转移：枚举 p < i, 如果 nums[p] != nums[i] 就从 dfs(p, j-1) + 1 转移过来
 * 转移：枚举 p < i, 如果 nums[p] == nums[i] 就从 dfs(p, j) + 1 转移过来
 *
 * f[x][j] 表示维护以数值 x 结尾的，有至多 j 对相邻元素的最长子序列的长度
 * 设 x = nums[i]
 * 1. 不选：f[x][j] 不变 (不需要考虑)
 * 2. 选：把 x 加到以 x 结尾的子序列的末尾: f[x][j] = f[x][j] + 1
 * 3. 选：把 x 加到以 y 结尾的子序列的末尾: f[x][j] = f[y][j-1] + 1 if y != x
 * => f[x][j] = max(f[x][j] + 1, f[y][j-1] + 1)
 * mx[j] 表示 max(f[y][j] for y in set)
 * f[x][j] = max(f[x][j] + 1, mx + 1)
 */