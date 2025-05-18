package LC601_900;

public class LC800_SimilarRGBColor {
    /**
     * The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.
     *
     * For example, "#15c" is shorthand for the color "#1155cc".
     * The similarity between the two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)2 - (CD - WX)2 - (EF - YZ)2.
     *
     * Given a string color that follows the format "#ABCDEF", return a string represents the color that is most
     * similar to the given color and has a shorthand (i.e., it can be represented as some "#XYZ").
     *
     * Any answer which has the same highest similarity as the best answer will be accepted.
     *
     * Input: color = "#09f166"
     * Output: "#11ee66"
     *
     * Input: color = "#4e3fe1"
     * Output: "#5544dd"
     *
     * Constraints:
     *
     * color.length == 7
     * color[0] == '#'
     * color[i] is either digit or character in the range ['a', 'f'] for i > 0.
     * @param color
     * @return
     */
    // time = O(n), space = O(n)
    public String similarRGB(String color) {
        String[] strs = {"00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "aa", "bb", "cc", "dd", "ee", "ff"};
        StringBuilder sb = new StringBuilder();
        sb.append('#');

        int n = color.length();
        for (int i = 1; i < n; i += 2) {
            String s = color.substring(i, i + 2);
            int min = Integer.MAX_VALUE;
            String t = "";
            for (String str : strs) {
                int res = Math.abs(Integer.parseInt(s, 16) - Integer.parseInt(str, 16));
                if (res < min) {
                    min = res;
                    t = str;
                }
            }
            sb.append(t);
        }
        return sb.toString();
    }
}