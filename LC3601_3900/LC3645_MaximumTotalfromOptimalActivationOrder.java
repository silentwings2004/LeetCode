package LC3601_3900;
import java.util.*;
public class LC3645_MaximumTotalfromOptimalActivationOrder {
    /**
     * You are given two integer arrays value and limit, both of length n.
     *
     * Initially, all elements are inactive. You may activate them in any order.
     *
     * To activate an inactive element at index i, the number of currently active elements must be strictly less than
     * limit[i].
     * When you activate the element at index i, it adds value[i] to the total activation value (i.e., the sum of
     * value[i] for all elements that have undergone activation operations).
     * After each activation, if the number of currently active elements becomes x, then all elements j with
     * limit[j] <= x become permanently inactive, even if they are already active.
     * Return the maximum total you can obtain by choosing the activation order optimally.
     *
     * Input: value = [3,5,8], limit = [2,1,3]
     * Output: 16
     *
     * Input: value = [4,2,6], limit = [1,1,1]
     * Output: 6
     *
     * Input: value = [4,1,5,2], limit = [3,3,2,3]
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= n == value.length == limit.length <= 10^5
     * 1 <= value[i] <= 10^5
     * 1 <= limit[i] <= n
     * @param value
     * @param limit
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxTotal(int[] value, int[] limit) {
        int n = value.length;
        List<Integer>[] q = new List[n + 1];
        for (int i = 0; i <= n; i++) q[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) q[limit[i]].add(value[i]);
        long res = 0;
        for (int i = 1; i <= n; i++) {
            List<Integer> p = q[i];
            Collections.sort(p, (o1, o2) -> o2 - o1);
            for (int j = 0; j < Math.min(p.size(), i); j++) res += p.get(j);
        }
        return res;
    }
}