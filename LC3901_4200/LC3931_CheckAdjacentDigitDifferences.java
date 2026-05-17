package LC3901_4200;

public class LC3931_CheckAdjacentDigitDifferences {
    /**
     * You are given a string s consisting of digits.
     *
     * Return true if the absolute difference between every pair of adjacent digits is at most 2, otherwise return false.
     *
     * The absolute difference between a and b is defined as abs(a - b).
     *
     * Input: s = "132"
     * Output: true
     *
     * Input: s = "129"
     * Output: false
     *
     * Constraints:
     *
     * 2 <= s.length <= 100
     * s consists only of digits.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isAdjacentDiffAtMostTwo(String s) {
        int n = s.length();
        for (int i = 1; i < n; i++) {
            int x = s.charAt(i) - '0', y = s.charAt(i - 1) - '0';
            if (Math.abs(x - y) > 2) return false;
        }
        return true;
    }
}