package LC1201_1500;

public class LC1360_NumberofDaysBetweenTwoDates {
    /**
     * Write a program to count the number of days between two dates.
     *
     * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
     *
     * Input: date1 = "2019-06-29", date2 = "2019-06-30"
     * Output: 1
     *
     * Input: date1 = "2020-01-15", date2 = "2019-12-31"
     * Output: 15
     *
     * Constraints:
     *
     * The given dates are valid dates between the years 1971 and 2100.
     * @param date1
     * @param date2
     * @return
     */
    // time = O(1), space = O(1)
    private final int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int daysBetweenDates(String date1, String date2) {
        return Math.abs(get(date1) - get(date2));
    }

    private int get(String date) {
        String[] strs = date.split("-");
        int year = Integer.parseInt(strs[0]);
        int month = Integer.parseInt(strs[1]);
        int day = Integer.parseInt(strs[2]);

        int res = 0;
        for (int i = 1971; i < year; i++) res += 365 + is_leap(i);

        for (int i = 1; i < month; i++) res += get_days(year, i);
        return res + day;
    }

    private int is_leap(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) return 1;
        return 0;
    }

    private int get_days(int year, int month) {
        if (month != 2) return months[month];
        return is_leap(year) + 28;
    }
}
/**
 * 1971.1.1 - d1
 * 1971.1.1 - d2
 */