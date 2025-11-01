package LC3601_3900;
import java.util.*;
public class LC3730_MaximumCaloriesBurntfromJumps {
    /**
     * You are given an integer array heights of size n, where heights[i] represents the height of the ith block in an
     * exercise routine.
     *
     * You start on the ground (height 0) and must jump onto each block exactly once in any order.
     *
     * The calories burned for a jump from a block of height a to a block of height b is (a - b)^2.
     * The calories burned for the first jump from the ground to the chosen first block heights[i] is (0 - heights[i])^2.
     * Return the maximum total calories you can burn by selecting an optimal jumping sequence.
     *
     * Note: Once you jump onto the first block, you cannot return to the ground.
     *
     * Input: heights = [1,7,9]
     * Output: 181
     *
     * Input: heights = [5,2,4]
     * Output: 38
     *
     * Input: heights = [3,3]
     * Output: 9
     *
     * Constraints:
     *
     * 1 <= n == heights.length <= 10^5
     * 1 <= heights[i] <= 10^5
     * @param heights
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public long maxCaloriesBurnt(int[] heights) {
        Arrays.sort(heights);
        int n = heights.length;
        long res = 0;
        for (int i = -1, j = n - 1; i < j;) {
            res += 1L * (heights[j] - (i == -1 ? 0 : heights[i])) * (heights[j] - (i == -1 ? 0 : heights[i]));
            i++;
            res += 1L * (heights[j] - heights[i]) * (heights[j] - heights[i]);
            j--;
        }
        return res;
    }
}