package LC2401_2700;

public class LC2566_MaximumDifferencebyRemappingaDigit {
    /**
     * You are given an integer num. You know that Danny Mittal will sneakily remap one of the 10 possible digits
     * (0 to 9) to another digit.
     *
     * Return the difference between the maximum and minimum values Danny can make by remapping exactly one digit in num.
     *
     * Notes:
     *
     * When Danny remaps a digit d1 to another digit d2, Danny replaces all occurrences of d1 in num with d2.
     * Danny can remap a digit to itself, in which case num does not change.
     * Danny can remap different digits for obtaining minimum and maximum values respectively.
     * The resulting number after remapping can contain leading zeroes.
     * We mentioned "Danny Mittal" to congratulate him on being in the top 10 in Weekly Contest 326.
     *
     * Input: num = 11891
     * Output: 99009
     *
     * Input: num = 90
     * Output: 99
     *
     * Constraints:
     *
     * 1 <= num <= 10^8
     * @param num
     * @return
     */
    // time = O(n), space = O(n)
    public int minMaxDifference(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int n = chars.length;
        char c = '\0';
        for (int i = 0; i < n; i++) {
            if (chars[i] == '9') continue;
            else if (c == '\0') c = chars[i];
            if (chars[i] == c) chars[i] = '9';
        }
        int max = Integer.parseInt(String.valueOf(chars));
        chars = String.valueOf(num).toCharArray();
        c = chars[0];
        for (int i = 0; i < n; i++) {
            if (chars[i] == c) chars[i] = '0';
        }
        int min = Integer.parseInt(String.valueOf(chars));
        return max - min;
    }
}