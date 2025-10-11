package LC3601_3900;
import java.util.*;
public class LC3697_ComputeDecimalRepresentation {
    /**
     * You are given a positive integer n.
     *
     * A positive integer is a base-10 component if it is the product of a single digit from 1 to 9 and a non-negative
     * power of 10. For example, 500, 30, and 7 are base-10 components, while 537, 102, and 11 are not.
     *
     * Express n as a sum of only base-10 components, using the fewest base-10 components possible.
     *
     * Return an array containing these base-10 components in descending order.
     *
     * Input: n = 537
     * Output: [500,30,7]
     *
     * Input: n = 102
     * Output: [100,2]
     *
     * Input: n = 6
     * Output: [6]
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int[] decimalRepresentation(int n) {
        List<Integer> q = new ArrayList<>();
        for (int i = 9; i >= 0; i--) {
            int t = (int)Math.pow(10, i);
            if (n / t > 0) q.add(n / t * t);
            n %= t;
        }
        int[] res = new int[q.size()];
        for (int i = 0; i < q.size(); i++) res[i] = q.get(i);
        return res;
    }

    // S2
    // time = O(logn), space = O(1)
    public int[] decimalRepresentation2(int n) {
        List<Integer> q = new ArrayList<>();
        int p = 1;
        while (n > 0) {
            int t = n % 10;
            if (t > 0) q.add(t * p);
            p *= 10;
            n /= 10;
        }
        int m = q.size();
        int[] res = new int[m];
        for (int i = 0; i < m; i++) res[i] = q.get(m - 1 - i);
        return res;
    }
}