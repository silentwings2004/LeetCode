package LC601_900;
import java.util.*;
public class LC624_MaximumDistanceinArrays {
    /**
     * You are given m arrays, where each array is sorted in ascending order.
     *
     * You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We
     * define the distance between two integers a and b to be their absolute difference |a - b|.
     *
     * Return the maximum distance.
     *
     * Input: arrays = [[1,2,3],[4,5],[1,2,3]]
     * Output: 4
     *
     * Input: arrays = [[1],[1]]
     * Output: 0
     *
     * Constraints:
     *
     * m == arrays.length
     * 2 <= m <= 10^5
     * 1 <= arrays[i].length <= 500
     * -10^4 <= arrays[i][j] <= 10^4
     * arrays[i] is sorted in ascending order.
     * There will be at most 105 integers in all the arrays.
     * @param arrays
     * @return
     */
    // time = O(n), space = O(1)
    public int maxDistance(List<List<Integer>> arrays) {
        int minv = arrays.get(0).get(0);
        int maxv = arrays.get(0).get(arrays.get(0).size() - 1);
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> q = arrays.get(i);
            int a = q.get(0), b = q.get(q.size() - 1);
            res = Math.max(res, b - minv);
            res = Math.max(res, maxv - a);
            minv = Math.min(minv, a);
            maxv = Math.max(maxv, b);
        }
        return res;
    }
}