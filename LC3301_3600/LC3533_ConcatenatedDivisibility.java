package LC3301_3600;
import java.util.*;
public class LC3533_ConcatenatedDivisibility {
    /**
     * You are given an array of positive integers nums and a positive integer k.
     *
     * A permutation of nums is said to form a divisible concatenation if, when you concatenate the decimal
     * representations of the numbers in the order specified by the permutation, the resulting number is divisible by k.
     *
     * Return the lexicographically smallest permutation (when considered as a list of integers) that forms a divisible
     * concatenation. If no such permutation exists, return an empty list.
     *
     * A permutation is a rearrangement of all the elements of an array.
     *
     * An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a
     * has an element that is less than the corresponding element in b.
     * If the first min(a.length, b.length) elements do not differ, then the shorter array is the lexicographically
     * smaller one.
     *
     * Input: nums = [3,12,45], k = 5
     * Output: [3,12,45]
     *
     * Input: nums = [10,5], k = 10
     * Output: [5,10]
     *
     * Input: nums = [1,2,3], k = 5
     * Output: []
     *
     * Constraints:
     *
     * 1 <= nums.length <= 13
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= 100
     * @param nums
     * @param k
     * @return
     */
    // S1: 状压DP
    // time = O(2^n * n * k), space = O(2^n * k)
    public int[] concatenatedDivisibility(int[] nums, int k) {
        int n = nums.length, maxLen = 0;
        int[] len = new int[n], rem = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = String.valueOf(nums[i]).length();
            rem[i] = nums[i] % k;
            maxLen = Math.max(maxLen, len[i]);
        }
        int[] p = new int[maxLen + 1];
        p[0] = 1 % k;
        for (int i = 1; i <= maxLen; i++) p[i] = p[i - 1] * 10 % k;

        boolean[][] f = new boolean[1 << n][k];
        f[(1 << n) - 1][0] = true;
        for (int i = (1 << n) - 1; i >= 0; i--) {
            for (int r = 0; r < k; r++) {
                for (int j = 0; j < n; j++) {
                    if ((i >> j & 1) == 0) {
                        int ns = i | 1 << j;
                        int nr = (r * p[len[j]] + rem[j]) % k;
                        if (f[ns][nr]) {
                            f[i][r] = true;
                            break;
                        }
                    }
                }
            }
        }

        if (!f[0][0]) return new int[0];

        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) a[i] = new int[]{nums[i], i};
        Arrays.sort(a, (o1, o2) -> o1[0] - o2[0]);

        int[] res = new int[n];
        for (int i = 0, state = 0, r = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int idx = a[j][1];
                if ((state >> idx & 1) == 1) continue;
                int ns = state | 1 << idx;
                int nr = (r * p[len[idx]] + rem[idx]) % k;
                if (f[ns][nr]) {
                    res[i] = nums[idx];
                    state = ns;
                    r = nr;
                    break;
                }
            }
        }
        return res;
    }

    // S2: dfs
    // time = O(n * k * 2^n), space = O(2^n * k)
    int[] nums, p, res;
    boolean[][] f;
    int k;
    public int[] concatenatedDivisibility2(int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        Arrays.sort(nums);
        int n = nums.length;
        p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = (int)Math.pow(10, String.valueOf(nums[i]).length());
        }
        res = new int[n];
        f = new boolean[1 << n][k];
        if (!dfs((1 << n) - 1, 0)) return new int[0];
        return res;
    }

    private boolean dfs(int state, int x) {
        if (state == 0) return x == 0;
        if (f[state][x]) return false;
        f[state][x] = true;
        for (int i = 0; i < nums.length; i++) {
            if ((state >> i & 1) == 1 && dfs(state ^ (1 << i), (x * p[i] + nums[i]) % k)) {
                int idx = nums.length - Integer.bitCount(state);
                res[idx] = nums[i];
                return true;
            }
        }
        return false;
    }
}
/**
 * 状压DP
 * 要计算的是字典序最小的数组，不是拼接后的数
 * 为了让字典序最小，我们要从小到大枚举。
 * 核心思路：把 nums 从小到大排序，然后写一个类似 LC46 的暴搜
 * 枚举答案的第一个位置填 nums[0],nums[1],…,nums[n−1]
 * 枚举答案的第二个位置填 nums[0],nums[1],…,nums[n−1]，但不能填之前填过的数字
 * 在枚举的过程中，维护拼接的数字模 k 的结果。
 * 一旦我们找到了答案（拼接的 n 个数模 k 等于 0），就立刻返回 true，不再继续递归搜索。
 * 10! = 10^6 => 13! = 10^9, 太大
 * 复杂度优化
 * 最关键的地方：把 O(n!) -> O(2^n) 核心点：不关心填数字的顺序，只关心填了哪些数字
 * 注意有重复的状态，
 * 比如 k=2，先填 2 再填 4，24 mod k = 0；
 * 先填 4 再填 2，42 mod k=0，都会递归到「选了 2 和 4，且当前拼接的数字模 k 为 0」的状态
 * 无论先填 2 还是先填 4，我们其实并不关心，无论是 24 还是 42，都不影响接下来填什么
 * 所以只要记录前面填过哪些数字即可，填过的数字集合里的顺序无所谓
 * 如果集合里的数字很大怎么办？=> 用数字在数组里的下标来表示即可，一共 0~n-1个下标 => 2^n
 * => 只需要 2^n 种填法(选或不选) => 这样我们就把 O(n!) => O(2^n) 因为我们在这里并不关心全排列的顺序
 */