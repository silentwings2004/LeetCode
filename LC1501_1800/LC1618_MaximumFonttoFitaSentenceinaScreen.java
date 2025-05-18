package LC1501_1800;

public class LC1618_MaximumFonttoFitaSentenceinaScreen {
    /**
     * You are given a string text. We want to display text on a screen of width w and height h. You can choose any font
     * size from array fonts, which contains the available font sizes in ascending order.
     *
     * You can use the FontInfo interface to get the width and height of any character at any available font size.
     *
     * The FontInfo interface is defined as such:
     *
     * interface FontInfo {
     *   // Returns the width of character ch on the screen using font size fontSize.
     *   // O(1) per call
     *   public int getWidth(int fontSize, char ch);
     *
     *   // Returns the height of any character on the screen using font size fontSize.
     *   // O(1) per call
     *   public int getHeight(int fontSize);
     * }
     * The calculated width of text for some fontSize is the sum of every getWidth(fontSize, text[i]) call for each
     * 0 <= i < text.length (0-indexed). The calculated height of text for some fontSize is getHeight(fontSize). Note
     * that text is displayed on a single line.
     *
     * It is guaranteed that FontInfo will return the same value if you call getHeight or getWidth with the same
     * parameters.
     *
     * It is also guaranteed that for any font size fontSize and any character ch:
     *
     * getHeight(fontSize) <= getHeight(fontSize+1)
     * getWidth(fontSize, ch) <= getWidth(fontSize+1, ch)
     * Return the maximum font size you can use to display text on the screen. If text cannot fit on the display with
     * any font size, return -1.
     *
     * Input: text = "helloworld", w = 80, h = 20, fonts = [6,8,10,12,14,16,18,24,36]
     * Output: 6
     *
     * Input: text = "leetcode", w = 1000, h = 50, fonts = [1,2,4]
     * Output: 4
     *
     * Input: text = "easyquestion", w = 100, h = 100, fonts = [10,15,20,25]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= text.length <= 50000
     * text contains only lowercase English letters.
     * 1 <= w <= 10^7
     * 1 <= h <= 10^4
     * 1 <= fonts.length <= 10^5
     * 1 <= fonts[i] <= 10^5
     * fonts is sorted in ascending order and does not contain duplicates.
     * @param text
     * @param w
     * @param h
     * @param fonts
     * @param fontInfo
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {
        int m = fonts.length;
        int l = 0, r = m - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(text, fonts[mid], fontInfo, w, h)) l = mid;
            else r = mid - 1;
        }
        return check(text, fonts[r], fontInfo, w, h) ? fonts[r] : -1;
    }

    private boolean check(String s, int x, FontInfo fontInfo, int w, int h) {
        int n = s.length(), width = 0, height = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            width += fontInfo.getWidth(x, c);
            height = fontInfo.getHeight(x);
            if (width > w || height > h) return false;
        }
        return true;
    }

    interface FontInfo {
        // Return the width of char ch when fontSize is used.
        public int getWidth(int fontSize, char ch) {}
        // Return Height of any char when fontSize is used.
        public int getHeight(int fontSize)
    }
}