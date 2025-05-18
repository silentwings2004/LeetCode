package LC601_900;
import java.util.*;
public class LC683_KEmptySlots {
    /**
     * You have n bulbs in a row numbered from 1 to n. Initially, all the bulbs are turned off. We turn on exactly one
     * bulb every day until all bulbs are on after n days.
     *
     * You are given an array bulbs of length n where bulbs[i] = x means that on the (i+1)th day, we will turn on the
     * bulb at position x where i is 0-indexed and x is 1-indexed.
     *
     * Given an integer k, return the minimum day number such that there exists two turned on bulbs that have exactly
     * k bulbs between them that are all turned off. If there isn't such day, return -1.
     *
     * Input: bulbs = [1,3,2], k = 1
     * Output: 2
     *
     * Input: bulbs = [1,2,3], k = 1
     * Output: -1
     *
     * Constraints:
     *
     * n == bulbs.length
     * 1 <= n <= 2 * 10^4
     * 1 <= bulbs[i] <= n
     * bulbs is a permutation of numbers from 1 to n.
     * 0 <= k <= 2 * 10^4
     * @param bulbs
     * @param k
     * @return
     */
    // S1: TreeSet
    // time = O(nlogn), space = O(n)
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int x = bulbs[i];
            Integer lk = set.lower(x), hk = set.higher(x);
            if (lk != null && x - lk - 1 == k) return i + 1;
            if (hk != null && hk - x - 1 == k) return i + 1;
            set.add(x);
        }
        return -1;
    }

    // S2: Sliding Window
    // time = O(n), space = O(n)
    public int kEmptySlots2(int[] bulbs, int k) {
        int n = bulbs.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[bulbs[i] - 1] = i + 1;
        int l = 0, r = k + 1, res = Integer.MAX_VALUE;
        for (int i = 0; r < n; i++) {
            if (a[i] < a[l] || a[i] <= a[r]) {
                if (i == r) res = Math.min(res, Math.max(a[l], a[r]));
                l = i;
                r = k + 1 + i;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}