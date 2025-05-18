package LC901_1200;

public class LC942_DIStringMatch {
    /**
     * A permutation perm of n + 1 integers of all the integers in the range [0, n] can be represented as a string s of
     * length n where:
     *
     * s[i] == 'I' if perm[i] < perm[i + 1], and
     * s[i] == 'D' if perm[i] > perm[i + 1].
     * Given a string s, reconstruct the permutation perm and return it. If there are multiple valid permutations perm,
     * return any of them.
     *
     * Input: s = "IDID"
     * Output: [0,4,1,3,2]
     *
     * Input: s = "III"
     * Output: [0,1,2,3]
     *
     * Input: s = "DDI"
     * Output: [3,2,0,1]
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either 'I' or 'D'.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int[] diStringMatch(String s) {
        int n = s.length(), l = 0, r = n;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'I') res[i] = l++;
            else res[i] = r--;
        }
        res[n] = l;
        return res;
    }

    // S2: 字典序最小序列
    // time = O(n), space = O(1)
    public int[] diStringMatch2(String s) {
        s = "I" + s;
        int n = s.length(), idx = 0, max = -1;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == 'D') j++;
            int count = j - i;
            for (int k = max + count; k >= max + 1; k--) res[idx++] = k;
            max = max + count;
            i = j - 1;
        }
        return res;
    }
}
/**
 * 字符串两端和中间比总是单调的，所以可以无脑在两端根据要求设置为最大或者最小值
 * 基本思想是将s的开头加上一个“I”，将s切分为若干"IDD..D"的pattern，每个pattern对应的是一段单调递减的序列。
 * 保证序列与序列之间不重合即可。
 */