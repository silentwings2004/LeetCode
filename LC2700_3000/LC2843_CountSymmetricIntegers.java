package LC2700_3000;

public class LC2843_CountSymmetricIntegers {
    /**
     * You are given two positive integers low and high.
     *
     * An integer x consisting of 2 * n digits is symmetric if the sum of the first n digits of x is equal to the sum
     * of the last n digits of x. Numbers with an odd number of digits are never symmetric.
     *
     * Return the number of symmetric integers in the range [low, high].
     *
     * Input: low = 1, high = 100
     * Output: 9
     *
     * Input: low = 1200, high = 1230
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= low <= high <= 10^4
     * @param low
     * @param high
     * @return
     */
    // time = O(n), space = O(1)
    public int countSymmetricIntegers(int low, int high) {
        int res = 0;
        for (int i = low; i <= high; i++) {
            if (check(i)) res++;
        }
        return res;
    }

    private boolean check(int x) {
        String s = String.valueOf(x);
        int n = s.length();
        if (n % 2 == 1) return false;
        int a = 0, b = 0;
        for (int i = 0; i < n; i++) {
            if (i < n / 2) a += s.charAt(i) - '0';
            else b += s.charAt(i) - '0';
        }
        return a == b;
    }
}