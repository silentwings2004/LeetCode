package LC601_900;
import java.util.*;
public class LC731_MyCalendarII {
    /**
     * You are implementing a program to use as your calendar. We can add a new event if adding the event will not
     * cause a triple booking.
     *
     * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all
     * the three events.).
     *
     * The event can be represented as a pair of integers start and end that represents a booking on the half-open
     * interval [start, end), the range of real numbers x such that start <= x < end.
     *
     * Implement the MyCalendarTwo class:
     *
     * MyCalendarTwo() Initializes the calendar object.
     * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without
     * causing a triple booking. Otherwise, return false and do not add the event to the calendar.
     *
     * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
     * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
     * Output
     * [null, true, true, true, false, true, true]
     *
     * Constraints:
     *
     * 0 <= start < end <= 10^9
     * At most 1000 calls will be made to book.
     */
    // S1: List
    // time = O(nlogn), space = O(n)
    private List<int[]> events;
    public LC731_MyCalendarII() {
        events = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        List<int[]> temp = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            if (!(events.get(i)[1] <= start || events.get(i)[0] >= end)) {
                temp.add(new int[]{events.get(i)[0], events.get(i)[1]});
            }
        }
        Collections.sort(temp, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        for (int i = 1; i < temp.size(); i++) {
            if (temp.get(i)[0] < temp.get(i - 1)[1]) return false;
        }
        events.add(new int[]{start, end});
        return true;
    }

    // S2: TreeMap
    // time = O(nlogn), space = O(n)
    class MyCalendarTwo {
        TreeMap<Integer, Integer> map;
        public MyCalendarTwo() {
            map = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);

            int sum = 0;
            for (int v : map.values()) {
                sum += v;
                if (sum >= 3) {
                    map.put(start, map.get(start) - 1);
                    map.put(end, map.get(end) + 1);
                    return false;
                }
            }
            return true;
        }
    }
}
/**
 * 允许插入2次
 * 与falling squre类似
 * 这里考察差分
 */