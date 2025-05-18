package LC1201_1500;
import java.util.*;
public class LC1429_FirstUniqueNumber {
    /**
     * You have a queue of integers, you need to retrieve the first unique integer in the queue.
     *
     * Implement the FirstUnique class:
     *
     * FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
     * int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no
     * such integer.
     * void add(int value) insert value to the queue.
     *
     * Input:
     * ["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
     * [[[2,3,5]],[],[5],[],[2],[],[3],[]]
     * Output:
     * [null,2,null,2,null,3,null,-1]
     *
     * Input:
     * ["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
     * [[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
     * Output:
     * [null,-1,null,null,null,null,null,17]
     *
     * Input:
     * ["FirstUnique","showFirstUnique","add","showFirstUnique"]
     * [[[809]],[],[809],[]]
     * Output:
     * [null,809,null,-1]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^8
     * 1 <= value <= 10^8
     * At most 50000 calls will be made to showFirstUnique and add.
     * @param nums
     */
    // S1
    TreeMap<Integer, Integer> map;
    HashMap<Integer, Integer> idxMap, cnt;
    int idx;
    // time = O(nlogn), space = O(n)
    public LC1429_FirstUniqueNumber(int[] nums) {
        map = new TreeMap<>();
        idxMap = new HashMap<>();
        cnt = new HashMap<>();
        for (int x : nums) map.put(x, cnt.getOrDefault(x, 0) + 1);
        for (int x : nums) {
            if (cnt.get(x) == 1) {
                map.put(idx, x);
                idxMap.put(x, idx);
                idx++;
            }
        }
    }
    // time = O(logk), space = O(k)
    public int showFirstUnique() {
        return map.size() > 0 ? map.get(map.firstKey()) : -1;
    }
    // time = O(logk), space = O(n)
    public void add(int value) {
        cnt.put(value, cnt.getOrDefault(value, 0) + 1);
        if (cnt.get(value) == 1) {
            map.put(idx, value);
            idxMap.put(value, idx);
            idx++;
        } else if (cnt.get(value) == 2) {
            int pos = idxMap.get(value);
            idxMap.remove(value);
            map.remove(pos);
        }
    }

    // S2
    class FirstUnique {
        Queue<Integer> q;
        HashMap<Integer, Integer> map;
        // time = O(n), space = O(n)
        public FirstUnique(int[] nums) {
            q = new LinkedList<>();
            map = new HashMap<>();
            for (int x : nums) add(x);
        }
        // time = O(1), space = O(n)
        public int showFirstUnique() {
            if (q.isEmpty()) return -1;
            while (!q.isEmpty() && map.get(q.peek()) > 1) q.poll();
            return q.isEmpty() ? -1 : q.peek();
        }
        // time = O(1), space = O(n)
        public void add(int value) {
            int cnt = map.getOrDefault(value, 0);
            if (cnt == 0) {
                q.offer(value);
                map.put(value, 1);
            } else if (cnt == 1) map.put(value, 2);
        }
    }
}
