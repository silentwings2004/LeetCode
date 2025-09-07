package LC3601_3900;
import java.util.*;
public class LC3668_RestoreFinishingOrder {
    /**
     * You are given an integer array order of length n and an integer array friends.
     *
     * order contains every integer from 1 to n exactly once, representing the IDs of the participants of a race in
     * their finishing order.
     * friends contains the IDs of your friends in the race sorted in strictly increasing order. Each ID in friends is
     * guaranteed to appear in the order array.
     * Return an array containing your friends' IDs in their finishing order.
     *
     * Input: order = [3,1,2,5,4], friends = [1,3,4]
     * Output: [3,1,4]
     *
     * Input: order = [1,4,5,3,2], friends = [2,5]
     * Output: [5,2]
     *
     * Constraints:
     *
     * 1 <= n == order.length <= 100
     * order contains every integer from 1 to n exactly once
     * 1 <= friends.length <= min(8, n)
     * 1 <= friends[i] <= n
     * friends is strictly increasing
     * @param order
     * @param friends
     * @return
     */
    // S1
    // time = O(m * n), space = O(1)
    public int[] recoverOrder(int[] order, int[] friends) {
        int m = order.length, n = friends.length;
        int[] res = new int[n];
        for (int i = 0, idx = 0; i < m; i++) {
            for (int x : friends) {
                if (x == order[i]) {
                    res[idx++] = x;
                    break;
                }
            }
        }
        return res;
    }

    // time = O(m + nlogn), space = O(n)
    public int[] recoverOrder2(int[] order, int[] friends) {
        int m = order.length, n = friends.length;
        HashSet<Integer> set = new HashSet<>();
        int[][] a = new int[n][2];
        for (int x : friends) set.add(x);
        for (int i = 0, j = 0; i < m; i++) {
            if (set.contains(order[i])) a[j++] = new int[]{order[i], i};
        }
        Arrays.sort(a, (o1, o2) -> o1[1] - o2[1]);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = a[i][0];
        return res;
    }
}