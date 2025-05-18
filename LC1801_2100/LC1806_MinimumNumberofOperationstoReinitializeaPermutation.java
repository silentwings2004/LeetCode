package LC1801_2100;
import java.util.*;
public class LC1806_MinimumNumberofOperationstoReinitializeaPermutation {
    /**
     * You are given an even integer n. You initially have a permutation perm of size n where perm[i] == i (0-indexed).
     *
     * In one operation, you will create a new array arr, and for each i:
     *
     * If i % 2 == 0, then arr[i] = perm[i / 2].
     * If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
     * You will then assign arr to perm.
     *
     * Return the minimum non-zero number of operations you need to perform on perm to return the permutation to its
     * initial value.
     *
     * Input: n = 2
     * Output: 1
     *
     * Input: n = 4
     * Output: 2
     *
     * Input: n = 6
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= n <= 1000
     * n is even.
     * @param n
     * @return
     */
    // S1: Simulation
    // time = O(n^2), space = O(n)
    public int reinitializePermutation(int n) {
        int[] perm = new int[n], backup = new int[n];
        for (int i = 0; i < n; i++) perm[i] = backup[i] = i;

        int cnt = 0;
        int[] arr = new int[n];
        while (true) {
            for (int i = 0; i < n; i++) {
                arr[i] = i % 2 == 0 ? perm[i / 2] : perm[n / 2 + (i - 1) / 2];
            }
            cnt++;
            if (Arrays.equals(arr, backup)) break;
            perm = arr.clone();
        }
        return cnt;
    }

    // S2: Math (Euler Theorem)
    // time = O(n), space = O(1)
    public int reinitializePermutation2(int n) {
        if (n == 2) return 1;
        int p = 2, k = 1;
        while (p % (n - 1) != 1) {
            p = p * 2 % (n - 1);
            k++;
        }
        return k;
    }
}
/**
 * 本题的操作是：将数组的前半部分元素重新分配到偶数位（index=0,2,4,6...），
 * 将数组的后半部分元素重新分配到奇数位（index=1,3,5,7...）。
 * if i < n/2, f(i) = i*2
 * if n/2 <= i < n, f(i) = i*2 - (n-1)
 * 非常危险，只有对1有效，如果track的比如是3，就会有问题，但整个数组的周期和某个元素未必相等
 * f(i) = i * 2 mod (n - 1)
 * f^k(i) = i * 2^k mod (n - 1) => 2^k mod(n - 1) = 1
 * => 2和n-1互质 => k = phi(n - 1) => k <= phi(n - 1) <= n - 1 => k <= n - 1 => O(n)
 * 注意：这道题如果求n-1的欧拉函数则不正确，因为phi[n-1]只是一个可行解，但并不能保证得到的k是最小的！
 */