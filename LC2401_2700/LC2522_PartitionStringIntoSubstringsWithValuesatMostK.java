package LC2401_2700;
import java.util.*;
public class LC2522_PartitionStringIntoSubstringsWithValuesatMostK {
    /**
     * You are given a string s consisting of digits from 1 to 9 and an integer k.
     *
     * A partition of a string s is called good if:
     *
     * Each digit of s is part of exactly one substring.
     * The value of each substring is less than or equal to k.
     * Return the minimum number of substrings in a good partition of s. If no good partition of s exists, return -1.
     *
     * Note that:
     *
     * The value of a string is its result when interpreted as an integer. For example, the value of "123" is 123 and
     * the value of "1" is 1.
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "165462", k = 60
     * Output: 4
     *
     * Input: s = "238182", k = 5
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is a digit from '1' to '9'.
     * 1 <= k <= 10^9
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumPartition(String s, int k) {
        int n = s.length(), res = 0;
        long t = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - '0';
            if (u > k) return -1;
            t = t * 10 + u;
            if (t > k) {
                res++;
                t = u;
            }
        }
        return res + 1;
    }

    // S2: dp
    // time = O(10n), space = O(n)
    public int minimumPartition2(String s, int k) {
        int n = s.length(), INF = (int) 1e8;
        int[] f = new int[n + 1];
        Arrays.fill(f, INF);
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            long t = 0, p = 1;
            for (int j = i; j > 0; j--) {
                t += p * (s.charAt(j - 1) - '0');
                p *= 10;
                if (t > k) break;
                f[i] = Math.min(f[i], f[j - 1] + 1);
            }
            if (f[i] == INF) return -1;
        }
        return f[n];
    }
}
/**
 * dp
 * 状态表示f(i):
 * 集合：所有前i个字符的所有分割方案的集合
 * 属性：最小值
 * 状态计算：集合划分
 * f(i) = f(i - len) + 1
 *
 */