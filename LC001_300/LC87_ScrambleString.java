package LC001_300;
import java.util.*;
public class LC87_ScrambleString {
    /**
     * We can scramble a string s to get a string t using the following algorithm:
     *
     * If the length of the string is 1, stop.
     * If the length of the string is > 1, do the following:
     * Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y
     * where s = x + y.
     * Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become
     * s = x + y or s = y + x.
     * Apply step 1 recursively on each of the two substrings x and y.
     * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return
     * false.
     *
     * Input: s1 = "great", s2 = "rgeat"
     * Output: true
     *
     * Constraints:
     *
     * s1.length == s2.length
     * 1 <= s1.length <= 30
     * s1 and s2 consist of lower-case English letters.
     * @param s1
     * @param s2
     * @return
     */
    // S1: DFS + Memo
    // time = O(5^n), space = O(n)
    HashMap<String, Boolean> map = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        String h = s1 + "#" + s2;
        if (map.containsKey(h)) return map.get(h);
        if (s1.equals(s2)) {
            map.put(h, true);
            return true;
        }

        int n = s1.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s1.charAt(i) - 'a']++;
        for (int i = 0; i < n; i++) cnt[s2.charAt(i) - 'a']--;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                map.put(h, false);
                return false;
            }
        }

        for (int i = 1; i < n; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))) {
                map.put(h, true);
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                    isScramble(s1.substring(i), s2.substring(0, n - i))) {
                map.put(h, true);
                return true;
            }
        }
        map.put(h, false);
        return false;
    }

    // S2: DP
    // time = O(n^4), space = O(n^3)
    public boolean isScramble2(String s1, String s2) {
        int n = s1.length();
        boolean[][][] f = new boolean[n][n][n + 1];

        for (int k = 1; k <= n; k++) {
            for (int i = 0; i + k - 1 < n; i++) {
                for (int j = 0; j + k - 1 < n; j++) {
                    if (k == 1) f[i][j][k] = (s1.charAt(i) == s2.charAt(j));
                    else {
                        for (int u = 1; u < k; u++) {
                            if (f[i][j][u] && f[i + u][j + u][k - u] || f[i][j + k - u][u] && f[i + u][j][k - u]) {
                                f[i][j][k] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
}
/**
 * 基本的思想就是：在S1上找到一个切割点，左边长度为i, 右边长为len - i。 有2种情况表明它们是IsScramble
 * (1). S1的左边和S2的左边是IsScramble， S1的右边和S2的右边是IsScramble
 * (2). S1的左边和S2的右边是IsScramble， S1的右边和S2的左边是IsScramble （实际上是交换了S1的左右子树）
 * 我们可以在递归中加适当的剪枝：在进入递归前，先把2个字符串排序，再比较，如果不相同，则直接退出掉。
 *
 * 状态表示：f[i, j, k]
 * 1.1 集合：s1[i ~ i + k - 1]与s2[j, j + k - 1]所有匹配方案的集合
 * 1.2 属性：集合是否非空
 * 状态计算
 * 将f[i, j, k]表示的集合按s1第一段的长度划分划分成k - 1类。
 * 设s1第一段的长度为u。则s1[i ~ i + k - 1]与s2[j, j + k - 1]有两种匹配方案，分别判断即可：
 * (1) f[i][j][u] && f[i + u][j + u][k - u]
 * (2) f[i][j + k - u][u] && f[i + u][j][k - u]
 * 时间复杂度分析：状态数 O(n^3)，状态转移计算量为 O(n)，所以总时间复杂度为 O(n^4).
 */
