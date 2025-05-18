package LC3001_3300;
import java.util.*;
public class LC3253_ConstructStringwithMinimumCostI {
    /**
     * You are given a string target, an array of strings words, and an integer array costs, both arrays of the same
     * length.
     *
     * Imagine an empty string s.
     *
     * You can perform the following operation any number of times (including zero):
     *
     * Choose an index i in the range [0, words.length - 1].
     * Append words[i] to s.
     * The cost of operation is costs[i].
     * Return the minimum cost to make s equal to target. If it's not possible, return -1.
     *
     * Input: target = "abcdef", words = ["abdef","abc","d","def","ef"], costs = [100,1,1,10,5]
     * Output: 7
     *
     * Input: target = "aaaa", words = ["z","zz","zzz"], costs = [1,10,100]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= target.length <= 2000
     * 1 <= words.length == costs.length <= 50
     * 1 <= words[i].length <= target.length
     * target and words[i] consist only of lowercase English letters.
     * 1 <= costs[i] <= 105
     * @param target
     * @param words
     * @param costs
     * @return
     */
    // time = O(n * m * k), space = O(n * k)
    public int minimumCost(String target, String[] words, int[] costs) {
        int n = target.length(), inf = 0x3f3f3f3f;
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;

        int m = words.length;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                int len = words[j].length();
                if (i - len < 0) continue;
                if (target.substring(i - len, i).equals(words[j])) {
                    f[i] = Math.min(f[i], f[i - len] + costs[j]);
                }
            }
        }
        return f[n] == inf ? -1 : f[n];
    }
}