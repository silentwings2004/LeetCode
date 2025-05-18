package LC3001_3300;

public class LC3019_NumberofChangingKeys {
    /**
     * You are given a 0-indexed string s typed by a user. Changing a key is defined as using a key different from the
     * last used key. For example, s = "ab" has a change of a key while s = "bBBb" does not have any.
     *
     * Return the number of times the user had to change the key.
     *
     * Note: Modifiers like shift or caps lock won't be counted in changing the key that is if a user typed the letter
     * 'a' and then the letter 'A' then it will not be considered as a changing of key.
     *
     * Input: s = "aAbBcC"
     * Output: 2
     *
     * Input: s = "AaAaAaaA"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of only upper case and lower case English letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int countKeyChanges(String s) {
        int n = s.length(), res = 0, last = -1;
        for (int i = 0; i < n; i++) {
            int u = Character.toLowerCase(s.charAt(i)) - 'a';
            if (u == last) continue;
            if (last != -1) res++;
            last = u;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int countKeyChanges2(String s) {
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if ((s.charAt(i) & 31) != (s.charAt(i - 1) & 31)) res++;
        }
        return res;
    }
}