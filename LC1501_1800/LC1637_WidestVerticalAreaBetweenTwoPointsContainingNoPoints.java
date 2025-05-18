package LC1501_1800;
import java.util.*;
public class LC1637_WidestVerticalAreaBetweenTwoPointsContainingNoPoints {
    /**
     * Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such
     * that no points are inside the area.
     *
     * A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The
     * widest vertical area is the one with the maximum width.
     *
     * Note that points on the edge of a vertical area are not considered included in the area.
     *
     * Input: points = [[8,7],[9,9],[7,4],[9,7]]
     * Output: 1
     *
     * Input: points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
     * Output: 3
     *
     * Constraints:
     *
     * n == points.length
     * 2 <= n <= 10^5
     * points[i].length == 2
     * 0 <= xi, yi <= 10^9
     * @param points
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (o1, o2) -> o1[0] - o2[0]);

        int n = points.length, max = 0;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, points[i][0] - points[i - 1][0]);
        }
        return max;
    }
}
