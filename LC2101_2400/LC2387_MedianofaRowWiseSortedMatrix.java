package LC2101_2400;

public class LC2387_MedianofaRowWiseSortedMatrix {
    /**
     * Given an m x n matrix grid containing an odd number of integers where each row is sorted in non-decreasing order,
     * return the median of the matrix.
     *
     * You must solve the problem in O(m * log(n)) time complexity.
     *
     * Input: grid = [[1,1,2],[2,3,3],[1,3,4]]
     * Output: 2
     *
     * Input: grid = [[1,1,3,3,4]]
     * Output: 3
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 500
     * m and n are both odd.
     * 1 <= grid[i][j] <= 10^6
     * grid[i] is sorted in non-decreasing order.
     * @param grid
     * @return
     */
    // time = O(m * log(n)), space = O(1)
    public int matrixMedian(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int l = 1, r = (int) 1e6, k = m * n / 2 + 1;
        while (l < r) {
            int mid = l + r + 1 >> 1, sum = 0;
            for (int i = 0; i < m; i++) {
                int idx = helper(grid[i], mid);
                sum += n - idx;
            }
            if (sum < k) r = mid - 1;
            else l = mid;
        }
        return r;
    }

    private int helper(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] < t) l = mid + 1;
            else r = mid;
        }
        return nums[r] >= t ? r : r + 1;
    }
}
