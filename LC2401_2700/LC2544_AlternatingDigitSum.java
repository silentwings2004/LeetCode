package LC2401_2700;

public class LC2544_AlternatingDigitSum {
    /**
     * You are given a positive integer n. Each digit of n has a sign according to the following rules:
     *
     * The most significant digit is assigned a positive sign.
     * Each other digit has an opposite sign to its adjacent digits.
     * Return the sum of all digits with their corresponding sign.
     *
     * Input: n = 521
     * Output: 4
     *
     * Input: n = 111
     * Output: 1
     *
     * Input: n = 886996
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public int alternateDigitSum(int n) {
        int cnt = 0, s1 = 0, s2 = 0;
        while (n > 0) {
            if (cnt % 2 == 0) s1 += n % 10;
            else s2 += n % 10;
            n /= 10;
            cnt++;
        }
        return cnt % 2 == 0 ? s2 - s1 : s1 - s2;
    }
}