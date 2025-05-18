package LC301_600;
import java.util.*;
public class LC568_MaximumVacationDays {
    /**
     * LeetCode wants to give one of its best employees the option to travel among n cities to collect algorithm
     * problems. But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and
     * weeks. Your job is to schedule the traveling to maximize the number of vacation days you could take, but there
     * are certain rules and restrictions you need to follow.
     *
     * Rules and restrictions:
     *
     * You can only travel among n cities, represented by indexes from 0 to n - 1. Initially, you are in the city
     * indexed 0 on Monday.
     * The cities are connected by flights. The flights are represented as an n x n matrix (not necessarily symmetrical),
     * called flights representing the airline status from the city i to the city j. If there is no flight from the
     * city i to the city j, flights[i][j] == 0; Otherwise, flights[i][j] == 1. Also, flights[i][i] == 0 for all i.
     * You totally have k weeks (each week has seven days) to travel. You can only take flights at most once per day
     * and can only take flights on each week's Monday morning. Since flight time is so short, we do not consider the
     * impact of flight time.
     * For each city, you can only have restricted vacation days in different weeks, given an n x k matrix called days
     * representing this relationship. For the value of days[i][j], it represents the maximum days you could take a
     * vacation in the city i in the week j.
     * You could stay in a city beyond the number of vacation days, but you should work on the extra days, which will
     * not be counted as vacation days.
     * If you fly from city A to city B and take the vacation on that day, the deduction towards vacation days will
     * count towards the vacation days of city B in that week.
     * We do not consider the impact of flight hours on the calculation of vacation days.
     * Given the two matrices flights and days, return the maximum vacation days you could take during k weeks.
     *
     * Input: flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[1,3,1],[6,0,3],[3,3,3]]
     * Output: 12
     *
     * Input: flights = [[0,0,0],[0,0,0],[0,0,0]], days = [[1,1,1],[7,7,7],[7,7,7]]
     * Output: 3
     *
     * Input: flights = [[0,1,1],[1,0,1],[1,1,0]], days = [[7,0,0],[0,7,0],[0,0,7]]
     * Output: 21
     *
     * Constraints:
     *
     * n == flights.length
     * n == flights[i].length
     * n == days.length
     * k == days[i].length
     * 1 <= n, k <= 100
     * flights[i][j] is either 0 or 1.
     * 0 <= days[i][j] <= 7
     * @param flights
     * @param days
     * @return
     */
    // time = O(m * n^2), space = O(m * n)
    public int maxVacationDays(int[][] flights, int[][] days) {
        int n = flights.length, m = days[0].length;
        int[][] f = new int[m + 1][n];
        for (int i = 0; i <= m; i++) Arrays.fill(f[i], Integer.MIN_VALUE);
        f[0][0] = 0;
        for (int k = 1; k <= m; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (flights[j][i] == 1 || i == j) {
                        f[k][i] = Math.max(f[k][i], f[k - 1][j] + days[i][k - 1]);
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) res = Math.max(res, f[m][i]);
        return res;
    }
}