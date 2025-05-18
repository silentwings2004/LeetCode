package LC301_600;
import java.util.*;
public class LC401_BinaryWatch {
    /**
     * A binary watch has 4 LEDs on the top to represent the hours (0-11), and 6 LEDs on the bottom to represent the
     * minutes (0-59). Each LED represents a zero or one, with the least significant bit on the right.
     *
     * For example, the below binary watch reads "4:51".
     *
     * Given an integer turnedOn which represents the number of LEDs that are currently on (ignoring the PM), return all
     * possible times the watch could represent. You may return the answer in any order.
     *
     * The hour must not contain a leading zero.
     *
     * For example, "01:00" is not valid. It should be "1:00".
     * The minute must be consist of two digits and may contain a leading zero.
     *
     * For example, "10:2" is not valid. It should be "10:02".
     *
     * Input: turnedOn = 1
     * Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
     *
     * Constraints:
     *
     * 0 <= turnedOn <= 10
     * @param turnedOn
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    res.add(h + ":" + (m < 10 ? "0" + m : m));
                }
            }
        }
        return res;
    }

    // S2
    // time = O(1), space = O(1)
    public List<String> readBinaryWatch2(int turnedOn) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 1 << 10; i++) {
            int s = 0;
            for (int j = 0; j < 10; j++) {
                if ((i >> j & 1) == 1) s++;
            }
            if (s == turnedOn) {
                int a = i >> 6, b = i & 63;
                if (a < 12 && b < 60) {
                    res.add(a + ":" + (b < 10 ? "0" + b : b));
                }
            }
        }
        return res;
    }
}