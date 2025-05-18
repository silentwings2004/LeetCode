package LC001_300;
import java.util.*;
public class LC60_PermutationSequence {
    /**
     * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
     *
     * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
     *
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * Given n and k, return the kth permutation sequence.
     *
     * Input: n = 3, k = 3
     * Output: "213"
     *
     * Constraints:
     *
     * 1 <= n <= 9
     * 1 <= k <= n!
     * @param n
     * @param k
     * @return
     */
    // S1:
    // time = O(n^2), space = O(n)
    public String getPermutation(int n, int k) {
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= n; i++) digits.add(i);

        k--; // make it 0-index
        String res = "";

        while (n > 0) {
            int a = k / fact(n - 1);
            res += (digits.get(a)); // 确定最高位

            k -= a * fact(n - 1); // 一位一位确定，循环n次,一直循环到n = 1 -> k = 0
            n--;

            digits.remove(a); // 确定一位就移除一位，在剩下的数字当中去选择 -> delete: O(n)
        }
        return res;
    }

    private int fact(int k) {
        int res = 1;
        for (int i = 1; i <= k; i++) {
            res *= i;
        }
        return res;
    }

    // S2
    // time = O(n^2), space = O(n)
    public String getPermutation2(int n, int k) {
        StringBuilder sb = new StringBuilder();
        boolean[] st = new boolean[10];
        for (int i = 0; i < n; i++) {
            int fact = 1;
            for (int j = 1; j <= n - 1 - i; j++) fact *= j;

            for (int j = 1; j <= n; j++) {
                if (!st[j]) {
                    if (fact < k) k -= fact;
                    else {
                        sb.append(j);
                        st[j] = true;
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    // S3: next permutation
    // time = O(n * k), space = O(n)
    int n;
    public String getPermutation3(int n, int k) {
        this.n = n;
        char[] nums = new char[n];
        for (int i = 1; i <= n; i++) nums[i - 1] = (char)(i + '0');
        while (k > 1) {
            nums = nextPermutation(nums);
            k--;
        }
        return String.valueOf(nums);
    }

    private char[] nextPermutation(char[] nums) {
        int i = n - 1;
        while (i > 0 && nums[i] <= nums[i - 1]) i--;
        if (i == 0) {
            reverse(nums, 0, n - 1);
            return nums;
        }
        int j = n - 1;
        while (j >= i && nums[j] <= nums[i - 1]) j--;
        swap(nums, i - 1, j);
        reverse(nums, i, n - 1);
        return nums;
    }

    private void reverse(char[] nums, int i, int j) {
        while (i < j) swap(nums, i++, j--);
    }

    private void swap(char[] nums, int i, int j) {
        char c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }
}
/**
 * n = 4, k = 10
 * 第1位填1 => 6个数
 * 第1位填2 => 12个数 => 第1位填2
 * 一共有n位 => 最多需要遍历n次 => O(n^2)
 * 动态的删除一个数，动态的找到第k大的数 => 平衡树 O(n * (logn)^2)
 */