package LC1501_1800;
import java.util.*;
public class LC1620_CoordinateWithMaximumNetworkQuality {
    /**
     * You are given an array of network towers towers, where towers[i] = [xi, yi, qi] denotes the ith network tower
     * with location (xi, yi) and quality factor qi. All the coordinates are integral coordinates on the X-Y plane, and
     * the distance between the two coordinates is the Euclidean distance.
     *
     * You are also given an integer radius where a tower is reachable if the distance is less than or equal to radius.
     * Outside that distance, the signal becomes garbled, and the tower is not reachable.
     *
     * The signal quality of the ith tower at a coordinate (x, y) is calculated with the formula ⌊qi / (1 + d)⌋, where
     * d is the distance between the tower and the coordinate. The network quality at a coordinate is the sum of the
     * signal qualities from all the reachable towers.
     *
     * Return the array [cx, cy] representing the integral coordinate (cx, cy) where the network quality is maximum. If
     * there are multiple coordinates with the same network quality, return the lexicographically minimum non-negative
     * coordinate.
     *
     * Note:
     *
     * A coordinate (x1, y1) is lexicographically smaller than (x2, y2) if either:
     * x1 < x2, or
     * x1 == x2 and y1 < y2.
     * ⌊val⌋ is the greatest integer less than or equal to val (the floor function).
     *
     * Input: towers = [[1,2,5],[2,1,7],[3,1,9]], radius = 2
     * Output: [2,1]
     *
     * Input: towers = [[23,11,21]], radius = 9
     * Output: [23,11]
     *
     * Input: towers = [[1,2,13],[2,1,7],[0,1,9]], radius = 2
     * Output: [1,2]
     *
     * Constraints:
     *
     * 1 <= towers.length <= 50
     * towers[i].length == 3
     * 0 <= xi, yi, qi <= 50
     * 1 <= radius <= 50
     * @param towers
     * @param radius
     * @return
     */
    // time = O(n), space = O(1)
    public int[] bestCoordinate(int[][] towers, int radius) {
        int max = 0;
        int[] res = new int[2];
        for (int x = 0; x <= 50; x++) {
            for (int y = 0; y <= 50; y++) {
                int sum = 0;
                for (int[] t : towers) {
                    int d = (t[0] - x) * (t[0] - x) + (t[1] - y) * (t[1] - y);
                    if (d <= radius * radius) {
                        sum += (int)(t[2] / (1 + Math.sqrt(d)));
                    }
                }
                if (sum > max) {
                    max = sum;
                    res = new int[]{x, y};
                }
            }
        }
        return res;
    }
}
