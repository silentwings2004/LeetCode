package LC1501_1800;

public class LC1515_BestPositionforaServiceCentre {
    /**
     * A delivery company wants to build a new service center in a new city. The company knows the positions of all the
     * customers in this city on a 2D-Map and wants to build the new center in a position such that the sum of the
     * euclidean distances to all customers is minimum.
     *
     * Given an array positions where positions[i] = [xi, yi] is the position of the ith customer on the map, return the
     * minimum sum of the euclidean distances to all customers.
     *
     * In other words, you need to choose the position of the service center [xcentre, ycentre] such that the following
     * formula is minimized:
     *
     * Answers within 10-5 of the actual value will be accepted.
     *
     * Input: positions = [[0,1],[1,0],[1,2],[2,1]]
     * Output: 4.00000
     *
     * Input: positions = [[1,1],[3,3]]
     * Output: 2.82843
     *
     * Constraints:
     *
     * 1 <= positions.length <= 50
     * positions[i].length == 2
     * 0 <= xi, yi <= 100
     * @param positions
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[][] points;
    public double getMinDistSum(int[][] positions) {
        points = positions;
        double l = 0, r = 100;
        while (r - l > 1e-7) {
            double x1 = l + (r - l) / 3;
            double x2 = l + (r - l) / 3 * 2;
            if (calc(x1) >= calc(x2)) l = x1;
            else r = x2;
        }
        return calc(r);
    }

    private double calc(double x) {
        double l = 0, r = 100;
        while (r - l > 1e-7) {
            double y1 = l + (r - l) / 3;
            double y2 = l + (r - l) / 3 * 2;
            if (get_sum(x, y1) >= get_sum(x, y2)) l = y1;
            else r = y2;
        }
        return get_sum(x, r);
    }

    private double get_sum(double x, double y) {
        double s = 0;
        for (int[] p : points) {
            double a = p[0], b = p[1];
            s += Math.sqrt((x - a) * (x - a) + (y - b) * (y - b));
        }
        return s;
    }
}
/**
 * 模拟退火，爬山法
 * 三分法
 * 迭代
 *
 * 1. 固定横坐标时，对于y是否是凸函数
 * 2. f(x0,y)
 */