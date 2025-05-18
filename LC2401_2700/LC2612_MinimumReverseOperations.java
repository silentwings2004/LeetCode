package LC2401_2700;
import java.util.*;
public class LC2612_MinimumReverseOperations {
    /**
     * You are given an integer n and an integer p in the range [0, n - 1]. Representing a 0-indexed array arr of
     * length n where all positions are set to 0's, except position p which is set to 1.
     *
     * You are also given an integer array banned containing some positions from the array. For the ith position in
     * banned, arr[banned[i]] = 0, and banned[i] != p.
     *
     * You can perform multiple operations on arr. In an operation, you can choose a subarray with size k and reverse
     * the subarray. However, the 1 in arr should never go to any of the positions in banned. In other words, after
     * each operation arr[banned[i]] remains 0.
     *
     * Return an array ans where for each i from [0, n - 1], ans[i] is the minimum number of reverse operations needed
     * to bring the 1 to position i in arr, or -1 if it is impossible.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * The values of ans[i] are independent for all i's.
     * The reverse of an array is an array containing the values in reverse order.
     *
     * Input: n = 4, p = 0, banned = [1,2], k = 4
     * Output: [0,-1,-1,1]
     *
     * Input: n = 5, p = 0, banned = [2,4], k = 3
     * Output: [0,-1,-1,-1,-1]
     *
     * Input: n = 4, p = 2, banned = [0,1,3], k = 1
     * Output: [-1,-1,0,-1]
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 0 <= p <= n - 1
     * 0 <= banned.length <= n - 1
     * 0 <= banned[i] <= n - 1
     * 1 <= k <= n
     * banned[i] != p
     * all values in banned are unique
     * @param n
     * @param p
     * @param banned
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        TreeSet<Integer> odd = new TreeSet<>();
        TreeSet<Integer> even = new TreeSet<>();
        boolean[] st = new boolean[n];
        for (int x : banned) st[x] = true;

        for (int i = 0; i < n; i++) {
            if (st[i] || i == p) continue;
            if (i % 2 == 1) odd.add(i);
            else even.add(i);
        }

        int[] res = new int[n];
        Arrays.fill(res, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(p);
        res[p] = 0;

        while (!q.isEmpty()) {
            int t = q.poll();
            int a = Math.abs(t - k + 1);
            int b = t + k - 1;
            if (b >= n) b = 2 * n - k - t - 1;
            TreeSet<Integer> set = a % 2 == 0 ? even : odd;
            Integer ck = set.ceiling(a);
            while (ck != null && ck <= b) {
                q.offer(ck);
                res[ck] = res[t] + 1;
                set.remove(ck);
                ck = set.ceiling(a);
            }
        }
        return res;
    }
}
/**
 * If we have an array of size 10 and x is 6 and k is 4, we could move x to positions 3,5,7 and 9.
 * If k is 3, we could move x to 4, 6(current position), 8.
 * Notice that the parity (mod 2) of index we could move to are same.
 * So if we can find the minimum and maximum index that could be visited,
 * we can assume that all index of same parity in between can be visited with a single reversal.
 */