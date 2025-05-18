package LC301_600;

public class LC553_OptimalDivision {
    /**
     * You are given an integer array nums. The adjacent integers in nums will perform the float division.
     *
     * For example, for nums = [2,3,4], we will evaluate the expression "2/3/4".
     * However, you can add any number of parenthesis at any position to change the priority of operations. You want to
     * add these parentheses such the value of the expression after the evaluation is maximum.
     *
     * Return the corresponding expression that has the maximum value in string format.
     *
     * Note: your expression should not contain redundant parenthesis.
     *
     * Input: nums = [1000,100,10,2]
     * Output: "1000/(100/10/2)"
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10
     * 2 <= nums[i] <= 1000
     * There is only one optimal division for the given iput.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        if (n == 1) return String.valueOf(nums[0]);
        if (n == 2) return nums[0] + "/" + nums[1];

        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append('/').append('(');
        for (int i = 1; i < n - 1; i++) {
            sb.append(nums[i]).append('/');
        }
        sb.append(nums[n - 1]).append(')');
        return sb.toString();
    }
}
/**
 * A / (B / C) / (D / E) / F
 * 除了A肯定是分子，剩余部分肯定是分母 -> 分子固定，分母最小
 * 脱括号，要最小 -> 全部都是除号，因为所有数都是大于1的整数 -> 越除越小
 *
 * 如果想要结果变大的话，应该尽可能的让除法变成乘法
 * a / b / c => a /(b / c) = a / b * c
 * a2 一定是除法 => a1 / (a2 / a3 ... /an) => a1 / a2 * a3 * a4 ... * an 一定是最优解
 * 脑筋急转弯问题
 */