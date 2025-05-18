package LC3001_3300;
import java.util.*;
public class LC3255_FindthePowerofKSizeSubarraysII {
    /**
     * You are given an array of integers nums of length n and a positive integer k.
     *
     * The power of an array is defined as:
     *
     * Its maximum element if all of its elements are consecutive and sorted in ascending order.
     * -1 otherwise.
     * You need to find the power of all subarrays of nums of size k.
     *
     * Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].
     *
     * Input: nums = [1,2,3,4,3,2,5], k = 3
     * Output: [3,4,-1,-1,-1]
     *
     * Input: nums = [2,2,2,2,2], k = 4
     * Output: [-1,-1]
     *
     * Input: nums = [3,2,3,2,3,2], k = 2
     * Output: [-1,3,-1,3,-1]
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] - nums[j - 1] == 1) j++;
            map.put(i, j - 1);
            i = j - 1;
        }

        for (int i = 0; i + k - 1 < n; i++) {
            int j = i + k - 1;
            Integer fk = map.floorKey(i);
            if (fk == null || map.get(fk) < j) res[i] = -1;
            else res[i] = nums[j];
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int[] resultsArray2(int[] nums, int k) {
        int n = nums.length, cnt = 0;
        int[] res = new int[n - k + 1];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] == nums[i - 1] + 1) cnt++;
            else cnt = 1;
            if (cnt >= k) res[i - k + 1] = nums[i];
        }
        return res;
    }
}