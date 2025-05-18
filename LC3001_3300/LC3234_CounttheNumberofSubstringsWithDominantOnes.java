package LC3001_3300;
import java.util.*;
public class LC3234_CounttheNumberofSubstringsWithDominantOnes {
    /**
     * You are given a binary string s.
     *
     * Return the number of substrings with dominant ones.
     *
     * A string has dominant ones if the number of ones in the string is greater than or equal to the square of the
     * number of zeros in the string.
     *
     * Input: s = "00011"
     * Output: 5
     *
     * Input: s = "101101"
     * Output: 16
     *
     * Constraints:
     *
     * 1 <= s.length <= 4 * 10^4
     * s consists only of characters '0' and '1'.
     * @param s
     * @return
     */
    // time = O(n * sqrt(n)), space = O(n)
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] s1 = new int[n + 1];
        int[] s0 = new int[n + 1];
        HashMap<Integer, int[]> m0 = new HashMap<>();
        HashMap<Integer, Integer> m1 = new HashMap<>();
        m0.put(0, new int[]{0, 0});
        m1.put(0, 0);
        for (int i = 1; i <= n; i++) {
            s1[i] = s1[i - 1];
            s0[i] = s0[i - 1];
            if (s.charAt(i - 1) == '0') s0[i]++;
            else s1[i]++;
            if (!m0.containsKey(s0[i])) m0.put(s0[i], new int[]{i, i});
            else m0.get(s0[i])[1] = i;
            m1.put(s1[i], i);
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (s1[i] == 0) continue;
            for (int j = 0; j * j <= s1[i] && j <= s0[i]; j++) {
                int l = m0.get(s0[i] - j)[0], r = m0.get(s0[i] - j)[1];
                int x = m1.get(s1[i] - j * j);
                int t = Math.min(i, Math.min(r + 1, x + 1));
                res += Math.max(0, t - l);
            }
        }
        return res;
    }
}
/**
 * 暴力枚举
 * cnt1 = 子串中的 1 的个数
 * cnt0 = 子串中的 0 的个数
 *
 * cnt0^2 <= cnt1 <= n
 * cnt0 <= sqrt(n) <= sqrt(40000) = 200
 * 枚举子串左端点，枚举子串右端点
 * for left in range(n):
 *   # 改成枚举 0 的个数
 *   for right in range(left, n):
 *
 * 1. 子串左端点是 left, 枚举子串中的 0 的个数 cnt0
 * cnt0 = 0: p - left, 其中 p 是 left 及其右边第一个 0 的下标
 * cnt0 = 1: q - p, 如果 cnt1 >= cnt0^2
 */