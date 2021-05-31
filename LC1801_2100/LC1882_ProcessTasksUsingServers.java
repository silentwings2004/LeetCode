package LC1801_2100;
import java.util.*;
public class LC1882_ProcessTasksUsingServers {
    /**
     * You are given two 0-indexed integer arrays servers and tasks of lengths n and m respectively. servers[i] is the
     * weight of the iserver, and tasks[j] is the time needed to process the j task in seconds.
     *
     * You are running a simulation system that will shut down after all tasks are processed. Each server can only
     * process one task at a time. You will be able to process the jth task starting from the jth second beginning
     * with the 0th task at second 0. To process task j, you assign it to the server with the smallest weight that is
     * free, and in case of a tie, choose the server with the smallest index. If a free server gets assigned task j at
     * second t, it will be free again at the second t + tasks[j].
     *
     * If there are no free servers, you must wait until one is free and execute the free tasks as soon as possible.
     * If multiple tasks need to be assigned, assign them in order of increasing index.
     *
     * You may assign multiple tasks at the same second if there are multiple free servers.
     *
     * Build an array ans of length m, where ans[j] is the index of the server the jth task will be assigned to.
     *
     * Return the array ans.
     *
     * Input: servers = [3,3,2], tasks = [1,2,3,2,1,2]
     * Output: [2,2,0,2,1,2]
     *
     * Constraints:
     *
     * servers.length == n
     * tasks.length == m
     * 1 <= n, m <= 2 * 10^5
     * 1 <= servers[i], tasks[j] <= 2 * 10^5
     * @param servers
     * @param tasks
     * @return
     */
    // time = O((m + n) * logm), space = O(m)
    public int[] assignTasks(int[] servers, int[] tasks) {
        // corner case
        if (servers == null || servers.length == 0 || tasks == null || tasks.length == 0) return new int[0];

        int[] res = new int[tasks.length];

        // [time, weight, idx]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : (o1[1] != o2[1] ? o1[1] - o2[1] : o1[2] - o2[2]));

        for (int i = 0; i < servers.length; i++) { // O(m)
            pq.offer(new int[]{0, servers[i], i}); // O(logm)
        }

        int k = 0, t = 0;
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> o1[1] != o2[1] ? o1[1] - o2[1] : o1[2] - o2[2]);
        while (k < tasks.length) { // O(n)
            while (!pq.isEmpty() && pq.peek()[0] <= t) pq2.offer(pq.poll()); // O(logm)
            while (k <= Math.min(t, tasks.length - 1) && !pq2.isEmpty()) {
                int[] top = pq2.poll();
                res[k] = top[2];
                top[0] = t + tasks[k];
                pq.offer(top);
                k++;
            }
            t = Math.max(t + 1, k);
            if (pq2.size() == 0) t = Math.max(pq.peek()[0], t);
        }
        return res;
    }
}