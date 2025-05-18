package LC3001_3300;

public class LC3047_FindtheLargestAreaofSquareInsideTwoRectangles {
    /**
     * There exist n rectangles in a 2D plane. You are given two 0-indexed 2D integer arrays bottomLeft and topRight,
     * both of size n x 2, where bottomLeft[i] and topRight[i] represent the bottom-left and top-right coordinates of
     * the ith rectangle respectively.
     *
     * You can select a region formed from the intersection of two of the given rectangles. You need to find the largest
     * area of a square that can fit inside this region if you select the region optimally.
     *
     * Return the largest possible area of a square, or 0 if there do not exist any intersecting regions between the
     * rectangles.
     *
     * Input: bottomLeft = [[1,1],[2,2],[3,1]], topRight = [[3,3],[4,4],[6,6]]
     * Output: 1
     *
     * Input: bottomLeft = [[1,1],[2,2],[1,2]], topRight = [[3,3],[4,4],[3,4]]
     * Output: 1
     *
     * Input: bottomLeft = [[1,1],[3,3],[3,1]], topRight = [[2,2],[4,4],[4,2]]
     * Output: 0
     *
     * Constraints:
     *
     * n == bottomLeft.length == topRight.length
     * 2 <= n <= 10^3
     * bottomLeft[i].length == topRight[i].length == 2
     * 1 <= bottomLeft[i][0], bottomLeft[i][1] <= 10^7
     * 1 <= topRight[i][0], topRight[i][1] <= 10^7
     * bottomLeft[i][0] < topRight[i][0]
     * bottomLeft[i][1] < topRight[i][1]
     * @param bottomLeft
     * @param topRight
     * @return
     */
    // time = O(n^2), space = O(1)
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                res = Math.max(res, cal(bottomLeft[i], topRight[i], bottomLeft[j], topRight[j]));
            }
        }
        return res;
    }

    private long cal(int[] a, int[] b, int[] c, int[] d) {
        int x1 = Math.max(a[0], c[0]), y1 = Math.max(a[1], c[1]);
        int x2 = Math.min(b[0], d[0]), y2 = Math.min(b[1], d[1]);
        long t = Math.min(x2 - x1, y2 - y1);
        return t > 0 ? t * t : 0;
    }
}