package LC3301_3600;
import java.util.*;
public class LC3369_DesignanArrayStatisticsTracker {
    /**
     * Design a data structure that keeps track of the values in it and answers some queries regarding their mean,
     * median, and mode.
     *
     * Implement the StatisticsTracker class.
     *
     * StatisticsTracker(): Initialize the StatisticsTracker object with an empty array.
     * void addNumber(int number): Add number to the data structure.
     * void removeFirstAddedNumber(): Remove the earliest added number from the data structure.
     * int getMean(): Return the floored mean of the numbers in the data structure.
     * int getMedian(): Return the median of the numbers in the data structure.
     * int getMode(): Return the mode of the numbers in the data structure. If there are multiple modes, return the
     * smallest one.
     *
     * Note:
     *
     * The mean of an array is the sum of all the values divided by the number of values in the array.
     * The median of an array is the middle element of the array when it is sorted in non-decreasing order. If there
     * are two choices for a median, the larger of the two values is taken.
     * The mode of an array is the element that appears most often in the array.
     *
     * Input:
     * ["StatisticsTracker", "addNumber", "addNumber", "addNumber", "addNumber", "getMean", "getMedian", "getMode",
     * "removeFirstAddedNumber", "getMode"]
     * [[], [4], [4], [2], [3], [], [], [], [], []]
     *
     * Output:
     * [null, null, null, null, null, 3, 4, 4, null, 2]
     *
     * Input:
     * ["StatisticsTracker", "addNumber", "addNumber", "getMean", "removeFirstAddedNumber", "addNumber", "addNumber",
     * "removeFirstAddedNumber", "getMedian", "addNumber", "getMode"]
     * [[], [9], [5], [], [], [5], [6], [], [], [8], []]
     *
     * Output:
     * [null, null, null, 7, null, null, null, null, 6, null, 5]
     *
     * Constraints:
     *
     * 1 <= number <= 10^9
     * At most, 105 calls will be made to addNumber, removeFirstAddedNumber, getMean, getMedian, and getMode in total.
     * removeFirstAddedNumber, getMean, getMedian, and getMode will be called only if there is at least one element in
     * the data structure.
     */
    TreeSet<int[]> minh, maxh;
    Queue<int[]> q;
    long s;
    int n;
    HashMap<Integer, Integer> cnt;
    TreeSet<int[]> ms;
    public LC3369_DesignanArrayStatisticsTracker() {
        minh = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        maxh = new TreeSet<>((o1, o2) -> o2[0] != o1[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
        ms = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
        q = new LinkedList<>();
        cnt = new HashMap<>();
    }
    // time = O(logn), space = O(n)
    public void addNumber(int number) {
        add(number, n);
        rebalance();
        q.offer(new int[]{number, n});
        s += number;
        n++;
        cnt.put(number, cnt.getOrDefault(number, 0) + 1);
        if (cnt.get(number) > 1) ms.remove(new int[]{cnt.get(number) - 1, number});
        ms.add(new int[]{cnt.get(number), number});
    }
    // time = O(logn), space = O(n)
    public void removeFirstAddedNumber() {
        int[] t = q.poll();
        int x = t[0], idx = t[1];
        s -= x;
        ms.remove(new int[]{cnt.get(x), x});
        cnt.put(x, cnt.get(x) - 1);
        if (cnt.get(x) == 0) cnt.remove(x);
        else ms.add(new int[]{cnt.get(x), x});
        if (minh.contains(t)) minh.remove(t);
        else maxh.remove(t);
        rebalance();
    }
    // time = O(1), space = O(n)
    public int getMean() {
        int sz = minh.size() + maxh.size();
        return (int)(s / sz);
    }
    // time = O(logn), space = O(n)
    public int getMedian() {
        int sz = minh.size() + maxh.size();
        if (sz % 2 == 1) return minh.first()[0];
        return Math.max(minh.first()[0], maxh.first()[0]);
    }
    // time = O(logn), space = O(n)
    public int getMode() {
        return ms.first()[1];
    }

    private void add(int x, int idx) {
        maxh.add(new int[]{x, idx});
        minh.add(maxh.first());
        maxh.remove(maxh.first());
    }

    private void rebalance() {
        while (minh.size() < maxh.size()) {
            minh.add(maxh.first());
            maxh.remove(maxh.first());
        }
        if (minh.size() - maxh.size() > 1) {
            maxh.add(minh.first());
            minh.remove(minh.first());
        }
    }
}