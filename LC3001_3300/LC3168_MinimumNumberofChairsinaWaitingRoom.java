package LC3001_3300;

public class LC3168_MinimumNumberofChairsinaWaitingRoom {
    /**
     * You are given a string s. Simulate events at each second i:
     *
     * If s[i] == 'E', a person enters the waiting room and takes one of the chairs in it.
     * If s[i] == 'L', a person leaves the waiting room, freeing up a chair.
     * Return the minimum number of chairs needed so that a chair is available for every person who enters the waiting
     * room given that it is initially empty.
     *
     * Input: s = "EEEEEEE"
     * Output: 7
     *
     * Input: s = "ELELEEL"
     * Output: 2
     *
     * Input: s = "ELEELEELLL"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length <= 50
     * s consists only of the letters 'E' and 'L'.
     * s represents a valid sequence of entries and exits.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumChairs(String s) {
        int res = 0, n = s.length();
        for (int i = 0, v = 0; i < n; i++) {
            if (s.charAt(i) == 'E') v++;
            else v--;
            res = Math.max(res, v);
        }
        return res;
    }
}