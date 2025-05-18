package LC3301_3600;
import java.util.*;
public class LC3476_MaximizeProfitfromTaskAssignment {
    /**
     * You are given an integer array workers, where workers[i] represents the skill level of the ith worker. You are
     * also given a 2D integer array tasks, where:
     *
     * tasks[i][0] represents the skill requirement needed to complete the task.
     * tasks[i][1] represents the profit earned from completing the task.
     * Each worker can complete at most one task, and they can only take a task if their skill level is equal to the
     * task's skill requirement. An additional worker joins today who can take up any task, regardless of the skill
     * requirement.
     *
     * Return the maximum total profit that can be earned by optimally assigning the tasks to the workers.
     *
     * Input: workers = [1,2,3,4,5], tasks = [[1,100],[2,400],[3,100],[3,400]]
     * Output: 1000
     *
     * Input: workers = [10,10000,100000000], tasks = [[1,100]]
     * Output: 100
     *
     * Input: workers = [7], tasks = [[3,3],[3,3]]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= workers.length <= 10^5
     * 1 <= workers[i] <= 10^9
     * 1 <= tasks.length <= 10^5
     * tasks[i].length == 2
     * 1 <= tasks[i][0], tasks[i][1] <= 10^9
     * @param workers
     * @param tasks
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxProfit(int[] workers, int[][] tasks) {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] t : tasks) {
            map.putIfAbsent(t[0], new PriorityQueue<>((o1, o2) -> o2 - o1));
            map.get(t[0]).offer(t[1]);
        }

        long res = 0;
        for (int x : workers) {
            if (!map.containsKey(x)) continue;
            res += map.get(x).poll();
            if (map.get(x).size() == 0) map.remove(x);
        }
        if (map.size() > 0) {
            int mx = 0;
            for (int x : map.keySet()) {
                mx = Math.max(mx, map.get(x).peek());
            }
            res += mx;
        }
        return res;
    }
}