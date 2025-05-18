package LC3001_3300;

public class LC3042_CountPrefixandSuffixPairsI {
    /**
     * You are given a 0-indexed string array words.
     *
     * Let's define a boolean function isPrefixAndSuffix that takes two strings, str1 and str2:
     *
     * isPrefixAndSuffix(str1, str2) returns true if str1 is both a
     * prefix
     *  and a
     * suffix
     *  of str2, and false otherwise.
     * For example, isPrefixAndSuffix("aba", "ababa") is true because "aba" is a prefix of "ababa" and also a suffix,
     * but isPrefixAndSuffix("abc", "abcd") is false.
     *
     * Return an integer denoting the number of index pairs (i, j) such that i < j, and
     * isPrefixAndSuffix(words[i], words[j]) is true.
     *
     * Input: words = ["a","aba","ababa","aa"]
     * Output: 4
     *
     * Input: words = ["pa","papa","ma","mama"]
     * Output: 2
     *
     * Input: words = ["abab","ab"]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= words.length <= 50
     * 1 <= words[i].length <= 10
     * words[i] consists only of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n^3), space = O(n)
    public int countPrefixSuffixPairs(String[] words) {
        int n = words.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (check(words[i], words[j])) res++;
            }
        }
        return res;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        if (m > n) return false;
        if (t.substring(0, m).equals(s) && t.substring(n - 1 - m + 1).equals(s)) return true;
        return false;
    }
}