package LC2401_2700;
import java.util.*;
public class LC2615_SumofDistances {
    /**
     * You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is
     * the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.
     *
     * Return the array arr.
     *
     * Input: nums = [1,3,1,1,2]
     * Output: [5,0,3,4,0]
     *
     * Input: nums = [0,5,3]
     * Output: [0,0,0]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long[] distance(int[] nums) {
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(i);
        }

        long[] arr = new long[n];
        for (int key : map.keySet()) {
            int m = map.get(key).size();
            if (m == 1) continue;
            List<Integer> q = map.get(key);
            long[] s = new long[m + 1];
            for (int i = 1; i <= m; i++) s[i] = s[i - 1] + q.get(i - 1);
            for (int i = 1; i <= m; i++) {
                int x = q.get(i - 1);
                long a = (long)x * (i - 1), b = s[i] - x;
                long c = (long)x * (m - i), d = s[m] - s[i];
                arr[x] = a - b + d - c;
            }
        }
        return arr;
    }
}