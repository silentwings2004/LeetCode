package LC3901_4200;

public class LC3980_MinimumOperationstoTransformBinaryString {
    /**
     * You are given two binary strings s1 and s2 of the same length n.
     *
     * You can perform the following operations on s1 any number of times, in any order:
     *
     * Choose an index i such that s1[i] is '0' and change it to '1'.
     * Choose an index i such that 0 <= i < n - 1, and both s1[i] and s1[i + 1] are '1'. Change both characters to '0'.
     * Return the minimum number of operations required to make s1 equal to s2. If it is impossible to make s1 equal to
     * s2, return -1.
     *
     * Input: s1 = "11", s2 = "00"
     * Output: 1
     *
     * Input: s1 = "01", s2 = "10"
     * Output: 3
     *
     * Input: s1 = "1", s2 = "0"
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == s1.length == s2.length <= 10^5
     * s1 and s2 consist only of '0' and '1'.
     * @param s1
     * @param s2
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minOperations(String s1, String s2) {
        if (s1.equals("1") && s2.equals("0")) return -1;
        char[] s = s1.toCharArray();
        int n = s.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == s2.charAt(i)) continue;
            res++;
            if (s[i] == '1') {
                if (i == n - 1) res++; // if 11 -> 10: 2; if 01 -> 00: 2 => so should be 2 all the time
                else {
                    res += s[i + 1] == '0' ? 1 : 0; // 10 -> 00: 2; 11 -> 01: 00: 1
                    s[i + 1] = '0';
                }
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int minOperations2(String s1, String s2) {
        int n = s1.length();
        if (n == 1 && s1.charAt(0) == '1' && s2.charAt(0) == '0') return -1;

        boolean changed = false;
        int res = 0;
        for (int i = 0; i < n; i++) {
            char c = changed ? '0' : s1.charAt(i);
            changed = false;
            if (c == s2.charAt(i)) continue;
            if (c == '0') res++; // 操作一
            else if (i < n - 1 && s1.charAt(i + 1) == '1') {
                res++; // 操作二，并且顺带把 s[i + 1] 也改了
                changed = true; // s[i + 1] = '0'
            } else res += 2; // 先把 s[i+1] 改成'1'，转化成上面的情况；或者把 s[i-1] 改了，也需要改两次
        }
        return res;
    }
}
/**
 * 贪心
 * 改右边比左边好
 */