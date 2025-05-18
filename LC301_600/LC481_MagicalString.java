package LC301_600;

public class LC481_MagicalString {
    /**
     * A magical string s consists of only '1' and '2' and obeys the following rules:
     *
     * The string s is magical because concatenating the number of contiguous occurrences of characters '1' and '2'
     * generates the string s itself.
     * The first few elements of s is s = "1221121221221121122……". If we group the consecutive 1's and 2's in s, it
     * will be "1 22 11 2 1 22 1 22 11 2 11 22 ......" and the occurrences of 1's or 2's in each group are
     * "1 2 2 1 1 2 1 2 2 1 2 2 ......". You can see that the occurrence sequence is s itself.
     *
     * Given an integer n, return the number of 1's in the first n number in the magical string s.
     *
     * Input: n = 6
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    public int magicalString(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append("122");
        for (int i = 2, k = 1; sb.length() < n; i++, k = 3 - k) {
            for (int j = 0; j < sb.charAt(i) - '0'; j++) sb.append(k);
        }

        int res = 0;
        for (int i = 0; i < n; i++) res += sb.charAt(i) == '1' ? 1 : 0;
        return res;
    }
}
/**
 * s = "1 22 11 2 1 221221121122……"
 *      1 2  2  1 1
 * 上面的数是1和2交替，下面的数就代表有几个1或者2，可以无限重复下去这个过程
 * 上面字符串长度严格大于下面字符串的长度
 * 下面字符串多一位，上面字符串会增加1或者2位，所以这个过程可以永久持续下去
 * 根据n把字符串的前n位构造出来
 */