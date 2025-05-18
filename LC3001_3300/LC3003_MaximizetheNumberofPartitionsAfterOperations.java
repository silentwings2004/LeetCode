package LC3001_3300;
import java.util.*;
public class LC3003_MaximizetheNumberofPartitionsAfterOperations {
    /**
     * You are given a 0-indexed string s and an integer k.
     *
     * You are to perform the following partitioning operations until s is empty:
     *
     * Choose the longest prefix of s containing at most k distinct characters.
     * Delete the prefix from s and increase the number of partitions by one. The remaining characters (if any) in s
     * maintain their initial order.
     * Before the operations, you are allowed to change at most one index in s to another lowercase English letter.
     *
     * Return an integer denoting the maximum number of resulting partitions after the operations by optimally choosing
     * at most one index to change.
     *
     * Input: s = "accca", k = 2
     * Output: 3
     *
     * Input: s = "aabaab", k = 3
     * Output: 1
     *
     * Input: s = "xxyz", k = 1
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists only of lowercase English letters.
     * 1 <= k <= 26
     * @param s
     * @param k
     * @return
     */
    // time = O(n * 26^2), space = O(n * 26^2)
    HashMap<Long, Integer> map;
    String s;
    int k;
    public int maxPartitionsAfterOperations(String s, int k) {
        this.s = s;
        this.k = k;
        map = new HashMap<>();
        return dfs(0, 0, 0);
    }

    private int dfs(int u, int state, int changed) {
        if (u == s.length()) return 1;

        long ns = (long)u << 32 | state << 1 | changed;
        if (map.containsKey(ns)) return map.get(ns);

        int res = 0;
        // not change s[i]
        int b = 1 << (s.charAt(u) - 'a');
        int newState = state | b;
        if (Integer.bitCount(newState) > k) res = dfs(u + 1, b, changed) + 1;
        else res = dfs(u + 1, newState, changed);

        if (changed == 0) {
            for (int j = 0; j < 26; j++) {
                newState = state | (1 << j);
                if (Integer.bitCount(newState) > k) res = Math.max(res, dfs(u + 1, 1 << j, 1) + 1);
                else res = Math.max(res, dfs(u + 1, newState, 1));
            }
        }
        map.put(ns, res);
        return res;
    }
}
/**
 * 预处理：
 * 1. 每个后缀在不修改的时候，需要分成多少份 suf[i]
 * 2. 每个后缀的第一个字母修改成 ch = a, b, c, ... 的时候，这个后缀需要分成多少份 suf_change[i][ch]
 * suf[i] = suf[j+1] + 1
 * j 是二分出来的从 i 开始的这一段的最右位置
 * suf[i] 没有单调性
 *
 * 1. 枚举修改s[i] 为 a, b, c, ..., z 中的一个
 * 2. 特殊情况，修改的字母，会属于下一段
 */