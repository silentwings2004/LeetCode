package LC301_600;
import java.util.*;
public class LC523_ContinuousSubarraySum {
    /**
     * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a
     * continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is
     * also an integer.
     *
     * Input: [23, 2, 4, 6, 7],  k=6
     * Output: True
     *
     * Constraints:
     *
     * The length of the array won't exceed 10,000.
     * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
     *
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        for (int i = 2; i <= n; i++) {
            set.add(s[i - 2] % k);
            if (set.contains(s[i] % k)) return true;
        }
        return false;
    }

    // S2
    public boolean checkSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            int r = sum % k;
            if (map.containsKey(r)) {
                int len = i - map.get(r);
                if (len >= 2) return true;
            } else map.put(r, i);
        }
        return false;
    }
}

/**
 * 大方向：subarray的和转化为2个prefix sum之差，非常常见！
 * sum(s[i:j]) = presum[j] - presum[i - 1]   => O(1)
 * 2个前缀和之差 => 这两个前缀和要对k同余
 * 至少含有2个元素 => i >= j + 2
 * [0, 1, ...... j - 2]  j - 1   j
 */
