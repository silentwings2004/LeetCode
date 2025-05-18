package LC2700_3000;
import java.util.*;
public class LC2895_MinimumProcessingTime {
    /**
     * You have n processors each having 4 cores and n * 4 tasks that need to be executed such that each core should
     * perform only one task.
     *
     * Given a 0-indexed integer array processorTime representing the time at which each processor becomes available
     * for the first time and a 0-indexed integer array tasks representing the time it takes to execute each task,
     * return the minimum time when all of the tasks have been executed by the processors.
     *
     * Note: Each core executes the task independently of the others.
     *
     * Input: processorTime = [8,10], tasks = [2,2,3,1,8,7,4,5]
     * Output: 16
     *
     * Input: processorTime = [10,20], tasks = [2,3,1,2,5,8,4,3]
     * Output: 23
     *
     * Constraints:
     *
     * 1 <= n == processorTime.length <= 25000
     * 1 <= tasks.length <= 10^5
     * 0 <= processorTime[i] <= 10^9
     * 1 <= tasks[i] <= 10^9
     * tasks.length == 4 * n
     * @param processorTime
     * @param tasks
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        Collections.sort(processorTime);
        Collections.sort(tasks, (o1, o2) -> o2 - o1);
        int n = tasks.size(), res = 0;
        for (int i = 0, j = 0; i < n; i += 4, j++) {
            int t = 0;
            for (int k = 0; k < 4; k++) t = Math.max(t, processorTime.get(j) + tasks.get(i + k));
            res = Math.max(res, t);
        }
        return res;
    }
}