package LC901_1200;
import java.util.*;
public class LC1074_NumberofSubmatricesThatSumtoTarget {
    /**
     * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
     *
     * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
     *
     * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is
     * different: for example, if x1 != x1'.
     *
     * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= matrix.length <= 100
     * 1 <= matrix[0].length <= 100
     * -1000 <= matrix[i] <= 1000
     * -10^8 <= target <= 10^8
     * @param matrix
     * @param target
     * @return
     */
    // S1
    // time = O(m^2 * n^2), space = O(m * n)
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            int[] temp = new int[n];
            for (int j = i; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    temp[k] += matrix[j][k];
                }
                count += helper(temp, target);
            }
        }
        return count;
    }

    private int helper(int[] temp, int target) {
        int n = temp.length, preSum = 0, count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 特别注意，开始要补个(0, 1)来应对从开始到现在为止的和刚好为target的情况，也算是一种，所以要+1

        for (int j = 0; j < n; j++) {
            preSum += temp[j];
            count += map.getOrDefault(preSum - target, 0);
            map.put(preSum, map.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    // S2:
    // time = O(m^2 * n), space = O(m * n)
    public int numSubmatrixSumTarget2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m + 1][n + 1];

        // 预处理下每列的前缀和
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] + matrix[i - 1][j - 1];
            }
        }

        int res = 0;
        for (int i = 1; i <= m; i++) { // 上行边界
            for (int j = i; j <= m; j++) { // 下行边界
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int presum = 0;
                for (int k = 1; k <= n; k++) {
                    presum += s[j][k] - s[i - 1][k]; // 上下行边界之间的列之和
                    res += map.getOrDefault(presum - target, 0);
                    map.put(presum, map.getOrDefault(presum, 0) + 1);
                }
            }
        }
        return res;
    }
}
/**
 * 大矩阵里找小矩阵，找上界和下界，然后拍扁成1D array，在列上都累加起来
 * 找一个顶在第二行，底在第三行的submatrix，就相当于找一个interval
 * 遍历上下两条边，把中间的累加起来 -> 找一个interval，使得sum == target
 * YYYY[YYYY]YYYY
 * 在一个一维数组里，找sum => prefix sum
 * preSum[i] - preSum[j] = target
 * 设计一个有序容器，用二分法去查找
 * time = O(m * m * n * n)
 */
