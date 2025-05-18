package LC2401_2700;

public class LC2466_CountWaysToBuildGoodStrings {
    /**
     * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and
     * then at each step perform either of the following:
     *
     * Append the character '0' zero times.
     * Append the character '1' one times.
     * This can be performed any number of times.
     *
     * A good string is a string constructed by the above process having a length between low and high (inclusive).
     *
     * Return the number of different good strings that can be constructed satisfying these properties. Since the answer
     * can be large, return it modulo 10^9 + 7.
     *
     * Input: low = 3, high = 3, zero = 1, one = 1
     * Output: 8
     *
     * Input: low = 2, high = 3, zero = 1, one = 2
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= low <= high <= 10^5
     * 1 <= zero, one <= low
     * @param low
     * @param high
     * @param zero
     * @param one
     * @return
     */
    // time = O(n), space = O(n)
    public int countGoodStrings(int low, int high, int zero, int one) {
        int mod = (int)(1e9 + 7);
        int[] f = new int[high + 1];
        f[0] = 1;

        int res = 0;
        for (int i = 1; i <= high; i++) {
            if (i >= one) f[i] = f[i - one];
            if (i >= zero) f[i] = (f[i] + f[i - zero]) % mod;
            if (i >= low) res = (res + f[i]) % mod;
        }
        return res;
    }

    // S2: DP
    // time = O(n), space = O(n)
    public int countGoodStrings2(int low, int high, int zero, int one) {
        int mod = (int) 1e9 + 7;
        int[] f = new int[high + 1];
        f[zero]++;
        f[one]++;

        for (int i = 1; i <= high; i++) {
            if (i >= zero) f[i] = (f[i] + f[i - zero]) % mod;
            if (i >= one) f[i] = (f[i] + f[i - one]) % mod;
        }

        int res = 0;
        for (int i = low; i <= high; i++) res = (res + f[i]) % mod;
        return res;
    }
}
