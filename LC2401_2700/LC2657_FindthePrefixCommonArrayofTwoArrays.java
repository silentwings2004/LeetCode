package LC2401_2700;

public class LC2657_FindthePrefixCommonArrayofTwoArrays {
    /**
     * You are given two 0-indexed integer permutations A and B of length n.
     *
     * A prefix common array of A and B is an array C such that C[i] is equal to the count of numbers that are present
     * at or before the index i in both A and B.
     *
     * Return the prefix common array of A and B.
     *
     * A sequence of n integers is called a permutation if it contains all integers from 1 to n exactly once.
     *
     * Input: A = [1,3,2,4], B = [3,1,2,4]
     * Output: [0,2,3,4]
     *
     * Input: A = [2,3,1], B = [3,1,2]
     * Output: [0,1,3]
     *
     * Constraints:
     *
     * 1 <= A.length == B.length == n <= 50
     * 1 <= A[i], B[i] <= n
     * It is guaranteed that A and B are both a permutation of n integers.
     * @param A
     * @param B
     * @return
     */
    // time = O(n), space = O(n)
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        boolean[] st = new boolean[n + 1];
        int[] res = new int[n];
        for (int i = 0, s = 0; i < n; i++) {
            if (st[A[i]]) s++;
            else st[A[i]] = true;
            if (st[B[i]]) s++;
            else st[B[i]] = true;
            res[i] = s;
        }
        return res;
    }
}