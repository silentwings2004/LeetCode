package LC301_600;

public class LC492_ConstructtheRectangle {
    /**
     * A web developer needs to know how to design a web page's size. So, given a specific rectangular web page’s area,
     * your job by now is to design a rectangular web page, whose length L and width W satisfy the following requirements:
     *
     * The area of the rectangular web page you designed must equal to the given target area.
     * The width W should not be larger than the length L, which means L >= W.
     * The difference between length L and width W should be as small as possible.
     * Return an array [L, W] where L and W are the length and width of the web page you designed in sequence.
     *
     * Input: area = 4
     * Output: [2,2]
     *
     * Input: area = 122122
     * Output: [427,286]
     *
     * Constraints:
     *
     * 1 <= area <= 10^7
     * @param area
     * @return
     */
    // time = O(sqrt(n)), space = O(1)
    public int[] constructRectangle(int area) {
        for (int i = (int) Math.sqrt(area); i > 0; i--) {
            if (area % i == 0) return new int[]{area / i, i};
        }
        return new int[0];
    }
}
