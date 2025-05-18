package LC3301_3600;

public class LC3442_MaximumDifferenceBetweenEvenandOddFrequencyI {
    /**
     * You are given a string s consisting of lowercase English letters. Your task is to find the maximum difference
     * between the frequency of two characters in the string such that:
     *
     * One of the characters has an even frequency in the string.
     * The other character has an odd frequency in the string.
     * Return the maximum difference, calculated as the frequency of the character with an odd frequency minus the
     * frequency of the character with an even frequency.
     *
     * Input: s = "aaaaabbc"
     * Output: 3
     *
     * Input: s = "abcabcab"
     * Output: 1
     *
     * Constraints:
     *
     * 3 <= s.length <= 100
     * s consists only of lowercase English letters.
     * s contains at least one character with an odd frequency and one with an even frequency.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int maxDifference(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }
        int max = 0, min = n;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            if (cnt[i] % 2 == 1) max = Math.max(max, cnt[i]);
            else min = Math.min(min, cnt[i]);
        }
        return max - min;
    }
}