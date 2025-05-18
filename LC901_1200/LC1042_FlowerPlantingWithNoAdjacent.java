package LC901_1200;
import java.util.*;
public class LC1042_FlowerPlantingWithNoAdjacent {
    /**
     * You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional
     * path between garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers.
     *
     * All gardens have at most 3 paths coming into or leaving it.
     *
     * Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they
     * have different types of flowers.
     *
     * Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden.
     * The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.
     *
     * Input: n = 3, paths = [[1,2],[2,3],[3,1]]
     * Output: [1,2,3]
     *
     * Input: n = 4, paths = [[1,2],[3,4]]
     * Output: [1,2,1,2]
     *
     * Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
     * Output: [1,2,3,4]
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * 0 <= paths.length <= 2 * 10^4
     * paths[i].length == 2
     * 1 <= xi, yi <= n
     * xi != yi
     * Every garden has at most 3 paths coming into or leaving it.
     * @param n
     * @param paths
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 10010, M = N * 4;
    int[] h, e, ne;
    int idx;
    public int[] gardenNoAdj(int n, int[][] paths) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        Arrays.fill(h, -1);
        idx = 0;

        for (int[] p : paths) {
            int a = p[0] - 1, b = p[1] - 1;
            add(a, b);
            add(b, a);
        }

        int[] res = new int[n];
        for (int u = 0; u < n; u++) {
            boolean[] st = new boolean[5];
            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                st[res[j]] = true;
            }

            for (int i = 1; i <= 4; i++) {
                if (!st[i]) {
                    res[u] = i;
                    break;
                }
            }
        }
        return res;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}