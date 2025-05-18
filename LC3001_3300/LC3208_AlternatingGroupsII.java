package LC3001_3300;

public class LC3208_AlternatingGroupsII {
    /**
     * There is a circle of red and blue tiles. You are given an array of integers colors and an integer k. The color of
     * tile i is represented by colors[i]:
     *
     * colors[i] == 0 means that tile i is red.
     * colors[i] == 1 means that tile i is blue.
     * An alternating group is every k contiguous tiles in the circle with alternating colors (each tile in the group
     * except the first and last one has a different color from its left and right tiles).
     *
     * Return the number of alternating groups.
     *
     * Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.
     *
     * Input: colors = [0,1,0,1,0], k = 3
     * Output: 3
     *
     * Input: colors = [0,1,0,0,1,0,1], k = 6
     * Output: 2
     *
     * Input: colors = [1,1,0,1], k = 4
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= colors.length <= 10^5
     * 0 <= colors[i] <= 1
     * 3 <= k <= colors.length
     * @param colors
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int res = 0;
        for (int i = 0; i < n + k - 1; i++) {
            int j = i + 1;
            while (j < n + k - 1 && colors[j % n] != colors[(j - 1) % n]) j++;
            int len = j - i;
            res += Math.max(0, len - k + 1);
            i = j - 1;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int numberOfAlternatingGroups2(int[] colors, int k) {
        int n = colors.length;
        int res = 0, cnt = 0;
        for (int i = 0; i < n * 2; i++) {
            if (i > 0 && colors[i % n] == colors[(i - 1) % n]) cnt = 0;
            cnt++;
            if (i >= n && cnt >= k) res++;
        }
        return res;
    }
}
/**
 * 把环形的转化成非环形的来做
 * 考虑下非环形的时候怎么做？
 * 0 1 0 1 0
 * 对于右端点，到此为止，交替子数组最长是多少？
 * 子数组 -> 子序列 (普通数组)
 */