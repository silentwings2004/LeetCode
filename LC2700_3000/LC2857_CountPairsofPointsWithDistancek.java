package LC2700_3000;
import java.util.*;
public class LC2857_CountPairsofPointsWithDistancek {
    /**
     * You are given a 2D integer array coordinates and an integer k, where coordinates[i] = [xi, yi] are the
     * coordinates of the ith point in a 2D plane.
     *
     * We define the distance between two points (x1, y1) and (x2, y2) as (x1 XOR x2) + (y1 XOR y2) where XOR is the
     * bitwise XOR operation.
     *
     * Return the number of pairs (i, j) such that i < j and the distance between points i and j is equal to k.
     *
     * Input: coordinates = [[1,2],[4,2],[1,3],[5,2]], k = 5
     * Output: 2
     *
     * Input: coordinates = [[1,3],[1,3],[1,3],[1,3],[1,3]], k = 0
     * Output: 10
     *
     * Constraints:
     *
     * 2 <= coordinates.length <= 50000
     * 0 <= xi, yi <= 10^6
     * 0 <= k <= 100
     * @param coordinates
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int countPairs(List<List<Integer>> coordinates, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        int res = 0;
        for (List<Integer> c : coordinates) {
            int x1 = c.get(0), y1 = c.get(1);
            for (int u = 0; u <= 100; u++) {
                int x2 = u ^ x1, y2 = (k - u) ^ y1;
                String h = x2 + "#" + y2;
                if (map.containsKey(h)) res += map.get(h);
            }
            String h = x1 + "#" + y1;
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        return res;
    }
}