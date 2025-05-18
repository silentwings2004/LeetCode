package LC2700_3000;

public class LC2810_FaultyKeyboard {
    /**
     * Your laptop keyboard is faulty, and whenever you type a character 'i' on it, it reverses the string that you have
     * written. Typing other characters works as expected.
     *
     * You are given a 0-indexed string s, and you type each character of s using your faulty keyboard.
     *
     * Return the final string that will be present on your laptop screen.
     *
     * Input: s = "string"
     * Output: "rtsng"
     *
     * Input: s = "poiinter"
     * Output: "ponter"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of lowercase English letters.
     * s[0] != 'i'
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String finalString(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != 'i') sb.append(c);
            else sb.reverse();
        }
        return sb.toString();
    }
}