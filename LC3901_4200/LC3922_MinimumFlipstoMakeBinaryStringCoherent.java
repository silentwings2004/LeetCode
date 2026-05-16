package LC3901_4200;

public class LC3922_MinimumFlipstoMakeBinaryStringCoherent {
    /**
     * You are given a binary string s.
     *
     * A string is considered coherent if it does not contain "011" or "110" as subsequences.
     *
     * In one operation, you can flip any character in s ('0' to '1' or '1' to '0').
     *
     * Return an integer denoting the minimum number of modifications required to make s coherent.
     *
     * A subsequence is a string that can be derived from another string by deleting some or no characters without
     * changing the order of the remaining characters.
     *
     * Input: s = "1010"
     * Output: 1
     *
     * Input: s = "0110"
     * Output: 1
     *
     * Input: s = "1000"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either '0' or '1'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int minFlips(String s) {
        int n = s.length(), one = 0, two1 = 0;
        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - '0';
            one += x;
            if (i == 0 || i == n - 1) {
                if (x == 0) two1++;
            } else {
                if (x == 1) two1++;
            }
        }
        int all0 = one, all1 = n - one;
        int one1 = one > 0 ? one - 1 : 1;
        return Math.min(all0, Math.min(all1, Math.min(one1, two1)));
    }
}