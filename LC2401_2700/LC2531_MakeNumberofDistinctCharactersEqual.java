package LC2401_2700;

public class LC2531_MakeNumberofDistinctCharactersEqual {
    /**
     * You are given two 0-indexed strings word1 and word2.
     *
     * A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and
     * swapping word1[i] with word2[j].
     *
     * Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with
     * exactly one move. Return false otherwise.
     *
     * Input: word1 = "ac", word2 = "b"
     * Output: false
     *
     * Input: word1 = "abcc", word2 = "aab"
     * Output: true
     *
     * Input: word1 = "abcde", word2 = "fghij"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= word1.length, word2.length <= 10^5
     * word1 and word2 consist of only lowercase English letters.
     * @param word1
     * @param word2
     * @return
     */
    // time = O(n + m), space = O(1)
    public boolean isItPossible(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int sz1 = 0, sz2 = 0;
        int[] cnt1 = new int[26], cnt2 = new int[26];
        for (int i = 0; i < m; i++) {
            int u = word1.charAt(i) - 'a';
            cnt1[u]++;
            if (cnt1[u] == 1) sz1++;
        }
        for (int i = 0; i < n; i++) {
            int u = word2.charAt(i) - 'a';
            cnt2[u]++;
            if (cnt2[u] == 1) sz2++;
        }

        if (Math.abs(sz1 - sz2) > 2) return false;

        for (int i = 0; i < 26; i++) {
            if (cnt1[i] == 0) continue;
            for (int j = 0; j < 26; j++) {
                if (cnt2[j] == 0) continue;
                int a = sz1, b = sz2;
                if (i == j) {
                    if (a == b) return true;
                    continue;
                }
                if (cnt1[j] == 0) a++;
                if (cnt2[i] == 0) b++;
                if (cnt1[i] == 1) a--;
                if (cnt2[j] == 1) b--;
                if (a == b) return true;
            }
        }
        return false;
    }
}