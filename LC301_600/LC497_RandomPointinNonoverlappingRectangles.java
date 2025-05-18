package LC301_600;
import java.util.*;
public class LC497_RandomPointinNonoverlappingRectangles {
    /**
     * You are given an array of non-overlapping axis-aligned rectangles rects where rects[i] = [ai, bi, xi, yi]
     * indicates that (ai, bi) is the bottom-left corner point of the ith rectangle and (xi, yi) is the top-right corner
     * point of the ith rectangle. Design an algorithm to pick a random integer point inside the space covered by one of
     * the given rectangles. A point on the perimeter of a rectangle is included in the space covered by the rectangle.
     *
     * Any integer point inside the space covered by one of the given rectangles should be equally likely to be returned.
     *
     * Note that an integer point is a point that has integer coordinates.
     *
     * Implement the Solution class:
     *
     * Solution(int[][] rects) Initializes the object with the given rectangles rects.
     * int[] pick() Returns a random integer point [u, v] inside the space covered by one of the given rectangles.
     *
     * Input
     * ["Solution", "pick", "pick", "pick", "pick", "pick"]
     * [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
     * Output
     * [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
     *
     * Constraints:
     *
     * 1 <= rects.length <= 100
     * rects[i].length == 4
     * -109 <= ai < xi <= 10^9
     * -109 <= bi < yi <= 10^9
     * xi - ai <= 2000
     * yi - bi <= 2000
     * All the rectangles do not overlap.
     * At most 10^4 calls will be made to pick.
     * @param rects
     */
    // time = O(nlogn), space = O(n)
    int n;
    int[][] rects;
    int[] s;
    Random random;
    public LC497_RandomPointinNonoverlappingRectangles(int[][] rects) {
        this.rects = rects;
        n = rects.length;
        s = new int[n + 1];
        random = new Random();
        for (int i = 1; i <= n; i++) {
            int dx = rects[i - 1][2] - rects[i - 1][0] + 1;
            int dy = rects[i - 1][3] - rects[i - 1][1] + 1;
            s[i] = s[i - 1] + dx * dy;
        }
    }

    public int[] pick() {
        int k = random.nextInt(s[n]) + 1;
        int l = 1, r = n;
        while (l < r) {
            int mid = l + r >> 1;
            if (s[mid] >= k) r = mid;
            else l = mid + 1;
        }
        int[] t = rects[r - 1];
        int dx = t[2] - t[0] + 1;
        int dy = t[3] - t[1] + 1;
        return new int[]{random.nextInt(dx) + t[0], random.nextInt(dy) + t[1]};
    }
}
/**
 * rand() % M => [0, M-1]
 * ______|____|______
 * 把矩形的面积变化为线段的长度，最后从数轴上选一个点，线段越长，落到的概率就越高
 * 先求下矩形的面积，用线段上的一段来表示矩形，每段长度恰好等于矩形面积
 * 先随机在哪一段上，然后在矩形内部随机点
 */