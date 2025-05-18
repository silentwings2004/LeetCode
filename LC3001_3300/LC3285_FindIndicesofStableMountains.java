package LC3001_3300;
import java.util.*;
public class LC3285_FindIndicesofStableMountains {
    /**
     * There are n mountains in a row, and each mountain has a height. You are given an integer array height where
     * height[i] represents the height of mountain i, and an integer threshold.
     *
     * A mountain is called stable if the mountain just before it (if it exists) has a height strictly greater than
     * threshold. Note that mountain 0 is not stable.
     *
     * Return an array containing the indices of all stable mountains in any order.
     *
     * Input: height = [1,2,3,4,5], threshold = 2
     *
     * Output: [3,4]
     *
     * Input: height = [10,1,10,1,10], threshold = 3
     *
     * Output: [1,3]
     *
     * Input: height = [10,1,10,1,10], threshold = 10
     *
     * Output: []
     *
     * Constraints:
     *
     * 2 <= n == height.length <= 100
     * 1 <= height[i] <= 100
     * 1 <= threshold <= 100
     * @param height
     * @param threshold
     * @return
     */
    // time = O(n), space = O(1)
    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> res = new ArrayList<>();
        int n = height.length;
        for (int i = 1; i < n; i++) {
            if (height[i - 1] > threshold) res.add(i);
        }
        return res;
    }
}