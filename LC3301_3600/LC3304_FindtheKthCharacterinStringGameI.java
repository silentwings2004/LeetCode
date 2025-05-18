package LC3301_3600;

public class LC3304_FindtheKthCharacterinStringGameI {
    /**
     * Alice and Bob are playing a game. Initially, Alice has a string word = "a".
     *
     * You are given a positive integer k.
     *
     * Now Bob will ask Alice to perform the following operation forever:
     *
     * Generate a new string by changing each character in word to its next character in the English alphabet, and
     * append it to the original word.
     * For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates "zbac".
     *
     * Return the value of the kth character in word, after enough operations have been done for word to have at least
     * k characters.
     *
     * Note that the character 'z' can be changed to 'a' in the operation.
     *
     * Input: k = 5
     * Output: "b"
     *
     * Input: k = 10
     * Output: "c"
     *
     * Constraints:
     *
     * 1 <= k <= 500
     * @param k
     * @return
     */
    // S1
    // time = O(k), space = O(k)
    public char kthCharacter(int k) {
        StringBuilder sb = new StringBuilder();
        sb.append('a');
        while (sb.length() < k) {
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < sb.length(); i++) {
                int u = sb.charAt(i) - 'a';
                u = (u + 1) % 26;
                sb2.append((char)(u + 'a'));
            }
            sb.append(sb2);
        }
        return sb.charAt(k - 1);
    }

    // S2
    // time = O(logk), space = O(1)
    public char kthCharacter2(int k) {
        return (char)('a' + Integer.bitCount(k - 1));
    }
}