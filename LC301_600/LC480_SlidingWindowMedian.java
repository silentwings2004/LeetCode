package LC301_600;
import java.util.*;
public class LC480_SlidingWindowMedian {
    /**
     * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle
     * value. So the median is the mean of the two middle values.
     *
     * For examples, if arr = [2,3,4], the median is 3.
     * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
     * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from
     * the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding
     * window moves right by one position.
     *
     * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be
     * accepted.
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
     * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 10^5
     * -2^31 <= nums[i] <= 2^31 - 1
     * @param nums
     * @param k
     * @return
     */
    // S1: Two Heaps
    // time = O(n * k), space = O(k)
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> {
            if (o1 > o2) return -1;
            if (o1 == o2) return 0;
            return 1;
        });

        int n = nums.length, idx = 0;
        double[] res = new double[n - k + 1];
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            maxHeap.offer(x);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() - maxHeap.size() > 1) maxHeap.offer(minHeap.poll());
            if (i >= k - 1) {
                if (minHeap.size() == maxHeap.size()) {
                    res[idx++] = ((double) minHeap.peek() + maxHeap.peek()) / 2;
                } else res[idx++] = (double) minHeap.peek();
                if (!minHeap.remove(nums[idx - 1])) maxHeap.remove(nums[idx - 1]);
            }
        }
        return res;
    }

    // S2: Two Multi-Set
    // time = O(n * logk), space = O(k)
    public double[] medianSlidingWindow2(int[] nums, int k) {
        TreeMap<Integer, Integer> minMap = new TreeMap<>();
        TreeMap<Integer, Integer> maxMap = new TreeMap<>();
        int n = nums.length, idx = 0;
        int a = 0, b = 0;
        double[] res = new double[n - k + 1];
        for (int i = 0; i < n; i++) {
            add(maxMap, nums[i]);
            add(minMap, maxMap.lastKey());
            remove(maxMap, maxMap.lastKey());
            a++;
            if (a - b > 1) {
                add(maxMap, minMap.firstKey());
                remove(minMap, minMap.firstKey());
                a--;
                b++;
            }
            if (i >= k - 1) {
                if (a == b) res[idx++] = ((double) minMap.firstKey() + maxMap.lastKey()) / 2;
                else res[idx++] = minMap.firstKey();
                if (minMap.containsKey(nums[idx - 1])) {
                    remove(minMap, nums[idx - 1]);
                    a--;
                }
                else {
                    remove(maxMap, nums[idx - 1]);
                    b--;
                }
            }
        }
        return res;
    }

    private void add(TreeMap<Integer, Integer> map, int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
    }

    private void remove(TreeMap<Integer, Integer> map, int x) {
        map.put(x, map.get(x) - 1);
        if (map.get(x) == 0) map.remove(x);
    }
}
/**
 * 希望能够自动排序的容器，首选set或者multiset。
 * 对顶堆 维护下前一半和后一半的分界点
 */

