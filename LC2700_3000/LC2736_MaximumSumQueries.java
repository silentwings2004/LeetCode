package LC2700_3000;
import java.util.*;
public class LC2736_MaximumSumQueries {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2, each of length n, and a 1-indexed 2D array queries
     * where queries[i] = [xi, yi].
     *
     * For the ith query, find the maximum value of nums1[j] + nums2[j] among all indices j (0 <= j < n), where
     * nums1[j] >= xi and nums2[j] >= yi, or -1 if there is no j satisfying the constraints.
     *
     * Return an array answer where answer[i] is the answer to the ith query.
     *
     * Input: nums1 = [4,3,1,2], nums2 = [2,4,9,5], queries = [[4,1],[1,3],[2,5]]
     * Output: [6,10,7]
     *
     * Input: nums1 = [3,2,5], nums2 = [2,3,4], queries = [[4,4],[3,2],[1,1]]
     * Output: [9,9,9]
     *
     * Input: nums1 = [2,1], nums2 = [2,3], queries = [[3,3]]
     * Output: [-1]
     *
     * Constraints:
     *
     * nums1.length == nums2.length
     * n == nums1.length
     * 1 <= n <= 10^5
     * 1 <= nums1[i], nums2[i] <= 10^9
     * 1 <= queries.length <= 10^5
     * queries[i].length == 2
     * xi == queries[i][1]
     * yi == queries[i][2]
     * 1 <= xi, yi <= 10^9
     * @param nums1
     * @param nums2
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length, m = queries.length;
        TreeMap<Integer, TreeSet<int[]>> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            int x = queries[i][0], y = queries[i][1];
            map.putIfAbsent(x, new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1])); // 这里必须对2维排序，否则会被去重！
            map.get(x).add(new int[]{y, i});
        }

        int[] res = new int[m];
        Arrays.fill(res, -1);
        int[][] w = new int[n][3];
        for (int i = 0; i < n; i++) w[i] = new int[]{nums1[i] + nums2[i], nums1[i], nums2[i]};
        Arrays.sort(w, (o1, o2) -> o2[0] - o1[0]);

        for (int[] t : w) {
            int sum = t[0], x = t[1], y = t[2];
            List<Integer> rem1 = new ArrayList<>();
            for (int a : map.keySet()) {
                if (a > x) break;
                List<int[]> rem2 = new ArrayList<>();
                for (int[] b : map.get(a)) {
                    if (b[0] > y) break;
                    res[b[1]] = sum;
                    rem2.add(b);
                }
                map.get(a).removeAll(rem2);
                if (map.get(a).size() == 0) rem1.add(a);
            }
            for (int a : rem1) map.remove(a);
        }
        return res;
    }
}
/**
 * Ref: LC2612 => 线性
 */