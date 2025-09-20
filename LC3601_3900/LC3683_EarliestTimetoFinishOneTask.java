package LC3601_3900;

public class LC3683_EarliestTimetoFinishOneTask {
    /**
     * You are given a 2D integer array tasks where tasks[i] = [si, ti].
     *
     * Each [si, ti] in tasks represents a task with start time si that takes ti units of time to finish.
     *
     * Return the earliest time at which at least one task is finished.
     *
     * Input: tasks = [[1,6],[2,3]]
     * Output: 5
     *
     * Input: tasks = [[100,100],[100,100],[100,100]]
     * Output: 200
     *
     * Constraints:
     *
     * 1 <= tasks.length <= 100
     * tasks[i] = [si, ti]
     * 1 <= si, ti <= 100
     * @param tasks
     * @return
     */
    // time = O(n), space = O(1)
    public int earliestTime(int[][] tasks) {
        int res = 201;
        for (int[] x : tasks) res = Math.min(res, x[0] + x[1]);
        return res;
    }
}