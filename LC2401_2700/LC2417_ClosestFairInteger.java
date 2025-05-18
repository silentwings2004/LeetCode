package LC2401_2700;
import java.util.*;
public class LC2417_ClosestFairInteger {
    /**
     * You are given a positive integer n.
     *
     * We call an integer k fair if the number of even digits in k is equal to the number of odd digits in it.
     *
     * Return the smallest fair integer that is greater than or equal to n.
     *
     * Input: n = 2
     * Output: 10
     *
     * Input: n = 403
     * Output: 1001
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public int closestFair(int n) {
        int m = getDigits(n);
        if (m % 2 == 1) return helper(m);
        while (!check(n)) {
            n++;
            m = getDigits(n);
            if (m % 2 == 1) return helper(m);
        }
        return n;
    }

    private int getDigits(int x) {
        int cnt = 0;
        while (x > 0) {
            x /= 10;
            cnt++;
        }
        return cnt;
    }

    private boolean check(int x) {
        String s = String.valueOf(x);
        int m = s.length(), odd = 0;
        for (char c : s.toCharArray()) {
            int d = c - '0';
            if (d % 2 == 1) odd++;
        }
        return odd == m / 2;
    }

    private int helper(int x) {
        int base = (int) Math.pow(10, x);
        int k = (x + 1) / 2 - 1, p = 0;
        for (int i = 0; i < k; i++) p = p * 10 + 1;
        return base + p;
    }
}