package LC1201_1500;
import java.util.*;
public class LC1313_DecompressRunLengthEncodedList {
    /**
     * We are given a list nums of integers representing a list compressed with run-length encoding.
     *
     * Consider each adjacent pair of elements [freq, val] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such
     * pair, there are freq elements with value val concatenated in a sublist. Concatenate all the sublists from left
     * to right to generate the decompressed list.
     *
     * Return the decompressed list.
     *
     * Input: nums = [1,2,3,4]
     * Output: [2,4,4,4]
     *
     * Input: nums = [1,1,2,3]
     * Output: [1,3,3]
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * nums.length % 2 == 0
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n * m), space = O(n * m)
    public int[] decompressRLElist(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                res.add(nums[i + 1]);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}