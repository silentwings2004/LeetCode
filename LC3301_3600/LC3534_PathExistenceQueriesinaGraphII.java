package LC3301_3600;
import java.util.*;
public class LC3534_PathExistenceQueriesinaGraphII {
    /**
     * You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.
     *
     * You are also given an integer array nums of length n and an integer maxDiff.
     *
     * An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most
     * maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).
     *
     * You are also given a 2D integer array queries. For each queries[i] = [ui, vi], find the minimum distance between
     * nodes ui and vi. If no path exists between the two nodes, return -1 for that query.
     *
     * Return an array answer, where answer[i] is the result of the ith query.
     *
     * Note: The edges between the nodes are unweighted.
     *
     * Input: n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]
     * Output: [1,1]
     *
     * Input: n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]
     * Output: [1,2,-1,1]
     *
     * Input: n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]
     * Output: [0,-1,-1]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * 0 <= maxDiff <= 10^5
     * 1 <= queries.length <= 10^5
     * queries[i] == [ui, vi]
     * 0 <= ui, vi < n
     * @param n
     * @param nums
     * @param maxDiff
     * @param queries
     * @return
     */
    // S1: binary lifting
    // time = O(nlogn), space = O(nlogn)
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) a[i] = new int[]{nums[i], i};
        Arrays.sort(a, (o1, o2) -> o1[0] - o2[0]);
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[a[i][1]] = i;

        int lg = 32 - Integer.numberOfLeadingZeros(n);
        int[][] l = new int[lg][n], r = new int[lg][n];

        for (int i = 0, j = 0; i < n; i++) {
            while (j + 1 < n && a[j + 1][0] - a[i][0] <= maxDiff) j++;
            r[0][i] = j;
        }
        for (int i = n - 1, j = n - 1; i >= 0; i--) {
            while (j - 1 >= 0 && a[i][0] - a[j - 1][0] <= maxDiff) j--;
            l[0][i] = j;
        }

        for (int k = 1; k < lg; k++) {
            for (int i = 0; i < n; i++) {
                l[k][i] = l[k - 1][l[k - 1][i]];
                r[k][i] = r[k - 1][r[k - 1][i]];
            }
        }

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            int pu = pos[u], pv = pos[v];
            if (pu == pv) continue;
            if (pu < pv) {
                if (getRight(pu, r, lg) < pv) res[i] = -1;
                else {
                    int t = 0;
                    for (int k = lg - 1; k >= 0; k--) {
                        int nxt = r[k][pu];
                        if (nxt < pv) {
                            t |= 1 << k;
                            pu = nxt;
                        }
                    }
                    res[i] = t + 1;
                }
            } else {
                if (getLeft(pu, l, lg) > pv) res[i] = -1;
                else {
                    int t = 0;
                    for (int k = lg - 1; k >= 0; k--) {
                        int nxt = l[k][pu];
                        if (nxt > pv) {
                            t |= 1 << k;
                            pu = nxt;
                        }
                    }
                    res[i] = t + 1;
                }
            }
        }
        return res;
    }

    private int getLeft(int x, int[][] l, int lg) {
        for (int k = lg - 1; k >= 0; k--) {
            int nxt = l[k][x];
            x = Math.min(x, nxt);
        }
        return x;
    }

    private int getRight(int x, int[][] r, int lg) {
        for (int k = lg - 1; k >= 0; k--) {
            int nxt = r[k][x];
            x = Math.max(x, nxt);
        }
        return x;
    }

    // S2: two pointers + binary lifting
    // time = ((n + m) * logn), space = O(nlogn)
    public int[] pathExistenceQueries2(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (o1, o2) -> nums[o1] - nums[o2]);

        int[] rank = new int[n];
        for (int i = 0; i < n; i++) rank[idx[i]] = i;

        int lg = 32 - Integer.numberOfLeadingZeros(n);
        int[][] fa = new int[n][lg];
        for (int i = 0, j = 0; i < n; i++) {
            while (nums[idx[i]] - nums[idx[j]] > maxDiff) j++;
            fa[i][0] = j;
        }
        // 倍增
        for (int k = 0; k < lg - 1; k++) {
            for (int i = 0; i < n; i++) {
                fa[i][k + 1] = fa[fa[i][k]][k];
            }
        }

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            if (l == r) continue;
            l = rank[l];
            r = rank[r];
            if (l > r) { // 保证 l 在 r 左边
                int tmp = l;
                l = r;
                r = tmp;
            }
            // 从 r 开始，向左跳到 l
            int t = 0;
            for (int k = lg - 1; k >= 0; k--) {
                if (fa[r][k] > l) {
                    t |= 1 << k;
                    r = fa[r][k];
                }
            }
            res[i] = fa[r][0] > l ? -1 : t + 1;
        }
        return res;
    }
}
/**
 * 把 nums 当作 n 个点，画在一维数轴上
 * 如果 nums[u] 与 nums[v] 的绝对差（距离）超过 maxDiff，无法一步到达，我们可以从 nums[v] 开始，向 nums[u] 的方向跳，
 * 但每一步的跳跃距离不能超过 maxDiff，且必须跳到点上，也就是跳到 nums 中的数上
 * 只跳一步，最多可以跳多远？可以排序后用 "双指针" 计算
 * 最少要跳多少步？可以用 "倍增" 计算
 */