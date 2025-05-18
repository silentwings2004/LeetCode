package LC2700_3000;

public class LC2937_MakeThreeStringsEqual {
    /**
     * You are given three strings s1, s2, and s3. You have to perform the following operation on these three strings
     * as many times as you want.
     *
     * In one operation you can choose one of these three strings such that its length is at least 2 and delete the
     * rightmost character of it.
     *
     * Return the minimum number of operations you need to perform to make the three strings equal if there is a way to
     * make them equal, otherwise, return -1.
     *
     * Input: s1 = "abc", s2 = "abb", s3 = "ab"
     * Output: 2
     *
     * Input: s1 = "dac", s2 = "bac", s3 = "cac"
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= s1.length, s2.length, s3.length <= 100
     * s1, s2 and s3 consist only of lowercase English letters.
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    // time = O(n), space = O(n)
    public int findMinimumOperations(String s1, String s2, String s3) {
        int a = s1.length(), b = s2.length(), c = s3.length();
        for (int i = Math.min(a, Math.min(b, c)); i > 0; i--) {
            String x = s1.substring(0, i), y = s2.substring(0, i), z = s3.substring(0, i);
            if (x.equals(y) && y.equals(z)) return a + b + c - 3 * i;
        }
        return -1;
    }
}