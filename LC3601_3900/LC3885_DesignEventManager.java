package LC3601_3900;
import java.util.*;
public class LC3885_DesignEventManager {
    /**
     * You are given an initial list of events, where each event has a unique eventId and a priority.
     *
     * Implement the EventManager class:
     *
     * EventManager(int[][] events) Initializes the manager with the given events, where events[i] = [eventIdi,
     * priorityi]
     * void updatePriority(int eventId, int newPriority) Updates the priority of the active event with id eventId to
     * newPriority.
     * int pollHighest() Removes and returns the eventId of the active event with the highest priority. If multiple
     * active events have the same priority, return the smallest eventId among them. If there are no active events,
     * return -1.
     * An event is called active if it has not been removed by pollHighest().
     *
     * Input:
     * ["EventManager", "pollHighest", "updatePriority", "pollHighest", "pollHighest"]
     * [[[[5, 7], [2, 7], [9, 4]]], [], [9, 7], [], []]
     * Output:
     * [null, 2, null, 5, 9]
     *
     * Input:
     * ["EventManager", "pollHighest", "pollHighest", "pollHighest"]
     * [[[[4, 1], [7, 2]]], [], [], []]
     * Output:
     * [null, 7, 4, -1]
     *
     * Constraints:
     *
     * 1 <= events.length <= 10^5
     * events[i] = [eventId, priority]
     * 1 <= eventId <= 10^9
     * 1 <= priority <= 10^9
     * All the values of eventId in events are unique.
     * 1 <= newPriority <= 10^9
     * For every call to updatePriority, eventId refers to an active event.
     * At most 105 calls in total will be made to updatePriority and pollHighest.
     * @param events
     */
    // S1: TreeSet
    TreeSet<int[]> set;
    HashMap<Integer, Integer> map;
    // time = O(nlogn), space = O(n)
    public LC3885_DesignEventManager(int[][] events) {
        set = new TreeSet<>((o1, o2) -> o1[1] != o2[1] ? o2[1] - o1[1] : o1[0] - o2[0]);
        map = new HashMap<>();
        for (int[] e : events) {
            set.add(e);
            map.put(e[0], e[1]);
        }
    }
    // time = O(logn), space = O(n)
    public void updatePriority(int eventId, int newPriority) {
        int p = map.get(eventId);
        set.remove(new int[]{eventId, p});
        set.add(new int[]{eventId, newPriority});
        map.put(eventId, newPriority);
    }
    // time = O(logn), space = O(n)
    public int pollHighest() {
        if (set.isEmpty()) return -1;
        int[] t = set.first();
        set.remove(t);
        map.remove(t[0]);
        return t[0];
    }

    // S2: Lazy PQ
    // time = O(nlogn), space = O(n)
    class EventManager {
        HashMap<Integer, Integer> map;
        PriorityQueue<int[]> pq;
        public EventManager(int[][] events) {
            map = new HashMap<>();
            pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
            for (int[] e : events) {
                int id = e[0], p = e[1];
                map.put(id, p);
                pq.offer(new int[]{p, id});
            }
        }

        public void updatePriority(int eventId, int newPriority) {
            map.put(eventId, newPriority);
            pq.offer(new int[]{newPriority, eventId});
        }

        public int pollHighest() {
            while (!pq.isEmpty()) {
                int[] e = pq.poll();
                int p = e[0], id = e[1];
                if (map.getOrDefault(id, -1) == p) {
                    map.remove(id);
                    return id;
                }
            }
            return -1;
        }
    }
}