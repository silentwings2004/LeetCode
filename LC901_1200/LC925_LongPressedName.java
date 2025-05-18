package LC901_1200;

public class LC925_LongPressedName {
    /**
     * Your friend is typing his name into a keyboard. Sometimes, when typing a character c, the key might get long
     * pressed, and the character will be typed 1 or more times.
     *
     * You examine the typed characters of the keyboard. Return True if it is possible that it was your friends name,
     * with some characters (possibly none) being long pressed.
     *
     * Input: name = "alex", typed = "aaleex"
     * Output: true
     *
     * Input: name = "saeed", typed = "ssaaedd"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= name.length, typed.length <= 1000
     * name and typed consist of only lowercase English letters.
     * @param name
     * @param typed
     * @return
     */
    // time = O(m + n), space = O(1)
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length(), n = typed.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (name.charAt(i) != typed.charAt(j)) return false;
            int x = i + 1, y = j + 1;
            while (x < m && name.charAt(x) == name.charAt(i)) x++;
            while (y < n && typed.charAt(y) == typed.charAt(j)) y++;
            if (x - i > y - j) return false;
            i = x;
            j = y;
        }
        return i == m && j == n;
    }
}