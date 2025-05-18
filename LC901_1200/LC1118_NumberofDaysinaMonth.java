package LC901_1200;

public class LC1118_NumberofDaysinaMonth {
    /**
     * Given a year year and a month month, return the number of days of that month.
     *
     * Input: year = 1992, month = 7
     * Output: 31
     *
     * Input: year = 2000, month = 2
     * Output: 29
     *
     * Input: year = 1900, month = 2
     * Output: 28
     *
     * Constraints:
     *
     * 1583 <= year <= 2100
     * 1 <= month <= 12
     * @param year
     * @param month
     * @return
     */
    // time = O(1), space = O(1)
    public int numberOfDays(int year, int month) {
        int[] m = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month != 2) return m[month];
        return isLeapYear(year) ? 29 : 28;
    }

    private boolean isLeapYear(int year) {
        if (year % 100 != 0 && year % 4 == 0 || year % 400 == 0) return true;
        return false;
    }
}