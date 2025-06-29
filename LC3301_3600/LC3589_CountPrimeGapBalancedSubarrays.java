package LC3301_3600;
import java.util.*;
public class LC3589_CountPrimeGapBalancedSubarrays {
    /**
     * You are given an integer array nums and an integer k.
     *
     * A subarray is called prime-gap balanced if:
     *
     * It contains at least two prime numbers, and
     * The difference between the maximum and minimum prime numbers in that subarray is less than or equal to k.
     * Return the count of prime-gap balanced subarrays in nums.
     *
     * Note:
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     * A prime number is a natural number greater than 1 with only two factors, 1 and itself.
     *
     * Input: nums = [1,2,3], k = 1
     * Output: 2
     *
     * Input: nums = [2,3,5,7], k = 3
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 5 * 10^4
     * 0 <= k <= 5 * 10^4
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int primeSubarray(int[] nums, int k) {
        HashSet<Integer> primes = get_primes(50000);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        int n = nums.length, res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int x = nums[i];
            if (primes.contains(x)) {
                map.put(x, map.getOrDefault(x, 0) + 1);
                while (map.lastKey() - map.firstKey() > k) {
                    int y = nums[j];
                    if (!primes.contains(y)) {
                        j++;
                        continue;
                    }
                    map.put(y, map.get(y) - 1);
                    if (map.get(y) == 0) map.remove(y);
                    set.remove(j);
                    j++;
                }
                if (set.size() > 0) {
                    int r = set.last();
                    res += r - j + 1;
                }
                set.add(i);
            } else {
                if (set.size() > 1) {
                    int r = set.lower(set.last());
                    res += r - j + 1;
                }
            }
        }
        return res;
    }

    private HashSet<Integer> get_primes(int n) {
        boolean[] st = new boolean[n + 1];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            if (!st[i]) {
                set.add(i);
                for (int j = i + i; j <= n; j += i) {
                    st[j] = true;
                }
            }
        }
        return set;
    }

    // S2
    // time = O(n), space = O(n)
    final int N = 500010;
    boolean[] np = new boolean[N];
    boolean f = false;
    public int primeSubarray2(int[] nums, int k) {
        init();
        Deque<Integer> minq = new LinkedList<>();
        Deque<Integer> maxq = new LinkedList<>();
        int a = -1, b = -1;
        int res = 0, l = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (!np[x]) {
                b = a;
                a = i;

                while (!minq.isEmpty() && x <= nums[minq.peekLast()]) minq.pollLast();
                minq.offerLast(i);
                while (!maxq.isEmpty() && x >= nums[maxq.peekLast()]) maxq.pollLast();
                maxq.offerLast(i);

                while (nums[maxq.peekFirst()] - nums[minq.peekFirst()] > k) {
                    l++;
                    if (minq.peekFirst() < l) minq.pollFirst();
                    if (maxq.peekFirst() < l) maxq.pollFirst();
                }
            }
            res += b - l + 1;
        }
        return res;
    }

    private void init() {
        if (f) return;
        f = true;
        np[1] = true;
        for (int i = 2; i * i < N; i++) {
            if (np[i]) continue;
            for (int j = i * i; j < N; j += i) {
                np[j] = true;
            }
        }
    }
}