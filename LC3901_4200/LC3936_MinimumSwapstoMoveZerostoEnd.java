package LC3901_4200;

public class LC3936_MinimumSwapstoMoveZerostoEnd {
    /**
     * You are given an integer array nums.
     *
     * In one operation, you can choose any two distinct indices i and j and swap nums[i] and nums[j].
     *
     * Return an integer denoting the minimum number of operations required to move all 0s to the end of the array.
     *
     * Input: nums = [0,1,0,3,12]
     * Output: 2
     *
     * Input: nums = [0,1,0,2]
     * Output: 1
     *
     * Input: nums = [1,2,0]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumSwaps(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int x : nums) {
            if (x == 0) cnt++;
        }
        int res = 0;
        for (int i = 1; i <= cnt; i++) {
            if (nums[n - i] != 0) res++;
        }
        return res;
    }

    // S2: 相向双指针
    // time = O(n), space = O(1)
    public int minimumSwaps2(int[] nums) {
        int n = nums.length, res = 0;
        int l = 0, r = n - 1;
        while (l < r) {
            if (nums[l] != 0) l++;
            else if (nums[r] == 0) r--;
            else {
                res++; // 交换 nums[l] 和 nums[r]
                l++;
                r--;
            }
        }
        return res;
    }
}