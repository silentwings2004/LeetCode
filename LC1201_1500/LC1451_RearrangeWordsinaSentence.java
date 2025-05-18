package LC1201_1500;
import java.util.*;
public class LC1451_RearrangeWordsinaSentence {
    /**
     * Given a sentence text (A sentence is a string of space-separated words) in the following format:
     *
     * First letter is in upper case.
     * Each word in text are separated by a single space.
     * Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their
     * lengths. If two words have the same length, arrange them in their original order.
     *
     * Return the new text following the format shown above.
     *
     * Input: text = "Leetcode is cool"
     * Output: "Is cool leetcode"
     *
     * Input: text = "Keep calm and code on"
     * Output: "On and keep calm code"
     *
     * Input: text = "To be or not to be"
     * Output: "To be or to be not"
     *
     * Constraints:
     *
     * text begins with a capital letter and then contains lowercase letters and single space between words.
     * 1 <= text.length <= 10^5
     * @param text
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String arrangeWords(String text) {
        String[] strs = text.split(" ");
        int n = strs.length;
        String[][] w = new String[n][2];
        for (int i = 0; i < n; i++) w[i] = new String[]{strs[i], String.valueOf(i)};
        Arrays.sort(w, (o1, o2) -> o1[0].length() != o2[0].length() ? o1[0].length() - o2[0].length() : Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (Character.isUpperCase(w[i][0].charAt(0))) sb.append(work(w[i][0]));
            else sb.append(w[i][0]);
            sb.append(' ');
        }
        sb.setLength(sb.length() - 1);
        return work2(sb.toString());
    }

    private String work(String s) {
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    private String work2(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}