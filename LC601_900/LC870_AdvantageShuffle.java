package LC601_900;
import java.util.*;
public class LC870_AdvantageShuffle {
    /**
     * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for
     * which A[i] > B[i].
     *
     * Return any permutation of A that maximizes its advantage with respect to B.
     *
     * Input: A = [2,7,11,15], B = [1,10,4,11]
     * Output: [2,11,7,15]
     *
     * Note:
     *
     * 1 <= A.length = B.length <= 10000
     * 0 <= A[i] <= 10^9
     * 0 <= B[i] <= 10^9
     * @param A
     * @param B
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int[] advantageCount(int[] A, int[] B) {
        // corner case
        if (A == null || A.length == 0 || B == null || B.length == 0) return new int[0];

        int n = A.length;
        int[] res = new int[n];

        Arrays.sort(A); // O(nlogn)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < n; i++) maxHeap.offer(new int[]{i, B[i]}); // O(nlogn)

        int hi = n - 1, lo = 0;
        while (!maxHeap.isEmpty()) {  // O(nlogn)
            int idx = maxHeap.peek()[0], val = maxHeap.poll()[1];
            if (A[hi] > val) res[idx] = A[hi--];
            else res[idx] = A[lo++];
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int[] advantageCount2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Arrays.sort(nums1);
        Integer[] id = new Integer[n];
        for (int i = 0; i < n; i++) id[i] = i;
        Arrays.sort(id, (o1, o2) -> nums2[o1] - nums2[o2]);

        int[] res = new int[n];
        int l = 0, r = n - 1;
        for (int x : nums1) {
            if (x > nums2[id[l]]) res[id[l++]] = x;
            else res[id[r--]] = x;
        }
        return res;
    }
}
/**
 * 首先2个数组一起从小到大排序
 * (1) a > b => a 赢 b
 * (2) a <= b => a 谁都赢不了，占掉b最大的一个
 * 贪心的思路
 * 证明这个方案是否最优？
 * 任给一个最优解，转化成贪心解
 * 从前往后看，找到第一个最优解和贪心解不同的位置
 * (1) a > b => a vs y, b vs x => a vs b, x vs y
 * (2) a <= 最小值 => a vs y, x vs b => a vs b, x vs y
 */
