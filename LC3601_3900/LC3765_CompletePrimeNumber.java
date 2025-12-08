package LC3601_3900;

public class LC3765_CompletePrimeNumber {
    /**
     * You are given an integer num.
     *
     * A number num is called a Complete Prime Number if every prefix and every suffix of num is prime.
     *
     * Return true if num is a Complete Prime Number, otherwise return false.
     *
     * Note:
     *
     * A prefix of a number is formed by the first k digits of the number.
     * A suffix of a number is formed by the last k digits of the number.
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     * Single-digit numbers are considered Complete Prime Numbers only if they are prime.
     *
     * Input: num = 23
     * Output: true
     *
     * Input: num = 39
     * Output: false
     *
     * Input: num = 7
     * Output: true
     *
     * Constraints:
     *
     * 1 <= num <= 10^9
     * @param num
     * @return
     */
    // time = O(logn), space = O(1)
    public boolean completePrime(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (!check(s, 0, i)) return false;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (!check(s, i, n - 1)) return false;
        }
        return true;
    }

    private boolean check(String s, int l, int r) {
        int x = 0;
        for (int i = l; i <= r; i++) x = x * 10 + s.charAt(i) - '0';
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}