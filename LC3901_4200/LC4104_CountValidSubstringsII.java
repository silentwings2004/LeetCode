package LC3901_4200;

public class LC4104_CountValidSubstringsII {
    /**
     * You are given a string s consisting only of the characters 'a', 'b', and 'c'.
     *
     * A string is called valid if it does not contain "abc" as a subsequence.
     *
     * Return the number of valid substrings in s. Substrings with different starting or ending indices are counted
     * separately.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * A subsequence is a string that can be derived from another string by deleting some or no characters without
     * changing the order of the remaining characters.
     *
     * Input: s = "abac"
     * Output: 9
     *
     * Input: s = "abc"
     * Output: 5
     *
     * Input: s = "ac"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s only consists of 'a', 'b', and 'c' characters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public long countValidSubstrings(String s) {
        int n = s.length();
        long res = 0;
        int la = -1; // 上 1 个‘a'的位置
        int abb = -1; // 最后 1 个'b'之前的最后一个'a'
        int ma = -1; // 所有 abc 三元组中 a 的最大位置
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') la = i;
            else if (s.charAt(i) == 'b') abb = la;
            else ma = Math.max(ma, abb);
            res += i - ma; // 以 i 结尾的有效子串：左端点在 [ma + 1, i]
        }
        return res;
    }
}