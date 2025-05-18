package LC3301_3600;
import java.util.*;
public class LC3462_MaximumSumWithatMostKElements {
    /**
     * You are given a 2D integer matrix grid of size n x m, an integer array limits of length n, and an integer k.
     * The task is to find the maximum sum of at most k elements from the matrix grid such that:
     *
     * The number of elements taken from the ith row of grid does not exceed limits[i].
     *
     * Return the maximum sum.
     *
     * Input: grid = [[1,2],[3,4]], limits = [1,2], k = 2
     * Output: 7
     *
     * Input: grid = [[5,3,7],[8,2,6]], limits = [2,2], k = 3
     * Output: 21
     *
     * Constraints:
     *
     * n == grid.length == limits.length
     * m == grid[i].length
     * 1 <= n, m <= 500
     * 0 <= grid[i][j] <= 10^5
     * 0 <= limits[i] <= m
     * 0 <= k <= min(n * m, sum(limits))
     * @param grid
     * @param limits
     * @param k
     * @return
     */
    // time = O(m * n * logk), space = O(k)
    public long maxSum(int[][] grid, int[] limits, int k) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long res = 0;
        for (int i = 0; i < m; i++) {
            Arrays.sort(grid[i]);
            for (int j = n - 1, cnt = 0; j >= 0 && cnt < limits[i]; j--, cnt++) {
                pq.offer(grid[i][j]);
                res += grid[i][j];
                if (pq.size() > k) res -= pq.poll();
            }
        }
        return res;
    }
}