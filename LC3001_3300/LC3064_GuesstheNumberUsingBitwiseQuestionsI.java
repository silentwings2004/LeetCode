package LC3001_3300;

public class LC3064_GuesstheNumberUsingBitwiseQuestionsI {
    /**
     * There is a number n that you have to find.
     *
     * There is also a pre-defined API int commonSetBits(int num), which returns the number of bits where both n and
     * num are 1 in that position of their binary representation. In other words, it returns the number of set bits in
     * n & num, where & is the bitwise AND operator.
     *
     * Return the number n.
     *
     * Input: n = 31
     * Output: 31
     *
     * Input: n = 33
     * Output: 33
     *
     * Constraints:
     *
     * 1 <= n <= 2^30 - 1
     * 0 <= num <= 2^30 - 1
     * If you ask for some num out of the given range, the output wouldn't be reliable.
     * @return
     */
    // time = O(1), space = O(1)
    public int findNumber() {
        int res = 0;
        for (int i = 0; i <= 30; i++) {
            if (commonSetBits(1 << i) == 1) res |= 1 << i;
        }
        return res;
    }
    // dummy function
    int commonSetBits(int num) {return 0;};
}
