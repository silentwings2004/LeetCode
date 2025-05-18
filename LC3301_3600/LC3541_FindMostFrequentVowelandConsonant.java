package LC3301_3600;

public class LC3541_FindMostFrequentVowelandConsonant {
    /**
     * You are given a string s consisting of lowercase English letters ('a' to 'z').
     *
     * Your task is to:
     *
     * Find the vowel (one of 'a', 'e', 'i', 'o', or 'u') with the maximum frequency.
     * Find the consonant (all other letters excluding vowels) with the maximum frequency.
     * Return the sum of the two frequencies.
     *
     * Note: If multiple vowels or consonants have the same maximum frequency, you may choose any one of them. If
     * there are no vowels or no consonants in the string, consider their frequency as 0.
     *
     * The frequency of a letter x is the number of times it occurs in the string.
     *
     * Input: s = "successes"
     * Output: 6
     *
     * Input: s = "aeiaeia"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of lowercase English letters only.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int maxFreqSum(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }
        int a = 0, b = 0;
        String vowel = "aeiou";
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            char c = (char)('a' + i);
            if (vowel.indexOf(c) != -1) a = Math.max(a, cnt[i]);
            else b = Math.max(b, cnt[i]);
        }
        return a + b;
    }
}