package LC2401_2700;

public class LC2562_FindtheArrayConcatenationValue {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * The concatenation of two numbers is the number formed by concatenating their numerals.
     *
     * For example, the concatenation of 15, 49 is 1549.
     * The concatenation value of nums is initially equal to 0. Perform this operation until nums becomes empty:
     *
     * If there exists more than one number in nums, pick the first element and last element in nums respectively and
     * add the value of their concatenation to the concatenation value of nums, then delete the first and last element
     * from nums.
     * If one element exists, add its value to the concatenation value of nums, then delete it.
     *
     * Return the concatenation value of the nums.
     *
     * Input: nums = [7,52,2,4]
     * Output: 596
     *
     * Input: nums = [5,14,13,8,12]
     * Output: 673
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^4
     * @param nums
     * @return
     */
    // S1
    // time = O(n * k), space = O(k)
    public long findTheArrayConcVal(int[] nums) {
        long sum = 0;
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (i != j) sum += Integer.parseInt(nums[i] + "" + nums[j]);
            else sum += nums[i];
            i++;
            j--;
        }
        return sum;
    }

    // S2
    // time = O(nlogk), space = O(1)
    public long findTheArrayConcVal2(int[] nums) {
        long res = 0;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            res += nums[i] * getLen(nums[j]) + nums[j];
            i++;
            j--;
        }
        if (i == j) res += nums[i];
        return res;
    }

    private int getLen(int x) {
        int p = 1;
        while (x > 0) {
            x /= 10;
            p *= 10;
        }
        return p;
    }
}