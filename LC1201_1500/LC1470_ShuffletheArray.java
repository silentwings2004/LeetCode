package LC1201_1500;

public class LC1470_ShuffletheArray {
    /**
     * Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
     *
     * Return the array in the form [x1,y1,x2,y2,...,xn,yn].
     *
     * Input: nums = [2,5,1,3,4,7], n = 3
     * Output: [2,3,5,4,1,7]
     *
     * Constraints:
     *
     * 1 <= n <= 500
     * nums.length == 2n
     * 1 <= nums[i] <= 10^3
     * @param nums
     * @param n
     * @return
     */
    // time - O(n), space = O(1)
    public int[] shuffle(int[] nums, int n) {
        int[] res = new int[n * 2];
        for (int i = 0, j = n, k = 0; i < n; i++, j++, k += 2) {
            res[k] = nums[i];
            res[k + 1] = nums[j];
        }
        return res;
    }
}
