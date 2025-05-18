package LC2700_3000;
import java.util.*;
public class LC2869_MinimumOperationstoCollectElements {
    /**
     * You are given an array nums of positive integers and an integer k.
     *
     * In one operation, you can remove the last element of the array and add it to your collection.
     *
     * Return the minimum number of operations needed to collect elements 1, 2, ..., k.
     *
     * Input: nums = [3,1,5,4,2], k = 2
     * Output: 4
     *
     * Input: nums = [3,1,5,4,2], k = 5
     * Output: 5
     *
     * Input: nums = [3,2,5,3,1], k = 3
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= nums.length
     * 1 <= k <= nums.length
     * The input is generated such that you can collect elements 1, 2, ..., k.
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minOperations(List<Integer> nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        int n = nums.size();
        for (int i = n - 1; i >= 0; i--) {
            int x = nums.get(i);
            if (x >= 1 && x <= k) set.add(x);
            if (set.size() == k) return n - i;
        }
        return -1;
    }

    // S2
    // time = O(n), space = O(1)
    public int minOperations2(List<Integer> nums, int k) {
        int n = nums.size();
        long u = (2L << k) - 2; // 1~k对应的集合
        long s = 0;
        for (int i = n - 1; i >= 0; i--) {
            s |= 1L << nums.get(i);
            if ((s & u) == u) return n - i; // u 是 s 的子集
        }
        return -1;
    }
}