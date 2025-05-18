package LC1501_1800;
import java.util.*;
public class LC1585_CheckIfStringIsTransformableWithSubstringSortOperations {
    /**
     * Given two strings s and t, transform string s into string t using the following operation any number of times:
     *
     * Choose a non-empty substring in s and sort it in place so the characters are in ascending order.
     * For example, applying the operation on the underlined substring in "14234" results in "12344".
     * Return true if it is possible to transform s into t. Otherwise, return false.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "84532", t = "34852"
     * Output: true
     *
     * Input: s = "34521", t = "23415"
     * Output: true
     *
     * Input: s = "12345", t = "12435"
     * Output: false
     *
     * Constraints:
     *
     * s.length == t.length
     * 1 <= s.length <= 10^5
     * s and t consist of only digits.
     * @param s
     * @param t
     * @return
     */
    // time = O(n + m), space = O(n)
    public boolean isTransformable(String s, String t) {
        Queue<Integer>[] pos = new Queue[10];
        for (int i = 0; i < 10; i++) pos[i] = new LinkedList<>();
        int n = s.length(), m = t.length();
        for (int i = 0; i < n; i++) pos[s.charAt(i) - '0'].offer(i);
        for (int i = 0; i < m; i++) {
            int num = t.charAt(i) - '0';
            if (pos[num].isEmpty()) return false;
            int idx = pos[num].peek();
            for (int j = 0; j < num; j++) {
                if (!pos[j].isEmpty() && pos[j].peek() < idx) return false;
            }
            pos[num].poll();
        }
        return true;
    }
}
/**util*;间的最前面；如果把区间缩小一点，操作之后最小的数字依然会跑到这个区间的最前面。
 * 可见区间的大小，其实就是影响着最小数字的“迁移程度”。
 * 本题的操作，本质上就是“冒泡”：允许任何一个数字可以和它前面比它大的数字交换，并且能够连续操作，前移到所能到达的任何位置。
 * 对于任何一个字符，只要前面的数字比它大，它就可以不停前移
 * s: x x 5 x 5 x
 * t: 5 x x x x x
 * 如果有若干个5，一定会找一个离它最接近的5
 * pos[0] = {....}
 * pos[1] = {....}
 * pos[2] = {....}
 * pos[3] = {....}
 * pos[4] = {....}
 */