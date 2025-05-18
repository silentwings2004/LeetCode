package LC2401_2700;

public class LC2498_FrogJumpII {
    /**
     * You are given a 0-indexed integer array stones sorted in strictly increasing order representing the positions
     * of stones in a river.
     *
     * A frog, initially on the first stone, wants to travel to the last stone and then return to the first stone.
     * However, it can jump to any stone at most once.
     *
     * The length of a jump is the absolute difference between the position of the stone the frog is currently on and
     * the position of the stone to which the frog jumps.
     *
     * More formally, if the frog is at stones[i] and is jumping to stones[j], the length of the jump is
     * |stones[i] - stones[j]|.
     * The cost of a path is the maximum length of a jump among all jumps in the path.
     *
     * Return the minimum cost of a path for the frog.
     *
     * Input: stones = [0,2,5,6,7]
     * Output: 5
     *
     * Input: stones = [0,3,9]
     * Output: 9
     *
     * Constraints:
     *
     * 2 <= stones.length <= 10^5
     * 0 <= stones[i] <= 10^9
     * stones[0] == 0
     * stones is sorted in a strictly increasing order.
     * @param stones
     * @return
     */
    // S1: Greedy
    // time = O(n), space = O(1)
    public int maxJump(int[] stones) {
        int n = stones.length, res = 0;
        if (n == 2) return stones[1];
        for (int i = 0; i + 2 < n; i++) res = Math.max(res, stones[i + 2] - stones[i]);
        return res;
    }

    // S2: binary search
    // time = O(nlogn), space = O(1)
    public int maxJump2(int[] stones) {
        int n = stones.length;
        int l = 0, r = stones[n - 1];
        while (l < r) {
            int mid = l + r >> 1;
            if (check(stones, mid)) r = mid;
            else l = mid + 1;
        }
        return check(stones, r) ? r : -1;
    }

    private boolean check(int[] nums, int t) {
        int n = nums.length, last = nums[0];
        boolean[] st = new boolean[n];
        for (int i = 1; i < n; i++) {
            while (i < n && nums[i] - last <= t) i++;
            if (last == nums[i - 1]) return false;
            last = nums[i - 1];
            st[i - 1] = true;
        }

        last = nums[n - 1];
        for (int i = n - 2; i > 0; i--) {
            while (i > 0 && last - nums[i] <= t && st[i]) i--;
            if (last - nums[i] > t || st[i] || last == nums[i]) return false;
            if (i == 0) return true;
            last = nums[i];
            st[i] = true;
        }
        return true;
    }
}
/**
 * 本题可以看成两只青蛙从起点到终点，除了两处端点外，一个石头只能被一个青蛙踩
 * 任何时候，都让离得更远的青蛙先落地。
 * 该方案必然导致了两个青蛙轮流落地。
 * 所以，最优解法就是找全局最大的stones[i+2]-stones[i].
 */