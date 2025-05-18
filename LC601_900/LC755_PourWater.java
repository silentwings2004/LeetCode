package LC601_900;
import com.sun.source.tree.Tree;

import java.util.*;
public class LC755_PourWater {
    /**
     * You are given an elevation map represents as an integer array heights where heights[i] representing the height
     * of the terrain at index i. The width at each index is 1. You are also given two integers volume and k. volume
     * units of water will fall at index k.
     *
     * Water first drops at the index k and rests on top of the highest terrain or water at that index. Then, it flows
     * according to the following rules:
     *
     * If the droplet would eventually fall by moving left, then move left.
     * Otherwise, if the droplet would eventually fall by moving right, then move right.
     * Otherwise, rise to its current position.
     * Here, "eventually fall" means that the droplet will eventually be at a lower level if it moves in that direction.
     * Also, level means the height of the terrain plus any water in that column.
     *
     * We can assume there is infinitely high terrain on the two sides out of bounds of the array. Also, there could not
     * be partial water being spread out evenly on more than one grid block, and each unit of water has to be in exactly
     * one block.
     *
     * Input: heights = [2,1,1,2,1,2,2], volume = 4, k = 3
     * Output: [2,2,2,3,2,2,2]
     *
     * Input: heights = [1,2,3,4], volume = 2, k = 2
     * Output: [2,3,3,4]
     *
     * Input: heights = [3,1,3], volume = 5, k = 1
     * Output: [4,4,4]
     *
     * Constraints:
     *
     * 1 <= heights.length <= 100
     * 0 <= heights[i] <= 99
     * 0 <= volume <= 2000
     * 0 <= k < heights.length
     * @param heights
     * @param volume
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int[] pourWater(int[] heights, int volume, int k) {
        int n = heights.length;
        int t = k;
        for (int i = 0; i < volume; i++) {
            while (t > 0 && heights[t] >= heights[t - 1]) t--;
            while (t + 1 < n && heights[t] >= heights[t + 1]) t++;
            while (t - 1 >= k && heights[t] >= heights[t - 1]) t--;
            heights[t]++;
        }
        return heights;
    }
}