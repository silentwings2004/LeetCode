package LC3301_3600;
import java.util.*;
public class LC3431_MinimumUnlockedIndicestoSortNums {
    /**
     * You are given an array nums consisting of integers between 1 and 3, and a binary array locked of the same size.
     *
     * We consider nums sortable if it can be sorted using adjacent swaps, where a swap between two indices i and i + 1
     * is allowed if nums[i] - nums[i + 1] == 1 and locked[i] == 0.
     *
     * In one operation, you can unlock any index i by setting locked[i] to 0.
     *
     * Return the minimum number of operations needed to make nums sortable. If it is not possible to make nums
     * sortable, return -1.
     *
     * Input: nums = [1,2,1,2,3,2], locked = [1,0,1,1,0,1]
     * Output: 0
     *
     * Input: nums = [1,2,1,1,3,2,2], locked = [1,0,1,1,0,1,0]
     * Output: 2
     *
     * Input: nums = [1,2,1,2,3,2,1], locked = [0,0,0,0,0,0,0]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 3
     * locked.length == nums.length
     * 0 <= locked[i] <= 1
     * @param nums
     * @param locked
     * @return
     */
    // time = O(n), space = O(1)
    public int minUnlockedIndices(int[] nums, int[] locked) {
        int n = nums.length, res = 0;
        int[][] pos = new int[3][2];
        for (int i = 0; i < 3; i++) Arrays.fill(pos[i], -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i] - 1;
            if (pos[x][0] == -1) pos[x] = new int[]{i, i};
            else pos[x][1] = i;
        }

        if (pos[0][1] != -1 && pos[2][0] != -1 && pos[0][1] > pos[2][0]) return -1;
        if (pos[1][0] != -1 && pos[0][1] != -1 && pos[1][0] < pos[0][1]) {
            for (int i = pos[1][0]; i + 1 <= pos[0][1]; i++) {
                if (locked[i] == 1) {
                    locked[i] = 0;
                    res++;
                }
            }
        }
        if (pos[1][1] != -1 && pos[2][0] != -1 && pos[2][0] < pos[1][1]) {
            for (int i = pos[2][0]; i + 1 <= pos[1][1]; i++) {
                if (locked[i] == 1) {
                    locked[i] = 0;
                    res++;
                }
            }
        }
        return res;
    }
}