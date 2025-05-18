package LC1501_1800;
import java.util.*;
public class LC1520_MaximumNumberofNonOverlappingSubstrings {
    /**
     * Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet
     * the following conditions:
     *
     * The substrings do not overlap, that is for any two substrings s[i..j] and s[x..y], either j < x or i > y is true.
     * A substring that contains a certain character c must also contain all occurrences of c.
     * Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the
     * same number of substrings, return the one with minimum total length. It can be shown that there exists a unique
     * solution of minimum total length.
     *
     * Notice that you can return the substrings in any order.
     *
     * Input: s = "adefaddaccc"
     * Output: ["e","f","ccc"]
     *
     * Input: s = "abbaccd"
     * Output: ["d","bb","cc"]
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<String> maxNumOfSubstrings(String s) {
        int[] start = new int[26], end = new int[26]; // find start and end pos for each char
        Arrays.fill(start, -1);
        Arrays.fill(end, -1);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (start[u] == -1) start[u] = i;
            end[u] = i;
        }
        List<int[]> q = new ArrayList<>(); // find valid intervals starting with each char
        for (int i = 0; i < 26; i++) {
            int l = start[i], r = end[i];
            boolean flag = true;
            for (int j = l; j <= r; j++) {
                int u = s.charAt(j) - 'a';
                if (start[u] < l) {
                    flag = false;
                    break;
                }
                r = Math.max(r, end[u]);
            }
            if (flag) q.add(new int[]{l, r});
        }

        int m = q.size();
        boolean[] st = new boolean[m];
        Collections.sort(q, ((o1, o2) -> (o1[1] - o2[0]) - (o2[1] - o2[0]))); // intervals contain all valid intervals
        for (int i = 0; i < m; i++) {  // brute-force to filter out the larger intervals, at most 26 intervals
            if (st[i]) continue;
            for (int j = i + 1; j < m; j++) {
                if (st[j]) continue;
                int[] a = q.get(i), b = q.get(j);
                if (b[0] < a[0] && b[1] > a[1]) st[j] = true;
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (!st[i]) {
                int l = q.get(i)[0], r = q.get(i)[1];
                res.add(s.substring(l, r + 1));
            }
        }
        return res;
    }
}
/**
 * 1. 统计下包含每个字符的最小子串
 * 2. 从中挑一些出来，这些子串相互之间无交集 => 从中找最优解
 * 3. 如何找极小子串？=> 迭代，逐步更新
 * 4. 如何从26个子串中找到最优解？
 * m substrings => overlap?
 * 这m个substring里面不可能有2个区间是partially overlap的 => disjoined or cover, no partial overlap
 * A___B_x_C
 *     B_x_C_x__D
 * 因为[B,C]并不是一个合法有效的区间，里面有字符x，其之所以并没有停止在C而延伸到了D，根本原因就是C后面一定还有字符x
 * 所有对于第一个string，如果包含了[B,C]的话，就一定也会延伸到D才行，所以这种部分overlap的情况一定不会出现!
 * => 要么完全disjoined，要么完全contain
 * abcba
 *  bcb
 * 相交取1个，不相交都取
 * 只能取一个的 => 取最短的
 */