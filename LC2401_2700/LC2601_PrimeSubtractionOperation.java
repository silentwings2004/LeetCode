package LC2401_2700;
import java.util.*;
public class LC2601_PrimeSubtractionOperation {
    /**
     * You are given a 0-indexed integer array nums of length n.
     *
     * You can perform the following operation as many times as you want:
     *
     * Pick an index i that you haven’t picked before, and pick a prime p strictly less than nums[i], then subtract p
     * from nums[i].
     * Return true if you can make nums a strictly increasing array using the above operation and false otherwise.
     *
     * A strictly increasing array is an array whose each element is strictly greater than its preceding element.
     *
     * Input: nums = [4,9,6,10]
     * Output: true
     *
     * Input: nums = [6,8,11,12]
     * Output: true
     *
     * Input: nums = [5,8,3]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 1000
     * nums.length == n
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 1010;
    boolean[] st;
    int[] primes;
    int cnt;
    public boolean primeSubOperation(int[] nums) {
        primes = new int[N];
        st = new boolean[N];
        cnt = 0;
        get_primes(N - 1);
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < cnt; i++) set.add(primes[i]);

        int n = nums.length;
        for (int i = n - 1; i - 1 >= 0; i--) {
            if (nums[i] > nums[i - 1]) continue;
            Integer hk = set.higher(nums[i - 1] - nums[i]);
            if (hk == null || hk >= nums[i - 1]) return false;
            nums[i - 1] -= hk;
        }
        return true;
    }

    private void get_primes(int n) {
        for (int i = 2; i <= n; i++) {
            if (!st[i]) primes[cnt++] = i;
            for (int j = 0; i * primes[j] <= n; j++) {
                st[i * primes[j]] = true;
                if (i % primes[j] == 0) break;
            }
        }
    }
}