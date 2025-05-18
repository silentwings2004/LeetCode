package LC2401_2700;

public class LC2600_KItemsWiththeMaximumSum {
    /**
     * There is a bag that consists of items, each item has a number 1, 0, or -1 written on it.
     *
     * You are given four non-negative integers numOnes, numZeros, numNegOnes, and k.
     *
     * The bag initially contains:
     *
     * numOnes items with 1s written on them.
     * numZeroes items with 0s written on them.
     * numNegOnes items with -1s written on them.
     * We want to pick exactly k items among the available items. Return the maximum possible sum of numbers written on
     * the items.
     *
     * Input: numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
     * Output: 2
     *
     * Input: numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
     * Output: 3
     *
     * Constraints:
     *
     * 0 <= numOnes, numZeros, numNegOnes <= 50
     * 0 <= k <= numOnes + numZeros + numNegOnes
     * @param numOnes
     * @param numZeros
     * @param numNegOnes
     * @param k
     * @return
     */
    // time = O(1), space = O(1)
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) return k;
        k -= numOnes;
        if (numZeros >= k) return numOnes;
        k -= numZeros;
        return numOnes - k;
    }
}