package LC3001_3300;
import java.util.*;
public class LC3092_MostFrequentIDs {
    /**
     * The problem involves tracking the frequency of IDs in a collection that changes over time. You have two integer
     * arrays, nums and freq, of equal length n. Each element in nums represents an ID, and the corresponding element
     * in freq indicates how many times that ID should be added to or removed from the collection at each step.
     *
     * Addition of IDs: If freq[i] is positive, it means freq[i] IDs with the value nums[i] are added to the collection
     * at step i.
     * Removal of IDs: If freq[i] is negative, it means -freq[i] IDs with the value nums[i] are removed from the
     * collection at step i.
     * Return an array ans of length n, where ans[i] represents the count of the most frequent ID in the collection
     * after the ith step. If the collection is empty at any step, ans[i] should be 0 for that step.
     *
     * Input: nums = [2,3,2,1], freq = [3,2,-3,1]
     * Output: [3,3,2,2]
     *
     * Input: nums = [5,5,3], freq = [2,-2,1]
     * Output: [2,0,1]
     *
     * Constraints:
     *
     * 1 <= nums.length == freq.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * -105 <= freq[i] <= 10^5
     * freq[i] != 0
     * The input is generated such that the occurrences of an ID will not be negative in any step.
     * @param nums
     * @param freq
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    public long[] mostFrequentIDs(int[] nums, int[] freq) {
        long[] cnt = new long[N];
        int n = nums.length;
        TreeMap<Long, Integer> map = new TreeMap<>();
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            int x = nums[i], f = freq[i];
            if (map.containsKey(cnt[x])) {
                map.put(cnt[x], map.get(cnt[x]) - 1);
                if (map.get(cnt[x]) == 0) map.remove(cnt[x]);
            }
            cnt[x] += f;
            if (cnt[x] > 0) map.put(cnt[x], map.getOrDefault(cnt[x], 0) + 1);
            if (map.size() > 0) res[i] = map.lastKey();
        }
        return res;
    }

    // S2: heap
    // time = O(nlogn), space = O(n)
    public long[] mostFrequentIDs2(int[] nums, int[] freq) {
        int n = nums.length;
        long[] res = new long[n];
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o2[0], o1[0]));
        long[] cnt = new long[N];
        for (int i = 0; i < n; i++) {
            int x = nums[i], f = freq[i];
            cnt[x] += f;
            pq.offer(new long[]{cnt[x], x});
            while (pq.peek()[0] != cnt[(int)pq.peek()[1]]) pq.poll();
            res[i] = pq.peek()[0];
        }
        return res;
    }
}