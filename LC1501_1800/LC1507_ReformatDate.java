package LC1501_1800;

public class LC1507_ReformatDate {
    /**
     * Given a date string in the form Day Month Year, where:
     *
     * Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
     * Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
     * Year is in the range [1900, 2100].
     * Convert the date string to the format YYYY-MM-DD, where:
     *
     * YYYY denotes the 4 digit year.
     * MM denotes the 2 digit month.
     * DD denotes the 2 digit day.
     *
     * Input: date = "20th Oct 2052"
     * Output: "2052-10-20"
     *
     * Input: date = "6th Jun 1933"
     * Output: "1933-06-06"
     *
     * Input: date = "26th May 1960"
     * Output: "1960-05-26"
     *
     * Constraints:
     *
     * The given dates are guaranteed to be valid, so no error handling is necessary.
     * @param date
     * @return
     */
    // time = O(1), space = O(1)
    public String reformatDate(String date) {
        String[] month = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] strs = date.split(" ");
        int d = 0, m = 0, y = Integer.parseInt(strs[2]);
        for (char c : strs[0].toCharArray()) {
            if (Character.isDigit(c)) d = d * 10 + c - '0';
        }
        for (int i = 0; i < 12; i++) {
            if (month[i].equals(strs[1])) {
                m = i + 1;
                break;
            }
        }
        return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d);
    }
}