package LC3001_3300;

public class LC3114_LatestTimeYouCanObtainAfterReplacingCharacters {
    /**
     * You are given a string s representing a 12-hour format time where some of the digits (possibly none) are replaced
     * with a "?".
     *
     * 12-hour times are formatted as "HH:MM", where HH is between 00 and 11, and MM is between 00 and 59. The earliest
     * 12-hour time is 00:00, and the latest is 11:59.
     *
     * You have to replace all the "?" characters in s with digits such that the time we obtain by the resulting string
     * is a valid 12-hour format time and is the latest possible.
     *
     * Return the resulting string.
     *
     * Input: s = "1?:?4"
     * Output: "11:54"
     *
     * Input: s = "0?:5?"
     * Output: "09:59"
     *
     * Constraints:
     *
     * s.length == 5
     * s[2] is equal to the character ":".
     * All characters except s[2] are digits or "?" characters.
     * The input is generated such that there is at least one time between "00:00" and "11:59" that you can obtain
     * after replacing the "?" characters.
     * @param s
     * @return
     */
    // time = O(1), space = O(1)
    public String findLatestTime(String s) {
        String[] strs = s.split(":");
        for (int i = 11; i >= 0; i--) {
            for (int j = 59; j >= 0; j--) {
                if (check(strs[1], j)) {
                    if (check(strs[0], i)) {
                        String x = i < 10 ? "0" + i : String.valueOf(i);
                        String y = j < 10 ? "0" + j : String.valueOf(j);
                        return x + ":" + y;
                    }
                }
            }
        }
        return s;
    }

    private boolean check(String t, int ts) {
        int a = t.charAt(0), b = t.charAt(1);
        int x = ts / 10, y = ts % 10;
        if ((a == '?' || a - '0' == x) && (b == '?' || b - '0' == y)) return true;
        return false;
    }
}