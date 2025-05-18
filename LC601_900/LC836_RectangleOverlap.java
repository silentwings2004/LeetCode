package LC601_900;

public class LC836_RectangleOverlap {
    /**
     * An axis-aligned rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) is the coordinate of its
     * bottom-left corner, and (x2, y2) is the coordinate of its top-right corner. Its top and bottom edges are parallel
     * to the X-axis, and its left and right edges are parallel to the Y-axis.
     *
     * Two rectangles overlap if the area of their intersection is positive. To be clear, two rectangles that only touch
     * at the corner or edges do not overlap.
     *
     * Given two axis-aligned rectangles rec1 and rec2, return true if they overlap, otherwise return false.
     *
     * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
     * Output: true
     *
     * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
     * Output: false
     *
     * Input: rec1 = [0,0,1,1], rec2 = [2,2,3,3]
     * Output: false
     *
     * Constraints:
     *
     * rec1.length == 4
     * rec2.length == 4
     * -10^9 <= rec1[i], rec2[i] <= 10^9
     * rec1 and rec2 represent a valid rectangle with a non-zero area.
     * @param rec1
     * @param rec2
     * @return
     */
    // time = O(1), space = O(1)
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return check(rec1[0], rec1[2], rec2[0], rec2[2]) && check(rec1[1], rec1[3], rec2[1], rec2[3]);
    }

    private boolean check(int a, int b, int c, int d) {
        return a < b && c < d && b > c && d > a;
    }
}