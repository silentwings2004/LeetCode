package LC3901_4200;
import java.util.*;
public class LC4042_ValidKUniqueSubarraysII {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * You are also given integers l0 and r0, which define the first query, and an integer q, representing the total
     * number of queries to process.
     *
     * A subarray nums[li..ri] is considered valid if:
     *
     * It contains exactly k distinct numbers, and
     * Every distinct number in it occurs an even number of times.
     * For query 0, set l0 = l0 and r0 = r0.
     *
     * Let ansi denote the result of the ith query, where ansi = 1 if nums[li..ri] is valid, and ansi = 0 otherwise.
     *
     * For each i > 0, generate the next query as follows:
     *
     * If ansi-1 = 1, set gi-1 = li-1 + ri-1. Otherwise, set gi-1 = ri-1 - li-1.
     * Compute li = (li-1 XOR gi-1) % n and ri = (ri-1 XOR gi-1) % n.
     * If li > ri, swap them.
     * Return a boolean array ans, where ans[i] is true if ansi = 1, and false otherwise.
     *
     * Input: nums = [1,2,2,1], k = 2, l0 = 1, r0 = 2, q = 2
     * Output: [false,true]
     *
     * Input: nums = [1,2,3,3,4], k = 1, l0 = 2, r0 = 3, q = 2
     * Output: [true,false]
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 5 × 10^5
     * 1 <= nums[i] <= 5 × 10^5
     * 1 <= k <= n
     * 0 <= l0 < r0 <= n - 1
     * 1 <= q <= 5 × 10^5
     * @param nums
     * @param k
     * @param l0
     * @param r0
     * @param q
     * @return
     */
    // time = O(n + q), space = O(n)
    Random random = new Random();
    public boolean[] validSubarrays(int[] nums, int k, int l0, int r0, int q) {
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

        boolean[] res = new boolean[q];
        int l = 0, r = 0;
        for (int i = 0; i < q; i++) {
            if (i == 0) {
                l = l0;
                r = r0;
            } else {
                int g = res[i - 1] ? l + r : r - l;
                int nl = (l ^ g) % n;
                int nr = (r ^ g) % n;
                if (nl > nr) {
                    int t = nl;
                    nl = nr;
                    nr = t;
                }
                l = nl;
                r = nr;
            }
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
}