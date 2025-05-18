package LC901_1200;
import java.util.*;
public class LC943_FindtheShortestSuperstring {
    /**
     * Given an array of strings words, return the smallest string that contains each string in words as a substring.
     * If there are multiple valid strings of the smallest length, return any of them.
     *
     * You may assume that no string in words is a substring of another string in words.
     *
     * Input: words = ["alex","loves","leetcode"]
     * Output: "alexlovesleetcode"
     *
     * Input: words = ["catg","ctaagt","gcta","ttca","atgcatc"]
     * Output: "gctaagttcatgcatc"
     *
     * Constraints:
     *
     * 1 <= words.length <= 12
     * 1 <= words[i].length <= 20
     * words[i] consists of lowercase English letters.
     * All the strings of words are unique.
     * @param words
     * @return
     */
    // S1
    // time = O(n^2 * (2^n + k)), space = O(n * (2^n + k))  k: maximum length of each word
    public String shortestSuperstring(String[] words) {
        // corner case
        if (words == null || words.length == 0) return "";

        int n = words.length, m = (1 << n);
        int[][] dp = new int[m][n];
        for (int[] f : dp) Arrays.fill(f, Integer.MAX_VALUE / 2);
        int[][] parent = new int[m][n];
        for (int[] f : parent) Arrays.fill(f, -1);
        int[][] dis = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) dis[i][j] = cal(words, i, j);
            }
        }

        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = words[i].length();
        }

        for (int set = 0; set < m; set++) {  // O(2^n)
            for (int last = 0; last < n; last++) {
                if ((set & (1 << last)) == 0) continue;
                int set_prev = set - (1 << last);
                if (set_prev == 0) continue;
                for (int last_prev = 0; last_prev < n; last_prev++) {
                    if ((set_prev & (1 << last_prev)) == 0) continue;
                    if (dp[set_prev][last_prev] + dis[last_prev][last] < dp[set][last]) {
                        dp[set][last] = dp[set_prev][last_prev] + dis[last_prev][last];
                        parent[set][last] = last_prev;
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        int start = -1;
        for (int last = 0; last < n; last++) {
            if (dp[m - 1][last] < res) {
                res = dp[m - 1][last];
                start = last;
            }
        }
        int set = m - 1, last = start;
        List<Integer> path = new ArrayList<>();
        path.add(last);

        while (parent[set][last] != -1) {
            int last_prev = parent[set][last];
            path.add(last_prev);
            set = set - (1 << last);
            last = last_prev;
        }
        Collections.reverse(path);
        String ans = words[path.get(0)];
        for (int i = 1; i < n; i++) {
            ans = combine(ans, words[path.get(i)]);
        }
        return ans;
    }

    private int cal(String[] A, int i, int j) {
        String s = A[i], t = A[j];
        for (int k = Math.min(s.length(), t.length()); k >= 0; k--) {
            if (s.substring(s.length() - k, s.length()).equals(t.substring(0, k))) return t.length() - k;
        }
        return 0;
    }

    private String combine(String s, String t) {
        for (int k = Math.min(s.length(), t.length()); k >= 0; k--) {
            if (s.substring(s.length() - k, s.length()).equals(t.substring(0, k))) return s + t.substring(k);
        }
        return s + t;
    }

    // S2
    final int INF = (int)1e8;
    public String shortestSuperstring2(String[] words) {
        int n = words.length;
        int[][] tr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tr[i][j] = get(words[i], words[j]);
            }
        }

        int[][] f = new int[1 << n][n];
        for (int i = 0; i < 1 << n; i++) Arrays.fill(f[i], INF);
        for (int i = 0; i < n; i++) f[1 << i][i] = words[i].length();
        for (int i = 0; i < 1 << n; i++) {
            for (int j = 0; j < n; j++) {
                if (f[i][j] == INF) continue;
                for (int k = 0; k < n; k++) {
                    if ((i >> k & 1) == 0) {
                        f[i | (1 << k)][k] = Math.min(f[i | (1 << k)][k], f[i][j] + words[k].length() - tr[j][k]);
                    }
                }
            }
        }

        // 反推
        int i = (1 << n) - 1, j = 0;
        for (int k = 0; k < n; k++) {
            if (f[i][k] < f[i][j]) j = k;
        }

        List<Integer> q = new LinkedList<>();
        for (int u = 0; u < n; u++) {
            q.add(j);
            for (int k = 0; k < n; k++) {
                if (f[i - (1 << j)][k] + words[j].length() - tr[k][j] == f[i][j]) {
                    i -= 1 << j;
                    j = k;
                    break;
                }
            }
        }
        Collections.reverse(q);
        StringBuilder sb = new StringBuilder(words[q.get(0)]);
        for (i = 1; i < q.size(); i++) {
            sb.append(words[q.get(i)].substring(tr[q.get(i - 1)][q.get(i)]));
        }
        return sb.toString();
    }

    private int get(String a, String b) {
        int m = a.length(), n = b.length();
        for (int i = Math.min(m, n); i > 0; i--) {
            if (a.substring(m - i).equals(b.substring(0, i))) return i;
        }
        return 0;
    }
}
/**
 * 本质上是个旅行商问题(travel salesman problem, tsp)
 * A, B, C
 * abc, bcd, cde
 * ABC => abcde 尽量多的重用
 * ACB
 * BAC
 * BCA
 * CAB
 * CBA
 * 本质上就是找一个全排列，看哪个全排列合起来的superstring最短
 * 把a,b,c看成点，a与b合成的字符串可以看成从a到b的距离
 * A -> B = 1，A后面最少加多少字符，可以使A的后缀是B
 * B -> C = 1
 * A -> C = 2 (+DE)
 * B -> A = 3
 * C -> A = 3
 * C -> B = 3
 *
 * NP问题 -> 暴力搜索
 * 竞赛选手：动态规划 + 状态压缩
 * dp[set][last]: the shortest path if we have travelled the cities in set and the last stop is "last"
 * A, B, C, D
 * for set = 0001 : 1111  // 遍历顺序非常重要：小 -> 大， set_prev一定比set小，所以在set之前一定被更新过了
 *     for last = ...
 *          // dp[set][last] =
 *          set_prev = set - last
 *          for (last_prev = ...)
 *              dp[set][last] = min(dp[set_prev][last_prev] + dis(last_prev, last))
 *              parent[set][last] = last_prev -> 不停往前回溯
 *
 * dp[0111][B] = min(dp[0011][C]+dis(C,B)，dp[0011][D]+dis(D,B))
 *     BCD                  以C结尾
 *
 * f(i,j): 当前已选择的串的状态，且以j结尾的所有串的最短长度
 * wj 与 wk 重合的部分越长越好
 * 由于wj和wk不会互为子串，所以wk是不会向前超过wj的
 * f(i | 1 << k, k)
 * 反推状态
 * 暴力预处理下每2个串的重叠长度
 */
