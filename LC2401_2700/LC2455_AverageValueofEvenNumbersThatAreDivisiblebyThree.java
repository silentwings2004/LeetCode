package LC2401_2700;

public class LC2455_AverageValueofEvenNumbersThatAreDivisiblebyThree {
    /**
     * Given an integer array nums of positive integers, return the average value of all even integers that are
     * divisible by 3.
     *
     * Note that the average of n elements is the sum of the n elements divided by n and rounded down to the nearest
     * integer.
     *
     * Input: nums = [1,3,6,10,12,15]
     * Output: 9
     *
     * Input: nums = [1,2,4,7,10]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int averageValue(int[] nums) {
        int sum = 0, cnt = 0;
        for (int x : nums) {
            if (x % 6 == 0) {
                cnt++;
                sum += x;
            }
        }
        return cnt == 0 ? 0 : sum / cnt;
    }
}
