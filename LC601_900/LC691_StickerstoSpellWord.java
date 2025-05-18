package LC601_900;
import java.util.*;
public class LC691_StickerstoSpellWord {
    /**
     * We are given n different types of stickers. Each sticker has a lowercase English word on it.
     *
     * You would like to spell out the given string target by cutting individual letters from your collection of
     * stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite
     * quantities of each sticker.
     *
     * Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.
     *
     * Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target
     * was chosen as a concatenation of two random words.
     *
     * Input: stickers = ["with","example","science"], target = "thehat"
     * Output: 3
     *
     * Constraints:
     *
     * n == stickers.length
     * 1 <= n <= 50
     * 1 <= stickers[i].length <= 10
     * 1 <= target <= 15
     * stickers[i] and target consist of lowercase English letters.
     * @param stickers
     * @param target
     * @return
     */
    // time = O(2^n * m * k * n) = O(2^T * S * T), space = O(2^n) = O(2^T)  S: 所有贴纸中的字母总数, T: 目标单词中的字母数
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int N = (1 << n);
        int[] dp = new int[N];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {  // O(2^n)
            // update dp[i]
            if (dp[i] == Integer.MAX_VALUE) continue; // 现在的状态只能从过去状态推演而来，过去状态不存在，直接pass!
            for (String str : stickers) {   // O(m)
                int j = findNextStatus(i, str, target);
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1];
    }

    private int findNextStatus(int status, String str, String target) {
        int n = target.length();
        for (char ch : str.toCharArray()) {   // O(k)
            for (int k = 0; k < n; k++) {    // O(n)
                if (((status >> k) & 1) == 0 && target.charAt(k) == ch) { // 这个位置没收集到字母且对应的就是字母ch
                    status += (1 << k);
                    break;
                }
            }
        }
        return status;
    }

    // S2: 暴搜
    // time = O(2^m * n * (c + m)), space = O(2^m)
    final int N = 1 << 15, INF = 20;
    int n;
    String[] strs;
    String target;
    int[] f;
    int[][] g;
    public int minStickers2(String[] stickers, String target) {
        strs = stickers;
        this.target = target;
        n = target.length();
        f = new int[N];
        g = new int[N][26];

        Arrays.fill(f, -1);
        for (int i = 0; i < N; i++) Arrays.fill(g[i], -1);
        int res = dfs(0);
        if (res == INF) res = -1;
        return res;
    }

    private int dfs(int state) {
        if (f[state] != -1) return f[state];
        if (state == (1 << n) - 1) return f[state] = 0;
        int v = INF;

        for (String str : strs) {
            int cur = state;
            for (char c : str.toCharArray()) {
                cur = fill(cur, c);
            }
            if (cur != state) v = Math.min(v, dfs(cur) + 1);
        }
        f[state] = v;
        return f[state];
    }

    private int fill(int state, char c) {
        int v = g[state][c - 'a'];
        if (v != -1) return v;
        v = state;

        for (int i = 0; i < n; i++) {
            if ((state >> i & 1) == 0 && target.charAt(i) == c) {
                v |= 1 << i;
                break;
            }
        }
        g[state][c - 'a'] = v;
        return v;
    }
}
/**
 * 状态压缩dp
 * 看上去像搜索，没有多少dp的感觉，很大概率上是无脑的搜索 => 指数级
 * 类似dp的形式，本质上还是搜索 => np搜索 用状态压缩dp来表示
 * dp[state]
 * state typically use a 32-bit int (int32)
 * 1 <= target <= 15  -> 可以用一个unsigned int来表示，一般不超过32位
 * 具体来说什么是状态？
 * target = "hello"
 * "he"
 * 000000011000 => dp[24]
 * dp[000000011111] => dp[31]
 * "lo"
 * 000000000101  => dp[5]
 * 000000000011
 * "llo"
 * 000000000111
 * "h"
 * 000000010000
 *
 * dp状态 只会从小的推到大的
 * j + str => i
 * i - str => j?X
 *
 * 非常经典的状态压缩dp，一般用在无脑搜索的题目里
 * 在无脑搜索里，会用到记忆化来去重
 * 在这里用一个32int的二进制表示来记忆一个状态，本质上殊途同归
 * 不同点：如果是搜索的话，你有可能不停的访问到同一个状态
 * 如果遇到频繁需要调取HashMap的时候，效率就没有这里的dp那么高效
 * 数组寻找起来效率比hash高
 * 大量依赖操作，依赖性特别强，dp就会占优势
 */
