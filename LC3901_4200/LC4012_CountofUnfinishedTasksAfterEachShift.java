package LC3901_4200;

public class LC4012_CountofUnfinishedTasksAfterEachShift {
    /**
     * You are given two integer arrays tasks and shifts.
     *
     * tasks[i] represents the time required to complete the ith task.
     * shifts[j] represents the amount of time available during the jth shift.
     * The tasks must be processed in order from left to right.
     *
     * Carry-over: If a task is not completed during a shift, processing continues from the same point in that task
     * during the next shift.
     * Restart: If all tasks are completed during a shift, the shift ends immediately. Any unused time in that shift is
     * discarded, and the next shift begins again from task 0.
     * A task is unfinished if it has not been fully completed. This includes a task that is currently in progress.
     *
     * Return an integer array ans where ans[j] is the number of unfinished tasks immediately after the jth shift.
     *
     * Input: tasks = [1,4,4], shifts = [9,1,4]
     * Output: [0,2,1]
     *
     * Input: tasks = [2,3,4], shifts = [20,4,5]
     * Output: [0,2,0]
     *
     * Input: tasks = [4,2], shifts = [3,6,1]
     * Output: [2,0,2]
     *
     * Constraints:
     *
     * 1 <= tasks.length <= 10^5
     * 1 <= shifts.length <= 10^5
     * 1 <= tasks[i] <= 10^9
     * 1 <= shifts[i] <= 10^9
     * @param tasks
     * @param shifts
     * @return
     */
    // time = O(mlogn), space = O(n)
    public int[] countTasks(int[] tasks, int[] shifts) {
        int n = tasks.length, m = shifts.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + tasks[i - 1];

        int[] res = new int[m];
        long t = 0;
        for (int i = 0; i < m; i++) {
            long cur = t + shifts[i];
            if (cur >= s[n]) t = 0;
            else {
                t = cur;
                res[i] = n - find(s, t); // s 是 1-index，所以这里找到的相当于是原数组 0-index 的 k + 1
            }
        }
        return res;
    }

    private int find(long[] s, long t) {
        int l = 0, r = s.length;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (s[mid] <= t) l = mid;
            else r = mid - 1;
        }
        return s[r] <= t ? r : r - 1;
    }
}
/**
 * 由于 tasks 中的数都是非负数，所以 s 是递增的。
 * 在有序数组 s 中二分查找最后一个 ≤ t 的数的下标 k，那么已完成的任务下标为 [0,k]，未完成的任务下标为 [k+1,n−1]，这有 n−k−1 个。
 * 在 s 中二分查找第一个 > t 的数的下标 k' = k + 1，那么未完成的任务数为 n − k' 个。
 */