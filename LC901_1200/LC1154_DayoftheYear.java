package LC901_1200;

public class LC1154_DayoftheYear {
    /**
     * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the
     * year.
     *
     * Input: date = "2019-01-09"
     * Output: 9
     *
     * Constraints:
     *
     * date.length == 10
     * date[4] == date[7] == '-', and all other date[i]'s are digits
     * date represents a calendar date between Jan 1st, 1900 and Dec 31, 2019.
     * @param date
     * @return
     */
    // time = O(1), space = O(1)
    public int dayOfYear(String date) {
        int[] days = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] strs = date.split("-");
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);
        int res = 0;
        for (int i = 0; i < month; i++) {
            res += days[i];
            if (i == 2) res += is_leap(year);
        }
        return res + day;
    }

    private int is_leap(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) return 1;
        return 0;
    }
}