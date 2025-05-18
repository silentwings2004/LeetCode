package LC301_600;

public class LC504_Base7 {
    /**
     * Given an integer num, return a string of its base 7 representation.
     *
     * Input: num = 100
     * Output: "202"
     *
     * Input: num = -7
     * Output: "-10"
     *
     * Constraints:
     *
     * -10^7 <= num <= 10^7
     * @param num
     * @return
     */
    // time = O(logk), space = O(logk)
    public String convertToBase7(int num) {
        if (num == 0) return "0";
        boolean is_neg = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 7);
            num /= 7;
        }
        String s = sb.reverse().toString();
        if (is_neg) s = "-" + s;
        return s;
    }
}