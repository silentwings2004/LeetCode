package LC2700_3000;
import java.util.*;
public class LC2784_CheckifArrayisGood {
    /**
     * You are given an integer array nums. We consider an array good if it is a permutation of an array base[n].
     *
     * base[n] = [1, 2, ..., n - 1, n, n] (in other words, it is an array of length n + 1 which contains 1 to n - 1
     * exactly once, plus two occurrences of n). For example, base[1] = [1, 1] and base[3] = [1, 2, 3, 3].
     *
     * Return true if the given array is good, otherwise return false.
     *
     * Note: A permutation of integers represents an arrangement of these numbers.
     *
     * Input: nums = [2, 1, 3]
     * Output: false
     *
     * Input: nums = [1, 3, 3, 2]
     * Output: true
     *
     * Input: nums = [1, 1]
     * Output: true
     *
     * Input: nums = [3, 4, 4, 1, 2, 1]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= num[i] <= 200
     * @param nums
     * @return
     */
    // S1: bucket sort
    // time = O(n), space = O(n)
    public boolean isGood(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[110];
        for (int x : nums) {
            if (x > n - 1) return false;
            cnt[x]++;
        }
        for (int i = 1; i <= n - 1; i++) {
            if (i < n - 1 && cnt[i] != 1) return false;
            if (i == n - 1 && cnt[i] != 2) return false;
        }
        return true;
    }

    // S2: sort
    // time = O(nlogn), space = O(logn)
    public boolean isGood2(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != i + 1) return false;
        }
        return nums[n - 1] == n - 1;
    }
}