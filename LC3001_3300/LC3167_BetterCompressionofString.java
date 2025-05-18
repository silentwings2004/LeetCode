package LC3001_3300;

public class LC3167_BetterCompressionofString {
    /**
     * You are given a string compressed representing a compressed version of a string. The format is a character
     * followed by its frequency. For example, "a3b1a1c2" is a compressed version of the string "aaabacc".
     *
     * We seek a better compression with the following conditions:
     *
     * Each character should appear only once in the compressed version.
     * The characters should be in alphabetical order.
     * Return the better compression of compressed.
     *
     * Note: In the better version of compression, the order of letters may change, which is acceptable.
     *
     * Input: compressed = "a3c9b2c1"
     * Output: "a3b2c10"
     *
     * Input: compressed = "c2b3a1"
     * Output: "a1b3c2"
     *
     * Input: compressed = "a2b4c1"
     * Output: "a2b4c1"
     *
     * Constraints:
     *
     * 1 <= compressed.length <= 6 * 10^4
     * compressed consists only of lowercase English letters and digits.
     * compressed is a valid compression, i.e., each character is followed by its frequency.
     * Frequencies are in the range [1, 10^4] and have no leading zeroes.
     * @param compressed
     * @return
     */
    // time = O(n), space = O(n)
    public String betterCompression(String compressed) {
        int n = compressed.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            int u = compressed.charAt(i) - 'a';
            int j = i + 1, v = 0;
            while (j < n && Character.isDigit(compressed.charAt(j))) {
                v = v * 10 + compressed.charAt(j++) - '0';
            }
            cnt[u] += v;
            i = j - 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (cnt[i] == 0) continue;
            sb.append((char)(i + 'a')).append(cnt[i]);
        }
        return sb.toString();
    }
}