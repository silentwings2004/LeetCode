package LC2401_2700;
import java.util.*;
public class LC2564_SubstringXORQueries {
    /**
     * You are given a binary string s, and a 2D integer array queries where queries[i] = [firsti, secondi].
     * <p>
     * For the ith query, find the shortest substring of s whose decimal value, val, yields secondi when bitwise XORed
     * with firsti. In other words, val ^ firsti == secondi.
     * <p>
     * The answer to the ith query is the endpoints (0-indexed) of the substring [lefti, righti] or [-1, -1] if no such
     * substring exists. If there are multiple answers, choose the one with the minimum lefti.
     * <p>
     * Return an array ans where ans[i] = [lefti, righti] is the answer to the ith query.
     * <p>
     * A substring is a contiguous non-empty sequence of characters within a string.
     * <p>
     * Input: s = "101101", queries = [[0,5],[1,2]]
     * Output: [[0,2],[2,3]]
     * <p>
     * Input: s = "0101", queries = [[12,8]]
     * Output: [[-1,-1]]
     * <p>
     * Input: s = "1", queries = [[4,5]]
     * Output: [[0,0]]
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 10^4
     * s[i] is either '0' or '1'.
     * 1 <= queries.length <= 10^5
     * 0 <= firsti, secondi <= 10^9
     *
     * @param s
     * @param queries
     * @return
     */
    // time = O(32 * n + m), space = O(32 * n)
    public int[][] substringXorQueries(String s, int[][] queries) {
        int n = s.length(), m = queries.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int len = 1; len <= 32; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                String sub = s.substring(i, j + 1);
                if (map.containsKey(sub)) continue;
                map.put(sub, i);
            }
        }

        int[][] res = new int[m][2];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            int t = a ^ b;
            String str = Integer.toBinaryString(t);
            if (map.containsKey(str)) res[i] = new int[]{map.get(str), map.get(str) + str.length() - 1};
            else res[i] = new int[]{-1, -1};
        }
        return res;
    }

    // S2
    // time = O(32 * n + m), space = O(32 * n)
    public int[][] substringXorQueries2(String s, int[][] queries) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int n = s.length(), m = queries.length;
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            int v = a ^ b;
            map.putIfAbsent(v, new ArrayList<>());
            map.get(v).add(i);
        }

        int[][] res = new int[m][2];
        for (int i = 0; i < m; i++) Arrays.fill(res[i], -1);
        for (int len = 1; len <= 31; len++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum = sum * 2 + s.charAt(i) - '0';
                if (i >= len) sum -= (1 << len) * (s.charAt(i - len) - '0');
                if (map.containsKey(sum)) {
                    for (int idx : map.get(sum)) {
                        if (res[idx][0] != -1) break;
                        res[idx] = new int[]{i - len + 1, i};
                    }
                }
            }
        }
        return res;
    }
}
/**
 * 首先我们知道x^a=b可以推出x=a^b。
 * 于是本题本质就是给出了一系列的val，问在二进制串里最早出现val的子串。
 * 暴力枚举子串的时间复杂度是o(N^2)，本题能够优化的地方在于，val的长度是有上限的，最多31位（因为整型不超过2^32）。
 * 所以我们只要遍历长度分别为1,2,...,31的子串，跑31次双指针即可：查看固定长度的滑窗里的数值是否出现在query里即可。
 * 时间复杂度就是o(31N).
 */