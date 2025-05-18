package LC2401_2700;
import java.util.*;
public class LC2590_DesignaTodoList {
    /**
     * Design a Todo List Where users can add tasks, mark them as complete, or get a list of pending tasks. Users can
     * also add tags to tasks and can filter the tasks by certain tags.
     *
     * Implement the TodoList class:
     *
     * TodoList() Initializes the object.
     * int addTask(int userId, String taskDescription, int dueDate, List<String> tags) Adds a task for the user with
     * the ID userId with a due date equal to dueDate and a list of tags attached to the task. The return value is the
     * ID of the task. This ID starts at 1 and is sequentially increasing. That is, the first task's id should be 1,
     * the second task's id should be 2, and so on.
     * List<String> getAllTasks(int userId) Returns a list of all the tasks not marked as complete for the user with ID
     * userId, ordered by the due date. You should return an empty list if the user has no uncompleted tasks.
     * List<String> getTasksForTag(int userId, String tag) Returns a list of all the tasks that are not marked as
     * complete for the user with the ID userId and have tag as one of their tags, ordered by their due date. Return an
     * empty list if no such task exists.
     * void completeTask(int userId, int taskId) Marks the task with the ID taskId as completed only if the task exists
     * and the user with the ID userId has this task, and it is uncompleted.
     *
     * Input
     * ["TodoList", "addTask", "addTask", "getAllTasks", "getAllTasks", "addTask", "getTasksForTag", "completeTask",
     * "completeTask", "getTasksForTag", "getAllTasks"]
     * [[], [1, "Task1", 50, []], [1, "Task2", 100, ["P1"]], [1], [5], [1, "Task3", 30, ["P1"]], [1, "P1"], [5, 1],
     * [1, 2], [1, "P1"], [1]]
     * Output
     * [null, 1, 2, ["Task1", "Task2"], [], 3, ["Task3", "Task2"], null, null, ["Task3"], ["Task3", "Task1"]]
     *
     * Constraints:
     *
     * 1 <= userId, taskId, dueDate <= 100
     * 0 <= tags.length <= 100
     * 1 <= taskDescription.length <= 50
     * 1 <= tags[i].length, tag.length <= 20
     * All dueDate values are unique.
     * All the strings consist of lowercase and uppercase English letters and digits.
     * At most 100 calls will be made for each method.
     */
    // time = O(nlogn), space = O(n)
    HashMap<Integer, TreeMap<Integer, Integer>> user2Task; // {userID -> {date, id}}
    HashMap<Integer, HashSet<String>> id2Tag; // {id -> tags}
    HashMap<Integer, String> id2Task; // {id -> description}
    int id = 1;
    public LC2590_DesignaTodoList() {
        user2Task = new HashMap<>();
        id2Tag = new HashMap<>();
        id2Task = new HashMap<>();
    }

    public int addTask(int userId, String taskDescription, int dueDate, List<String> tags) {
        user2Task.putIfAbsent(userId, new TreeMap<>());
        user2Task.get(userId).put(dueDate, id);
        id2Tag.put(id, new HashSet<>(tags));
        id2Task.put(id, taskDescription);
        return id++;
    }

    public List<String> getAllTasks(int userId) {
        List<String> res = new ArrayList<>();
        if (!user2Task.containsKey(userId)) return res;
        TreeMap<Integer, Integer> map = user2Task.get(userId);
        for (int id : map.values()) res.add(id2Task.get(id));
        return res;
    }

    public List<String> getTasksForTag(int userId, String tag) {
        List<String> res = new ArrayList<>();
        if (!user2Task.containsKey(userId)) return res;
        TreeMap<Integer, Integer> map = user2Task.get(userId);
        for (int id : map.values()) {
            if (id2Tag.getOrDefault(id, new HashSet<>()).contains(tag)) res.add(id2Task.get(id));
        }
        return res;
    }

    public void completeTask(int userId, int taskId) {
        if (!user2Task.containsKey(userId)) return;
        TreeMap<Integer, Integer> map = user2Task.get(userId);
        boolean flag = false;
        for (int date : map.keySet()) {
            if (map.get(date) == taskId) {
                map.remove(date);
                flag = true;
                break;
            }
        }
        if (map.size() == 0) user2Task.remove(userId);
        else user2Task.put(userId, map);
        if (id2Tag.containsKey(taskId) && flag) id2Tag.remove(taskId);
        if (id2Task.containsKey(taskId) && flag) id2Task.remove(taskId);
    }
}
