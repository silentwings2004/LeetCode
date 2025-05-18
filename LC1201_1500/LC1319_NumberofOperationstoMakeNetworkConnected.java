package LC1201_1500;

import java.util.HashMap;

public class LC1319_NumberofOperationstoMakeNetworkConnected {
    /**
     * There are n computers numbered from 0 to n-1 connected by ethernet cables connections forming a network where
     * connections[i] = [a, b] represents a connection between computers a and b. Any computer can reach any other
     * computer directly or indirectly through the network.
     *
     * Given an initial computer network connections. You can extract certain cables between two directly connected
     * computers, and place them between any pair of disconnected computers to make them directly connected. Return the
     * minimum number of times you need to do this in order to make all the computers connected. If it's not possible,
     * return -1.
     *
     * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 1 <= connections.length <= min(n*(n-1)/2, 10^5)
     * connections[i].length == 2
     * 0 <= connections[i][0], connections[i][1] < n
     * connections[i][0] != connections[i][1]
     * There are no repeated connections.
     * No two computers are connected by more than one cable.
     * @param n
     * @param connections
     * @return
     */
    // time = O(m * a(n)), space = O(n)
    int[] p;
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        int res = n;
        for (int[] x : connections) {
            int a = x[0], b = x[1];
            if (find(a) != find(b)) {
                p[find(a)] = find(b);
                res--;
            }
        }
        return res - 1;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}