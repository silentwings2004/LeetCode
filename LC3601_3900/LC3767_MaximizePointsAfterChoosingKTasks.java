package LC3601_3900;
import java.util.*;
public class LC3767_MaximizePointsAfterChoosingKTasks {
    /**
     * You are given two integer arrays, technique1 and technique2, each of length n, where n represents the number of
     * tasks to complete.
     *
     * If the ith task is completed using technique 1, you earn technique1[i] points.
     * If it is completed using technique 2, you earn technique2[i] points.
     * You are also given an integer k, representing the minimum number of tasks that must be completed using technique
     * 1.
     *
     * You must complete at least k tasks using technique 1 (they do not need to be the first k tasks).
     *
     * The remaining tasks may be completed using either technique.
     *
     * Return an integer denoting the maximum total points you can earn.
     *
     * Input: technique1 = [5,2,10], technique2 = [10,3,8], k = 2
     * Output: 22
     *
     * Input: technique1 = [10,20,30], technique2 = [5,15,25], k = 2
     * Output: 60
     *
     * Input: technique1 = [1,2,3], technique2 = [4,5,6], k = 0
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= n == technique1.length == technique2.length <= 10^5
     * 1 <= technique1[i], technique2[i] <= 10^5
     * 0 <= k <= n
     * @param technique1
     * @param technique2
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxPoints(int[] technique1, int[] technique2, int k) {
        int n = technique1.length;
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) p[i] = i;
        Arrays.sort(p, (o1, o2) -> {
            int v1 = technique1[o1] - technique2[o1];
            int v2 = technique1[o2] - technique2[o2];
            return v2 - v1;
        });
        long res = 0;
        for (int i = 0; i < n; i++, k--) {
            if (k > 0) res += technique1[p[i]];
            else res += Math.max(technique1[p[i]], technique2[p[i]]);
        }
        return res;
    }
}