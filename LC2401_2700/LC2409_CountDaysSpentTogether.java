package LC2401_2700;

public class LC2409_CountDaysSpentTogether {
    /**
     * Alice and Bob are traveling to Rome for separate business meetings.
     *
     * You are given 4 strings arriveAlice, leaveAlice, arriveBob, and leaveBob. Alice will be in the city from the
     * dates arriveAlice to leaveAlice (inclusive), while Bob will be in the city from the dates arriveBob to leaveBob
     * (inclusive). Each will be a 5-character string in the format "MM-DD", corresponding to the month and day of the
     * date.
     *
     * Return the total number of days that Alice and Bob are in Rome together.
     *
     * You can assume that all dates occur in the same calendar year, which is not a leap year. Note that the number
     * of days per month can be represented as: [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31].
     *
     * Input: arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19"
     * Output: 3
     *
     * Input: arriveAlice = "10-01", leaveAlice = "10-31", arriveBob = "11-01", leaveBob = "12-31"
     * Output: 0
     *
     * Constraints:
     *
     * All dates are provided in the format "MM-DD".
     * Alice and Bob's arrival dates are earlier than or equal to their leaving dates.
     * The given dates are valid dates of a non-leap year.
     * @param arriveAlice
     * @param leaveAlice
     * @param arriveBob
     * @param leaveBob
     * @return
     */
    // time = O(1), space = O(1)
    int[] months = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int a = get(arriveAlice), b = get(leaveAlice);
        int c = get(arriveBob), d = get(leaveBob);
        return Math.max(0, Math.min(b, d) - Math.max(a, c) + 1);
    }

    private int get(String s) {
        int res = 0;
        String[] strs = s.split("-");
        int m = Integer.parseInt(strs[0]);
        int d = Integer.parseInt(strs[1]);
        for (int i = 1; i < m; i++) res += months[i];
        return res + d;
    }
}