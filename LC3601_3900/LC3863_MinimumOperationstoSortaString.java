package LC3601_3900;
import java.util.*;
public class LC3863_MinimumOperationstoSortaString {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * In one operation, you can select any substring of s that is not the entire string and sort it in ascending
     * alphabetical order.
     *
     * Return the minimum number of operations required to make s sorted in ascending order. If it is not possible,
     * return -1.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "dog"
     * Output: 1
     *
     * Input: s = "card"
     * Output: 2
     *
     * Input: s = "gf"
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int minOperations(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String t = String.valueOf(chars);
        if (t.equals(s)) return 0;
        if (n == 2) return -1;

        if (s.charAt(0) == chars[0] || s.charAt(n - 1) == chars[n - 1]) return 1;
        boolean f = false, g = false;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == chars[0]) {
                f = true;
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == chars[n - 1]) {
                g = true;
                break;
            }
        }
        if (f || g) return 2;
        return 3;
    }

    // S2
    // time = O(n), space = O(1)
    public int minOperations2(String s) {
        int n = s.length();
        boolean f = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                f = false;
                break;
            }
        }
        if (f) return 0;
        if (n == 2) return -1;

        char mn = s.charAt(0), mx = s.charAt(0);
        for (int i = 0; i < n; i++) {
            mn = (char)Math.min(mn, s.charAt(i));
            mx = (char)Math.max(mx, s.charAt(i));
        }

        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) return 1;
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == mn || s.charAt(i) == mx) return 2;
        }
        return 3;
    }
}
/**
 * 依次判断：
 * 1. 如果 s 已经是有序的，那么无需排序，操作 0 次
 * 2. 如果 s 的长度是 2，那么无解
 * (下面的情况 n >= 3)
 * 3. 什么时候操作 1 次即可？
 * 因为可以对 n - 1 个数排序，所以 1 次操作，几乎就能把数组排成有序
 * 如果第一个数是最小值，
 * 最后一个数是最大值
 * 4. 什么时候操作 2 次即可？
 * 如果可以把最小值排到第一个数，或者把最大值排到最后一个数，
 * 那么就变成了情况 3，再操作一次即可
 * => 最小值或者最大值，要在数组的中间[1,n-2]
 *
 * 5. 其余情况
 * 如果s[0]是最大的，s[n-1]是最小的，且[1,n-2]没有最小值和最大值，
 * 那么无法一次操作就把最小值排在最前面，或者把最大值排在最后面，
 * 至少操作 3 次
 * 操作 2 次，把最大值晕倒最后面，变成情况 3，再操作 1 次即可，一共操作 3 次
 */