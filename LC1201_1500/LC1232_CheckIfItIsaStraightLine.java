package LC1201_1500;

public class LC1232_CheckIfItIsaStraightLine {
    /**
     * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
     * Check if these points make a straight line in the XY plane.
     *
     * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
     * Output: true
     *
     * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
     * Output: false
     *
     * Constraints:
     *
     * 2 <= coordinates.length <= 1000
     * coordinates[i].length == 2
     * -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
     * coordinates contains no duplicate point.
     * @param coordinates
     * @return
     */
    // time = O(n), space = O(1)
    public boolean checkStraightLine(int[][] coordinates) {
        int n = coordinates.length;
        for (int i = 2; i < n; i++) {
            int x1 = coordinates[1][0] - coordinates[0][0], y1 = coordinates[1][1] - coordinates[0][1];
            int x2 = coordinates[i][0] - coordinates[0][0], y2 = coordinates[i][1] - coordinates[0][1];
            if (x1 * y2 - x2 * y1 != 0) return false;
        }
        return true;
    }
}
/**
 * 判断共线 => 求叉积
 * 向量不涉及除法
 */