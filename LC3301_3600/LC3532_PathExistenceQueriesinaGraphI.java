package LC3301_3600;
import java.util.*;
public class LC3532_PathExistenceQueriesinaGraphI {
    /**
     * You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.
     *
     * You are also given an integer array nums of length n sorted in non-decreasing order, and an integer maxDiff.
     *
     * An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most
     * maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).
     *
     * You are also given a 2D integer array queries. For each queries[i] = [ui, vi], determine whether there exists a
     * path between nodes ui and vi.
     *
     * Return a boolean array answer, where answer[i] is true if there exists a path between ui and vi in the ith query
     * and false otherwise.
     *
     * Input: n = 2, nums = [1,3], maxDiff = 1, queries = [[0,0],[0,1]]
     * Output: [true,false]
     *
     * Input: n = 4, nums = [2,5,6,8], maxDiff = 2, queries = [[0,1],[0,2],[1,3],[2,3]]
     * Output: [false,false,true,true]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * nums is sorted in non-decreasing order.
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
    // S1: Union Find
    // time = O(n + m), space = O(n)
    int[] p;
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        for (int i = 1, j = 0; i < n; i++) {
            while (nums[i] - nums[j] > maxDiff) j++;
            p[find(j)] = find(i);
        }

        int m = queries.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            if (find(u) == find(v)) res[i] = true;
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    // S2: Binary Search
    // time = O(n + mlogn), space = O(n)
    public boolean[] pathExistenceQueries2(int n, int[] nums, int maxDiff, int[][] queries) {
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] > maxDiff) q.add(i);
        }
        int m = queries.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            res[i] = binarySearch(q, u) == binarySearch(q, v);
        }
        return res;
    }

    private int binarySearch(List<Integer> q, int t) {
        int x = Collections.binarySearch(q, t);
        return x < 0 ? ~x : x; // 如果找不到 (~i 相当于 -(i+1))，得到正确的插入位置
    }

    // S3: 记录连通块的编号 (Optimal)
    // time = O(n + m), space = O(n)
    public boolean[] pathExistenceQueries3(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] id = new int[n];
        for (int i = 1; i < n; i++) {
            id[i] = id[i - 1];
            if (nums[i] - nums[i - 1] > maxDiff) id[i]++;
        }
        int m = queries.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            res[i] = id[u] == id[v];
        }
        return res;
    }
}
/**
 * 由于 nums 是有序的，如果 nums[i+1] − nums[i] > maxDiff，那么编号 ≤i 的节点无法跳到编号 ≥i+1 的节点。
 * 这样的 i 叫做「间断点」。
 * 遍历 nums，把所有间断点记录在 idx 中.
 * 对于询问 [u,v]，我们在 idx 中二分查找 ≥u 的第一个间断点，以及 ≥v 的第一个间断点。如果这两个间断点相同，则可以从 u 到达 v，否则不能.
 *
 * 遍历 nums，计算每个节点所在连通块的编号，从 0 开始.
 * 如果 nums[i] − nums[i−1] > maxDiff，那么 i 在一个新的连通块中，编号加一。否则编号不变.
 */