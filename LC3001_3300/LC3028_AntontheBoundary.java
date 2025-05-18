package LC3001_3300;

public class LC3028_AntontheBoundary {
    /**
     * An ant is on a boundary. It sometimes goes left and sometimes right.
     *
     * You are given an array of non-zero integers nums. The ant starts reading nums from the first element of it to
     * its end. At each step, it moves according to the value of the current element:
     *
     * If nums[i] < 0, it moves left by -nums[i] units.
     * If nums[i] > 0, it moves right by nums[i] units.
     * Return the number of times the ant returns to the boundary.
     *
     * Notes:
     *
     * There is an infinite space on both sides of the boundary.
     * We check whether the ant is on the boundary only after it has moved |nums[i]| units. In other words, if the ant
     * crosses the boundary during its movement, it does not count.
     *
     * Input: nums = [2,3,-5]
     * Output: 1
     *
     * Input: nums = [3,2,-3,-4]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * -10 <= nums[i] <= 10
     * nums[i] != 0
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int returnToBoundaryCount(int[] nums) {
        int res = 0, d = 0;
        for (int x : nums) {
            d += x;
            if (d == 0) res++;
        }
        return res;
    }
}