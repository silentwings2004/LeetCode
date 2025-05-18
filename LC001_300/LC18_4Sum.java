package LC001_300;
import java.util.*;
public class LC18_4Sum {
    /**
     * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that
     * a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
     *
     * Notice that the solution set must not contain duplicate quadruplets
     *
     * Input: nums = [1,0,-1,0,-2,2], target = 0
     * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     *
     * Constraints:
     *
     * 0 <= nums.length <= 200
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= target <= 10^9
     * @param nums
     * @param target
     * @return
     */
    // time = O(n^3), space = O(1)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int k = j + 1, t = n - 1;
                while (k < t) {
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[t];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[t]));
                        while (k < t && nums[k] == nums[k + 1]) k++;
                        while (k < t && nums[t] == nums[t - 1]) t--;
                        k++;
                        t--;
                    } else if (sum < target) k++;
                    else t--;
                }
            }
        }
        return res;
    }

    // S2: similar to LC15
    // time = O(n^3), space = O(1)
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length;
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                for (int k = j + 1, u = n - 1; k < u; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) continue;
                    while (k < u - 1 && (long) nums[i] + nums[j] + nums[k] + nums[u - 1] >= target) u--;
                    if ((long) nums[i] + nums[j] + nums[k] + nums[u] == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[u]));
                    }
                }
            }
        }
        return res;
    }
}