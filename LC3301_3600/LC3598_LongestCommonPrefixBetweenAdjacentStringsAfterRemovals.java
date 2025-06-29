package LC3301_3600;

public class LC3598_LongestCommonPrefixBetweenAdjacentStringsAfterRemovals {
    /**
     * You are given an array of strings words. For each index i in the range [0, words.length - 1], perform the
     * following steps:
     *
     * Remove the element at index i from the words array.
     * Compute the length of the longest common prefix among all adjacent pairs in the modified array.
     * Return an array answer, where answer[i] is the length of the longest common prefix between the adjacent pairs
     * after removing the element at index i. If no adjacent pairs remain or if none share a common prefix, then
     * answer[i] should be 0.
     *
     * A prefix of a string is a substring that starts from the beginning of the string and extends to any point within
     * it.
     *
     * Input: words = ["jump","run","run","jump","run"]
     * Output: [3,0,0,3,3]
     *
     * Input: words = ["dog","racer","car"]
     * Output: [0,0,0]
     *
     * Constraints:
     *
     * 1 <= words.length <= 10^5
     * 1 <= words[i].length <= 10^4
     * words[i] consists of lowercase English letters.
     * The sum of words[i].length is smaller than or equal 10^5.
     * @param words
     * @return
     */
    // time = O(n), space = O(n)
    public int[] longestCommonPrefix(String[] words) {
        int n = words.length, m = n - 1;
        if (n == 1) return new int[n];
        int[] lcp = new int[m];
        for (int i = 0; i < m; i++) lcp[i] = get(words[i], words[i + 1]);
        int[] pre = new int[m], suf = new int[m];
        pre[0] = lcp[0];
        for (int i = 1; i < m; i++) pre[i] = Math.max(pre[i - 1], lcp[i]);
        suf[m - 1] = lcp[m - 1];
        for (int i = m - 2; i >= 0; i--) suf[i] = Math.max(suf[i + 1], lcp[i]);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int t = 0;
            if (i - 2 >= 0) t = Math.max(t, pre[i - 2]);
            if (i + 1 < m) t = Math.max(t, suf[i + 1]);
            if (i > 0 && i + 1 < n) {
                int v = get(words[i - 1], words[i + 1]);
                t = Math.max(t, v);
            }
            res[i] = t;
        }
        return res;
    }

    private int get(String a, String b) {
        int n = Math.min(a.length(), b.length()), i = 0;
        while (i < n && a.charAt(i) == b.charAt(i)) i++;
        return i;
    }
}