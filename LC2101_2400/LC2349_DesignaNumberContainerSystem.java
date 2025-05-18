package LC2101_2400;
import java.util.*;
public class LC2349_DesignaNumberContainerSystem {
    /**
     * Design a number container system that can do the following:
     *
     * Insert or Replace a number at the given index in the system.
     * Return the smallest index for the given number in the system.
     * Implement the NumberContainers class:
     *
     * NumberContainers() Initializes the number container system.
     * void change(int index, int number) Fills the container at index with the number. If there is already a number at
     * that index, replace it.
     * int find(int number) Returns the smallest index for the given number, or -1 if there is no index that is filled
     * by number in the system.
     *
     * Input
     * ["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
     * [[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
     * Output
     * [null, -1, null, null, null, null, 1, null, 2]
     *
     * Constraints:
     *
     * 1 <= index, number <= 10^9
     * At most 105 calls will be made in total to change and find.
     */
    HashMap<Integer, Integer> map;
    HashMap<Integer, TreeSet<Integer>> cnt;
    public LC2349_DesignaNumberContainerSystem() {
        map = new HashMap<>();
        cnt = new HashMap<>();
    }
    // time = O(nlogn), space = O(n)
    public void change(int index, int number) {
        if (map.containsKey(index)) {
            int v = map.get(index);
            cnt.get(v).remove(index);
            if (cnt.get(v).size() == 0) cnt.remove(v);
        }
        map.put(index, number);
        cnt.putIfAbsent(number, new TreeSet<>());
        cnt.get(number).add(index);
    }
    // time = O(nlogn), space = O(n)
    public int find(int number) {
        if (cnt.containsKey(number)) return cnt.get(number).first();
        return -1;
    }
}