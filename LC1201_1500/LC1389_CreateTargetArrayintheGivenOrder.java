package LC1201_1500;
import java.util.*;
public class LC1389_CreateTargetArrayintheGivenOrder {
    /**
     * Given two arrays of integers nums and index. Your task is to create target array under the following rules:
     *
     * Initially target array is empty.
     * From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
     * Repeat the previous step until there are no elements to read in nums and index.
     * Return the target array.
     *
     * It is guaranteed that the insertion operations will be valid.
     *
     * Input: nums = [0,1,2,3,4], index = [0,1,2,2,1]
     * Output: [0,4,1,3,2]
     *
     * Input: nums = [1,2,3,4,0], index = [0,1,2,3,0]
     * Output: [0,1,2,3,4]
     *
     * Input: nums = [1], index = [0]
     * Output: [1]
     *
     * Constraints:
     *
     * 1 <= nums.length, index.length <= 100
     * nums.length == index.length
     * 0 <= nums[i] <= 100
     * 0 <= index[i] <= i
     * @param nums
     * @param index
     * @return
     */
    // time = O(n), space = O(n)
    public int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) q.add(index[i], nums[i]);
        int[] res = new int[q.size()];
        for (int i = 0; i < q.size(); i++) res[i] = q.get(i);
        return res;
    }
}