package LC3901_4200;

public class LC3986_NumberofElapsedSecondsBetweenTwoTimes {
    /**
     * You are given two valid times startTime and endTime, each represented as a string in the format "HH:MM:SS".
     *
     * Return the number of seconds that have elapsed from startTime to endTime, inclusive of both endpoints.
     *
     * Input: startTime = "01:00:00", endTime = "01:00:25"
     * Output: 25
     *
     * Input: startTime = "12:34:56", endTime = "13:00:00"
     * Output: 1504
     *
     * Constraints:
     *
     * startTime.length == 8
     * endTime.length == 8
     * startTime and endTime are valid times in the format "HH:MM:SS"
     * 00 <= HH <= 23
     * 00 <= MM <= 59
     * 00 <= SS <= 59
     * endTime is not earlier than startTime
     * @param startTime
     * @param endTime
     * @return
     */
    // time = O(1), space = O(1)
    public int secondsBetweenTimes(String startTime, String endTime) {
        return get(endTime) - get(startTime);
    }

    private int get(String t) {
        int h = Integer.parseInt(t.substring(0, 2));
        int m = Integer.parseInt(t.substring(3, 5));
        int s = Integer.parseInt(t.substring(6, 8));
        return h * 3600 + m * 60 + s;
    }
}