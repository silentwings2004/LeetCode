package LC601_900;
import java.util.*;
public class LC859_BuddyStrings {
    /**
     * Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal,
     * otherwise, return false.
     *
     * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the
     * characters at s[i] and s[j].
     *
     * For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
     *
     * Input: s = "ab", goal = "ba"
     * Output: true
     *
     * Input: s = "ab", goal = "ab"
     * Output: false
     *
     * Input: s = "aa", goal = "aa"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= s.length, goal.length <= 2 * 10^4
     * s and goal consist of lowercase letters.
     * @param s
     * @param goal
     * @return
     */
    // time = O(n), space = O(n)
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;
        if (s.equals(goal)) {
            int[] cnt = new int[26];
            for (int i = 0; i < s.length(); i++) {
                if (++cnt[s.charAt(i) - 'a'] > 1) return true;
            }
            return false;
        }

        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) q.add(i);
        }
        if (q.size() != 2) return false;
        int x = q.get(0), y = q.get(1);
        if (s.charAt(x) == goal.charAt(y) && s.charAt(y) == goal.charAt(x)) return true;
        return false;
    }
}