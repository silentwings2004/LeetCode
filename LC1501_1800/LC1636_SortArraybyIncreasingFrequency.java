package LC1501_1800;
import java.util.*;
public class LC1636_SortArraybyIncreasingFrequency {
    /**
     * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If
     * multiple values have the same frequency, sort them in decreasing order.
     *
     * Return the sorted array.
     *
     * Input: nums = [1,1,2,2,2,3]
     * Output: [3,1,1,2,2,2]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int[] frequencySort(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int x : nums) count.put(x, count.getOrDefault(x, 0) + 1);

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int x : count.keySet()) {
            map.putIfAbsent(count.get(x), new ArrayList<>());
            map.get(count.get(x)).add(x);
        }

        int[] res = new int[nums.length];
        int idx = 0;
        for (int x : map.keySet()) {
            Collections.sort(map.get(x), (o1, o2) -> o2 - o1);
            for (int v : map.get(x)) {
                for (int i = 0; i< x; i++) res[idx++] = v;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int[] frequencySort2(int[] nums) {
        int[] cnt = new int[201];
        for (int x : nums) cnt[x + 100]++;

        int kind = 0;
        for (int x : cnt) {
            if (x > 0) kind++;
        }

        int min = 210, minIdx = -1, j = 0;
        for (int k = 0; k < kind; k++) {
            for (int i = 0; i < 201; i++) {
                if (cnt[i] > 0 && cnt[i] <= min) {
                    min = cnt[i];
                    minIdx = i;
                }
            }
            while (cnt[minIdx] > 0 && j < nums.length) {
                nums[j++] = minIdx - 100;
                cnt[minIdx]--;
            }
            min = 210;
        }
        return nums;
    }
}
