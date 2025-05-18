package LC2401_2700;

public class LC2481_MinimumCutstoDivideaCircle {
    /**
     * A valid cut in a circle can be:
     *
     * A cut that is represented by a straight line that touches two points on the edge of the circle and passes through
     * its center, or
     * A cut that is represented by a straight line that touches one point on the edge of the circle and its center.
     * Some valid and invalid cuts are shown in the figures below.
     *
     * Given the integer n, return the minimum number of cuts needed to divide a circle into n equal slices.
     *
     * Input: n = 4
     * Output: 2
     *
     * Input: n = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public int numberOfCuts(int n) {
        if (n == 1) return 0;
        return n % 2 == 0 ? n / 2 : n;
    }
}
