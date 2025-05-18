package LC2401_2700;
import java.util.*;
public class LC2593_FindScoreofanArrayAfterMarkingAllElements {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * Starting with score = 0, apply the following algorithm:
     *
     * Choose the smallest integer of the array that is not marked. If there is a tie, choose the one with the smallest
     * index.
     * Add the value of the chosen integer to score.
     * Mark the chosen element and its two adjacent elements if they exist.
     * Repeat until all the array elements are marked.
     * Return the score you get after applying the above algorithm.
     *
     * Input: nums = [2,1,3,4,5,2]
     * Output: 7
     *
     * Input: nums = [2,3,5,1,3,2]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // S1: Heap
    // time = O(nlogn), space = O(n)
    public long findScore(int[] nums) {
        int n = nums.length;
        long res = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int i = 0; i < n; i++) pq.offer(new int[]{nums[i], i});
        boolean[] st = new boolean[n];

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int val = t[0], idx = t[1];
            if (!st[idx]) {
                res += val;
                st[idx] = true;
                if (idx - 1 >= 0) st[idx - 1] = true;
                if (idx + 1 < n) st[idx + 1] = true;
            }
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long findScore2(int[] nums) {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        int n = nums.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i] = new int[]{nums[i], i};
            map.putIfAbsent(nums[i], new HashSet<>());
            map.get(nums[i]).add(i);
        }
        Arrays.sort(a, (o1, o2) -> o1[0] - o2[0]);

        long res = 0;
        for (int i = 0; i < n; i++) {
            int val = a[i][0], idx = a[i][1];
            if (map.get(val).contains(idx)) {
                res += val;
                map.get(val).remove(idx);
                int l = idx - 1, r = idx + 1;
                if (l >= 0 && map.get(nums[l]).contains(l)) map.get(nums[l]).remove(l);
                if (r < n && map.get(nums[r]).contains(r)) map.get(nums[r]).remove(r);
            }
        }
        return res;
    }

    // S3
    // time = O(nlogn), space = O(n)
    public long findScore3(int[] nums) {
        int n = nums.length;
        TreeSet<int[]> set = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int i = 0; i < n; i++) set.add(new int[]{nums[i], i});
        long res = 0;
        while (!set.isEmpty()) {
            int[] t = set.first();
            res += t[0];
            int idx = t[1];
            set.remove(t);
            if (idx > 0) set.remove(new int[]{nums[idx - 1], idx - 1});
            if (idx + 1 < n) set.remove(new int[]{nums[idx + 1], idx + 1});
        }
        return res;
    }
}