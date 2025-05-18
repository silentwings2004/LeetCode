package LC3301_3600;
import java.util.*;
public class LC3414_MaximumScoreofNonoverlappingIntervals {
    /**
     * You are given a 2D integer array intervals, where intervals[i] = [li, ri, weighti]. Interval i starts at position
     * li and ends at ri, and has a weight of weighti. You can choose up to 4 non-overlapping intervals. The score of
     * the chosen intervals is defined as the total sum of their weights.
     *
     * Return the lexicographically smallest array of at most 4 indices from intervals with maximum score, representing
     * your choice of non-overlapping intervals.
     *
     * Two intervals are said to be non-overlapping if they do not share any points. In particular, intervals sharing a
     * left or right boundary are considered overlapping.
     *
     * An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a
     * has an element that is less than the corresponding element in b.
     * If the first min(a.length, b.length) elements do not differ, then the shorter array is the lexicographically
     * smaller one.
     *
     * Input: intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]
     * Output: [2,3]
     *
     * Input: intervals = [[5,8,1],[6,7,7],[4,7,3],[9,10,6],[7,8,2],[11,14,3],[3,5,5]]
     * Output: [1,3,5,6]
     *
     * Constraints:
     *
     * 1 <= intevals.length <= 5 * 10^4
     * intervals[i].length == 3
     * intervals[i] = [li, ri, weighti]
     * 1 <= li <= ri <= 10^9
     * 1 <= weighti <= 10^9
     * @param intervals
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) {
            int r = intervals.get(i).get(1);
            w[i] = new int[]{r, i};
        }
        Arrays.sort(w, (o1, o2) -> o1[0] - o2[0]);

        Pair[][] f = new Pair[n + 1][5];
        Arrays.fill(f[0], new Pair(0, new ArrayList<>()));
        for (int i = 0; i < n; i++) {
            int k = find(w, i - 1, intervals.get(w[i][1]).get(0));
            f[i + 1][0] = new Pair(0, new ArrayList<>());
            for (int j = 1; j < 5; j++) {
                long s1 = 0, s2 = 0;
                s1 = f[i][j].sum;
                s2 = f[k + 1][j - 1].sum + intervals.get(w[i][1]).get(2);
                if (s1 > s2) {
                    f[i + 1][j] = f[i][j];
                    continue;
                }
                List<Integer> q = new ArrayList<>(f[k + 1][j - 1].ids);
                q.add(w[i][1]);
                Collections.sort(q);
                if (s1 == s2 && cmp(f[i][j].ids, q) < 0) q = f[i][j].ids;
                f[i + 1][j] = new Pair(s2, q);
            }
        }
        List<Integer> q = f[n][4].ids;
        int[] res = new int[q.size()];
        for (int i = 0; i < q.size(); i++) res[i] = q.get(i);
        return res;
    }

    private int cmp(List<Integer> a, List<Integer> b) {
        int n = Math.min(a.size(), b.size());
        for (int i = 0; i < n; i++) {
            if (a.get(i) == b.get(i)) continue;
            return a.get(i) - b.get(i);
        }
        return a.size() - b.size();
    }

    private int find(int[][] w, int r, int t) {
        int l = 0;
        if (r < l) return -1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (w[mid][0] < t) l = mid;
            else r = mid - 1;
        }
        return w[r][0] < t ? r : r - 1;
    }

    private class Pair {
        long sum;
        List<Integer> ids;
        public Pair(long sum, List<Integer> ids) {
            this.sum = sum;
            this.ids = ids;
        }
    }
}
/**
 * ref: LC1235
 */