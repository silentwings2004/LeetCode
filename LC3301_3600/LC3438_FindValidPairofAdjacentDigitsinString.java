package LC3301_3600;

public class LC3438_FindValidPairofAdjacentDigitsinString {
    /**
     * You are given a string s consisting only of digits. A valid pair is defined as two adjacent digits in s such that:
     *
     * The first digit is not equal to the second.
     * Each digit in the pair appears in s exactly as many times as its numeric value.
     * Return the first valid pair found in the string s when traversing from left to right. If no valid pair exists,
     * return an empty string.
     *
     * Input: s = "2523533"
     * Output: "23"
     *
     * Input: s = "221"
     * Output: "21"
     *
     * Input: s = "22"
     * Output: ""
     *
     * Constraints:
     *
     * 2 <= s.length <= 100
     * s only consists of digits from '1' to '9'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public String findValidPair(String s) {
        int n = s.length();
        int[] cnt = new int[10];
        for (int i = 0; i < n; i++) {
            int x = s.charAt(i) - '0';
            cnt[x]++;
        }
        for (int i = 1; i < n; i++) {
            int u = s.charAt(i) - '0', v = s.charAt(i - 1) - '0';
            if (u != v && cnt[u] == u && cnt[v] == v) return s.substring(i - 1, i + 1);
        }
        return "";
    }
}