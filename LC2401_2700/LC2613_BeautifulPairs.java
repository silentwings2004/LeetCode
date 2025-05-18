package LC2401_2700;
import java.util.*;
public class LC2613_BeautifulPairs {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2 of the same length. A pair of indices (i,j) is called
     * beautiful if|nums1[i] - nums1[j]| + |nums2[i] - nums2[j]| is the smallest amongst all possible indices pairs
     * where i < j.
     *
     * Return the beautiful pair. In the case that there are multiple beautiful pairs, return the lexicographically
     * smallest pair.
     *
     * Note that
     *
     * |x| denotes the absolute value of x.
     * A pair of indices (i1, j1) is lexicographically smaller than (i2, j2) if i1 < i2 or i1 == i2 and j1 < j2.
     *
     * Input: nums1 = [1,2,3,2,4], nums2 = [2,3,1,2,3]
     * Output: [0,3]
     *
     * Input: nums1 = [1,2,4,3,2,5], nums2 = [1,4,2,3,5,1]
     * Output: [1,4]
     *
     * Constraints:
     *
     * 2 <= nums1.length, nums2.length <= 10^5
     * nums1.length == nums2.length
     * 0 <= nums1i <= nums1.length
     * 0 <= nums2i <= nums2.length
     * @param nums1
     * @param nums2
     * @return
     */
    // S1: 折半查找
    // time = O(nlogn), space = O(n)
    final int N = 100010, INF = (int)1e9;
    List<int[]> points;
    public int[] beautifulPair(int[] nums1, int[] nums2) {
        points = new ArrayList<>();
        int n = nums1.length;
        HashMap<Long, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long t = get(nums1[i], nums2[i]);
            map.putIfAbsent(t, new ArrayList<>());
            map.get(t).add(i);
        }

        for (int i = 0; i < n; i++) {
            long t = get(nums1[i], nums2[i]);
            if (map.get(t).size() > 1) return new int[]{i, map.get(t).get(1)};
            points.add(new int[]{nums1[i], nums2[i], i});
        }
        Collections.sort(points, (o1, o2) -> o1[0] - o2[0]);
        int[] res = dfs(0, points.size() - 1);
        return new int[]{res[1], res[2]};
    }

    private int[] dfs(int l, int r) {
        if (l >= r) return new int[]{INF, -1, -1};

        int mid = l + r >> 1;
        int x = points.get(mid)[0];
        int[] t1 = dfs(l, mid), t2 = dfs(mid + 1, r);
        if (t1[0] > t2[0] || (t1[0] == t2[0] && (t1[1] > t2[1] || (t1[1] == t2[1] && t1[2] > t2[2])))) {
            t1 = t2;
        }

        List<int[]> q = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (Math.abs(points.get(i)[0] - x) <= t1[0]) q.add(points.get(i));
        }
        Collections.sort(q, (o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < q.size(); i++) {
            for (int j = i + 1; j < q.size(); j++) {
                if (q.get(j)[1] - q.get(i)[1] > t1[0]) break;
                int a = Math.min(q.get(i)[2], q.get(j)[2]);
                int b = Math.max(q.get(i)[2], q.get(j)[2]);
                int d = get_dist(q.get(i)[0], q.get(i)[1], q.get(j)[0], q.get(j)[1]);
                if (d < t1[0] || (d == t1[0] && (a < t1[1] || (a == t1[1] && b < t1[2])))) {
                    t1 = new int[]{d, a, b};
                }
            }
        }
        return t1;
    }

    private int get_dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }

    private long get(int x, int y) {
        return x * N + y;
    }
}