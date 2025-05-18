package LC1801_2100;

public class LC1915_NumberofWonderfulSubstrings {
    /**
     * A wonderful string is a string where at most one letter appears an odd number of times.
     *
     * For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
     * Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number
     * of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each
     * occurrence separately.
     *
     * A substring is a contiguous sequence of characters in a string.
     *
     * Input: word = "aba"
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= word.length <= 10^5
     * word consists of lowercase English letters from 'a' to 'j'.
     * @param word
     * @return
     */
    // time = O(n * 2^10), space = O(2^10)
    public long wonderfulSubstrings(String word) {
        int n = word.length();
        long[] f = new long[1 << 10];
        f[0] = 1;
        long res = 0;
        for (int i = 0, t = 0; i < n; i++) {
            int u = word.charAt(i) - 'a';
            t ^= 1 << u;
            for (int j = 0; j < 10; j++) {
                int x = 1 << j, y = t ^ x;
                res += f[y];
            }
            res += f[t];
            f[t]++;
        }
        return res;
    }
}
/**
 * x x x j [x x x i x] x x   => presum  区间和，区间频次 O(1)得出
 * 固定一个端点，遍历右端点
 * 1.区间内全是偶数
 * count[a][i] odd / even
 * count[a][j] odd / even
 *
 *  count[b][i] odd / even
 *  count[b][j] odd / even
 *
 *  同奇同偶 % 2 = 0 / 1
 *  state = 00011010101 状态压缩 每个前缀它们对于这10个字符频次的奇偶性的统计压缩到1个10位二进制数中
 *  state[i]
 *  state[j] = state[i]
 *
 *  2. 区间内某个字符的频次是奇数
 *  state[i] = 0001101010[1]
 *  state[j] = 0001101010[0] ^ (1 << k)  => 在某位上做flip操作  循环10次
 *  Hash + Prefix + state
 *
 *  aaai
 *  00...0
 *  ref: LC1542
 */