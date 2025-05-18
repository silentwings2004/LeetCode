package LC3301_3600;

public class LC3305_CountofSubstringsContainingEveryVowelandKConsonantsI {
    /**
     * You are given a string word and a non-negative integer k.
     *
     * Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least
     * once and exactly k consonants.
     *
     * Input: word = "aeioqq", k = 1
     * Output: 0
     *
     * Input: word = "aeiou", k = 0
     * Output: 1
     *
     * Input: word = "ieaouqqieaouqq", k = 1
     * Output: 3
     *
     * Constraints:
     *
     * 5 <= word.length <= 250
     * word consists only of lowercase English letters.
     * 0 <= k <= word.length - 5
     * @param word
     * @param k
     * @return
     */
    // time = O(n^3), space = O(1)
    public int countOfSubstrings(String word, int k) {
        int n = word.length(), res = 0;
        String vowel = "aeiou";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int[] cnt = new int[5];
                int state = 0, c = 0;
                for (int u = j; u <= i; u++) {
                    char ch = word.charAt(u);
                    int p = vowel.indexOf(ch);
                    if (p != -1) {
                        cnt[p]++;
                        state |= 1 << p;
                    } else c++;
                }
                if (state == (1 << 5) - 1 && c == k) res++;
            }
        }
        return res;
    }
}