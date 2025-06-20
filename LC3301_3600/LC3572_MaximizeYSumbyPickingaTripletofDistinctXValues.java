package LC3301_3600;
import java.util.*;
public class LC3572_MaximizeYSumbyPickingaTripletofDistinctXValues {
    /**
     * You are given two integer arrays x and y, each of length n. You must choose three distinct indices i, j, and k
     * such that:
     *
     * x[i] != x[j]
     * x[j] != x[k]
     * x[k] != x[i]
     * Your goal is to maximize the value of y[i] + y[j] + y[k] under these conditions. Return the maximum possible sum
     * that can be obtained by choosing such a triplet of indices.
     *
     * If no such triplet exists, return -1.
     *
     * Input: x = [1,2,1,3,2], y = [5,3,4,6,2]
     * Output: 14
     *
     * Input: x = [1,2,1,2], y = [4,5,6,7]
     * Output: -1
     *
     * Constraints:
     *
     * n == x.length == y.length
     * 3 <= n <= 10^5
     * 1 <= x[i], y[i] <= 10^6
     * @param x
     * @param y
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(x[i]) || map.get(x[i]) < y[i]) map.put(x[i], y[i]);
        }
        if (map.size() < 3) return -1;
        List<Integer> q = new ArrayList<>(map.values());
        Collections.sort(q, (o1, o2) -> o2 - o1);
        return q.get(0) + q.get(1) + q.get(2);
    }
}