package LC3901_4200;

public class LC4024_NearestAvailableDrone {
    /**
     * You are given a 2D integer array drones, where drones[i] = [xi, yi, rangei] represents the x-coordinate,
     * y-coordinate, and travel range of the ith drone.
     *
     * You are also given an integer array target = [tx, ty], representing the coordinates of the target.
     *
     * A drone drones[i] can reach the target if the Manhattan distance between its coordinates and the target
     * coordinates is less than or equal to its rangei.
     *
     * Return the index of the reachable drone with the minimum Manhattan distance to the target. If there is a tie,
     * return the smallest index. If no drone can reach the target, return -1.
     *
     * The Manhattan distance between two coordinates (xi, yi) and (xj, yj) is |xi - xj| + |yi - yj|.
     *
     * Input: drones = [[0,0,8],[2,2,9]], target = [3,4]
     * Output: 1
     *
     * Input: drones = [[2,1,5],[4,4,5],[6,6,8]], target = [5,5]
     * Output: 1
     *
     * Input: drones = [[4,4,5]], target = [8,6]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= drones.length <= 100
     * drones[i] = [xi, yi, rangei]
     * target = [tx, ty]
     * -25 <= xi, yi, tx, ty <= 25
     * 1 <= rangei <= 100
     * @param drones
     * @param target
     * @return
     */
    // time = O(n), space = O(1)
    public int nearestDrone(int[][] drones, int[] target) {
        int n = drones.length, md = 0x3f3f3f3f, res = -1;
        for (int i = 0; i < n; i++) {
            int x = drones[i][0], y = drones[i][1], r = drones[i][2];
            int d = Math.abs(x - target[0]) + Math.abs(y - target[1]);
            if (d <= r) {
                if (d < md) {
                    md = d;
                    res = i;
                }
            }
        }
        return res;
    }
}