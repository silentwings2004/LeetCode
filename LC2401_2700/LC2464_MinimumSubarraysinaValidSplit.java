package LC2401_2700;
import java.util.*;
public class LC2464_MinimumSubarraysinaValidSplit {
    /**
     * You are given an integer array nums.
     *
     * Splitting of an integer array nums into subarrays is valid if:
     *
     * the greatest common divisor of the first and last elements of each subarray is greater than 1, and
     * each element of nums belongs to exactly one subarray.
     * Return the minimum number of subarrays in valid subarray splitting of nums.
     *
     * Note that:
     *
     * The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
     * A subarray is a contiguous part of an array.
     *
     * Input: nums = [2,6,3,4,3]
     * Output: 2
     *
     * Input: nums = [3,5]
     * Output: 2
     *
     * Input: nums = [1,2,1]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1: DP
    // time = O(n^2), space = O(n)
    public int validSubarraySplit(int[] nums) {
        int n = nums.length, INF = 0x3f3f3f3f;
        int[] f = new int[n + 1];
        Arrays.fill(f, INF);
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                if (gcd(nums[j - 1], nums[i - 1]) > 1) {
                    f[i] = Math.min(f[i], f[j - 1] + 1);
                }
            }
        }
        return f[n] == INF ? -1 : f[n];
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2: Math + BFS
    // time = O(n * sqrt(n)), space = O(n)
    public int validSubarraySplit2(int[] nums) {
        int n = nums.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<Integer>[] primes = new List[n];
        for (int i = 0; i < n; i++) primes[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = 2; j <= x / j; j++) {
                if (x % j == 0) {
                    map.putIfAbsent(j, new ArrayList<>());
                    map.get(j).add(i);
                    primes[i].add(j);
                    while (x % j == 0) x /= j;
                }
            }
            if (x > 1) {
                map.putIfAbsent(x, new ArrayList<>());
                map.get(x).add(i);
                primes[i].add(x);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        int step = 0;
        boolean[] st = new boolean[n];
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int t = queue.poll();
                if (nums[t] == 1) continue;
                st[t] = true;
                if (t == n - 1) return step + 1;
                for (int x : primes[t]) {
                    for (int next : map.get(x)) {
                        if (next == t) continue;
                        if (next == n - 1) return step + 1;
                        if (next + 1 <= t) continue;
                        if (st[next + 1]) continue;
                        queue.offer(next + 1);
                        st[next + 1] = true;
                    }
                }
                if (!st[t + 1]) {
                    queue.offer(t + 1);
                    st[t + 1] = true;
                }
            }
            step++;
        }
        return -1;
    }
}
