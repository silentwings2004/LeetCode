package LC2401_2700;
import java.util.*;
public class LC2454_NextGreaterElementIV {
    /**
     * You are given a 0-indexed array of non-negative integers nums. For each integer in nums, you must find its
     * respective second greater integer.
     *
     * The second greater integer of nums[i] is nums[j] such that:
     *
     * j > i
     * nums[j] > nums[i]
     * There exists exactly one index k such that nums[k] > nums[i] and i < k < j.
     * If there is no such nums[j], the second greater integer is considered to be -1.
     *
     * For example, in the array [1, 2, 4, 3], the second greater integer of 1 is 4, 2 is 3, and that of 3 and 4 is -1.
     * Return an integer array answer, where answer[i] is the second greater integer of nums[i].
     *
     * Input: nums = [2,4,0,9,6]
     * Output: [9,6,6,-1,-1]
     *
     * Input: nums = [3,3]
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1: monotonic stack + pq
    // time = O(nlogn), space = O(n)
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] nextGreater = new int[n];
        Arrays.fill(nextGreater, n);
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && nums[stk.peek()] < nums[i]) nextGreater[stk.pop()] = i;
            stk.push(i);
        }

        int[][] p = new int[n][2];
        for (int i = 0; i < n; i++) p[i] = new int[]{nextGreater[i], i};
        Arrays.sort(p, (o1, o2) -> o1[0] - o2[0]);

        stk.clear();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0, j = 0; i < n; i++) {
            while (!pq.isEmpty() && pq.peek()[0] < nums[i]) {
                res[pq.poll()[1]] = nums[i];
            }
            if (p[j][0] >= i) continue;
            if (nums[p[j][1]] < nums[i]) res[p[j][1]] = nums[i];
            else pq.offer(new int[]{nums[p[j][1]], p[j][1]});
            j++;
            i--;
        }
        return res;
    }

    // S2: TreeSet + Iterator
    // time = O(nlogn), space = O(n)
    public int[] secondGreaterElement2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        int[][] p = new int[n][2];
        for (int i = 0; i < n; i++) p[i] = new int[]{nums[i], i};
        Arrays.sort(p, (o1, o2) -> o2[0] - o1[0]);

        TreeSet<Integer> set = new TreeSet<>();
        set.add(n);
        set.add(n + 1);

        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && p[j][0] == p[i][0]) j++;

            for (int k = i; k < j; k++) {
                Set<Integer> sub = set.tailSet(p[k][1]);
                Iterator<Integer> iter = sub.iterator();
                iter.next();
                int idx = iter.next();
                if (idx < n) res[p[k][1]] = nums[idx];
            }

            for (int k = i; k < j; k++) set.add(p[k][1]);
            i = j - 1;
        }
        return res;
    }

    // S3: Two Monotonic Stack
    // time = O(n), space = O(n)
    public int[] secondGreaterElement3(int[] nums) {
        int n = nums.length;
        int[] stk1 = new int[n + 1], stk2 = new int[n + 1];
        int tt1 = 0, tt2 = 0;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            while (tt2 > 0 && nums[stk2[tt2]] < nums[i]) res[stk2[tt2--]] = nums[i];
            int pos = tt1;
            while (tt1 > 0 && nums[stk1[tt1]] < nums[i]) tt1--;
            for (int j = tt1 + 1; j <= pos; j++) stk2[++tt2] = stk1[j];
            stk1[++tt1] = i;
        }
        return res;
    }
}