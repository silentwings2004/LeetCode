package LC901_1200;

public class LC1014_BestSightseeingPair {
    /**
     * You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two
     * sightseeing spots i and j have a distance j - i between them.
     *
     * The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the
     * sightseeing spots, minus the distance between them.
     *
     * Return the maximum score of a pair of sightseeing spots.
     *
     * Input: values = [8,1,5,2,6]
     * Output: 11
     *
     * Input: values = [1,2]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= values.length <= 5 * 10^4
     * 1 <= values[i] <= 1000
     * @param values
     * @return
     */
    // time = O(n), space = O(1)
    public int maxScoreSightseeingPair(int[] values) {
        int res = Integer.MIN_VALUE, maxv = values[0], n = values.length;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, maxv + values[i] - i);
            maxv = Math.max(maxv, values[i] + i);
        }
        return res;
    }
}