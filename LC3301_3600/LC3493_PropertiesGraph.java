package LC3301_3600;
import java.util.*;
public class LC3493_PropertiesGraph {
    /**
     * You are given a 2D integer array properties having dimensions n x m and an integer k.
     *
     * Define a function intersect(a, b) that returns the number of distinct integers common to both arrays a and b.
     *
     * Construct an undirected graph where each index i corresponds to properties[i]. There is an edge between node i
     * and node j if and only if intersect(properties[i], properties[j]) >= k, where i and j are in the range
     * [0, n - 1] and i != j.
     *
     * Return the number of connected components in the resulting graph.
     *
     * Input: properties = [[1,2],[1,1],[3,4],[4,5],[5,6],[7,7]], k = 1
     * Output: 3
     *
     * Input: properties = [[1,2,3],[2,3,4],[4,3,5]], k = 2
     * Output: 1
     *
     * Input: properties = [[1,1],[1,1]], k = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == properties.length <= 100
     * 1 <= m == properties[i].length <= 100
     * 1 <= properties[i][j] <= 100
     * 1 <= k <= m
     * @param properties
     * @param k
     * @return
     */
    // time = O(n^3 * logn), space = O(n)
    int[] p;
    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (check(properties[i], properties[j], k)) p[find(i)] = find(j);
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i == find(i)) res++;
        }
        return res;
    }

    private boolean check(int[] a, int[] b, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : a) set.add(x);
        int cnt = 0;
        for (int x : b) {
            if (set.contains(x)) {
                cnt++;
                set.remove(x);
            }
        }
        return cnt >= k;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}