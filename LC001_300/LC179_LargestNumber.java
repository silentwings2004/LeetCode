package LC001_300;
import java.util.*;
public class LC179_LargestNumber {
    /**
     * Given a list of non-negative integers nums, arrange them such that they form the largest number.
     *
     * Note: The result may be very large, so you need to return a string instead of an integer.
     *
     * Input: nums = [10,2]
     * Output: "210"
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String largestNumber(int[] nums) {
        int n = nums.length;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) arr[i] = nums[i];
        Arrays.sort(arr, (o1, o2) -> {
           String a = String.valueOf(o1), b = String.valueOf(o2);
           return (b + a).compareTo(a + b);
        });

        if (arr[0] == 0) return "0";
        StringBuilder sb = new StringBuilder();
        for (int x : arr) sb.append(x);
        return sb.toString();
    }
}
/**
 * 有全序关系的就可以排序
 * 反对称性
 */
