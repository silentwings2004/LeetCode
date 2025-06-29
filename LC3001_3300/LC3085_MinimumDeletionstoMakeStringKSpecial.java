package LC3001_3300;
import java.util.*;
public class LC3085_MinimumDeletionstoMakeStringKSpecial {
    /**
     * You are given a string word and an integer k.
     *
     * We consider word to be k-special if |freq(word[i]) - freq(word[j])| <= k for all indices i and j in the string.
     *
     * Here, freq(x) denotes the frequency of the character x in word, and |y| denotes the absolute value of y.
     *
     * Return the minimum number of characters you need to delete to make word k-special.
     *
     * Input: word = "aabcaba", k = 0
     * Output: 3
     *
     * Input: word = "dabdcbdcdcd", k = 2
     * Output: 2
     *
     * Input: word = "aaabaaa", k = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= word.length <= 10^5
     * 0 <= k <= 10^5
     * word consists only of lowercase English letters.
     * @param word
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumDeletions(String word, int k) {
        int[] cnt = new int[26];
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int u = word.charAt(i) - 'a';
            cnt[u]++;
        }
        Arrays.sort(cnt);

        int u = 0;
        while (cnt[u] == 0) u++;
        int s = 0, res = n + 1;
        for (int i = u; i < 26; i++) {
            int t = 0;
            for (int j = i + 1; j < 26; j++) {
                int d = Math.max(0, cnt[j] - cnt[i] - k);
                t += d;
            }
            res = Math.min(res, t + s);
            s += cnt[i];
        }
        return res;
    }

    // S2
    // time = O(n + 26^2), space = O(1)
    public int minimumDeletions2(String word, int k) {
        int[] cnt = new int[26];
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int u = word.charAt(i) - 'a';
            cnt[u]++;
        }
        Arrays.sort(cnt);
        int res = n;
        for (int i = 0, t = 0; i < 26; i++) {
            int v = t;
            for (int j = i + 1; j < 26; j++) {
                if (cnt[j] - cnt[i] <= k) continue;
                v += cnt[j] - cnt[i] - k;
            }
            res = Math.min(res, v);
            t += cnt[i];
        }
        return res;
    }
}
/**
 * 1.求最多保留多少个字母 max_save
 * 2. 出现次数最多 - 出现次数最少 <= k
 * 3. 枚举出现次数最少的字母，base
 * => 如果出现字母出现次数 < base, 全部删除
 * => 如果字母出现次数 > base，保留 min(c, base + k)
 * 4. len(word) - max-save
 */