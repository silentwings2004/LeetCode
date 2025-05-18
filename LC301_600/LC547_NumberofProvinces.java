package LC301_600;
import java.util.*;
public class LC547_NumberofProvinces {
    /**
     * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
     * and city b is connected directly with city c, then city a is connected indirectly with city c.
     *
     * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
     *
     * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are
     * directly connected, and isConnected[i][j] = 0 otherwise.
     *
     * Return the total number of provinces.
     *
     * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 200
     * n == isConnected.length
     * n == isConnected[i].length
     * isConnected[i][j] is 1 or 0.
     * isConnected[i][i] == 1
     * isConnected[i][j] == isConnected[j][i]
     * @param isConnected
     * @return
     */
    // time = O(n^2*logn), space = O(n)
    int[] p;
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;

        int cnt = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1 && find(i) != find(j)) {
                    p[find(i)] = find(j);
                    cnt--;
                }
            }
        }
        return cnt;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * Union Find 并查集 模板题
 * 1 -> 2
 * 2 -> 3
 * 4 -> 2
 * 3 -> 3
 * 先找老祖宗，老祖宗不相等，说明两个家族得彼此union起来
 * 打死也要背下来, findFather和union两个method!!!
 */
