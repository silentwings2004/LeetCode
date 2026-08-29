package LC3901_4200;
import java.util.*;
public class LC4032_LongestSubarrayWithatMostKDistinctPrimeFactors {
    /**
     * You are given an integer array nums consisting of positive integers and an integer k.
     *
     * The prime factor set of a subarray is the union of the distinct prime factors of all its elements.
     *
     * Return the length of the longest subarray whose prime factor set contains at most k distinct prime factors. If
     * no such subarray exists, return 0.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     *
     * Input: nums = [7,6,10,12,11], k = 3
     * Output: 3
     *
     * Input: nums = [4,6,9,18], k = 4
     * Output: 4
     *
     * Input: nums = [6,10,15], k = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 2 <= nums[i] <= 10^5
     * 1 <= k <= 10^4
     * @param nums
     * @param k
     * @return
     */
    // time = O(n * sqrt(mx)), space = O(n * log(mx))  mx = max{nums}
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        List<Integer>[] q = new List[n];
        for (int i = 0; i < n; i++) q[i] = get(nums[i]);

        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0, j = 0, t = 0; i < n; i++) {
            for (int x : q[i]) {
                int cnt = map.getOrDefault(x, 0);
                if (cnt == 0) t++;
                map.put(x, cnt + 1);
            }
            while (t > k) {
                for (int x : q[j++]) {
                    int cnt = map.get(x) - 1;
                    if (cnt == 0) {
                        t--;
                        map.remove(x);
                    } else map.put(x, cnt);
                }
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    private List<Integer> get(int x) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                res.add(i);
                while (x % i == 0) x /= i;
            }
        }
        if (x > 1) res.add(x);
        return res;
    }
}