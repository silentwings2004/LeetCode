package LC2101_2400;
import java.util.*;
public class LC2365_TaskSchedulerII {
    /**
     * You are given a 0-indexed array of positive integers tasks, representing tasks that need to be completed in
     * order, where tasks[i] represents the type of the ith task.
     *
     * You are also given a positive integer space, which represents the minimum number of days that must pass after
     * the completion of a task before another task of the same type can be performed.
     *
     * Each day, until all tasks have been completed, you must either:
     *
     * Complete the next task from tasks, or
     * Take a break.
     * Return the minimum number of days needed to complete all tasks.
     *
     * Input: tasks = [1,2,1,2,3,1], space = 3
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= tasks.length <= 10^5
     * 1 <= tasks[i] <= 10^9
     * 1 <= space <= tasks.length
     * @param tasks
     * @param space
     * @return
     */
    // time = O(n), space = O(n)
    public long taskSchedulerII(int[] tasks, int space) {
        int n = tasks.length;
        HashMap<Integer, Long> map = new HashMap<>();
        long res = 0;
        for (int x : tasks) {
            res = Math.max(res + 1, map.getOrDefault(x, 0L));
            map.put(x, res + space + 1);
        }
        return res;
    }
}
