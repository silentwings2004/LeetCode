package LC901_1200;

public class LC977_SquaresofaSortedArray {
    /**
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted
     * in non-decreasing order.
     *
     * Input: nums = [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     *
     * Input: nums = [-7,-3,2,3,11]
     * Output: [4,9,9,49,121]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * -10^4 <= nums[i] <= 10^4
     * nums is sorted in non-decreasing order.
     *
     * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution
     * using a different approach?
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0, j = n - 1, k = n - 1; i <= j;) {
            int a = nums[i] * nums[i], b = nums[j] * nums[j];
            if (a > b) {
                res[k--] = a;
                i++;
            } else {
                res[k--] = b;
                j--;
            }
        }
        return res;
    }
}