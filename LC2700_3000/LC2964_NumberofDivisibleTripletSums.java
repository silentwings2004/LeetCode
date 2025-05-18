package LC2700_3000;
import java.util.*;
public class LC2964_NumberofDivisibleTripletSums {
    /**
     * Given a 0-indexed integer array nums and an integer d, return the number of triplets (i, j, k) such that
     * i < j < k and (nums[i] + nums[j] + nums[k]) % d == 0.
     *
     * Input: nums = [3,3,4,7,8], d = 5
     * Output: 3
     *
     * Input: nums = [3,3,3,3], d = 3
     * Output: 4
     *
     * Input: nums = [3,3,3,3], d = 6
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^9
     * 1 <= d <= 10^9
     * @param nums
     * @param d
     * @return
     */
    // time = O(n^2), space = O(n)
    public int divisibleTripletCount(int[] nums, int d) {
        int n = nums.length, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                int r = (nums[i] + nums[j]) % d;
                res += map.getOrDefault((d - r) % d, 0);
            }
            int r = nums[i] % d;
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return res;
    }
}