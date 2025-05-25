package LC3301_3600;
import java.util.*;
public class LC3557_FindMaximumNumberofNonIntersectingSubstrings {
    /**
     * You are given a string word.
     *
     * Return the maximum number of non-intersecting substrings of word that are at least four characters long and start
     * and end with the same letter.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: word = "abcdeafdef"
     * Output: 2
     *
     * Input: word = "bcdaaaab"
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= word.length <= 2 * 10^5
     * word consists only of lowercase English letters.
     * @param word
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int maxSubstrings(String word) {
        TreeSet<Integer>[] pos = new TreeSet[26];
        for (int i = 0; i < 26; i++) pos[i] = new TreeSet<>();
        int n = word.length();
        for (int i = 0; i < n; i++) {
            int u = word.charAt(i) - 'a';
            pos[u].add(i + 1);
        }

        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1];
            if (i <= 3) continue;
            int u = word.charAt(i - 1) - 'a';
            Integer fk = pos[u].floor(i - 3);
            if (fk != null) f[i] = Math.max(f[i], f[fk - 1] + 1);
        }
        return f[n];
    }

    // S2
    // time = O(n), space = O(1)
    public int maxSubstrings2(String word) {
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        int n = word.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int u = word.charAt(i) - 'a';
            if (pos[u] < 0) pos[u] = i;
            else if (i - pos[u] + 1 >= 4) {
                res++;
                Arrays.fill(pos, -1);
            }
        }
        return res;
    }
}
/**
 * 想一想，第一个（最左边的）子串选哪个最好？
 * 这个子串的右端点越小越好，这样后面能选的子串就越多
 * 选了右端点为 i 的子串后，问题变成从 [i+1,n−1] 中能选多少个子串，这是一个和原问题相似，规模更小的子问题
 * 遍历 word，同时用 pos[ch] 记录字母 ch=word[i] 首次出现的位置
 * 如果 ch 首次出现，那么记录下标，即 pos[ch]=i
 * 否则，如果子串长度 ≥4，即 i−pos[ch]+1≥4，即 i−pos[ch]>2，那么找到了一个右端点尽量小的子串，答案加一。
 * 然后把 pos 重置，继续寻找下一个子串
 */