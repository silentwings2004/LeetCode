package LC3001_3300;
import java.util.*;
public class LC3215_CountTripletswithEvenXORSetBitsII {
    /**
     * Given three integer arrays a, b, and c, return the number of triplets (a[i], b[j], c[k]), such that the bitwise
     * XOR between the elements of each triplet has an even number of set bits.
     *
     * Input: a = [1], b = [2], c = [3]
     * Output: 1
     *
     * Input: a = [1,1], b = [2,3], c = [1,5]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= a.length, b.length, c.length <= 10^5
     * 0 <= a[i], b[i], c[i] <= 10^9
     * @param a
     * @param b
     * @param c
     * @return
     */
    // time = O(n), space = O(1)
    public long tripletCount(int[] a, int[] b, int[] c) {
        long[] c1 = helper(a), c2 = helper(b), c3 = helper(c);
        return c1[0] * c2[0] * c3[0] + c1[1] * c2[1] * c3[0] + c1[1] * c2[0] * c3[1] + c1[0] * c2[1] * c3[1];
    }

    private long[] helper(int[] a) {
        long[] c = new long[3];
        for (int x : a) {
            int t = Integer.bitCount(x);
            c[t % 2]++;
        }
        return c;
    }
}