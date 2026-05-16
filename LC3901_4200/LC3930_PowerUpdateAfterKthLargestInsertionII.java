package LC3901_4200;
import java.util.*;
public class LC3930_PowerUpdateAfterKthLargestInsertionII {
    /**
     * You are given an integer array nums and an integer p.
     *
     * You are also given a 2D integer array queries, where each queries[i] = [vali, ki].
     *
     * For each query:
     *
     * Insert vali into nums.
     * Let x be the kith largest element in the current nums.
     * Update p to px % (109 + 7).
     * Return an array ans where the ans[i] represents the value of p after processing the ith query.
     *
     * Input: nums = [2], p = 4, queries = [[3,1],[1,2]]
     * Output: [64,4096]
     *
     * Input: nums = [7,5], p = 6, queries = [[4,3],[7,2]]
     * Output: [1296,220296870]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 10^4
     * 1 <= nums[i] <= 10^9
     * 1 <= p <= 10^9
     * 1 <= queries.length <= 2 * 10^4
     * 1 <= vali <= 10^9
     * 1 <= ki <= n + i + 1
     * @param nums
     * @param p
     * @param queries
     * @return
     */
    // time = O((n + q) * log(n + q)), space = O(logM)
    public List<Integer> powerUpdate(int[] nums, int p, int[][] queries) {
        long mod = (long)(1e9 + 7);
        int n = nums.length, m = queries.length;

        // coordinate compression
        List<Integer> q = new ArrayList<>();
        for (int x : nums) q.add(x);
        for (int[] qu : queries) q.add(qu[0]);
        q = new ArrayList<>(new HashSet<>(q));
        Collections.sort(q);
        Fenwick fen = new Fenwick(q.size());
        for (int x : nums) {
            int idx = Collections.binarySearch(q, x); // 0-based
            fen.add(idx, 1);
        }

        int tot = n;
        long cur = p;
        List<Integer> res = new ArrayList<>();
        for (int[] qu : queries) {
            int v = qu[0], k = qu[1];
            int idx = Collections.binarySearch(q, v);
            fen.add(idx, 1);
            tot++;

            // kth largest -> (total-k+1)th smallest, 1-based
            int order = tot - k + 1;
            idx = fen.select(order - 1); // 0-based index in q
            int x = q.get(idx);
            cur = qmi(cur, x, mod);
            res.add((int)cur);
        }
        return res;
    }

    private long qmi(long a, long k, long mod) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }

    class Fenwick {
        private int n;
        private int[] a;

        public Fenwick(int n) {
            init(n);
        }

        public void init(int n) {
            this.n = n;
            this.a = new int[n];
        }

        public void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i)
                a[i - 1] += v;
        }

        public int sum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= i & -i)
                ans += a[i - 1];
            return ans;
        }

        public int rangeSum(int l, int r) {
            return sum(r) - sum(l);
        }

        public int select(int k) {
            int x = 0, cur = 0;
            for (int i = Integer.highestOneBit(n); i > 0; i /= 2) {
                if (x + i <= n && cur + a[x + i - 1] <= k) {
                    x += i;
                    cur += a[x - 1];
                }
            }
            return x;
        }
    }
}