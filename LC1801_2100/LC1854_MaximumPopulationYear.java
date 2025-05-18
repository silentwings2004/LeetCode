package LC1801_2100;
import java.util.*;
public class LC1854_MaximumPopulationYear {
    /**
     * You are given a 2D integer array logs where each logs[i] = [birthi, deathi] indicates the birth and death years
     * of the ith person.
     *
     * The population of some year x is the number of people alive during that year. The ith person is counted in year
     * x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the
     * year that they die.
     *
     * Return the earliest year with the maximum population.
     *
     * Input: logs = [[1993,1999],[2000,2010]]
     * Output: 1993
     *
     * Constraints:
     *
     * 1 <= logs.length <= 100
     * 1950 <= birthi < deathi <= 2050
     * @param logs
     * @return
     */
    // S1: diff Array
    // time = O(nlogn), space = O(n)
    public int maximumPopulation(int[][] logs) {
        List<int[]> diff = new ArrayList<>();
        for (int[] x : logs) {
            int a = x[0], b = x[1];
            diff.add(new int[]{a, 1});
            diff.add(new int[]{b, -1});
        }

        Collections.sort(diff, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int sum = 0, max = 0, res = 0;
        for (int[] x : diff) {
            sum += x[1];
            if (x[1] > 0) {
                if (sum > max) {
                    max = sum;
                    res = x[0];
                }
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int maximumPopulation2(int[][] logs) {
        // corner case
        if (logs == null || logs.length == 0 || logs[0] == null || logs[0].length == 0) return 0;

        int[] log = new int[101];
        for (int[] l : logs) {
            for (int i = l[0]; i < l[1]; i++) {
                log[i - 1950]++;
            }
        }

        int max = 0, res = 0;
        for (int i = 0; i < 101; i++) {
            if (max < log[i]) {
                max = log[i];
                res = i + 1950;
            }
        }
        return res;
    }
}
