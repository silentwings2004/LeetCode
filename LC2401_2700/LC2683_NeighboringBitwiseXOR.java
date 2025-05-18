package LC2401_2700;

public class LC2683_NeighboringBitwiseXOR {
    /**
     * A 0-indexed array derived with length n is derived by computing the bitwise XOR (⊕) of adjacent values in a
     * binary array original of length n.
     *
     * Specifically, for each index i in the range [0, n - 1]:
     *
     * If i = n - 1, then derived[i] = original[i] ⊕ original[0].
     * Otherwise, derived[i] = original[i] ⊕ original[i + 1].
     * Given an array derived, your task is to determine whether there exists a valid binary array original that could
     * have formed derived.
     *
     * Return true if such an array exists or false otherwise.
     *
     * A binary array is an array containing only 0's and 1's
     *
     * Input: derived = [1,1,0]
     * Output: true
     *
     * Input: derived = [1,1]
     * Output: true
     *
     * Input: derived = [1,0]
     * Output: false
     *
     * Constraints:
     *
     * n == derived.length
     * 1 <= n <= 10^5
     * The values in derived are either 0's or 1's
     * @param derived
     * @return
     */
    // time = O(n), space = O(n)
    public boolean doesValidArrayExist(int[] derived) {
        int n = derived.length;
        int a = 0, b = 0;
        for (int i = 0; i + 1 < n; i++) {
            b = a ^ derived[i];
            a = b;
        }
        return (a ^ 0) == derived[n - 1];
    }
}