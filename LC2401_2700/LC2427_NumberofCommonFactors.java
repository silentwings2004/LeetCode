package LC2401_2700;

public class LC2427_NumberofCommonFactors {
    /**
     * Given two positive integers a and b, return the number of common factors of a and b.
     *
     * An integer x is a common factor of a and b if x divides both a and b.
     *
     * Input: a = 12, b = 6
     * Output: 4
     *
     * Input: a = 25, b = 30
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= a, b <= 1000
     * @param a
     * @param b
     * @return
     */
    // time = O(n), space = O(1)
    public int commonFactors(int a, int b) {
        int cnt = 0;
        for (int i = 1; i <= Math.min(a, b); i++) {
            if (a % i == 0 && b % i == 0) cnt++;
        }
        return cnt;
    }
}
