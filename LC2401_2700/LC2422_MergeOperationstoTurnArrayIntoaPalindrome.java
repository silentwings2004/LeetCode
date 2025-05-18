package LC2401_2700;

public class LC2422_MergeOperationstoTurnArrayIntoaPalindrome {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * You can perform the following operation on the array any number of times:
     *
     * Choose any two adjacent elements and replace them with their sum.
     * For example, if nums = [1,2,3,1], you can apply one operation to make it [1,5,1].
     * Return the minimum number of operations needed to turn the array into a palindrome.
     *
     * Input: nums = [4,3,2,1,2,3,1]
     * Output: 2
     *
     * Input: nums = [1,2,3,4]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumOperations(int[] nums) {
        int n = nums.length, left = nums[0], right = nums[n - 1], cnt = 0;
        for (int i = 0, j = n - 1; i < j;) {
            if (left == right) {
                i++;
                j--;
                left = nums[i]; // 注意这里要记得更新left和right!
                right = nums[j];
            } else if (left < right) {
                i++;
                left += nums[i];
                cnt++;
            } else if (left > right) {
                j--;
                right += nums[j];
                cnt++;
            }
        }
        return cnt;
    }
}