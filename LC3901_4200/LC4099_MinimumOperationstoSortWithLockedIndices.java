package LC3901_4200;

public class LC4099_MinimumOperationstoSortWithLockedIndices {
    /**
     * You are given an integer array nums of length n, where nums is a permutation of the integers in the range [1, n].
     *
     * You are also given a binary array locked of length n and an integer k.
     *
     * locked[i] = 1 means index i is locked.
     * locked[i] = 0 means index i is unlocked.
     * You may perform the following operations in any order:
     *
     * Swap: Choose an index i such that both indices i and i + 1 are currently unlocked, and swap nums[i] and nums[i + 1].
     * Unlock: Choose an index i such that locked[i] = 1 and permanently unlock it by setting locked[i] = 0.
     * You may perform any number of swap operations but at most k unlock operations.
     *
     * Return the minimum total number of operations required to sort nums in strictly increasing order. If it is
     * impossible, return -1.
     *
     * A permutation is a rearrangement of all the elements of an array.
     *
     * A binary array is an array which contains only 0 and 1.
     *
     * Input: nums = [2,1,3,4], locked = [0,1,0,1], k = 1
     * Output: 2
     *
     * Input: nums = [3,1,2], locked = [1,0,1], k = 2
     * Output: 4
     *
     * Input: nums = [2,3,1], locked = [1,0,0], k = 0
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == nums.length == locked.length <= 2000
     * 1 <= nums[i] <= n
     * 0 <= locked[i] <= 1
     * nums is a permutation of the integers in the range [1, n].
     * 0 <= k <= n
     * @param nums
     * @param locked
     * @param k
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int minOperations(int[] nums, int[] locked, int k) {
        int n = nums.length;
        int unlocks = 0, swaps = 0;
        for (int i = 0, j = 0, mx = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            if (mx == i + 1) {
                if (i > j) {
                    for (int u = j; u <= i; u++) {
                        if (locked[u] == 1) unlocks++;
                        if (unlocks > k) return -1;
                    }
                    for (int a = j; a <= i; a++) {
                        for (int b = a + 1; b <= i; b++) {
                            if (nums[a] > nums[b]) swaps++;
                        }
                    }
                }
                j = i + 1;
            }
        }
        return unlocks + swaps;
    }

    // S2: BIT
    // time = O(nlogn), space = O(n)
    public int minOperations2(int[] nums, int[] locked, int k) {
        int n = nums.length, res = 0;
        Fenwick fen = new Fenwick(n);
        for (int i = 0; i < n;) {
            if (nums[i] == i + 1) { // nums[i] 已经在正确的位置上
                i++;
                continue;
            }
            int mx = -1;
            for (; i < n && mx != i; i++) { // mx != i 是块结束的条件, 比如 [3, 2, 1]
                if (locked[i] > 0) {
                    if (k == 0) return -1; // 无法解锁
                    k--;
                    res++;
                }
                int x = nums[i];
                res += fen.rangeSum(x, n); // x 与其左侧元素的逆序对个数，即已插入的 > x 的个数
                fen.add(x - 1, 1);
                mx = Math.max(mx, x);
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
/**
 * 结论：只通过邻项交换，把数组排成有序数组，最少交换次数为数组的逆序对的个数
 * 证明：上下界
 * 设 I 是数组的逆序对个数
 * 下界：每次交换至多把 I 减少 1，所以至少交换 I 次
 * 上界：只要数组不是有序的，那么必然存在一对相邻元素 x > y, 那么选择这对元素操作，就能把 I 恰好减少 1
 * 分组循环 -> 把每一段找到
 */