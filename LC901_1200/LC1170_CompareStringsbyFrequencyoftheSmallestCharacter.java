package LC901_1200;

public class LC1170_CompareStringsbyFrequencyoftheSmallestCharacter {
    /**
     * Let the function f(s) be the frequency of the lexicographically smallest character in a non-empty string s. For
     * example, if s = "dcce" then f(s) = 2 because the lexicographically smallest character is 'c', which has a
     * frequency of 2.
     *
     * You are given an array of strings words and another array of query strings queries. For each query queries[i],
     * count the number of words in words such that f(queries[i]) < f(W) for each W in words.
     *
     * Return an integer array answer, where each answer[i] is the answer to the ith query.
     *
     * Input: queries = ["cbd"], words = ["zaaaz"]
     * Output: [1]
     *
     * Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
     * Output: [1,2]
     *
     * Constraints:
     *
     * 1 <= queries.length <= 2000
     * 1 <= words.length <= 2000
     * 1 <= queries[i].length, words[i].length <= 10
     * queries[i][j], words[i][j] consist of lowercase English letters.
     * @param queries
     * @param words
     * @return
     */
    // S1: simulation
    // time = O(n + m), space = O(1)
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = queries.length, m = words.length;
        int[] cnt = new int[m], res = new int[n];
        for (int i = 0; i < m; i++) cnt[i] = cal(words[i]);
        for (int i = 0; i < n; i++) {
            int x = cal(queries[i]);
            for (int j = 0; j < m; j++) res[i] += x < cnt[j] ? 1 : 0;
        }
        return res;
    }

    private int cal(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) return cnt[i];
        }
        return -1;
    }

    // S2: prefix sum
    // time = O(n + m), space = O(1)
    public int[] numSmallerByFrequency2(String[] queries, String[] words) {
        int[] s = new int[11];
        for (String w : words) s[f(w)]++;
        for (int i = 1; i <= 10; i++) s[i] += s[i - 1];
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) res[i] = s[10] - s[f(queries[i])];
        return res;
    }

    private int f(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) return cnt[i];
        }
        return -1;
    }
}