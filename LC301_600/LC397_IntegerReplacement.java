package LC301_600;
import java.util.*;
public class LC397_IntegerReplacement {
    /**
     * Given a positive integer n, you can apply one of the following operations:
     *
     * If n is even, replace n with n / 2.
     * If n is odd, replace n with either n + 1 or n - 1.
     * Return the minimum number of operations needed for n to become 1.
     *
     * Input: n = 8
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 2^31 - 1
     * @param n
     * @return
     */
    // time = O(sqrt(n)), space = O(sqrt(n))
    HashMap<Long, Integer> map;
    public int integerReplacement(int n) {
        map = new HashMap<>();
        return f(n);
    }

    private int f(long n) {
        if (n == 1) return 0;
        if (map.containsKey(n)) return map.get(n);
        if (n % 2 == 0) {
            int t = f(n / 2) + 1;
            map.put(n, t);
            return t;
        }
        int t = Math.min(f(n - 1), f(n + 1)) + 1;
        map.put(n, t);
        return t;
    }
}