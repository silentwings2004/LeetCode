package LC001_300;
import java.util.*;
public class LC30_SubstringwithConcatenationofAllWords {
    /**
     * You are given a string s and an array of strings words of the same length. Return all starting indices of
     * substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any
     * intervening characters.
     *
     * You can return the answer in any order.
     *
     * Input: s = "barfoothefoobarman", words = ["foo","bar"]
     * Output: [0,9]
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists of lower-case English letters.
     * 1 <= words.length <= 5000
     * 1 <= words[i].length <= 30
     * words[i] consists of lower-case English letters.
     * @param s
     * @param words
     * @return
     */
    // S1
    // time = O((m + n) * w), space = O(n * w)    m: length of string s, n: words.length
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int n = s.length(), m = words.length, w = words[0].length();
        for (int i = 0; i + m * w <= n; i++) {
            HashMap<String, Integer> copy = new HashMap<>(map);
            int j = i, k = m;
            while (k > 0) {
                String word = s.substring(j, j + w);
                if (!copy.containsKey(word)) break;
                copy.put(word, copy.get(word) - 1);
                if (copy.get(word) == 0) copy.remove(word);
                k--;
                j += w;
            }
            if (k == 0) res.add(i);
        }
        return res;
    }

    // S2
    // time = O(n * w), space = O(n * w)
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = words.length, w = words[0].length();
        HashMap<String, Integer> tot = new HashMap<>();
        for (String word : words) tot.put(word, tot.getOrDefault(word, 0) + 1);

        for (int i = 0; i < w; i++) {
            HashMap<String, Integer> wd = new HashMap<>();
            int cnt = 0;
            for (int j = i; j + w <= n; j += w) {
                if (j >= i + m * w) {
                    String word = s.substring(j - m * w, j - (m - 1) * w);
                    wd.put(word, wd.get(word) - 1);
                    if (wd.get(word) < tot.getOrDefault(word, 0)) cnt--;
                }
                String word = s.substring(j, j + w);
                wd.put(word, wd.getOrDefault(word, 0) + 1);
                if (wd.get(word) <= tot.getOrDefault(word, 0)) cnt++;
                if (cnt == m) res.add(j - (m - 1) * w);
            }
        }
        return res;
    }
}
/**
 * 将起始位置分成w组
 * 连续的m个元素，恰好是给定的m个元素=> 经典滑动窗口
 * 所有单词存入哈希表
 * 所有窗口的元素也存入哈希表
 * 每次只会插入一个词，删除一个词
 * 如何判断两个集合相等 => 再用一个变量cnt去维护
 * time = O(n/w * w * w) = O(n * w)
 */