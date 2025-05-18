package LC1501_1800;
import java.util.*;
public class LC1505_MinimumPossibleIntegerAfteratMostKAdjacentSwapsOnDigits {
    /**
     * You are given a string num representing the digits of a very large integer and an integer k. You are allowed to
     * swap any two adjacent digits of the integer at most k times.
     *
     * Return the minimum integer you can obtain also as a string.
     *
     * Input: num = "4321", k = 4
     * Output: "1342"
     *
     * Input: num = "100", k = 1
     * Output: "010"
     *
     * Input: num = "36789", k = 1000
     * Output: "36789"
     *
     * Constraints:
     *
     * 1 <= num.length <= 3 * 10^4
     * num consists of only digits and does not contain leading zeros.
     * 1 <= k <= 10^9
     * @param num
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    int n;
    int[] tr;
    public String minInteger(String num, int k) {
        n = num.length();
        tr = new int[n + 1];
        Queue<Integer>[] q = new Queue[10];
        for (int i = 0; i < 10; i++) q[i] = new LinkedList<>();
        for (int i = 1; i <= n; i++) q[num.charAt(i - 1) - '0'].offer(i);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (!q[j].isEmpty()) {
                    int t = q[j].peek();
                    int pos = t + sum(t);
                    if (pos - i <= k) {
                        k -= pos - i;
                        sb.append(j);
                        q[j].poll();
                        add(1, 1);
                        add(t, -1);
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, int c) {
        for (int i = x; i <= n; i += lowbit(i)) tr[i] += c;
    }

    private int sum(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res += tr[i];
        return res;
    }
}
/**
 * 1.剩余的数中有0
 * 2.不能超过k步
 * 性质：0~9 每个数字一定从前往后用
 * 操作1：将某段数的下标全部加1
 * 操作2：求某个数当前的下标是什么
 * => 树状数组 / 线段树
 */