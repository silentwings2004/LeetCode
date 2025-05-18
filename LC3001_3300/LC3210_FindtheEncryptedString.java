package LC3001_3300;

public class LC3210_FindtheEncryptedString {
    /**
     * You are given a string s and an integer k. Encrypt the string using the following algorithm:
     *
     * For each character c in s, replace c with the kth character after c in the string (in a cyclic manner).
     * Return the encrypted string.
     *
     * Input: s = "dart", k = 3
     * Output: "tdar"
     *
     * Input: s = "aaa", k = 1
     * Output: "aaa"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * 1 <= k <= 10^4
     * s consists only of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String getEncryptedString(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(s.charAt((i + k) % n));
        return sb.toString();
    }

    // S2
    // time = O(n), space = O(n)
    public String getEncryptedString2(String s, int k) {
        int n = s.length();
        k %= n;
        return s.substring(k) + s.substring(0, k);
    }
}