package LC3901_4200;

public class LC3918_SumofPrimesBetweenNumberandItsReverse {
    /**
     * You are given an integer n.
     *
     * Let r be the integer formed by reversing the digits of n.
     *
     * Return the sum of all prime numbers between min(n, r) and max(n, r), inclusive.
     *
     * Input: n = 13
     * Output: 132
     *
     * Input: n = 10
     * Output: 17
     *
     * Input: n = 8
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int sumOfPrimesInRange(int n) {
        int x = n, m = 0;
        while (x > 0) {
            int v = x % 10;
            x /= 10;
            m = m * 10 + v;
        }
        int a = Math.min(m, n), b = Math.max(m, n), res = 0;
        for (int i = a; i <= b; i++) {
            if (check(i)) res += i;
        }
        return res;
    }

    private boolean check(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}