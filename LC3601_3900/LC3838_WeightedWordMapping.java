package LC3601_3900;

public class LC3838_WeightedWordMapping {
    /**
     * You are given an array of strings words, where each string represents a word containing lowercase English letters.
     *
     * You are also given an integer array weights of length 26, where weights[i] represents the weight of the ith
     * lowercase English letter.
     *
     * The weight of a word is defined as the sum of the weights of its characters.
     *
     * For each word, take its weight modulo 26 and map the result to a lowercase English letter using reverse
     * alphabetical order (0 -> 'z', 1 -> 'y', ..., 25 -> 'a').
     *
     * Return a string formed by concatenating the mapped characters for all words in order.
     *
     * Input: words = ["abcd","def","xyz"], weights = [5,3,12,14,1,2,3,2,10,6,6,9,7,8,7,10,8,9,6,9,9,8,3,7,7,2]
     * Output: "rij"
     *
     * Input: words = ["a","b","c"], weights = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]
     * Output: "yyy"
     *
     * Input: words = ["abcd"], weights = [7,5,3,4,3,5,4,9,4,2,2,7,10,2,5,10,6,1,2,2,4,1,3,4,4,5]
     * Output: "g"
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 10
     * weights.length == 26
     * 1 <= weights[i] <= 100
     * words[i] consists of lowercase English letters.
     * @param words
     * @param weights
     * @return
     */
    // time = O(n * m), space = O(n)
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            int n = w.length(), s = 0;
            for (int i = 0; i < n; i++) {
                int u = w.charAt(i) - 'a';
                s += weights[u];
            }
            sb.append((char)('a' + 25 - s % 26));
        }
        return sb.toString();
    }
}