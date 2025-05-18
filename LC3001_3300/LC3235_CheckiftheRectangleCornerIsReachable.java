package LC3001_3300;

public class LC3235_CheckiftheRectangleCornerIsReachable {
    /**
     * You are given two positive integers X and Y, and a 2D array circles, where circles[i] = [xi, yi, ri] denotes a
     * circle with center at (xi, yi) and radius ri.
     *
     * There is a rectangle in the coordinate plane with its bottom left corner at the origin and top right corner at
     * the coordinate (X, Y). You need to check whether there is a path from the bottom left corner to the top right
     * corner such that the entire path lies inside the rectangle, does not touch or lie inside any circle, and touches
     * the rectangle only at the two corners.
     *
     * Return true if such a path exists, and false otherwise.
     *
     * Input: X = 3, Y = 4, circles = [[2,1,1]]
     *
     * Output: true
     *
     * Input: X = 3, Y = 3, circles = [[1,1,2]]
     *
     * Output: false
     *
     * Input: X = 3, Y = 3, circles = [[2,1,1],[1,2,1]]
     *
     * Output: false
     *
     * Constraints:
     *
     * 3 <= X, Y <= 10^9
     * 1 <= circles.length <= 1000
     * circles[i].length == 3
     * 1 <= xi, yi, ri <= 10^9
     * @param X
     * @param Y
     * @param circles
     * @return
     */
    // time = O(n^2), space = O(n)
    public boolean canReachCorner(int X, int Y, int[][] circles) {
        int n = circles.length;
        boolean[] st = new boolean[n];
        for (int i = 0; i < n; i++) {
            long x = circles[i][0], y = circles[i][1], r = circles[i][2];
            if (check(x, y, r, 0, 0) || check(x, y, r, X, Y)|| !st[i] && (x <= X && Math.abs(y - Y) <= r ||
                    y <= Y && x <= r || y > Y && check(x, y, r, 0, Y)) && dfs(i, X, Y, circles, st)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(long ox, long oy, long r, long x, long y) {
        return (ox - x) * (ox - x) + (oy - y) * (oy - y) <= r * r;
    }

    private boolean dfs(int i, int X, int Y, int[][] circles, boolean[] st) {
        long x1 = circles[i][0], y1 = circles[i][1], r1 = circles[i][2];
        if (y1 <= Y && Math.abs(x1 - X) <= r1 || x1 <= X && y1 <= r1 || x1 > X && check(x1, y1, r1, X, 0)) {
            return true;
        }
        st[i] = true;
        for (int j = 0; j < circles.length; j++) {
            long x2 = circles[j][0], y2 = circles[j][1], r2 = circles[j][2];
            if (!st[j] && (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) <= (r1 + r2) * (r1 + r2) &&
                    x1 * r2 + x2 * r1 < (r1 + r2) * X && y1 * r2 + y2 * r1 < (r1 + r2) * Y &&
                    dfs(j, X, Y, circles, st)) {
                return true;
            }
        }
        return false;
    }
}
/**
 * 第 i 个圆抽象成节点 i (0 <= i <= n - 1)
 * 2. 左边界和上边界抽象成节点 n
 * 3. 右边界和下边界抽象成节点 n + 1
 * 4. 根据圆和圆，圆和矩形的相交/相切关系，在这些节点之间连边
 * 5. 答案就是 n 和 n + 1 没有连起来 (不在同一个连通块内)
 */