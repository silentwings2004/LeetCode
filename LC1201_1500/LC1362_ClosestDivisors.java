package LC1201_1500;
import java.util.*;
public class LC1362_ClosestDivisors {
    /**
     * Given an integer num, find the closest two integers in absolute difference whose product equals num + 1 or num + 2.
     *
     * Return the two integers in any order.
     *
     * Input: num = 8
     * Output: [3,3]
     *
     * Input: num = 123
     * Output: [5,25]
     *
     * Input: num = 999
     * Output: [40,25]
     *
     * Constraints:
     * 1 <= num <= 10^9
     *
     * @param num
     * @return
     */
    // S1
    // time = O(sqrt(n)), space = O(1)
    public int[] closestDivisors(int num) {
        int a = num + 1, b = num + 2;
        int[] t1 = get(a), t2 = get(b);
        return Math.abs(t1[1] - t1[0]) <= Math.abs(t2[1] - t2[0]) ? t1 : t2;
    }

    private int[] get(int x) {
        int[] res = new int[2];
        int a = (int)Math.sqrt(x);
        for (int i = a; i > 0; i--) {
            if (x % i == 0) {
                res = new int[]{i, x / i};
                break;
            }
        }
        return res;
    }

    // S2
    // time = O(sqrt(n)), space = O(1)
    public int[] closestDivisors2(int num) {
        int divisor = num == 1 ? num + 1 : num + 2;
        int i = (int) Math.sqrt(divisor);
        while (divisor % i > 1) i--;
        return new int[]{i, divisor / i};
    }
}