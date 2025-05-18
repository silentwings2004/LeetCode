package LC1501_1800;

public class LC1523_CountOddNumbersinanIntervalRange {
    /**
     * Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
     *
     * Input: low = 3, high = 7
     * Output: 3
     *
     * Input: low = 8, high = 10
     * Output: 1
     *
     * Constraints:
     *
     * 0 <= low <= high <= 10^9
     * @param low
     * @param high
     * @return
     */
    // time = O(1), space = O(1)
    public int countOdds(int low, int high) {
        int d = high - low + 1;
        if (d % 2 == 0) return d / 2;
        return low % 2 == 0 ? d / 2 : d / 2 + 1;
    }
}