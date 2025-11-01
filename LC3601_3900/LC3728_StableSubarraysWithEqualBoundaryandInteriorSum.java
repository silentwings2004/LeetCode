package LC3601_3900;
import java.util.*;
public class LC3728_StableSubarraysWithEqualBoundaryandInteriorSum {
    /**
     * You are given an integer array capacity.
     *
     * A subarray capacity[l..r] is considered stable if:
     *
     * Its length is at least 3.
     * The first and last elements are each equal to the sum of all elements strictly between them
     * (i.e., capacity[l] = capacity[r] = capacity[l + 1] + capacity[l + 2] + ... + capacity[r - 1]).
     * Return an integer denoting the number of stable subarrays.
     *
     * Input: capacity = [9,3,3,3,9]
     * Output: 2
     *
     * Input: capacity = [1,2,3,4,5]
     * Output: 0
     *
     * Input: capacity = [-4,4,0,0,-8,-4]
     * Output: 1
     *
     * Constraints:
     *
     * 3 <= capacity.length <= 10^5
     * -10^9 <= capacity[i] <= 10^9
     * @param capacity
     * @return
     */
    // time = O(n), space = O(n)
    public long countStableSubarrays(int[] capacity) {
        int n = capacity.length;
        HashMap<Integer, HashMap<Long, Integer>> map = new HashMap<>();
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + capacity[i - 1];
        long res = 0;

        for (int i = 2; i < n; i++) {
            int j = i - 2;
            int cl = capacity[j], cr = capacity[i];
            long t = s[j] + 2L * cl;
            map.putIfAbsent(cl, new HashMap<>());
            HashMap<Long, Integer> cnt = map.get(cl);
            cnt.put(t, cnt.getOrDefault(t, 0) + 1);
            if (map.get(cr) != null) res += map.get(cr).getOrDefault(s[i], 0);
        }
        return res;
    }
}