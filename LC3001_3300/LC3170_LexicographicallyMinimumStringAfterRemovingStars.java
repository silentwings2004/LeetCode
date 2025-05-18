package LC3001_3300;
import java.util.*;
public class LC3170_LexicographicallyMinimumStringAfterRemovingStars {
    /**
     * You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.
     *
     * While there is a '*', do the following operation:
     *
     * Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters,
     * you can delete any of them.
     * Return the lexicographically smallest resulting string after removing all '*' characters.
     *
     * Input: s = "aaba*"
     * Output: "aab"
     *
     * Input: s = "abc"
     * Output: "abc"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists only of lowercase English letters and '*'.
     * The input is generated such that it is possible to delete all '*' characters.
     * @param s
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String clearStars(String s) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        int n = s.length();
        boolean[] st = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '*') pq.offer(new int[]{s.charAt(i) - 'a', i});
            else {
                if (!pq.isEmpty()) {
                    int[] t = pq.poll();
                    st[t[1]] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!st[i] && s.charAt(i) != '*') sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}