package LC601_900;
import java.util.*;
public class LC826_MostProfitAssigningWork {
    /**
     * You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:
     *
     * difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
     * worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most
     * worker[j]).
     * Every worker can be assigned at most one job, but one job can be completed multiple times.
     *
     * For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker
     * cannot complete any job, their profit is $0.
     * Return the maximum profit we can achieve after assigning the workers to the jobs.
     *
     * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
     * Output: 100
     *
     * Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
     * Output: 0
     *
     * Constraints:
     *
     * n == difficulty.length
     * n == profit.length
     * m == worker.length
     * 1 <= n, m <= 10^4
     * 1 <= difficulty[i], profit[i], worker[i] <= 10^5
     * @param difficulty
     * @param profit
     * @param worker
     * @return
     */
    // time = O(mlogm + nlogn), space = O(m)
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int m = difficulty.length, n = worker.length;
        int[][] t = new int[m][2];
        for (int i = 0; i < m; i++) t[i] = new int[]{difficulty[i], profit[i]};
        Arrays.sort(t, (o1, o2) -> o1[0] - o2[0]);
        Arrays.sort(worker);

        int res = 0, p = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (j < m && t[j][0] <= worker[i]) p = Math.max(p, t[j++][1]);
            res += p;
        }
        return res;
    }
}