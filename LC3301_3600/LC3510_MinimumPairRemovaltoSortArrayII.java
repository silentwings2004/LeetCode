package LC3301_3600;
import java.util.*;
public class LC3510_MinimumPairRemovaltoSortArrayII {
    /**
     * Given an array nums, you can perform the following operation any number of times:
     *
     * Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
     * Replace the pair with their sum.
     * Return the minimum number of operations needed to make the array non-decreasing.
     *
     * An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it
     * exists).
     *
     * Input: nums = [5,2,3,1]
     * Output: 2
     *
     * Input: nums = [1,2,2]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length, dec = 0;
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = nums[i];
        TreeSet<long[]> set = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? Long.compare(o1[0], o2[0]) : Long.compare(o1[1], o2[1]));
        TreeSet<Integer> pos = new TreeSet<>();
        for (int i = 0; i + 1 < n; i++) {
            set.add(new long[]{a[i] + a[i + 1], i});
            if (a[i] > a[i + 1]) dec++;
        }
        for (int i = 0; i < n; i++) pos.add(i);

        int res = 0;
        while (dec > 0) {
            res++;
            long[] t = set.first();
            set.remove(t);
            long s = t[0];
            int i = (int) t[1];

            // (cur, nxt)
            int nxt = pos.higher(i);
            if (a[i] > a[nxt]) dec--;

            // (pre, cur)
            Integer pre = pos.lower(i);
            if (pre != null) {
                if (a[pre] > a[i]) dec--;
                if (a[pre] > s) dec++;
                set.remove(new long[]{a[pre] + a[i], pre});
                set.add(new long[]{a[pre] + s, pre});
            }

            // (nxt, nxt2)
            Integer nxt2 = pos.higher(nxt);
            if (nxt2 != null) {
                if (a[nxt] > a[nxt2]) dec--;
                if (s > a[nxt2]) dec++;
                set.remove(new long[]{a[nxt] + a[nxt2], nxt});
                set.add(new long[]{s + a[nxt2], i});
            }

            a[i] = s;
            pos.remove(nxt);
        }
        return res;
    }
}
/**
 * 每次操作都是固定的
 * 每次操作需要考虑4个数: pre, i, nxt, nxt2
 * 删掉 nxt
 * if i > nxt: dec--
 * 然后看当前数字和前面那个数字:
 * if nums[pre] > nums[i]: dec-- // 先删掉
 * if nums[pre] > s: dec++
 */