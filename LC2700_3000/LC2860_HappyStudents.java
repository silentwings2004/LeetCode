package LC2700_3000;
import java.util.*;
public class LC2860_HappyStudents {
    /**
     * You are given a 0-indexed integer array nums of length n where n is the total number of students in the class.
     * The class teacher tries to select a group of students so that all the students remain happy.
     *
     * The ith student will become happy if one of these two conditions is met:
     *
     * The student is selected and the total number of selected students is strictly greater than nums[i].
     * The student is not selected and the total number of selected students is strictly less than nums[i].
     * Return the number of ways to select a group of students so that everyone remains happy.
     *
     * Input: nums = [1,1]
     * Output: 2
     *
     * Input: nums = [6,0,3,3,6,7,2,7]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] < nums.length
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int countWays(List<Integer> nums) {
        Collections.sort(nums);
        int res = 0, n = nums.size();
        if (nums.get(0) > 0) res++;
        for (int i = 0; i < n; i++) {
            if (i + 1 > nums.get(i)) {
                if (i + 1 == n || nums.get(i + 1) > i + 1) res++;
            }
        }
        return res;
    }
}
/**
 * 1. 被选中的学生人数 > nums[i] => 意味着nums[i]越小，越能够满足条件
 * 2. 这位学生没有被选中，并且被选中的学生人数严格 < nums[i] => nums[i]越大，越能够满足这个条件
 * 3. 结论：在选择学生时，应该优先选择nums[i]小的学生
 * 4. 排序后，数组的前缀是优先要选的
 * 5. 只需要判断：
 * 选中的学生人数 > nums[i] => i + 1 > nums[i]
 * 选中的学生人数 < nums[i + 1] => i + 1 < nums[i + 1]
 */