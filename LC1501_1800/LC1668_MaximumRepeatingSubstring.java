package LC1501_1800;

public class LC1668_MaximumRepeatingSubstring {
    /**
     * For a string sequence, a string word is k-repeating if word concatenated k times is a substring of sequence.
     * The word's maximum k-repeating value is the highest value k where word is k-repeating in sequence. If word is
     * not a substring of sequence, word's maximum k-repeating value is 0.
     *
     * Given strings sequence and word, return the maximum k-repeating value of word in sequence.
     *
     * Input: sequence = "ababc", word = "ab"
     * Output: 2
     *
     * Input: sequence = "ababc", word = "ba"
     * Output: 1
     *
     * Input: sequence = "ababc", word = "ac"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= sequence.length <= 100
     * 1 <= word.length <= 100
     * sequence and word contains only lowercase English letters.
     * @param sequence
     * @param word
     * @return
     */
    // S1: brute-force
    // time = O(m * n), space = O(m * n)
    public int maxRepeating(String sequence, String word) {
        int k = 0;
        String t = word;
        while (t.length() <= sequence.length()) {
            if (!sequence.contains(t)) break;
            k++;
            t += word;
        }
        return k;
    }

    // S2: KMP
    // time = O(m), space = O(n)
    public int maxRepeating2(String sequence, String word) {
        int k = 0;
        String s = sequence, p = word;
        while (p.length() <= s.length()) {
            if (kmp(s, p)) {
                k++;
                p += word;
            } else break;
        }
        return k;
    }

    private boolean kmp(String s, String p) {
        int m = s.length(), n = p.length();
        s = "#" + s;
        p = "#" + p;
        int[] ne = new int[n + 1];

        for (int i = 2, j = 0; i <= n; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j + 1)) j = ne[j];
            if (p.charAt(i) == p.charAt(j + 1)) j++;
            ne[i] = j;
        }

        for (int i = 1, j = 0; i <= m; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j + 1)) j = ne[j];
            if (s.charAt(i) == p.charAt(j + 1)) j++;
            if (j == n) return true;
        }
        return false;
    }
}
