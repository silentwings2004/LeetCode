package LC2700_3000;
import java.util.*;
public class LC2831_FindtheLongestEqualSubarray {
    /**
     * You are given a 0-indexed integer array nums and an integer k.
     *
     * A subarray is called equal if all of its elements are equal. Note that the empty subarray is an equal subarray.
     *
     * Return the length of the longest possible equal subarray after deleting at most k elements from nums.
     *
     * A subarray is a contiguous, possibly empty sequence of elements within an array.
     *
     * Input: nums = [1,3,2,3,1,3], k = 3
     * Output: 3
     *
     * Input: nums = [1,1,2,2,1,1], k = 2
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= nums.length
     * 0 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    HashMap<Integer, List<Integer>> map;
    public int longestEqualSubarray(List<Integer> nums, int k) {
        int n = nums.size();
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(nums.get(i), new ArrayList<>());
            map.get(nums.get(i)).add(i);
        }

        int l = 1, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid, k)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private boolean check(int mid, int k) {
        for (int key : map.keySet()) {
            if (map.get(key).size() < mid) continue;
            List<Integer> q = map.get(key);
            int m = q.size();
            for (int i = 0; i + mid - 1 < m; i++) {
                int a = q.get(i), b = q.get(i + mid - 1);
                int len = b - a + 1;
                if (len - mid <= k) return true;
            }
        }
        return false;
    }

    // S2: two pointers
    // time = O(n), space = O(n)
    public int longestEqualSubarray2(List<Integer> nums, int k) {
        int n = nums.size();
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) g[nums.get(i)].add(i);
        int res = 0;
        for (int u = 1; u <= n; u++) {
            List<Integer> q = g[u];
            for (int i = 0, j = 0; i < q.size(); i++) {
                while ((q.get(i) - q.get(j)) - (i - j) > k) j++;
                res = Math.max(res, i - j + 1);
            }
        }
        return res;
    }
}