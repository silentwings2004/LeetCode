package LC2101_2400;

public class LC2380_TimeNeededtoRearrangeaBinaryString {
    /**
     * You are given a binary string s. In one second, all occurrences of "01" are simultaneously replaced with "10".
     * This process repeats until no occurrences of "01" exist.
     *
     * Return the number of seconds needed to complete this process.
     *
     * Input: s = "0110101"
     * Output: 4
     *
     * Input: s = "11100"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s[i] is either '0' or '1'.
     * @param s
     * @return
     */
    // S1: brute-force
    // time = O(n^2), space = O(n)
    public int secondsToRemoveOccurrences(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length, res = 0;
        while (true) {
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if (i + 1 < n && chars[i] == '0' && chars[i + 1] == '1') {
                    chars[i] = '1';
                    chars[i + 1] = '0';
                    i++;
                    flag = true;
                }
            }
            if (!flag) break;
            res++;
        }
        return res;
    }

    // S2: 递推
    // time = O(n), space = O(1)
    public int secondsToRemoveOccurrences2(String s) {
        int n = s.length(), res = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') cnt++;
            else {
                if (cnt > 0) res = Math.max(res + 1, cnt);
            }
        }
        return res;
    }
}
/**
 * S1: brute-force
 * 每一步都是0往右跳
 * 为什么不会超时呢？
 * 0000001111
 *   a    b
 * 第一个0要等待a - 1秒，再跳过b个1 => a + b - 1 => O(n^2)
 *
 * S2: O(n)
 * 主要看最后1个1如何移到它期待的位置上 => 全局第一个位置
 * 整个操作的瓶颈在于最后一个1，因为可能会被前面的1所block
 * ... 1 0...0 1  ...
 *     i       j
 * count = the number of zeros before j
 * 一旦被前面的1 block，就只能等前面的1往前挪1步后，才能在下一步往前走
 * f(j) = Math.max(f(i) + 1, count)
 * 01111000000000000001  有足够的0让后面的1往前走，走到前面堵塞已经解决了
 * 相反如果前面0特别少，那么后面的1一定会被前面的1给堵住而亦步亦趋 => 递推
 */
