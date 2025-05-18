package LC3301_3600;
import java.util.*;
public class LC3346_MaximumFrequencyofanElementAfterPerformingOperationsI {
    /**
     * You are given an integer array nums and two integers k and numOperations.
     *
     * You must perform an operation numOperations times on nums, where in each operation you:
     *
     * Select an index i that was not selected in any previous operations.
     * Add an integer in the range [-k, k] to nums[i].
     * Return the maximum possible frequency of any element in nums after performing the operations.
     *
     * The frequency of an element x is the number of times it occurs in the array.
     *
     * Input: nums = [1,4,5], k = 1, numOperations = 2
     *
     * Output: 2
     *
     * Input: nums = [5,11,20,20], k = 5, numOperations = 1
     *
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 0 <= k <= 10^5
     * 0 <= numOperations <= nums.length
     * @param nums
     * @param k
     * @param numOperations
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int maxFrequency(int[] nums, int k, int numOperations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        HashMap<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            map.put(x - k, map.getOrDefault(x - k, 0) + 1);
            map.put(x + k + 1, map.getOrDefault(x + k + 1, 0) - 1);
            map.putIfAbsent(x, 0);
        }

        int t = 0, res = 0;
        for (int x : map.keySet()) {
            t += map.get(x);
            res = Math.max(res, Math.min(numOperations + cnt.getOrDefault(x, 0), t));
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int maxFrequency2(int[] nums, int k, int numOperations) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<long[]> q = new ArrayList<>();
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            q.add(new long[]{x, 1});
            q.add(new long[]{x + k, 0});
            q.add(new long[]{1L * x + k * 2, 0});
        }
        Collections.sort(q, (o1, o2) -> Long.compare(o1[0], o2[0]));
        int n = q.size(), res = 0;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + (int)q.get(i - 1)[1];
        for (int i = 0, j = 0; i < n; i++) {
            long x = q.get(i)[0];
            while (j < i && q.get(j)[0] + k * 2 < x) j++;
            int cnt = s[i + 1] - s[j];
            cnt -= map.getOrDefault((int)(x - k), 0);
            if (cnt <= numOperations) res = Math.max(res, cnt + map.getOrDefault((int)(x - k), 0));
            else res = Math.max(res, map.getOrDefault((int)(x - k), 0) + numOperations);
        }
        return res;
    }

    // S3
    // time = O(nlogn), space = O(n)
    public int maxFrequency3(int[] nums, int k, int numOperations) {
        int n = nums.length, res = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        n = map.size();
        int[] b = new int[n];
        int idx = 0;
        for (int x : map.keySet()) b[idx++] = x;
        Arrays.sort(b);
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + map.get(b[i - 1]);

        for (int x = 1; x <= 100000; x++) {
            int l = find(b, x - k), r = find2(b, x + k), d = 0;
            if (map.containsKey(x)) d = Math.min(numOperations, s[r + 1] - s[l] - map.get(x)) + map.get(x);
            else d = Math.min(numOperations, s[r + 1] - s[l]);
            res = Math.max(res, d);
        }
        return res;
    }

    private int find(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= t) r = mid;
            else l = mid + 1;
        }
        return nums[r] >= t ? r : r + 1;
    }

    private int find2(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= t) l = mid;
            else r = mid - 1;
        }
        return nums[r] <= t ? r : r - 1;
    }
}