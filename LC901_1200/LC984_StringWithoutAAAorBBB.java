package LC901_1200;
import java.util.*;
public class LC984_StringWithoutAAAorBBB {
    /**
     * Given two integers a and b, return any string s such that:
     *
     * s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
     * The substring 'aaa' does not occur in s, and
     * The substring 'bbb' does not occur in s.
     *
     * Input: a = 1, b = 2
     * Output: "abb"
     *
     * Constraints:
     *
     * 0 <= a, b <= 100
     * It is guaranteed such an s exists for the given a and b.
     * @param a
     * @param b
     * @return
     */
    // S1
    // time = (a + b)log(a + b), space = O(a + b)
    public String strWithout3a3b(int a, int b) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
        pq.offer(new int[]{a, 'a'}); // {freq, char}
        pq.offer(new int[]{b, 'b'});

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            if (pq.size() == 1) {
                int k = pq.peek()[0];
                if (k > 2) return "";
                for (int i = 0; i < k; i++) sb.append((char)pq.poll()[1]);
                return sb.toString();
            }

            int[] x = pq.poll();
            int[] y = pq.poll();

            int k = Math.min(1 + x[0] - y[0], 2);
            for (int i = 0; i < k; i++) sb.append((char)x[1]);
            sb.append((char)y[1]);

            x[0] -= k;
            y[0]--;
            if (x[0] > 0) pq.offer(x);
            if (y[0] > 0) pq.offer(y);
        }
        return sb.toString();
    }

    // S2
    // time = O(a + b), space = O(1)
    public String strWithout3a3b2(int a, int b) {
        StringBuilder sb = new StringBuilder();
        if (a >= b) {
            while (a > 0 || b > 0) {
                if (a > b) {
                    sb.append("aab");
                    a -= 2;
                    b--;
                } else {
                    sb.append("ab");
                    a--;
                    b--;
                }
            }
        } else {
            while (a > 0 || b > 0) {
                if (b > a) {
                    sb.append("bba");
                    a--;
                    b -= 2;
                } else {
                    sb.append("ba");
                    a--;
                    b--;
                }
            }
        }
        for (int i = 0; i < Math.abs(a + b); i++) sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
/**
 * aabaabaabaabaabaabaabaa
 * a > b * 2 + 2
 * 套路解：aab aab aab ... ab ab ab ab ...
 * 如果a比b多，我们尽量每回合多塞点a，是为了让后面的回合里a和b的总数趋等
 * 每次若干个a，再加个b，后面趋等的话，只要输入ab即可。
 * ref: LC1405 是本题的升阶版
 */
