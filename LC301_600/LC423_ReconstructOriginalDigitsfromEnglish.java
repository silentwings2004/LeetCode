package LC301_600;
import java.util.*;
public class LC423_ReconstructOriginalDigitsfromEnglish {
    /**
     * Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in
     * ascending order.
     *
     * Note:
     * Input contains only lowercase English letters.
     * Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as
     * "abc" or "zerone" are not permitted.
     * Input length is less than 50,000.
     *
     * Input: "owoztneoer"
     * Output: "012"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
     * s is guaranteed to be valid.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public String originalDigits(String s) {
        // corner case
        if (s == null || s.length() == 0) return "";

        // zero one two three four five six seven eight nine
        int[] count = new int[10];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'z') count[0]++;
            if (c == 'w') count[2]++;
            if (c == 'x') count[6]++;
            if (c == 's') count[7]++; //7-6
            if (c == 'g') count[8]++;
            if (c == 'u') count[4]++;
            if (c == 'f') count[5]++; //5-4
            if (c == 'h') count[3]++; //3-8
            if (c == 'i') count[9]++; //9-8-5-6
            if (c == 'o') count[1]++; //1-0-2-4
        }
        count[7] -= count[6];
        count[5] -= count[4];
        count[3] -= count[8];
        count[9] -= (count[8] + count[5] + count[6]);
        count[1] -= (count[0] + count[2] + count[4]);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (count[i] > 0) {
                while (count[i]-- > 0) sb.append(i);
            }
        }
        return sb.toString();
    }

    // S2
    // time = O(nlogn), space = O(n)
    public String originalDigits2(String s) {
        String[] name = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int[] ord = new int[]{0, 8, 3, 2, 6, 4, 5, 1, 7, 9};
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;
        StringBuilder sb = new StringBuilder();
        for (int x : ord) {
            while (true) {
                boolean flag = true;
                for (char c : name[x].toCharArray()) {
                    if (cnt[c - 'a'] == 0) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    sb.append(x);
                    for (char c : name[x].toCharArray()) cnt[c - 'a']--;
                } else break;
            }
        }
        char[] chars = sb.toString().toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}