package LC3601_3900;
import java.util.*;
public class LC3801_MinimumCosttoMergeSortedLists {
    /**
     * You are given a 2D integer array lists, where each lists[i] is a non-empty array of integers sorted in
     * non-decreasing order.
     *
     * You may repeatedly choose two lists a = lists[i] and b = lists[j], where i != j, and merge them. The cost to
     * merge a and b is:
     *
     * len(a) + len(b) + abs(median(a) - median(b)), where len and median denote the list length and median,
     * respectively.
     *
     * After merging a and b, remove both a and b from lists and insert the new merged sorted list in any position.
     * Repeat merges until only one list remains.
     *
     * Return an integer denoting the minimum total cost required to merge all lists into one single sorted list.
     *
     * The median of an array is the middle element after sorting it in non-decreasing order. If the array has an even
     * number of elements, the median is the left middle element.
     *
     * Input: lists = [[1,3,5],[2,4],[6,7,8]]
     * Output: 18
     *
     * Input: lists = [[1,1,5],[1,4,7,8]]
     * Output: 10
     *
     * Input: lists = [[1],[1]]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= lists.length <= 12
     * 1 <= lists[i].length <= 500
     * -109 <= lists[i][j] <= 10^9
     * lists[i] is sorted in non-decreasing order.
     * The sum of lists[i].length will not exceed 2000.
     * @param lists
     * @return
     */
    // time = O(2^n * mlogm), space = O(2^n)
    public long minMergeCost(int[][] lists) {
        final long inf = (long)1E18;
        int n = lists.length;
        long[] len = new long[1 << n];
        long[] median = new long[1 << n];
        int[] a = new int[2010];
        for (int i = 1; i < 1 << n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    for (int x : lists[j]) {
                        a[cnt++] = x;
                    }
                }
            }
            Arrays.sort(a, 0, cnt);
            len[i] = cnt;
            median[i] = a[(cnt - 1) / 2];
        }

        long[] f = new long[1 << n];
        Arrays.fill(f, inf);
        for (int i = 0; i < n; i++) f[1 << i] = 0;
        for (int i = 1; i < 1 << n; i++) {
            if (Integer.bitCount(i) < 2) continue;
            for (int j = i; j > 0; j = (j - 1) & i) {
                int k = i ^ j;
                if (j < k) {
                    long t = len[i] + Math.abs(median[j] - median[k]);
                    f[i] = Math.min(f[i], f[j] + f[k] + t);
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
/**
 * 前置只是：学会位运算，做过一些简单的状压 DP (全排列状压)
 * 最后一步
 */