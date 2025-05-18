package LC3001_3300;

public class LC3280_ConvertDatetoBinary {
    /**
     * You are given a string date representing a Gregorian calendar date in the yyyy-mm-dd format.
     *
     * date can be written in its binary representation obtained by converting year, month, and day to their binary
     * representations without any leading zeroes and writing them down in year-month-day format.
     *
     * Return the binary representation of date.
     *
     * Input: date = "2080-02-29"
     * Output: "100000100000-10-11101"
     *
     * Input: date = "1900-01-01"
     * Output: "11101101100-1-1"
     *
     * Constraints:
     *
     * date.length == 10
     * date[4] == date[7] == '-', and all other date[i]'s are digits.
     * The input is generated such that date represents a valid Gregorian calendar date between Jan 1st, 1900 and
     * Dec 31st, 2100 (both inclusive).
     * @param date
     * @return
     */
    // time = O(n), space = O(n)
    public String convertDateToBinary(String date) {
        String[] strs = date.split("-");
        StringBuilder sb = new StringBuilder();
        for (String s : strs) sb.append(convert(s)).append('-');
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private String convert(String s) {
        int x = Integer.parseInt(s);
        return Integer.toBinaryString(x);
    }
}