package LC2401_2700;
import java.util.*;
public class LC2584_SplittheArraytoMakeCoprimeProducts {
    /**
     * You are given a 0-indexed integer array nums of length n.
     *
     * A split at an index i where 0 <= i <= n - 2 is called valid if the product of the first i + 1 elements and the
     * product of the remaining elements are coprime.
     *
     * For example, if nums = [2, 3, 3], then a split at the index i = 0 is valid because 2 and 9 are coprime, while a
     * split at the index i = 1 is not valid because 6 and 3 are not coprime. A split at the index i = 2 is not valid
     * because i == n - 1.
     * Return the smallest index i at which the array can be split validly or -1 if there is no such split.
     *
     * Two values val1 and val2 are coprime if gcd(val1, val2) == 1 where gcd(val1, val2) is the greatest common divisor
     * of val1 and val2.
     *
     * Input: nums = [4,7,8,15,3,5]
     * Output: 2
     *
     * Input: nums = [4,7,15,8,3,5]
     * Output: -1
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 10^4
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int findValidSplit(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            List<Integer> primes = get_primes(nums[i]);
            int t = i;
            for (int x : primes) {
                if (map.containsKey(x)) t = Math.min(x, map.get(x));
                else map.put(x, i);
            }
            arr[i] = new int[]{t, i};
        }
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
        int st = -1, ed = -1, k = 0, res = -1;
        for (int[] x : arr) {
            if (ed < x[0]) {
                if (st != -1) {
                    k++;
                    if (res == -1) res = ed;
                }
                st = x[0];
                ed = x[1];
            } else ed = Math.max(ed, x[1]);
        }
        if (st != -1) k++;
        if (k == 1) return -1;
        return res;
    }

    private List<Integer> get_primes(int x) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                primes.add(i);
                while (x % i == 0) x /= i;
            }
        }
        if (x > 1) primes.add(x);
        return primes;
    }

    // S2
    // time = O(n * sqrt(n)), space = O(n)
    final int N = 1000010;
    int[] primes;
    boolean[] st;
    int cnt;
    public int findValidSplit2(int[] nums) {
        primes = new int[N];
        st = new boolean[N];
        cnt = 0;
        getPrimes(N - 1);

        int n = nums.length;
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = 0; j < cnt; j++) {
                int p = primes[j];
                if (x == 1) break;
                if (p * p > x) {
                    int[] t = new int[2];
                    if (!map.containsKey(x)) t[0] = i;
                    else t[0] = map.get(x)[0];
                    t[1] = i;
                    map.put(x, t);
                    break;
                }

                if (x % p == 0) {
                    int[] t = new int[2];
                    if (!map.containsKey(p)) t[0] = i;
                    else t[0] = map.get(p)[0];
                    t[1] = i;
                    map.put(p, t);
                    while (x % p == 0) x /= p;
                }
            }
        }

        int[] diff = new int[N];
        for (int k : map.keySet()) {
            int[] v = map.get(k);
            int a = v[0], b = v[1];
            diff[a]++;
            diff[b]--;
        }

        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += diff[i];
            if (sum == 0) return i;
        }
        return -1;
    }

    private void getPrimes(int n) {
        for (int i = 2; i <= n; i++) {
            if (!st[i]) primes[cnt++] = i;
            for (int j = 0; i * primes[j] <= n; j++) {
                st[i * primes[j]] = true;
                if (i % primes[j] == 0) break;
            }
        }
    }
}
/**
 * 既然是互质，一个很自然的想法就是从质因数入手。
 * 如果某个质因数p在nums里存在的范围是从[a:b]，
 * 那么显然，我们所寻找的前缀切割位置不能在[a:b]的中间，否则切割前后的两部分就会有公约数p。
 * 考察每个元素，记录它的所有质因数。然后对每种质因数，我们记录它在nums里出现的范围，
 * 只要记录第一次出现和最后一次出现的位置即可，记做一个区间[a:b]
 * 之后我们要寻找某个前缀的位置，使其不能切割任何一个区间。
 * 很明显我们可以用扫描线（差分数组）来做。
 * 对于一个区间[a,b]，我们就记录差分diff[a]+=1, diff[b]-=1，
 * 这样当我们从前往后的积分值第一次出现零的时候，就表示该处没有落在任何区间里面，即是符合条件的前缀截止位置。
 */