package LC301_600;
import java.util.*;
public class LC393_UTF8Validation {
    /**
     * Given an integer array data representing the data, return whether it is a valid UTF-8 encoding.
     *
     * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
     *
     * For a 1-byte character, the first bit is a 0, followed by its Unicode code.
     * For an n-bytes character, the first n bits are all one's, the n + 1 bit is 0, followed by n - 1 bytes with the
     * most significant 2 bits being 10.
     *
     * Note: The input is an array of integers. Only the least significant 8 bits of each integer is used to store the
     * data. This means each integer represents only 1 byte of data.
     *
     * Input: data = [197,130,1]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= data.length <= 2 * 10^4
     * 0 <= data[i] <= 255
     * @param data
     * @return
     */
    // time = O(n), space = O(1)
    public boolean validUtf8(int[] data) {
        // corner case
        if (data == null || data.length == 0) return false;

        int left = 0;
        for (int d : data) {
            if (left == 0) { // check the first byte
                if ((d >> 3) == 30) left = 3;
                else if ((d >> 4) == 14) left = 2;
                else if ((d >> 5) == 6) left = 1;
                else if ((d >> 7) == 0) left = 0;
                else return false;
            } else {
                if (((d >> 6) & 2) != 2) return false;
                left--;
            }
        }
        return left == 0;
    }

    // S2
    // time = O(n), space = O(1)
    public boolean validUtf82(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; i++) {
            if (get(data[i], 7) == 0) continue;
            int k = 0;
            while (k <= 4 && get(data[i], 7 - k) == 1) k++;
            if (k == 1 || k > 4) return false;
            for (int j = 0; j < k - 1; j++) {
                int t = i + 1 + j;
                if (t >= n) return false;
                if (!(get(data[t], 7) == 1 && get(data[t], 6) == 0)) return false;
            }
            i += k - 1;
        }
        return true;
    }

    private int get(int x, int k) {
        return x >> k & 1;
    }
}
/**
 * how to check the prefix in bianry format?
 * (a >> k) == mask, k = 8 - len(mask)
 * eg. mask = 110, len(mask) = 3, (11000010 >> (8 - 3)) = 110 == mask
 */
