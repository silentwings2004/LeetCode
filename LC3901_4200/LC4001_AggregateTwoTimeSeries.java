package LC3901_4200;
import java.util.*;
public class LC4001_AggregateTwoTimeSeries {
    /**
     * You are given two 2D integer arrays series1 and series2.
     *
     * Each element in both series is of the form [timestamp, value], where:
     *
     * timestamp is an integer representing the time.
     * value is an integer representing the value at that timestamp.
     * Each array is sorted in strictly increasing order of timestamp.
     *
     * For any timestamp not present in a series, its value is taken from the next available timestamp in the same
     * series if one exists. Otherwise, its value is considered 0.
     *
     * The aggregated series is formed by summing the corresponding values from both series at every timestamp that
     * appears in either series.
     *
     * Return the aggregated series as a 2D integer array of [timestamp, summedValue] pairs, sorted in strictly
     * increasing order of timestamp.
     *
     * An array is strictly increasing if each element is strictly greater than the previous element.
     *
     * Input: series1 = [[1,3],[4,1]], series2 = [[2,2],[5,2]]
     * Output: [[1,5],[2,3],[4,3],[5,2]]
     *
     * Input: series1 = [[1,5],[3,1]], series2 = [[2,2]]
     * Output: [[1,7],[2,3],[3,1]]
     *
     * Input: series1 = [[1,5]], series2 = [[1000000000,2]]
     * Output: [[1,7],[1000000000,2]]
     *
     * Constraints:
     *
     * 1 <= series1.length, series2.length <= 10^5
     * series1[i].length == series2[i].length == 2
     * 1 <= series1[i][0], series2[i][0] <= 10^9
     * 1 <= series1[i][1], series2[i][1] <= 10^9
     * Each series is sorted in strictly increasing order of timestamp.
     * @param series1
     * @param series2
     * @return
     */
    // time = O(max(n, m)), space = O(1)
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        int n = series1.length, m = series2.length;
        int i = 0, j = 0;
        List<List<Integer>> res = new ArrayList<>();
        while (i < n || j < m) {
            int t = 0;
            if (j == m || i < n && series1[i][0] < series2[j][0]) t = series1[i][0];
            else if (i == n || j < m && series2[j][0] < series1[i][0]) t = series2[j][0];
            else t = series1[i][0];
            int v1 = i < n ? series1[i][1] : 0;
            int v2 = j < m ? series2[j][1] : 0;
            res.add(Arrays.asList(t, v1 + v2));
            if (i < n && t == series1[i][0]) i++;
            if (j < m && t == series2[j][0]) j++;
        }
        return res;
    }
}