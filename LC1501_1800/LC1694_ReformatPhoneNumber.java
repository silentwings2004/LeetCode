package LC1501_1800;
import java.util.*;
public class LC1694_ReformatPhoneNumber {
    /**
     * You are given a phone number as a string number. number consists of digits, spaces ' ', and/or dashes '-'.
     *
     * You would like to reformat the phone number in a certain manner. Firstly, remove all spaces and dashes. Then,
     * group the digits from left to right into blocks of length 3 until there are 4 or fewer digits. The final digits
     * are then grouped as follows:
     *
     * 2 digits: A single block of length 2.
     * 3 digits: A single block of length 3.
     * 4 digits: Two blocks of length 2 each.
     *
     * The blocks are then joined by dashes. Notice that the reformatting process should never produce any blocks of
     * length 1 and produce at most two blocks of length 2.
     *
     * Return the phone number after formatting.
     *
     * Input: number = "1-23-45 6"
     * Output: "123-456"
     *
     * Input: number = "123 4-567"
     * Output: "123-45-67"
     *
     * Input: number = "--17-5 229 35-39475 "
     * Output: "175-229-353-94-75"
     *
     * @param number
     * @return
     */
    // time = O(n), space = O(n)
    public String reformatNumber(String number) {
        int n = number.length(), cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = number.charAt(i);
            if (c == ' ' || c == '-') continue;
            else {
                sb.append(c);
                cnt++;
                if (cnt == 3) {
                    sb.append('-');
                    cnt = 0;
                }
            }
        }
        if (cnt == 0) sb.setLength(sb.length() - 1);
        else if (cnt == 1) {
            sb.deleteCharAt(sb.length() - 2);
            sb.insert(sb.length() - 2, '-');
        }
        return sb.toString();
    }
}
