package LC2401_2700;

public class LC2446_DetermineifTwoEventsHaveConflict {
    /**
     * You are given two arrays of strings that represent two inclusive events that happened on the same day, event1
     * and event2, where:
     *
     * event1 = [startTime1, endTime1] and
     * event2 = [startTime2, endTime2].
     * Event times are valid 24 hours format in the form of HH:MM.
     *
     * A conflict happens when two events have some non-empty intersection (i.e., some moment is common to both events).
     *
     * Return true if there is a conflict between two events. Otherwise, return false.
     *
     * Input: event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
     * Output: true
     *
     * Input: event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
     * Output: true
     *
     * Input: event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
     * Output: false
     *
     * Constraints:
     *
     * evnet1.length == event2.length == 2.
     * event1[i].length == event2[i].length == 5
     * startTime1 <= endTime1
     * startTime2 <= endTime2
     * All the event times follow the HH:MM format.
     * @param event1
     * @param event2
     * @return
     */
    // time = O(1), space = O(1)
    public boolean haveConflict(String[] event1, String[] event2) {
        int a = convert(event1[0]);
        int b = convert(event1[1]);
        int c = convert(event2[0]);
        int d = convert(event2[1]);

        if (a < c) return b >= c;
        else return a <= d;
    }

    private int convert(String s) {
        int h = Integer.parseInt(s.substring(0, 2));
        int m = Integer.parseInt(s.substring(3));
        return h * 60 + m;
    }
}