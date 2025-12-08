package LC3601_3900;
import java.util.*;
public class LC3769_SortIntegersbyBinaryReflection {
    /**
     * You are given an integer array nums.
     *
     * The binary reflection of a positive integer is defined as the number obtained by reversing the order of its
     * binary digits (ignoring any leading zeros) and interpreting the resulting binary number as a decimal.
     *
     * Sort the array in ascending order based on the binary reflection of each element. If two different numbers have
     * the same binary reflection, the smaller original number should appear first.
     *
     * Return the resulting sorted array.
     *
     * Input: nums = [4,5,4]
     * Output: [4,4,5]
     *
     * Input: nums = [3,6,5,8]
     * Output: [8,3,6,5]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn * logk), space = O(n) k: max{nums}
    public int[] sortByReflection(int[] nums) {
        int n = nums.length;
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) a[i] = nums[i];
        Arrays.sort(a, (o1, o2) -> {
            int v1 = get(o1), v2 = get(o2);
            if (v1 != v2) return v1 - v2;
            return o1 - o2;
        });
        for (int i = 0; i < n; i++) nums[i] = a[i];
        return nums;
    }

    private int get(int x) {
        String s = Integer.toBinaryString(x);
        String t = new StringBuilder(s).reverse().toString();
        int v = 0, n = t.length();
        for (int i = 0; i < n; i++) v = v * 2 + t.charAt(i) - '0';
        return v;
    }
}