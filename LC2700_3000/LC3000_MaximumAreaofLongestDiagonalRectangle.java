package LC2700_3000;

public class LC3000_MaximumAreaofLongestDiagonalRectangle {
    /**
     * You are given a 2D 0-indexed integer array dimensions.
     *
     * For all indices i, 0 <= i < dimensions.length, dimensions[i][0] represents the length and dimensions[i][1]
     * represents the width of the rectangle i.
     *
     * Return the area of the rectangle having the longest diagonal. If there are multiple rectangles with the longest
     * diagonal, return the area of the rectangle having the maximum area.
     *
     * Input: dimensions = [[9,3],[8,6]]
     * Output: 48
     *
     * Input: dimensions = [[3,4],[4,3]]
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= dimensions.length <= 100
     * dimensions[i].length == 2
     * 1 <= dimensions[i][0], dimensions[i][1] <= 100
     * @param dimensions
     * @return
     */
    // time = O(n), space = O(1)
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int res = 0, maxL = 0;
        for (int[] x : dimensions) {
            int a = x[0], b = x[1];
            int d = a * a + b * b;
            if (d >= maxL) {
                if (d > maxL) {
                    maxL = d;
                    res = a * b;
                } else res = Math.max(res, a * b);
            }
        }
        return res;
    }
}