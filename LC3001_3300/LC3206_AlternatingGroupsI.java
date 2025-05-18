package LC3001_3300;

public class LC3206_AlternatingGroupsI {
    /**
     * There is a circle of red and blue tiles. You are given an array of integers colors. The color of tile i is
     * represented by colors[i]:
     *
     * colors[i] == 0 means that tile i is red.
     * colors[i] == 1 means that tile i is blue.
     * Every 3 contiguous tiles in the circle with alternating colors (the middle tile has a different color from its
     * left and right tiles) is called an alternating group.
     *
     * Return the number of alternating groups.
     *
     * Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.
     *
     * Input: colors = [1,1,1]
     * Output: 0
     *
     * Input: colors = [0,1,0,0,1]
     * Output: 3
     *
     * Constraints:
     *
     * 3 <= colors.length <= 100
     * 0 <= colors[i] <= 1
     * @param colors
     * @return
     */
    // time = O(n), space = O(1)
    public int numberOfAlternatingGroups(int[] colors) {
        int n = colors.length;
        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            int a = i - 1, b = i % n, c = (i + 1) % n;
            if (colors[a] != colors[b] && colors[b] != colors[c]) res++;
        }
        return res;
    }
}