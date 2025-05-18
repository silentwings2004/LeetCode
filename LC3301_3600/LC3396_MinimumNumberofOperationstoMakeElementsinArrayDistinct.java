package LC3301_3600;

public class LC3396_MinimumNumberofOperationstoMakeElementsinArrayDistinct {
    /**
     * You are given an integer array nums. You need to ensure that the elements in the array are distinct. To achieve
     * this, you can perform the following operation any number of times:
     *
     * Remove 3 elements from the beginning of the array. If the array has fewer than 3 elements, remove all remaining
     * elements.
     * Note that an empty array is considered to have distinct elements. Return the minimum number of operations needed
     * to make the elements in the array distinct.
     *
     * Input: nums = [1,2,3,4,2,3,3,5,7]
     * Output: 2
     *
     * Input: nums = [4,5,6,4,4]
     * Output: 2
     *
     * Input: nums = [6,7,8,9]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumOperations(int[] nums) {
        int n = nums.length, res = 0;
        int[] cnt = new int[110];
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            cnt[x]++;
            if (cnt[x] > 1) {
                res = (i + 1 + 2) / 3;
                break;
            }
        }
        return res;
    }
}