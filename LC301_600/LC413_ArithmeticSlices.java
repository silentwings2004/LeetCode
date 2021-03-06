package LC301_600;
import java.util.*;
public class LC413_ArithmeticSlices {
    /**
     * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q)
     * such that 0 <= P < Q < N.
     *
     * A slice (P, Q) of the array A is called arithmetic if the sequence:
     * A[P], A[P + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
     *
     * The function should return the number of arithmetic slices in the array A.
     *
     * Example:
     *
     * A = [1, 2, 3, 4]
     *
     * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
     *
     * @param A
     * @return
     */
    // time = O(n), space = O(1)
    public int numberOfArithmeticSlices(int[] A) {
        // corner case
        if (A == null || A.length == 0) return 0;

        int[] dp = new int[A.length];
        int res = 0;

        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                res += dp[i];
            }
        }
        return res;
    }
}
