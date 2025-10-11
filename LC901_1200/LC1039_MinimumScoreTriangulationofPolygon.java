package LC901_1200;

public class LC1039_MinimumScoreTriangulationofPolygon {
    /**
     * You have a convex n-sided polygon where each vertex has an integer value. You are given an integer array values
     * where values[i] is the value of the ith vertex (i.e., clockwise order).
     *
     * You will triangulate the polygon into n - 2 triangles. For each triangle, the value of that triangle is the
     * product of the values of its vertices, and the total score of the triangulation is the sum of these values over
     * all n - 2 triangles in the triangulation.
     *
     * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
     *
     * Input: values = [1,2,3]
     * Output: 6
     *
     * Input: values = [3,7,4,5]
     * Output: 144
     *
     * Input: values = [1,3,1,4,1,5]
     * Output: 13
     *
     * Constraints:
     *
     * n == values.length
     * 3 <= n <= 50
     * 1 <= values[i] <= 100
     * @param values
     * @return
     */
    // time = O(n^3), space = O(n^2)
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] f = new int[n][n];

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 3) f[i][j] = values[i] * values[i + 1] * values[j];
                else {
                    f[i][j] = (int) 1e9;
                    for (int k = i + 1; k < j; k++) {
                        f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + values[i] * values[k] * values[j]);
                    }
                }
            }
        }
        return f[0][n - 1];
    }
}
/**
 * dp
 * 状态表示：
 * 集合：所有(i,j)的划分方案的集合
 * 属性：权值的最小值
 * 状态计算：以(i-j)这条边属于哪个三角形来分类，可以分成j-i-1类
 */