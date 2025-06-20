package LC3301_3600;

public class LC3577_CounttheNumberofComputerUnlockingPermutations {
    /**
     * You are given an array complexity of length n.
     *
     * There are n locked computers in a room with labels from 0 to n - 1, each with its own unique password. The
     * password of the computer i has a complexity complexity[i].
     *
     * The password for the computer labeled 0 is already decrypted and serves as the root. All other computers must
     * be unlocked using it or another previously unlocked computer, following this information:
     *
     * You can decrypt the password for the computer i using the password for computer j, where j is any integer less
     * than i with a lower complexity. (i.e. j < i and complexity[j] < complexity[i])
     * To decrypt the password for computer i, you must have already unlocked a computer j such that j < i and
     * complexity[j] < complexity[i].
     * Find the number of permutations of [0, 1, 2, ..., (n - 1)] that represent a valid order in which the computers
     * can be unlocked, starting from computer 0 as the only initially unlocked one.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Note that the password for the computer with label 0 is decrypted, and not the computer with the first position
     * in the permutation.
     *
     * Input: complexity = [1,2,3]
     * Output: 2
     *
     * Input: complexity = [3,3,3,4,4,4]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= complexity.length <= 10^5
     * 1 <= complexity[i] <= 10^9
     * @param complexity
     * @return
     */
    // time = O(n),s space = O(1)
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        long mod = (long)(1e9 + 7), res = 1;
        for (int i = 1; i < n; i++) {
            if (complexity[i] <= complexity[0]) return 0;
            res = res * i % mod;
        }
        return (int)res;
    }
}