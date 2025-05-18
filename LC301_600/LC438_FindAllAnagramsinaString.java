package LC301_600;
import java.util.*;
public class LC438_FindAllAnagramsinaString {
    /**
     * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the
     * answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using
     * all the original letters exactly once.
     *
     * Input: s = "cbaebabacd", p = "abc"
     * Output: [0,6]
     *
     * Constraints:
     *
     * 1 <= s.length, p.length <= 3 * 10^4
     * s and p consist of lowercase English letters.
     *
     * @param s
     * @param p
     * @return
     */
    // time = O(m + n), space = O(1)
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = p.length();
        int[] cnt1 = new int[26];
        for (int i = 0; i < m; i++) cnt1[p.charAt(i) - 'a']++;

        int[] cnt2 = new int[26];
        for (int i = 0, j = 0; i < n; i++) {
            cnt2[s.charAt(i) - 'a']++;
            if (i - j + 1 > m) cnt2[s.charAt(j++) - 'a']--;
            if (Arrays.equals(cnt1, cnt2)) res.add(j);
        }
        return res;
    }
}
/**
 * sliding window是固定的
 * 每3个一判断
 * 每次滑动1格即可，单指针就可以了
 */