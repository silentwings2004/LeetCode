package LC3301_3600;
import java.util.*;
public class LC3394_CheckifGridcanbeCutintoSections {
    /**
     * You are given an integer n representing the dimensions of an n x n grid, with the origin at the bottom-left
     * corner of the grid. You are also given a 2D array of coordinates rectangles, where rectangles[i] is in the form
     * [startx, starty, endx, endy], representing a rectangle on the grid. Each rectangle is defined as follows:
     *
     * (startx, starty): The bottom-left corner of the rectangle.
     * (endx, endy): The top-right corner of the rectangle.
     * Note that the rectangles do not overlap. Your task is to determine if it is possible to make either two
     * horizontal or two vertical cuts on the grid such that:
     *
     * Each of the three resulting sections formed by the cuts contains at least one rectangle.
     * Every rectangle belongs to exactly one section.
     * Return true if such cuts can be made; otherwise, return false.
     *
     * Input: n = 5, rectangles = [[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]]
     * Output: true
     *
     * Input: n = 4, rectangles = [[0,0,1,1],[2,0,3,4],[0,2,2,3],[3,0,4,3]]
     * Output: true
     *
     * Input: n = 4, rectangles = [[0,2,2,4],[1,0,3,2],[2,2,3,4],[3,0,4,2],[3,2,4,4]]
     * Output: false
     *
     * Constraints:
     *
     * 3 <= n <= 10^9
     * 3 <= rectangles.length <= 10^5
     * 0 <= rectangles[i][0] < rectangles[i][2] <= n
     * 0 <= rectangles[i][1] < rectangles[i][3] <= n
     * No two rectangles overlap.
     * @param n
     * @param rectangles
     * @return
     */
    // time = O(m), space = O(m)
    public boolean checkValidCuts(int n, int[][] rectangles) {
        int m = rectangles.length;
        int[][] r = new int[m][2], c = new int[m][2];
        for (int i = 0; i < m; i++) {
            int x1 = rectangles[i][0], y1 = rectangles[i][1];
            int x2 = rectangles[i][2], y2 = rectangles[i][3];
            r[i] = new int[]{x1, x2};
            c[i] = new int[]{y1, y2};
        }
        return check(r) || check(c);
    }

    private boolean check(int[][] segs) {
        Arrays.sort(segs, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int st = -1, ed = -1, cnt = 0;
        for (int[] x : segs) {
            if (x[0] >= ed) {
                if (st != -1) cnt++;
                st = x[0];
                ed = x[1];
            } else ed = Math.max(ed, x[1]);
        }
        if (st != -1) cnt++;
        return cnt >= 3;
    }
}