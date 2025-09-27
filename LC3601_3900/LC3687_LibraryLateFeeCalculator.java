package LC3601_3900;

public class LC3687_LibraryLateFeeCalculator {
    /**
     * You are given an integer array daysLate where daysLate[i] indicates how many days late the ith book was returned.
     *
     * The penalty is calculated as follows:
     *
     * If daysLate[i] == 1, penalty is 1.
     * If 2 <= daysLate[i] <= 5, penalty is 2 * daysLate[i].
     * If daysLate[i] > 5, penalty is 3 * daysLate[i].
     * Return the total penalty for all books.
     *
     * Input: daysLate = [5,1,7]
     *
     * Output: 32
     *
     * Input: daysLate = [1,1]
     *
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= daysLate.length <= 100
     * 1 <= daysLate[i] <= 100
     * @param daysLate
     * @return
     */
    // time = O(n), space = O(1)
    public int lateFee(int[] daysLate) {
        int res = 0;
        for (int x : daysLate) res += cal(x);
        return res;
    }

    private int cal(int x) {
        if (x == 1) return 1;
        if (x >= 2 && x <= 5) return 2 * x;
        return 3 * x;
    }
}