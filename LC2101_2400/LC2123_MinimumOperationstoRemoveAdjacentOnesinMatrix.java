package LC2101_2400;
import java.util.*;
public class LC2123_MinimumOperationstoRemoveAdjacentOnesinMatrix {
    /**
     * You are given a 0-indexed binary matrix grid. In one operation, you can flip any 1 in grid to be 0.
     *
     * A binary matrix is well-isolated if there is no 1 in the matrix that is 4-directionally connected (i.e.,
     * horizontal and vertical) to another 1.
     *
     * Return the minimum number of operations to make grid well-isolated.
     *
     * Input: grid = [[1,1,0],[0,1,1],[1,1,1]]
     * Output: 3
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] is either 0 or 1.
     * @param grid
     * @return
     */
    // S1: 匈牙利算法
    // time = O(V * E), space = O(m * n)
    int[][] grid;
    int[] match;
    HashSet<Integer> set;
    int m, n;
    int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int minimumOperations(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        match = new int[m * n];
        Arrays.fill(match, -1);
        set = new HashSet<>();

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 1 || grid[i][j] == 0) continue;
                set.clear();
                if (find(i * n + j)) res++;
            }
        }
        return res;
    }

    private boolean find(int u) {
        int x = u / n, y = u % n;
        for (int[] dir : directions) {
            int i = x + dir[0];
            int j = y + dir[1];
            if (i < 0 || i >= m || j < 0 || j >= n) continue;
            int v = i * n + j;
            if (set.contains(v)) continue;
            if (grid[i][j] == 0) continue;
            set.add(v);
            int t = match[v];
            if (t == -1 || find(t)) {
                match[v] = u;
                return true;
            }
        }
        return false;
    }
}
/**
 * Hungarian 二分图
 * 无权最大二分图匹配，匈牙利算法
 * 有权的就是KM算法
 * ref: LC1820, 2123
 * 为什么可以转化成二分图匹配？
 * 300 x 300 => 无法暴力
 * 1 作为node, 相邻1之间有条边 => 特点：这图一定是二分图
 * 把所有结点恰好分为2部分，使得所有边都是跨接这两部分的。
 * 什么样的图可以分解为二分图呢？ => 不含有奇数个结点的环,所有边都是跨接2个集合的。
 * 在本题这样的2d matrix里面，如果要构成环的话，一定是偶数个结点，不可能出现奇数个！
 * 撸一圈的话，肯定是上下对称，左右对称的，一定是偶数。
 * 所以一定可以二分！！！
 * 要翻转之后没有2个1相连，则相当于把跨接的其中一个集合的1都变成0即可。
 * 翻转的集合B，点越少越好，而A集合越多越好。
 * 1. 如果有些点是孤立点，不和任何其他点相连，那这些点可以放在A，也可以放在B里，但B要越少越好，所以都扔A里面。
 * 2. B里的每个点都可能和A的若干点相连，A里的点也会指向B
 * 这个B里面的结点数目，就是最大二分图匹配。
 * 在这里面找出尽量多的边，使得这些边没有公共的顶点，就意味着在B集合里就对应不同的结点
 * 一条边对应着一个B点
 * 最大二分图匹配能找出多少个这样的匹配边，就意味着B里有多少个结点。
 * LC1820 就是最大二分图匹配
 * 建图 + 匈牙利算法
 */