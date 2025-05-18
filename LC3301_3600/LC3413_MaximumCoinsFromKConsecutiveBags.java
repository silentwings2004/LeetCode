package LC3301_3600;
import java.util.*;
public class LC3413_MaximumCoinsFromKConsecutiveBags {
    /**
     * There are an infinite amount of bags on a number line, one bag for each coordinate. Some of these bags contain
     * coins.
     *
     * You are given a 2D array coins, where coins[i] = [li, ri, ci] denotes that every bag from li to ri contains ci
     * coins.
     *
     * The segments that coins contain are non-overlapping.
     *
     * You are also given an integer k.
     *
     * Return the maximum amount of coins you can obtain by collecting k consecutive bags.
     *
     * Input: coins = [[8,10,1],[1,3,2],[5,6,4]], k = 4
     * Output: 10
     *
     * Input: coins = [[1,10,3]], k = 2
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= coins.length <= 10^5
     * 1 <= k <= 10^9
     * coins[i] == [li, ri, ci]
     * 1 <= li <= ri <= 10^9
     * 1 <= ci <= 1000
     * The given segments are non-overlapping.
     * @param coins
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins, (o1, o2) -> o1[0] - o2[0]);
        int n = coins.length;
        int[][] dq = new int[n + 1][3]; // {l, r, c}
        int hh = 0, tt = -1;
        long res = 0, s = 0;
        for (int i = 0; i < n; i++) {
            dq[++tt] = coins[i];
            int l = coins[i][0], r = coins[i][1], c = coins[i][2];
            s += 1L * (r - l + 1) * c;
            long sr = 0;
            while (hh <= tt && r - dq[hh][0] + 1 > k) {
                int right = Math.max(l, dq[hh][0] + k);
                int left = r - k;
                sr = Math.max(sr, s - 1L * (r - right + 1) * c);
                if (left > dq[hh][1]) {
                    s -= 1L * (dq[hh][1] - dq[hh][0] + 1) * dq[hh][2];
                    hh++;
                } else {
                    s -= 1L * (left - dq[hh][0] + 1) * dq[hh][2];
                    if (left + 1 <= dq[hh][1]) dq[hh][0] = left + 1;
                    else hh++;
                }
            }
            res = Math.max(res, Math.max(s, sr));
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(logn)
    public long maximumCoins2(int[][] coins, int k) {
        Arrays.sort(coins, (o1, o2) -> o1[0] - o2[0]);
        long res = helper(coins, k);
        coins = reverse(coins);
        return Math.max(res, helper(coins, k));
    }

    private long helper(int[][] a, int k) {
        int n = a.length;
        long res = 0, s = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int l = a[i][0], r = a[i][1], c = a[i][2];
            s += 1L * (r - l + 1) * c;
            while (r - k + 1 > a[j][1]) {
                s -= 1L * (a[j][1] - a[j][0] + 1) * a[j][2];
                j++;
            }
            long t = Math.max(1L * a[j][2] * (r - k + 1 - a[j][0]), 0);
            res = Math.max(res, s - t);
        }
        return res;
    }

    private int[][] reverse(int[][] a) {
        int n = a.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int[] t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
        for (int i = 0; i < n; i++) {
            int t = a[i][0];
            a[i][0] = -a[i][1];
            a[i][1] = -t;
        }
        return a;
    }
}
/**
 * ref: LC2271  => 无权值，对齐右端点即可
 * 有了权值之后，对齐左端点和对齐右端点是不同的。
 */