package LC3301_3600;

public class LC3335_TotalCharactersinStringAfterTransformationsI {
    /**
     * You are given a string s and an integer t, representing the number of transformations to perform. In one
     * transformation, every character in s is replaced according to the following rules:
     *
     * If the character is 'z', replace it with the string "ab".
     * Otherwise, replace it with the next character in the alphabet. For example, 'a' is replaced with 'b', 'b' is
     * replaced with 'c', and so on.
     * Return the length of the resulting string after exactly t transformations.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: s = "abcyy", t = 2
     * Output: 7
     *
     * Input: s = "azbk", t = 1
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists only of lowercase English letters.
     * 1 <= t <= 10^5
     * @param s
     * @param t
     * @return
     */
    // time = O(n + 26 * t), space = O(1)
    public int lengthAfterTransformations(String s, int t) {
        long mod = (long)(1e9 + 7);
        long[] cnt = new long[26];
        int n = s.length();
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;
        while (t-- > 0) {
            long[] tmp = new long[26];
            for (int j = 0; j < 25; j++) tmp[j + 1] = cnt[j];
            tmp[0] = (tmp[0] + cnt[25]) % mod;
            tmp[1] = (tmp[1] + cnt[25]) % mod;
            cnt = tmp.clone();
        }
        long res = 0;
        for (int i = 0; i < 26; i++) res = (res + cnt[i]) % mod;
        return (int)res;
    }
}