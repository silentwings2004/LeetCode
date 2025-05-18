package LC2700_3000;
import java.util.*;
public class LC2840_CheckifStringsCanbeMadeEqualWithOperationsII {
    /**
     * You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.
     *
     * You can apply the following operation on any of the two strings any number of times:
     *
     * Choose any two indices i and j such that i < j and the difference j - i is even, then swap the two characters at
     * those indices in the string.
     * Return true if you can make the strings s1 and s2 equal, and false otherwise.
     *
     * Input: s1 = "abcdba", s2 = "cabdab"
     * Output: true
     *
     * Input: s1 = "abe", s2 = "bea"
     * Output: false
     *
     * Constraints:
     *
     * n == s1.length == s2.length
     * 1 <= n <= 10^5
     * s1 and s2 consist only of lowercase English letters.
     * @param s1
     * @param s2
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public boolean checkStrings(String s1, String s2) {
        return work(s1).equals(work(s2));
    }

    private String work(String s) {
        StringBuilder even = new StringBuilder(), odd = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) even.append(s.charAt(i));
            else odd.append(s.charAt(i));
        }
        char[] s1 = even.toString().toCharArray();
        char[] s2 = odd.toString().toCharArray();
        Arrays.sort(s1);
        Arrays.sort(s2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < s1.length || j < s2.length;) {
            if (i < s1.length) sb.append(s1[i++]);
            if (j < s2.length) sb.append(s2[j++]);
        }
        return sb.toString();
    }

    // S2: time = O(1), space = O(1)
    public boolean checkStrings2(String s1, String s2) {
        int[] cnt = new int[26];
        int n = s1.length();
        for (int i = 0; i < n; i += 2) cnt[s1.charAt(i) - 'a']++;
        for (int i = 0; i < n; i += 2) cnt[s2.charAt(i) - 'a']--;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) return false;
        }
        cnt = new int[26];
        for (int i = 1; i < n; i += 2) cnt[s1.charAt(i) - 'a']++;
        for (int i = 1; i < n; i += 2) cnt[s2.charAt(i) - 'a']--;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) return false;
        }
        return true;
    }
}
/**
 * j - i = 3
 * Counter(s1[::3]) == Counter(s2[::3])
 * Counter(s1[1::3]) == Counter(s2[1::3])
 * Counter(s1[2::3]) == Counter(s2[2::3])
 */