package LC2401_2700;
import java.util.*;
public class LC2526_FindConsecutiveIntegersfromaDataStream {
    /**
     * For a stream of integers, implement a data structure that checks if the last k integers parsed in the stream are
     * equal to value.
     *
     * Implement the DataStream class:
     *
     * DataStream(int value, int k) Initializes the object with an empty integer stream and the two integers value and k.
     * boolean consec(int num) Adds num to the stream of integers. Returns true if the last k integers are equal to
     * value, and false otherwise. If there are less than k integers, the condition does not hold true, so returns false.
     *
     * Input
     * ["DataStream", "consec", "consec", "consec", "consec"]
     * [[4, 3], [4], [4], [4], [3]]
     * Output
     * [null, false, false, true, false]
     *
     * Constraints:
     *
     * 1 <= value, num <= 10^9
     * 1 <= k <= 10^5
     * At most 105 calls will be made to consec.
     */
    // S1
    // time = O(1), space = O(1)
    int v, k, cnt;
    public LC2526_FindConsecutiveIntegersfromaDataStream(int value, int k) {
        v = value;
        this.k = k;
        cnt = 0;
    }

    public boolean consec(int num) {
        if (v == num) cnt++;
        else cnt = 0; // 只track最后出现的连续value的个数!
        return cnt >= k;
    }

    // S2
    // time = O(1), space = O(k)
    class DataStream {
        Queue<Integer> queue;
        HashMap<Integer, Integer> map;
        int v, k;
        public DataStream(int value, int k) {
            this.v = value;
            this.k = k;
            queue = new LinkedList<>();
            map = new HashMap<>();
        }

        public boolean consec(int num) {
            queue.offer(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (queue.size() > k) {
                int t = queue.poll();
                map.put(t, map.get(t) - 1);
                if (map.get(t) == 0) map.remove(t);
            }
            return map.getOrDefault(v, 0) == k;
        }
    }
}