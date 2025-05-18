package LC301_600;

public class LC443_StringCompression {
    /**
     * Given an array of characters chars, compress it using the following algorithm:
     *
     * Begin with an empty string s. For each group of consecutive repeating characters in chars:
     *
     * If the group's length is 1, append the character to s.
     * Otherwise, append the character followed by the group's length.
     * The compressed string s should not be returned separately, but instead be stored in the input character array
     * chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
     *
     * After you are done modifying the input array, return the new length of the array.
     *
     * You must write an algorithm that uses only constant extra space.
     *
     * Input: chars = ["a","a","b","b","c","c","c"]
     * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
     *
     * Constraints:
     *
     * 1 <= chars.length <= 2000
     * chars[i] is a lower-case English letter, upper-case English letter, digit, or symbol.
     * @param chars
     * @return
     */
    // time = O(n), space = O(1)
    public int compress(char[] chars) {
        int k = 0, n = chars.length;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && chars[j] == chars[i]) j++;
            int len = j - i;
            chars[k++] = chars[i];
            if (len > 1) {
                int t = k;
                while (len > 0) {
                    chars[t++] = (char)(len % 10 + '0');
                    len /= 10;
                }
                reverse(chars, k, t - 1);
                k = t;
            }
            i = j - 1;
        }
        return k;
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char t = chars[i];
            chars[i++] = chars[j];
            chars[j--] = t;
        }
    }
}