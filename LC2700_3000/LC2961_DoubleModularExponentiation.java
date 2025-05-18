package LC2700_3000;
import java.util.*;
public class LC2961_DoubleModularExponentiation {
    /**
     * You are given a 0-indexed 2D array variables where variables[i] = [ai, bi, ci, mi], and an integer target.
     *
     * An index i is good if the following formula holds:
     *
     * 0 <= i < variables.length
     * ((ai^bi % 10)^ci) % mi == target
     * Return an array consisting of good indices in any order.
     *
     * Input: variables = [[2,3,3,10],[3,3,3,1],[6,1,1,4]], target = 2
     * Output: [0,2]
     *
     * Input: variables = [[39,3,1000,1000]], target = 17
     * Output: []
     *
     * Constraints:
     *
     * 1 <= variables.length <= 100
     * variables[i] == [ai, bi, ci, mi]
     * 1 <= ai, bi, ci, mi <= 10^3
     * 0 <= target <= 10^3
     * @param variables
     * @param target
     * @return
     */
    // time = O(nlogk), space = O(1)
    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> res = new ArrayList<>();
        int n = variables.length;
        for (int i = 0; i < n; i++) {
            int a = variables[i][0], b = variables[i][1], c = variables[i][2], m = variables[i][3];
            long v1 = qmi(a, b, 10);
            long v2 = qmi(v1, c, m);
            if (v2 == target) res.add(i);
        }
        return res;
    }

    private long qmi(long a, long k, long mod) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}
