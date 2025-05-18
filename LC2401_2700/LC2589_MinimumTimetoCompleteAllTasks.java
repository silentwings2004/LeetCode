package LC2401_2700;
import java.util.*;
public class LC2589_MinimumTimetoCompleteAllTasks {
    /**
     * There is a computer that can run an unlimited number of tasks at the same time. You are given a 2D integer array
     * tasks where tasks[i] = [starti, endi, durationi] indicates that the ith task should run for a total of durationi
     * seconds (not necessarily continuous) within the inclusive time range [starti, endi].
     *
     * You may turn on the computer only when it needs to run a task. You can also turn it off if it is idle.
     *
     * Return the minimum time during which the computer should be turned on to complete all tasks.
     *
     * Input: tasks = [[2,3,1],[4,5,1],[1,5,2]]
     * Output: 2
     *
     * Input: tasks = [[1,3,2],[2,5,3],[5,6,2]]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= tasks.length <= 2000
     * tasks[i].length == 3
     * 1 <= starti, endi <= 2000
     * 1 <= durationi <= endi - starti + 1
     * @param tasks
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (o1, o2) -> o1[1] - o2[1]);
        int n = tasks.length, res = 0;
        boolean[] st = new boolean[2010];
        for (int[] x : tasks) {
            int a = x[0], b = x[1], c = x[2];
            for (int i = a; i <= b; i++) {
                if (st[i]) c--;
            }
            for (int i = b; i >= a && c > 0; i--) {
                if (!st[i]) {
                    st[i] = true;
                    c--;
                    res++;
                }
            }
        }
        return res;
    }

    // S2: 遍历有限的时间轴
    // time = O(nlogn + n * k), space = O(logn + k)
    public int findMinimumTime2(int[][] tasks) {
        Arrays.sort(tasks, (o1, o2) -> o1[1] - o2[1]);
        boolean[] st = new boolean[2010];
        for (int[] x : tasks) {
            int a = x[0], b = x[1], c = x[2];
            int overlap = 0;
            for (int i = a; i <= b; i++) {
                if (st[i]) overlap++;
            }
            if (overlap >= c) continue;
            int d = c - overlap;
            for (int i = b; i >= a; i--) {
                if (!st[i]) {
                    st[i] = true;
                    d--;
                }
                if (d == 0) break;
            }
        }
        int res = 0;
        for (int i = 1; i <= 2000; i++) res += st[i] ? 1 : 0;
        return res;
    }

    // S3: 取消时间轴限定的做法
    // time = O(nlogn), space = O(n)
    public int findMinimumTime3(int[][] tasks) {
        Arrays.sort(tasks, (o1, o2) -> o1[1] - o2[1]);
        List<int[]> q = new ArrayList<>();
        q.add(new int[]{-1, -1, 0});
        for (int[] x : tasks) {
            int start = x[0], end = x[1], duration = x[2];
            int idx = lower_bound(q, start); // find the last interval whose start < x[0]
            int n = q.size();
            // calculate overlap
            int overlap = q.get(n - 1)[2] - q.get(idx)[2];
            if (q.get(idx)[1] >= start) overlap += q.get(idx)[1] - start + 1;
            // check diff
            int diff = duration - overlap, cur = end;
            while (diff > 0) {
                if (Math.abs(q.get(q.size() - 1)[1] - cur) < diff) { // empty interval
                    diff -= Math.abs(q.get(q.size() - 1)[1] - cur);
                    cur = q.get(q.size() - 1)[0] - 1;
                    q.remove(q.size() - 1);
                } else {
                    q.add(new int[]{cur - diff + 1, end, q.get(q.size() - 1)[2] + end - (cur - diff + 1) + 1});
                    diff = 0;
                }

            }
        }
        return q.get(q.size() - 1)[2];
    }

    private int lower_bound(List<int[]> q, int t) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (q.get(mid)[0] < t) l = mid;
            else r = mid - 1;
        }
        return r;
    }
}
/**
 * 尽量多的overlap => 按照右端点排序
 * deadline靠前的任务拿出来
 * 尽量放到最后跑，才能尽可能互相重合，贴着deadline来做
 */