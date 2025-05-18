package LC2700_3000;
import java.util.*;
public class LC2912_NumberofWaystoReachDestinationintheGrid {
    /**
     * You are given two integers n and m which represent the size of a 1-indexed grid. You are also given an integer k,
     * a 1-indexed integer array source and a 1-indexed integer array dest, where source and dest are in the form [x, y]
     * representing a cell on the given grid.
     *
     * You can move through the grid in the following way:
     *
     * You can go from cell [x1, y1] to cell [x2, y2] if either x1 == x2 or y1 == y2.
     * Note that you can't move to the cell you are already in e.g. x1 == x2 and y1 == y2.
     * Return the number of ways you can reach dest from source by moving through the grid exactly k times.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: n = 3, m = 2, k = 2, source = [1,1], dest = [2,2]
     * Output: 2
     *
     * Input: n = 3, m = 4, k = 3, source = [1,2], dest = [2,3]
     * Output: 9
     *
     * Constraints:
     *
     * 2 <= n, m <= 10^9
     * 1 <= k <= 10^5
     * source.length == dest.length == 2
     * 1 <= source[1], dest[1] <= n
     * 1 <= source[2], dest[2] <= m
     * @param n
     * @param m
     * @param k
     * @param source
     * @param dest
     * @return
     */
    // time = O(k), space = O(1)
    public int numberOfWays(int n, int m, int k, int[] source, int[] dest) {
        long mod = (long)(1e9 + 7);
        long[][] f = new long[2][4];
        f[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            f[i & 1][0] = (f[i - 1 & 1][1] * (m - 1) + f[i - 1 & 1][2] * (n - 1)) % mod;
            f[i & 1][1] = (f[i - 1 & 1][1] * (m - 2) + f[i - 1 & 1][0] + f[i - 1 & 1][3] * (n - 1)) % mod;
            f[i & 1][2] = (f[i - 1 & 1][2] * (n - 2) + f[i - 1 & 1][0] + f[i - 1 & 1][3] * (m - 1)) % mod;
            f[i & 1][3] = (f[i - 1 & 1][3] * (m - 2 + n - 2) + f[i - 1 & 1][1] + f[i - 1 & 1][2]) % mod;
        }
        if (source[0] == dest[0] && source[1] == dest[1]) return (int)f[k & 1][0];
        if (source[0] == dest[0]) return (int)f[k & 1][1];
        if (source[1] == dest[1]) return (int)f[k & 1][2];
        return (int)f[k & 1][3];
    }
}
/**
 * 0: source
 * 1: same row + diff col
 * 2: same col + diff row
 * 3: other (not include 0, 1, 2)
 * trans:
 * 0 -> same row diff col * (m - 1) + same col diff row * (n - 1)
 * 1 -> same row diff col * (m - 2) + source + same col diff row * (n - 1)
 * 2 -> same col diff row * (n - 2) + source + same row diff col * (m - 1)
 * 3 -> other * (n - 2 + m - 2) + same row diff col + same col diff row
 */