package LC3601_3900;

public class LC3692_MajorityFrequencyCharacters {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * The frequency group for a value k is the set of characters that appear exactly k times in s.
     *
     * The majority frequency group is the frequency group that contains the largest number of distinct characters.
     *
     * Return a string containing all characters in the majority frequency group, in any order. If two or more frequency
     * groups tie for that largest size, pick the group whose frequency k is larger.
     *
     * Input: s = "aaabbbccdddde"
     * Output: "ab"
     *
     * Input: s = "abcd"
     * Output: "abcd"
     *
     * Input: s = "pfpfgi"
     * Output: "fp"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String majorityFrequencyGroup(String s) {
        int n = s.length(), ms = 0;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            cnt[u]++;
        }
        String res = "";
        for (int i = n; i > 0; i--) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (cnt[j] == i) sb.append((char)('a' + j));
            }
            if (sb.length() > ms) {
                ms = sb.length();
                res = sb.toString();
            }
        }
        return res;
    }
}