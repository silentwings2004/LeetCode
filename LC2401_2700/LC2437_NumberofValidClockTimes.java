package LC2401_2700;

import java.util.HashSet;

public class LC2437_NumberofValidClockTimes {
    /**
     * You are given a string of length 5 called time, representing the current time on a digital clock in the format
     * "hh:mm". The earliest possible time is "00:00" and the latest possible time is "23:59".
     *
     * In the string time, the digits represented by the ? symbol are unknown, and must be replaced with a digit from
     * 0 to 9.
     *
     * Return an integer answer, the number of valid clock times that can be created by replacing every ? with a digit
     * from 0 to 9.
     *
     * Input: time = "?5:00"
     * Output: 2
     *
     * Input: time = "0?:0?"
     * Output: 100
     *
     * Input: time = "??:??"
     * Output: 1440
     *
     * Constraints:
     *
     * time is a valid string of length 5 in the format "hh:mm".
     * "00" <= hh <= "23"
     * "00" <= mm <= "59"
     * Some of the digits might be replaced with '?' and need to be replaced with digits from 0 to 9.
     * @param time
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int countTime(String time) {
        int hour = 1;
        if (time.charAt(0) == '?') {
            if (time.charAt(1) == '?') hour = 24;
            else if (time.charAt(1) <= '3') hour = 3;
            else hour = 2;
        } else if (time.charAt(1) == '?') {
            if (time.charAt(0) <= '1') hour = 10;
            else hour = 4;
        }

        int minute = 1;
        if (time.charAt(3) == '?') {
            if (time.charAt(4) == '?') minute = 60;
            else minute = 6;
        } else if (time.charAt(4) == '?') minute = 10;
        return hour * minute;
    }

    // S2
    // time = O(1), space = O(1)
    public int countTime2(String time) {
        char[] chars = time.toCharArray();
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 5; k++) {
                    for (int l = 0; l <= 9; l++) {
                        if (time.charAt(0) == '?') chars[0] = (char)(i + '0');
                        if (time.charAt(1) == '?') chars[1] = (char)(j + '0');
                        if (time.charAt(3) == '?') chars[3] = (char)(k + '0');
                        if (time.charAt(4) == '?') chars[4] = (char)(l + '0');
                        if (isValid(chars)) set.add(String.valueOf(chars));
                    }
                }
            }
        }
        return set.size();
    }

    private boolean isValid(char[] chars) {
        String s = String.valueOf(chars);
        int hour = Integer.parseInt(s.substring(0, 2));
        int minute = Integer.parseInt(s.substring(3));
        if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) return true;
        return false;
    }

    // S3: brute-force
    // time = O(24 * 60 * 5), space = O(1)
    public int countTime3(String time) {
        int res = 0;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                String h = String.valueOf(i);
                if (i < 10) h = "0" + h;
                String m = String.valueOf(j);
                if (j < 10) m = "0" + m;
                String t = h + ":" + m;
                if (check(t, time)) res++;
            }
        }
        return res;
    }

    private boolean check(String s, String t) {
        for (int i = 0; i < 5; i++) {
            if (t.charAt(i) == '?') continue;
            if (s.charAt(i) != t.charAt(i)) return false;
        }
        return true;
    }
}