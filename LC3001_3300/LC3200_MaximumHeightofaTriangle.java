package LC3001_3300;

public class LC3200_MaximumHeightofaTriangle {
    /**
     * You are given two integers red and blue representing the count of red and blue colored balls. You have to
     * arrange these balls to form a triangle such that the 1st row will have 1 ball, the 2nd row will have 2 balls,
     * the 3rd row will have 3 balls, and so on.
     *
     * All the balls in a particular row should be the same color, and adjacent rows should have different colors.
     *
     * Return the maximum height of the triangle that can be achieved.
     *
     * Input: red = 2, blue = 4
     * Output: 3
     *
     * Input: red = 2, blue = 1
     * Output: 2
     *
     * Input: red = 10, blue = 1
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= red, blue <= 100
     * @param red
     * @param blue
     * @return
     */
    // S1
    // time = O(min(sqrt(m), sqrt(n)), space = O(1)
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(helper(red, blue), helper(blue, red));
    }

    private int helper(int a, int b) {
        int res = 0, x = 1, y = 2;
        while (true) {
            if (a < x) break;
            a -= x;
            x += 2;
            res++;
            if (b < y) break;
            b -= y;
            y += 2;
            res++;
        }
        return res;
    }

    // S2
    // time = O(1), space = O(1)
    public int maxHeightOfTriangle2(int red, int blue) {
        return Math.max(f(red, blue), f(blue, red));
    }

    private int f(int x, int y) {
        int odd = (int)Math.sqrt(x);
        int even = (int)((Math.sqrt(4 * y + 1) - 1) / 2);
        return odd > even ? even * 2 + 1 : odd * 2;
    }
}