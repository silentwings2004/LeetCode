package LC3301_3600;

public class LC3307_FindtheKthCharacterinStringGameII {
    /**
     * Alice and Bob are playing a game. Initially, Alice has a string word = "a".
     *
     * You are given a positive integer k. You are also given an integer array operations, where operations[i]
     * represents the type of the ith operation.
     *
     * Now Bob will ask Alice to perform all operations in sequence:
     *
     * If operations[i] == 0, append a copy of word to itself.
     * If operations[i] == 1, generate a new string by changing each character in word to its next character in the
     * English alphabet, and append it to the original word. For example, performing the operation on "c" generates "cd"
     * and performing the operation on "zb" generates "zbac".
     * Return the value of the kth character in word after performing all the operations.
     *
     * Note that the character 'z' can be changed to 'a' in the second type of operation.
     *
     * Input: k = 5, operations = [0,0,0]
     * Output: "a"
     *
     * Input: k = 10, operations = [0,1,0,1]
     * Output: "b"
     *
     * Constraints:
     *
     * 1 <= k <= 10^14
     * 1 <= operations.length <= 100
     * operations[i] is either 0 or 1.
     * The input is generated such that word has at least k characters after all operations.
     * @param k
     * @param operations
     * @return
     */
    // time = O(nlogk), space = O(1)
    public char kthCharacter(long k, int[] operations) {
        k--;
        int t = 0;
        for (int o : operations) {
            if ((k & 1) == 1 && o == 1) t++;
            k >>= 1;
        }
        return (char)('a' + t % 26);
    }
}