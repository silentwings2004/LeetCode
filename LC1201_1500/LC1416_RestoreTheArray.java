package LC1201_1500;

public class LC1416_RestoreTheArray {
    /**
     * A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is
     * printed as a string of digits s and all we know is that all integers in the array were in the range [1, k] and
     * there are no leading zeros in the array.
     *
     * Given the string s and the integer k, return the number of the possible arrays that can be printed as s using the
     * mentioned program. Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: s = "1000", k = 10000
     * Output: 1
     *
     * Input: s = "1000", k = 10
     * Output: 0
     *
     * Input: s = "1317", k = 2000
     * Output: 8
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of only digits and does not contain leading zeros.
     * 1 <= k <= 10^9
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int numberOfArrays(String s, int k) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        s = "#" + s;

        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.max(1, i - 10); j <= i; j++) {
                String t = s.substring(j, i + 1);
                if (Long.parseLong(t) == 0) break;
                if (t.charAt(0) == '0') continue;
                if (t.length() >= 11 || Long.parseLong(t) > k) continue;
                f[i] = (f[i] + f[j - 1]) % mod;
            }
        }
        return f[n];
    }
}