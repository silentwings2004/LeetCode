package LC3301_3600;

public class LC3453_SeparateSquaresI {
    /**
     * You are given a 2D integer array squares. Each squares[i] = [xi, yi, li] represents the coordinates of the
     * bottom-left point and the side length of a square parallel to the x-axis.
     *
     * Find the minimum y-coordinate value of a horizontal line such that the total area of the squares above the line
     * equals the total area of the squares below the line.
     *
     * Answers within 10^-5 of the actual answer will be accepted.
     *
     * Note: Squares may overlap. Overlapping areas should be counted multiple times.
     *
     * Input: squares = [[0,0,1],[2,2,1]]
     * Output: 1.00000
     *
     * Input: squares = [[0,0,2],[1,1,1]]
     * Output: 1.16667
     *
     * Constraints:
     *
     * 1 <= squares.length <= 5 * 10^4
     * squares[i] = [xi, yi, li]
     * squares[i].length == 3
     * 0 <= xi, yi <= 10^9
     * 1 <= li <= 10^9
     * @param squares
     * @return
     */
    // time = O(nlogn), space = O(1)
    public double separateSquares(int[][] squares) {
        final double eps = 1e-5;
        double l = 0, r = 2e9;
        while (r - l > eps) {
            double mid = (l + r) / 2;
            double up = 0, down = 0;
            for (int[] t : squares) {
                int x = t[0], y = t[1], d = t[2];
                if (mid >= y && mid <= y + d) {
                    up += 1.0 * d * (y + d - mid);
                    down += 1.0 * d * (mid - y);
                } else if (mid >= y) down += 1.0 * d * d;
                else if (mid <= y + d) up += 1.0 * d * d;
            }
            if (up > down) l = mid;
            else r = mid;
        }
        return r;
    }
}