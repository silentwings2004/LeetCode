package LC3601_3900;
import java.util.*;
public class LC3679_MinimumDiscardstoBalanceInventory {
    /**
     * You are given two integers w and m, and an integer array arrivals, where arrivals[i] is the type of item arriving
     * on day i (days are 1-indexed).
     *
     * Items are managed according to the following rules:
     *
     * Each arrival may be kept or discarded; an item may only be discarded on its arrival day.
     * For each day i, consider the window of days [max(1, i - w + 1), i] (the w most recent days up to day i):
     * For any such window, each item type may appear at most m times among kept arrivals whose arrival day lies in that
     * window.
     * If keeping the arrival on day i would cause its type to appear more than m times in the window, that arrival must
     * be discarded.
     * Return the minimum number of arrivals to be discarded so that every w-day window contains at most m occurrences
     * of each type.
     *
     * Input: arrivals = [1,2,1,3,1], w = 4, m = 2
     * Output: 0
     *
     * Input: arrivals = [1,2,3,3,3,4], w = 3, m = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= arrivals.length <= 10^5
     * 1 <= arrivals[i] <= 10^5
     * 1 <= w <= arrivals.length
     * 1 <= m <= w
     * @param arrivals
     * @param w
     * @param m
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minArrivalsToDiscard(int[] arrivals, int w, int m) {
        int n = arrivals.length, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        boolean[] st = new boolean[n];
        for (int i = 0, j = 0; i < n; i++) {
            if (i - j + 1 > w) {
                if (!st[j]) {
                    int y = arrivals[j];
                    map.put(y, map.get(y) - 1);
                }
                j++;
            }
            int x = arrivals[i], cnt = map.getOrDefault(x, 0);
            if (cnt + 1 > m) {
                st[i] = true;
                res++;
            } else map.put(x, map.getOrDefault(x, 0) + 1);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(k)
    public int minArrivalsToDiscard2(int[] arrivals, int w, int m) {
        int mx = arrivals[0];
        for (int x : arrivals) mx = Math.max(mx, x);
        int[] cnt = new int[mx + 1];
        int res = 0, n = arrivals.length;
        for (int i = 0, j = 0; i < n; i++) {
            int x = arrivals[i];
            if (cnt[x] == m) {
                arrivals[i] = 0;
                res++;
            } else cnt[x]++;
            int l = i - w + 1;
            if (l >= 0) cnt[arrivals[l]]--;
        }
        return res;
    }
}