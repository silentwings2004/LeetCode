package LC1501_1800;

public class LC1736_LatestTimebyReplacingHiddenDigits {
    /**
     * You are given a string time in the form of hh:mm, where some of the digits in the string are hidden
     * (represented by ?).
     *
     * The valid times are those inclusively between 00:00 and 23:59.
     *
     * Return the latest valid time you can get from time by replacing the hidden digits.
     *
     * Input: time = "2?:?0"
     * Output: "23:50"
     *
     * Input: time = "0?:3?"
     * Output: "09:39"
     *
     * Input: time = "1?:22"
     * Output: "19:22"
     *
     * Constraints:
     *
     * time is in the format hh:mm.
     * It is guaranteed that you can produce a valid time from the given string.
     * @param time
     * @return
     */
    // time = O(1), space = O(1)
    public String maximumTime(String time) {
        for (int i = 23; i >= 0; i--) {
            for (int j = 59; j >= 0; j--) {
                String s = (i < 10 ? "0" + i : i) + ":" + (j < 10 ? "0" + j : j);
                if (check(time, s)) return s;
            }
        }
        return "";
    }

    private boolean check(String t, String s) {
        for (int i = 0; i < 5; i++) {
            if (t.charAt(i) == s.charAt(i) || t.charAt(i) == '?') continue;
            return false;
        }
        return true;
    }
}