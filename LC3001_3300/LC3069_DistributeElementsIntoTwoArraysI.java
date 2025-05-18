package LC3001_3300;
import java.util.*;
public class LC3069_DistributeElementsIntoTwoArraysI {
    /**
     * You are given a 1-indexed array of distinct integers nums of length n.
     *
     * You need to distribute all the elements of nums between two arrays arr1 and arr2 using n operations. In the first
     * operation, append nums[1] to arr1. In the second operation, append nums[2] to arr2. Afterwards, in the ith
     * operation:
     *
     * If the last element of arr1 is greater than the last element of arr2, append nums[i] to arr1. Otherwise, append
     * nums[i] to arr2.
     * The array result is formed by concatenating the arrays arr1 and arr2. For example, if arr1 == [1,2,3] and
     * arr2 == [4,5,6], then result = [1,2,3,4,5,6].
     *
     * Return the array result.
     *
     * Input: nums = [2,1,3]
     * Output: [2,3,1]
     *
     * Input: nums = [5,4,3,8]
     * Output: [5,3,4,8]
     *
     * Constraints:
     *
     * 3 <= n <= 50
     * 1 <= nums[i] <= 100
     * All elements in nums are distinct.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int[] resultArray(int[] nums) {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        int n = nums.length;
        a.add(nums[0]);
        b.add(nums[1]);
        for (int i = 2; i < n; i++) {
            if (a.get(a.size() - 1) > b.get(b.size() - 1)) a.add(nums[i]);
            else b.add(nums[i]);
        }

        int[] res = new int[n];
        for (int i = 0, j = 0; i < a.size(); i++, j++) res[j] = a.get(i);
        for (int i = 0, j = a.size(); i < b.size(); i++, j++) res[j] = b.get(i);
        return res;
    }
}