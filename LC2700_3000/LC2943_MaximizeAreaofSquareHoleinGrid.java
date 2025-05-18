package LC2700_3000;
import java.util.*;
public class LC2943_MaximizeAreaofSquareHoleinGrid {
    /**
     * There is a grid with n + 2 horizontal bars and m + 2 vertical bars, and initially containing 1 x 1 unit cells.
     *
     * The bars are 1-indexed.
     *
     * You are given the two integers, n and m.
     *
     * You are also given two integer arrays: hBars and vBars.
     *
     * hBars contains distinct horizontal bars in the range [2, n + 1].
     * vBars contains distinct vertical bars in the range [2, m + 1].
     * You are allowed to remove bars that satisfy any of the following conditions:
     *
     * If it is a horizontal bar, it must correspond to a value in hBars.
     * If it is a vertical bar, it must correspond to a value in vBars.
     * Return an integer denoting the maximum area of a square-shaped hole in the grid after removing some bars
     * (possibly none).
     *
     * Input: n = 2, m = 1, hBars = [2,3], vBars = [2]
     * Output: 4
     *
     * Input: n = 1, m = 1, hBars = [2], vBars = [2]
     * Output: 4
     *
     * Input: n = 2, m = 3, hBars = [2,3], vBars = [2,3,4]
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * 1 <= m <= 10^9
     * 1 <= hBars.length <= 100
     * 2 <= hBars[i] <= n + 1
     * 1 <= vBars.length <= 100
     * 2 <= vBars[i] <= m + 1
     * All values in hBars are distinct.
     * All values in vBars are distinct.
     * @param n
     * @param m
     * @param hBars
     * @param vBars
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int t = Math.min(find(hBars), find(vBars));
        return t * t;
    }

    private int find(int[] w) {
        Arrays.sort(w);
        int res = 0;
        int n = w.length;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && w[j] - w[j - 1] == 1) j++;
            int len = j - i;
            res = Math.max(res, len + 1);
            i = j - 1;
        }
        return res;
    }
}
/**
 * 1. 横线，竖线是相互独立的
 * 2. 把 hBars 排序，找连续递增最长子段，子段长度+1就是这条边的最长长度
 */