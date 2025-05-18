package LC2401_2700;
import java.util.*;
public class LC2655_FindMaximalUncoveredRanges {
    /**
     * You are given an integer n which is the length of a 0-indexed array nums, and a 0-indexed 2D-array ranges, which
     * is a list of sub-ranges of nums (sub-ranges may overlap).
     *
     * Each row ranges[i] has exactly 2 cells:
     *
     * ranges[i][0], which shows the start of the ith range (inclusive)
     * ranges[i][1], which shows the end of the ith range (inclusive)
     * These ranges cover some cells of nums and leave some cells uncovered. Your task is to find all of the uncovered
     * ranges with maximal length.
     *
     * Return a 2D-array answer of the uncovered ranges, sorted by the starting point in ascending order.
     *
     * By all of the uncovered ranges with maximal length, we mean satisfying two conditions:
     *
     * Each uncovered cell should belong to exactly one sub-range
     * There should not exist two ranges (l1, r1) and (l2, r2) such that r1 + 1 = l2
     *
     * Input: n = 10, ranges = [[3,5],[7,8]]
     * Output: [[0,2],[6,6],[9,9]]
     *
     * Input: n = 3, ranges = [[0,2]]
     * Output: []
     *
     * Input: n = 7, ranges = [[2,4],[0,3]]
     * Output: [[5,6]]
     *
     *
     Constraints:

     1 <= n <= 10^9
     0 <= ranges.length <= 10^6
     ranges[i].length = 2
     0 <= ranges[i][j] <= n - 1
     ranges[i][0] <= ranges[i][1]
     * @param n
     * @param ranges
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {
        List<int[]> res = new ArrayList<>(), q = new ArrayList<>();
        Arrays.sort(ranges, (o1, o2) -> o1[0] - o2[0]);
        int st = -1, ed = -1;
        for (int[] r : ranges) {
            if (ed < r[0]) {
                if (st != -1) q.add(new int[]{st, ed});
                st = r[0];
                ed = r[1];
            } else ed = Math.max(ed, r[1]);
        }
        if (st != -1) q.add(new int[]{st, ed});

        st = 0;
        for (int[] x : q) {
            int a = x[0], b = x[1];
            if (a > st) res.add(new int[]{st, a - 1});
            st = b + 1;
            if (st > n - 1) break;
        }
        if (st <= n - 1) res.add(new int[]{st, n - 1});
        return res.toArray(new int[res.size()][]);
    }
}