package LC901_1200;

public class LC1071_GreatestCommonDivisorofStrings {
    /**
     * For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with
     * itself one or more times).
     *
     * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
     *
     * Input: str1 = "ABCABC", str2 = "ABC"
     * Output: "ABC"
     *
     * Input: str1 = "ABABAB", str2 = "ABAB"
     * Output: "AB"
     *
     * Input: str1 = "LEET", str2 = "CODE"
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= str1.length, str2.length <= 1000
     * str1 and str2 consist of English uppercase letters.
     * @param str1
     * @param str2
     * @return
     */
    // time = O(n), space = O(n)
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}