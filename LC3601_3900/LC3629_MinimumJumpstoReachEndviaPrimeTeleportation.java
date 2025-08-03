package LC3601_3900;
import java.util.*;
public class LC3629_MinimumJumpstoReachEndviaPrimeTeleportation {
    /**
     * You are given an integer array nums of length n.
     *
     * You start at index 0, and your goal is to reach index n - 1.
     *
     * From any index i, you may perform one of the following operations:
     *
     * Adjacent Step: Jump to index i + 1 or i - 1, if the index is within bounds.
     * Prime Teleportation: If nums[i] is a prime number p, you may instantly jump to any index j != i such that
     * nums[j] % p == 0.
     * Return the minimum number of jumps required to reach index n - 1.
     *
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     *
     * Note: Please do not copy the description during the contest to maintain the integrity of your submissions.
     *
     * Input: nums = [1,2,4,6]
     * Output: 2
     *
     * Input: nums = [2,3,4,7,9]
     * Output: 2
     *
     * Input: nums = [4,6,5,8]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(nlogU), space = O(nlogU) U = max(nums)
    HashSet<Integer> set;
    HashMap<Integer, List<Integer>> map;
    int[] nums;
    public int minJumps(int[] nums) {
        this.nums = nums;
        set = new HashSet<>();
        map = new HashMap<>();
        int n = nums.length;
        if (n == 1) return 0;
        for (int i = 0; i < n; i++) work(i);

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        boolean[] st = new boolean[n];
        st[0] = true;

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int u = q.poll();
                if (u == n - 1) return step;
                if (u + 1 < n && !st[u + 1]) {
                    st[u + 1] = true;
                    q.offer(u + 1);
                }
                if (u - 1 >= 0 && !st[u - 1]) {
                    st[u - 1] = true;
                    q.offer(u - 1);
                }
                if (set.contains(nums[u])) {
                    for (int v : map.getOrDefault(nums[u], new ArrayList<>())) {
                        if (st[v]) continue;
                        st[v] = true;
                        q.offer(v);
                    }
                    map.remove(nums[u]);
                }
            }
            step++;
        }
        return -1;
    }

    private void work(int u) {
        int x = nums[u];
        boolean f = true;
        if (x < 2) {
            f = false;
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(u);
            return;
        }
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                f = false;
                map.putIfAbsent(i, new ArrayList<>());
                map.get(i).add(u);
                while (x % i == 0) x /= i;
            }
        }
        if (f) set.add(nums[u]);
        if (x > 1) {
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(u);
        }
    }
}