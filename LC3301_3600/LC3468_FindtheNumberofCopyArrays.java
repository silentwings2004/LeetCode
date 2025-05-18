package LC3301_3600;

public class LC3468_FindtheNumberofCopyArrays {
    /**
     * You are given an array original of length n and a 2D array bounds of length n x 2, where bounds[i] = [ui, vi].
     *
     * You need to find the number of possible arrays copy of length n such that:
     *
     * (copy[i] - copy[i - 1]) == (original[i] - original[i - 1]) for 1 <= i <= n - 1.
     * ui <= copy[i] <= vi for 0 <= i <= n - 1.
     * Return the number of such arrays.
     *
     * Input: original = [1,2,3,4], bounds = [[1,2],[2,3],[3,4],[4,5]]
     * Output: 2
     *
     * Input: original = [1,2,3,4], bounds = [[1,10],[2,9],[3,8],[4,7]]
     * Output: 4
     *
     * Input: original = [1,2,1,2], bounds = [[1,1],[2,3],[3,3],[2,3]]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= n == original.length <= 10^5
     * 1 <= original[i] <= 10^9
     * bounds.length == n
     * bounds[i].length == 2
     * 1 <= bounds[i][0] <= bounds[i][1] <= 10^9
     * @param original
     * @param bounds
     * @return
     */
    // time = O(n), space = O(1)
    public int countArrays(int[] original, int[][] bounds) {
        int n = original.length, l = Integer.MIN_VALUE, r = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            l = Math.max(l, bounds[i][0] - original[i]);
            r = Math.min(r, bounds[i][1] - original[i]);
        }
        return l > r ? 0 : r - l + 1;
    }
}