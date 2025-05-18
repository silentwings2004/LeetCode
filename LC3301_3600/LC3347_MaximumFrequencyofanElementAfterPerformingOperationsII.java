package LC3301_3600;

import java.util.*;

public class LC3347_MaximumFrequencyofanElementAfterPerformingOperationsII {
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
     * Output: 2
     *
     * Input: nums = [5,11,20,20], k = 5, numOperations = 1
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= k <= 10^9
     * 0 <= numOperations <= nums.length
     * @param nums
     * @param k
     * @param numOperations
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int maxFrequency(int[] nums, int k, int numOperations) {
        HashMap<Integer, Integer> cnt = new HashMap<>();
        TreeMap<Integer, Integer> diff = new TreeMap<>();
        for (int x : nums) {
            cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            diff.put(x, diff.getOrDefault(x, 0) + 0);
            diff.put(x - k, diff.getOrDefault(x - k, 0) + 1);
            diff.put(x + k + 1, diff.getOrDefault(x + k + 1, 0) - 1);
        }

        int res = 0, sd = 0;
        for (int x : diff.keySet()) {
            sd += diff.get(x);
            res = Math.max(res, Math.min(sd, numOperations + cnt.getOrDefault(x, 0)));
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

    // S3: sliding window
    // time = O(nlogn), space = O(logn)
    public int maxFrequency3(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0, cnt = 0, l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            if (i < n - 1 && nums[i] == nums[i + 1]) continue;
            while (nums[l] < nums[i] - k) l++;
            while (r < n && nums[r] <= nums[i] + k) r++;
            res = Math.max(res, Math.min(r - l, numOperations + cnt));
            cnt = 0;
        }
        if (res >= numOperations) return res;

        for (int i = 0, j = 0; i < n; i++) {
            while (nums[j] < nums[i] - k * 2) j++;
            res = Math.max(res,  i - j + 1);
        }
        return Math.min(res, numOperations);
    }
}