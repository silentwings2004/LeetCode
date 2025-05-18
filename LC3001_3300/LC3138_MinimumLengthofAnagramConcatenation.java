package LC3001_3300;
import java.util.*;
public class LC3138_MinimumLengthofAnagramConcatenation {
    /**
     * You are given a string s, which is known to be a concatenation of anagrams of some string t.
     *
     * Return the minimum possible length of the string t.
     *
     * An anagram is a word or phrase formed by rearranging the letters of a word or phrase, typically using all the
     * original letters exactly once.
     *
     * Input: s = "abba"
     * Output: 2
     *
     * Input: s = "cdef"
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consist only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[][] cnt;
    public int minAnagramLength(String s) {
        int n = s.length();
        cnt = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            cnt[i] = cnt[i - 1].clone();
            int u = s.charAt(i - 1) - 'a';
            cnt[i][u]++;
        }

        List<Integer> p = get_divisors(n);
        int res = n;
        for (int x : p) {
            if (check(s, x)) {
                res = x;
                break;
            }
        }
        return res;
    }

    private boolean check(String s, int x) {
        int n = s.length();
        int[] t = cnt[x];
        for (int i = x + x; i <= n; i += x) {
            int[] w = new int[26];
            for (int j = 0; j < 26; j++) {
                w[j] = cnt[i][j] - cnt[i - x][j];
            }
            if (!Arrays.equals(t, w)) return false;
        }
        return true;
    }

    private List<Integer> get_divisors(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n / i; i++) {
            if (n % i == 0) {
                res.add(i);
                if (i != n / i) res.add(n / i);
            }
        }
        Collections.sort(res);
        return res;
    }
}
/**
 * 1. 暴力枚举 len(s) 的因子，记作 k
 * 2. k 个字母，分出一段，每个段 (排序/哈希表统计) 都是一样的
 */