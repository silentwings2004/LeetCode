package LC2401_2700;

public class LC2559_CountVowelStringsinRanges {
    /**
     * You are given a 0-indexed array of strings words and a 2D array of integers queries.
     *
     * Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri
     * (both inclusive) of words that start and end with a vowel.
     *
     * Return an array ans of size queries.length, where ans[i] is the answer to the ith query.
     *
     * Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.
     *
     * Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
     * Output: [2,3,0]
     *
     * Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
     * Output: [3,2,1]
     *
     * Constraints:
     *
     * 1 <= words.length <= 10^5
     * 1 <= words[i].length <= 40
     * words[i] consists only of lowercase English letters.
     * sum(words[i].length) <= 3 * 10^5
     * 1 <= queries.length <= 10^5
     * 0 <= li <= ri < words.length
     * @param words
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    public int[] vowelStrings(String[] words, int[][] queries) {
        String vowel = "aeiou";
        int n = words.length, m = queries.length;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + (check(words[i - 1], vowel) ? 1 : 0);
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0] + 1, r = queries[i][1] + 1;
            res[i] = s[r] - s[l - 1];
        }
        return res;
    }

    private boolean check(String s, String t) {
        int a = s.charAt(0), b = s.charAt(s.length() - 1);
        return t.indexOf(a) != -1 && t.indexOf(b) != -1;
    }
}