package LC3301_3600;
import java.util.*;
public class LC3408_DesignTaskManager {
    /**
     * There is a task management system that allows users to manage their tasks, each associated with a priority. The
     * system should efficiently handle adding, modifying, executing, and removing tasks.
     *
     * Implement the TaskManager class:
     *
     * TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples.
     * Each element in the input list is of the form [userId, taskId, priority], which adds a task to the specified user
     * with the given priority.
     *
     * void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the user
     * with userId. It is guaranteed that taskId does not exist in the system.
     *
     * void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority. It is
     * guaranteed that taskId exists in the system.
     *
     * void rmv(int taskId) removes the task identified by taskId from the system. It is guaranteed that taskId exists
     * in the system.
     *
     * int execTop() executes the task with the highest priority across all users. If there are multiple tasks with the
     * same highest priority, execute the one with the highest taskId. After executing, the taskId is removed from the
     * system. Return the userId associated with the executed task. If no tasks are available, return -1.
     *
     * Note that a user may be assigned multiple tasks.
     *
     * Input:
     * ["TaskManager", "add", "edit", "execTop", "rmv", "add", "execTop"]
     * [[[[1, 101, 10], [2, 102, 20], [3, 103, 15]]], [4, 104, 5], [102, 8], [], [101], [5, 105, 15], []]
     *
     * Output:
     * [null, null, null, 3, null, null, 5]
     *
     * Constraints:
     *
     * 1 <= tasks.length <= 10^5
     * 0 <= userId <= 10^5
     * 0 <= taskId <= 10^5
     * 0 <= priority <= 10^9
     * 0 <= newPriority <= 10^9
     * At most 2 * 10^5 calls will be made in total to add, edit, rmv, and execTop methods.
     * @param tasks
     */
    // time = O(nlogn), space = O(n)
    HashMap<Integer, int[]> map;
    TreeSet<int[]> set;
    public LC3408_DesignTaskManager(List<List<Integer>> tasks) {
        map = new HashMap<>();
        set = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (List<Integer> t : tasks) {
            int userId = t.get(0), taskId = t.get(1), priority = t.get(2);
            add(userId, taskId, priority);
        }
    }

    public void add(int userId, int taskId, int priority) {
        map.put(taskId, new int[]{userId, priority});
        set.add(new int[]{priority, taskId});
    }

    public void edit(int taskId, int newPriority) {
        int[] x = map.get(taskId);
        int userId = x[0];
        rmv(taskId);
        add(userId, taskId, newPriority);
    }

    public void rmv(int taskId) {
        int[] x = map.get(taskId);
        int priority = x[1];
        map.remove(taskId);
        set.remove(new int[]{priority, taskId});
    }

    public int execTop() {
        if (set.size() == 0) return -1;
        int[] x = set.first();
        int priority = x[0], taskId = x[1];
        int[] y = map.get(taskId);
        int userId = y[0];
        rmv(taskId);
        return userId;
    }
}
/**
 * 懒删除堆
 */