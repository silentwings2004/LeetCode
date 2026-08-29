package LC3901_4200;
import java.util.*;
public class LC4033_ValidKUniqueSubarraysI {
    /**
     * You are given an integer array nums and an integer k.
     *
     * You are also given a 2D integer array queries, where queries[i] = [li, ri] represents the subarray nums[li..ri].
     *
     * For each query, the subarray nums[li..ri] is considered valid if:
     *
     * It contains exactly k distinct numbers, and
     * The frequency of every number in the subarray is even.
     * Note: The frequency of a number in a subarray is the number of times it occurs in that subarray.
     *
     * Return a boolean array ans, where ans[i] is true if nums[li..ri] is valid, and false otherwise.
     *
     * A subarray is a contiguous, non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,2,1], k = 2, queries = [[0,1],[0,3],[1,2]]
     * Output: [false,true,false]
     *
     * Input: nums = [3,3,3], k = 1, queries = [[1,2],[0,2]]
     * Output: [true,false]
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= n
     * 1 <= queries.length <= 10^5
     * queries[i] == [li, ri]
     * 0 <= li < ri <= n - 1
     * @param nums
     * @param k
     * @param queries
     * @return
     */
    // S1: Mo's algorithm
    // time = O(mlogm + (n + m) * sqrt(n)), space = O(m + mx) mx = max{nums}
    public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {
        int n = nums.length, m = queries.length;
        int bs = (int)Math.sqrt(n) + 1;
        int[][] qs = new int[m][3];
        for (int i = 0; i < m; i++) {
            qs[i] = new int[]{queries[i][0], queries[i][1], i};
        }
        Arrays.sort(qs, (o1, o2) -> {
            int ba = o1[0] / bs, bb = o2[0] / bs;
            if (ba != bb) return ba - bb;
            if (ba % 2 == 0) return o1[1] - o2[1];
            return o2[1] - o1[1];
        });

        boolean[] res = new boolean[m];
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int[] freq = new int[mx + 1];
        int l = 0, r = -1, t = 0, odd = 0;
        for (int[] q : qs) {
            while (l > q[0]) {
                int x = nums[--l];
                if (freq[x] == 0) t++;
                if (freq[x] % 2 == 0) odd++;
                else odd--;
                freq[x]++;
            }

            while (r < q[1]) {
                int x = nums[++r];
                if (freq[x] == 0) t++;
                if (freq[x] % 2 == 0) odd++;
                else odd--;
                freq[x]++;
            }

            while (l < q[0]) {
                int x = nums[l++];
                freq[x]--;
                if (freq[x] % 2 == 0) odd--;
                else odd++;
                if (freq[x] == 0) t--;
            }

            while (r > q[1]) {
                int x = nums[r--];
                freq[x]--;
                if (freq[x] % 2 == 0) odd--;
                else odd++;
                if (freq[x] == 0) t--;
            }
            res[q[2]] = t == k && odd == 0;
        }
        return res;
    }

    // S2: 滑窗 + 异或和
    // time = O(n + m), space = O(n)
    Random random = new Random();
    public boolean[] validSubarrays2(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        long[] s = new long[n + 1];
        HashMap<Integer, Long> hash = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (!hash.containsKey(x)) hash.put(x, random.nextLong());
            s[i + 1] = s[i] ^ hash.get(x);
        }

        // 枚举子数组右端点 r 的同事，维护子数组左端点的范围 [l1,l2)，使得子数组内恰好有 k 个不同元素
        int[] l1 = calcLeft(nums, k + 1);
        int[] l2 = calcLeft(nums, k); // 记录每个 r 对应的 l1, l2，保存在数组中

        int m = queries.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            res[i] = s[r + 1] == s[l] && l1[r] <= l && l < l2[r]; // 判断 l 是否在区间 [l1[r], l2[r]) 中
        }
        return res;
    }

    private int[] calcLeft(int[] nums, int k) {
        int n = nums.length;
        int[] lefts = new int[n];
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0, l = 0; i < n; i++) {
            int x = nums[i];
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            while (cnt.size() >= k) {
                int y = nums[l++];
                cnt.put(y, cnt.get(y) - 1);
                if (cnt.get(y) == 0) cnt.remove(y);
            }
            lefts[i] = l;
        }
        return lefts;
    }

    // S3: 树状数组(离线回答询问)
    // time = O(n + mlogn), space = O(n + m)
    class Solution {
        Random random = new Random();
        public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {
            int n = nums.length;
            long[] s = new long[n + 1];
            HashMap<Integer, Long> hash = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int x = nums[i];
                if (!hash.containsKey(x)) hash.put(x, random.nextLong());
                s[i + 1] = s[i] ^ hash.get(x);
            }

            List<int[]>[] q = new List[n];
            for (int i = 0; i < n; i++) q[i] = new ArrayList<>();
            int m = queries.length;
            for (int i = 0; i < m; i++) {
                int l = queries[i][0], r = queries[i][1];
                q[r].add(new int[]{l, i});
            }

            Fenwick fen = new Fenwick(n);
            HashMap<Integer, Integer> last = new HashMap<>();
            boolean[] res = new boolean[m];
            for (int r = 0; r < n; r++) {
                int x = nums[r];
                if (last.containsKey(x)) fen.add(last.get(x), -1);
                last.put(x, r);
                fen.add(r, 1);
                for (int[] v : q[r]) {
                    int l = v[0], idx = v[1];
                    res[idx] = s[r + 1] == s[l] && fen.rangeSum(l, r + 1) == k;
                }
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

            public int rangeSum(int l, int r) { // 左开右闭
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
}
/**
 * 2 个要求分别解决
 * 1.子数组里是否每个数字出现的次数都是偶数
 * 把 nums[i] -> randint(2^64 - 1)
 * 如果所有数的出现次数都是偶数，那么异或和一定是 0
 * 如果有出现次数为奇数的书，无论这种数字有多少个，异或和一定是一个在[0,2^64-1]中的随机数, 异或和恰好等于 0 的概率为 1 / (2^64)
 * 恰好算错的概率就很小了
 * 2. 子数组里恰好有 k 个不同数字
 *
 * 洛谷：P1972 HH 的项链 -> 树状数组
 *
 */