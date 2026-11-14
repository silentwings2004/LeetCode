package LC3901_4200;
import java.util.*;
public class LC4095_CountFrequencyStaircaseSubarraysII {
    /**
     * You are given an integer array nums.
     *
     * Consider the distinct values of a subarray in the order in which they first appear.
     *
     * If there are k distinct values, the subarray is called a frequency staircase if the first distinct value occurs
     * exactly once, the second distinct value occurs exactly twice, and so on, with the kth distinct value occurring
     * exactly k times.
     *
     * Equivalently, the frequencies of the distinct values, in the order of their first occurrence, must be exactly
     * 1, 2, 3, ..., k.
     *
     * Return the number of frequency staircase subarrays in nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * The frequency of a value in a subarray is the number of times it occurs in that subarray.
     *
     * Input: nums = [5,8,8,2,2,2]
     * Output: 9
     *
     * Input: nums = [6,6,9,9]
     * Output: 5
     *
     * Input: nums = [2,3,4,4,3,4]
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(n * sqrt(n) + M), space = O(n + M)  M: max{nums}
    public int countStaircase(int[] nums) {
        int n = nums.length, mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int[] next = new int[n];
        int[] last = new int[mx + 1];
        Arrays.fill(last, n);
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            next[i] = last[x];
            last[x] = i;
        }

        List<Integer>[] pos = new List[mx + 1];
        for (int i = 0; i <= mx; i++) pos[i] = new ArrayList<>();
        int[] pidx = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            pidx[i] = pos[x].size();
            pos[x].add(i);
        }

        int[] w = new int[n + 1];
        for (int i = 1;; i++) {
            int x = i * (1 + i) / 2;
            if (x > n) break;
            w[x] = i;
        }

        int[] st = new int[mx + 1];
        int[] cnt = new int[mx + 1];
        int[] rank = new int[mx + 1];

        int res = 0;
        for (int i = 0; i < n; i++) {
            int rMax = next[i] - 1, cur = i + 1;
            int distinct = 0, satisfied = 0;
            for (int j = i; j <= rMax; j++) {
                int x = nums[j];
                if (st[x] != cur) {
                    st[x] = cur;
                    cnt[x] = 1;
                    distinct++;
                    rank[x] = distinct;

                    List<Integer> q = pos[x];
                    int idx = pidx[j], need = distinct;
                    if (idx + need - 1 >= q.size() || q.get(idx + need - 1) > rMax) break;
                    if (distinct == 1) satisfied = 1;
                } else {
                    int r = rank[x];
                    int before = cnt[x], after = before + 1;
                    cnt[x] = after;
                    if (before == r) satisfied--;
                    else if (after == r) satisfied++;
                }
                int len = j - i + 1;
                if (w[len] != 0 && distinct == satisfied) res++;
            }
        }
        return res;
    }

    // S2
    // time = O(n * sqrt(n)), sapce = O(n)
    public int countStaircase2(int[] nums) {
        int n = nums.length, mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        List<Integer>[] pos = new List[mx + 1];
        List<Integer> fs = new ArrayList<>();
        int res = 0;

        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            if (pos[x] == null) pos[x] = new ArrayList<>();
            pos[x].add(i); // pos[x][pos[x].size() - k] 即为右侧第 k 个 x 的下标

            // 把 fs 中的 x 移到最前面
            fs.remove(Integer.valueOf(x));
            fs.add(0, x);

            int m = fs.size();
            if (m * (m + 1) / 2 > n) { // firsts 不需要这么多元素
                fs.remove(m - 1);
                m--;
            }


            mx = 0;
            int mn = n;
            int r = i; // 子数组右开端点
            for (int k = 1; k <= m; k++) {
                x = fs.get(k - 1);
                r += k;
                // [i, r) 必须包含恰好 k 个 x
                List<Integer> ps = pos[x];
                int sz = ps.size();
                if (k > sz) break;
                // 更新 mx 和 mn 后，必须满足 mx < r <= mn
                mx = Math.max(mx, ps.get(sz - k));
                if (k < sz) mn = Math.min(mn, ps.get(sz - k - 1));
                if (mx >= mn || r > mn) break; // 继续遍历，r 更大，mn 更小，无法找到合法子数组
                if (mx < r) res++;
            }
        }
        return res;
    }
}
/**
 * k = sqrt(n)
 * l, k => O(n * sqrt(n))
 * 4: [2,3,5]
 * 对于子数组[L,R]来说，
 * 定义 p_i 表示子数组中的第 i 个不同元素，在子数组中的第 i 个数的下标
 * 定义 q_i 表示子数组中的第 i 个不同元素，在子数组中的第 i+1 个数的下标。如果没有，那么 q_i = n。
 * pi <= R < qi
 * [pi,q)
 * max(pi) <= R < min(qi)  i = 1 ~ k
 * mx <= R < mn  O(1) 判断子数组是否符合要求
 *
 * 结论：一个子数组是合法的，当且仅当
 * mx <= R < mn
 * mx 是 p_i 的前缀最大值
 * mn 是 q_i 的前缀最小值
 */