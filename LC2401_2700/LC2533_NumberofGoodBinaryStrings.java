package LC2401_2700;

public class LC2533_NumberofGoodBinaryStrings {
    /**
     * You are given four integers minLenght, maxLength, oneGroup and zeroGroup.
     *
     * A binary string is good if it satisfies the following conditions:
     *
     * The length of the string is in the range [minLength, maxLength].
     * The size of each block of consecutive 1's is a multiple of oneGroup.
     * For example in a binary string 00110111100 sizes of each block of consecutive ones are [2,4].
     * The size of each block of consecutive 0's is a multiple of zeroGroup.
     * For example, in a binary string 00110111100 sizes of each block of consecutive ones are [2,1,2].
     * Return the number of good binary strings. Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Note that 0 is considered a multiple of all the numbers.
     *
     * Input: minLength = 2, maxLength = 3, oneGroup = 1, zeroGroup = 2
     * Output: 5
     *
     * Input: minLength = 4, maxLength = 4, oneGroup = 4, zeroGroup = 3
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= minLength <= maxLength <= 10^5
     * 1 <= oneGroup, zeroGroup <= maxLength
     * @param minLength
     * @param maxLength
     * @param oneGroup
     * @param zeroGroup
     * @return
     */
    // time = O(n), space = O(n)
    public int goodBinaryStrings(int minLength, int maxLength, int oneGroup, int zeroGroup) {
        long mod = (long)(1e9 + 7), res = 0;
        long[] f = new long[maxLength + 1];
        f[0] = 1;

        for (int i = 1; i <= maxLength; i++) {
            if (i >= oneGroup) f[i] = f[i - oneGroup];
            if (i >= zeroGroup) f[i] = (f[i] + f[i - zeroGroup]) % mod;
            if (i >= minLength) res = (res + f[i]) % mod;
        }
        return (int) res;
    }
}
