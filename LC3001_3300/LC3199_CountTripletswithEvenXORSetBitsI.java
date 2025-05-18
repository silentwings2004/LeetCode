package LC3001_3300;

public class LC3199_CountTripletswithEvenXORSetBitsI {
    /**
     * Given three integer arrays a, b, and c, return the number of triplets (a[i], b[j], c[k]), such that the bitwise
     * XOR of the elements of each triplet has an even number of set bits.
     *
     * Input: a = [1], b = [2], c = [3]
     * Output: 1
     *
     * Input: a = [1,1], b = [2,3], c = [1,5]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= a.length, b.length, c.length <= 100
     * 0 <= a[i], b[i], c[i] <= 100
     * @param a
     * @param b
     * @param c
     * @return
     */
    // S1
    // time = O(n^3), space = O(1)
    public int tripletCount(int[] a, int[] b, int[] c) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                for (int k = 0; k < c.length; k++) {
                    int v = a[i] ^ b[j] ^ c[k];
                    if (Integer.bitCount(v) % 2 == 0) res++;
                }
            }
        }
        return res;
    }

    // S2
    // time = O(n * logk), space = O(1)
    public int tripletCount2(int[] a, int[] b, int[] c) {
        int[] c1 = helper(a), c2 = helper(b), c3 = helper(c);
        return c1[0] * c2[0] * c3[1] + c1[0] * c2[1] * c3[0] + c1[1] * c2[0] * c3[0] + c1[1] * c2[1] * c3[1];
    }

    private int[] helper(int[] nums) {
        int[] res = new int[2];
        for (int x : nums) {
            int v = Integer.bitCount(x);
            if (v % 2 == 1) res[0]++;
            else res[1]++;
        }
        return res;
    }
}
