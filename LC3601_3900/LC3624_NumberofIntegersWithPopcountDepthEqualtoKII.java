package LC3601_3900;
import java.util.*;
public class LC3624_NumberofIntegersWithPopcountDepthEqualtoKII {
    /**
     * You are given an integer array nums.
     *
     * For any positive integer x, define the following sequence:
     *
     * p0 = x
     * pi+1 = popcount(pi) for all i >= 0, where popcount(y) is the number of set bits (1's) in the binary representation
     * of y.
     * This sequence will eventually reach the value 1.
     *
     * The popcount-depth of x is defined as the smallest integer d >= 0 such that pd = 1.
     *
     * For example, if x = 7 (binary representation "111"). Then, the sequence is: 7 → 3 → 2 → 1, so the popcount-depth
     * of 7 is 3.
     *
     * You are also given a 2D integer array queries, where each queries[i] is either:
     *
     * [1, l, r, k] - Determine the number of indices j such that l <= j <= r and the popcount-depth of nums[j] is equal
     * to k.
     * [2, idx, val] - Update nums[idx] to val.
     * Return an integer array answer, where answer[i] is the number of indices for the ith query of type [1, l, r, k].
     *
     * Input: nums = [2,4], queries = [[1,0,1,1],[2,1,1],[1,0,1,0]]
     * Output: [2,1]
     *
     * Input: nums = [3,5,6], queries = [[1,0,2,2],[2,1,4],[1,1,2,1],[1,0,1,0]]
     * Output: [3,1,0]
     *
     * Input: nums = [1,2], queries = [[1,0,1,1],[2,0,3],[1,0,0,1],[1,0,0,2]]
     * Output: [1,0,1]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^15
     * 1 <= queries.length <= 10^5
     * queries[i].length == 3 or 4
     * queries[i] == [1, l, r, k] or,
     * queries[i] == [2, idx, val]
     * 0 <= l <= r <= n - 1
     * 0 <= k <= 5
     * 0 <= idx <= n - 1
     * 1 <= val <= 10^15
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n * k + (n + q) * logn), space = O(n * k)
    public int[] popcountDepth(long[] nums, long[][] queries) {
        final int N = 6;
        int n = nums.length;
        Fenwick[] fen = new Fenwick[N];
        for (int i = 0; i < N; i++) fen[i] = new Fenwick(n);
        int[] depth = new int[n];
        for (int i = 0; i < n; i++) {
            int d = get(nums[i]);
            depth[i] = d;
            fen[d].add(i, 1);
        }

        List<Integer> p = new ArrayList<>();
        for (long[] q : queries) {
            if (q[0] == 1) {
                int l = (int)q[1];
                int r = (int)q[2];
                int k = (int)q[3];
                p.add(fen[k].rangeSum(l, r + 1));
            } else {
                int idx = (int)q[1];
                long val = q[2];
                int od = depth[idx];
                fen[od].add(idx, -1);
                int nd = get(val);
                depth[idx] = nd;
                fen[nd].add(idx, 1);
            }
        }
        int[] res = new int[p.size()];
        for (int i = 0; i < p.size(); i++) res[i] = p.get(i);
        return res;
    }

    private int get(long x) {
        int d = 0;
        while (x != 1) {
            x = Long.bitCount(x);
            d++;
        }
        return d;
    }

    class Fenwick {
        private int n;
        private int[] a;
        public Fenwick(int n) {
            this.n = n;
            this.a = new int[n + 1];
        }

        private void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                a[i - 1] = a[i - 1] + v;
            }
        }

        private int sum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= i & -i) {
                ans = ans + a[i - 1];
            }
            return ans;
        }

        private int rangeSum(int l, int r) {
            return sum(r) - sum(l);
        }
    }
}
/**
 * f*(x) => f(2^63 - 1) = 63 ~ log2 很快收敛 迭代很快 => 最多4次
 * 哈希表套动态开点线段树 = 很大 k
 * 可持久化线段树 区间 <= k 的元素个数
 */
