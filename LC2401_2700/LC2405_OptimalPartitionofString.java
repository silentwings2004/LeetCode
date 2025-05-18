package LC2401_2700;

public class LC2405_OptimalPartitionofString {
    /**
     * Given a string s, partition the string into one or more substrings such that the characters in each substring
     * are unique. That is, no letter appears in a single substring more than once.
     *
     * Return the minimum number of substrings in such a partition.
     *
     * Note that each character should belong to exactly one substring in a partition.
     *
     * Input: s = "abacaba"
     * Output: 4
     *
     * Input: s = "ssssss"
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of only English lowercase letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int partitionString(String s) {
        int n = s.length(), res = 1;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            cnt[s.charAt(i) - 'a']++;
            int j = i + 1;
            while (j < n && cnt[s.charAt(j) - 'a'] == 0) cnt[s.charAt(j++) - 'a']++;
            if (j == n) break;
            res++;
            i = j - 1;
        }
        return res;
    }
}
