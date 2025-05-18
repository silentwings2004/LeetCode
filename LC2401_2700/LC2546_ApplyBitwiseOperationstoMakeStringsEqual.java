package LC2401_2700;

public class LC2546_ApplyBitwiseOperationstoMakeStringsEqual {
    /**
     * You are given two 0-indexed binary strings s and target of the same length n. You can do the following operation
     * on s any number of times:
     *
     * Choose two different indices i and j where 0 <= i, j < n.
     * Simultaneously, replace s[i] with (s[i] OR s[j]) and s[j] with (s[i] XOR s[j]).
     * For example, if s = "0110", you can choose i = 0 and j = 2, then simultaneously replace s[0] with (s[0] OR
     * s[2] = 0 OR 1 = 1), and s[2] with (s[0] XOR s[2] = 0 XOR 1 = 1), so we will have s = "1110".
     *
     * Return true if you can make the string s equal to target, or false otherwise.
     *
     * Input: s = "1010", target = "0110"
     * Output: true
     *
     * Input: s = "11", target = "00"
     * Output: false
     *
     * Constraints:
     *
     * n == s.length == target.length
     * 2 <= n <= 10^5
     * s and target consist of only the digits 0 and 1.
     * @param s
     * @param target
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public boolean makeStringsEqual(String s, String target) {
        if (s.equals(target)) return true;
        int n = s.length();
        boolean hasZero = false, hasOne = false;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == target.charAt(i)) {
                if (s.charAt(i) == '1') return true;
            } else if (s.charAt(i) == '1') hasOne = true;
            else hasZero = true;
        }
        return hasZero && hasOne;
    }

    // S2
    // time = O(n), space = O(1)
    public boolean makeStringsEqual2(String s, String target) {
        if (s.equals(target)) return true;
        return s.contains("1") && target.contains("1");
    }
}
/**
 * (0,0) -> (0,0)
 * (0,1) -> (1,1)
 * (1,0) -> (1,1)
 * (1,1) -> (1,0)
 * s: 1
 * t: ?
 * s 里至少有个1
 * t 里也至少有个1， 在什么位置无所谓
 * => s全为0或者t全为0 都不行
 */
