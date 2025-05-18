package LC901_1200;
import java.util.*;
public class LC948_BagofTokens {
    /**
     * You have an initial power of power, an initial score of 0, and a bag of tokens where tokens[i] is the value of
     * the ith token (0-indexed).
     *
     * Your goal is to maximize your total score by potentially playing each token in one of two ways:
     *
     * If your current power is at least tokens[i], you may play the ith token face up, losing tokens[i] power and
     * gaining 1 score.
     * If your current score is at least 1, you may play the ith token face down, gaining tokens[i] power and losing
     * 1 score.
     * Each token may be played at most once and in any order. You do not have to play all the tokens.
     *
     * Return the largest possible score you can achieve after playing any number of tokens.
     *
     * Input: tokens = [100], power = 50
     * Output: 0
     *
     * Input: tokens = [100,200], power = 150
     * Output: 1
     *
     * Input: tokens = [100,200,300,400], power = 200
     * Output: 2
     *
     * Constraints:
     *
     * 0 <= tokens.length <= 1000
     * 0 <= tokens[i], power < 10^4
     * @param tokens
     * @param power
     * @return
     */
    // S1
    // time = O(nlogn), space = O(1)
    public int bagOfTokensScore(int[] tokens, int power) {
        int n = tokens.length;
        Arrays.sort(tokens);
        int res = 0, t = 0;
        for (int i = 0, j = n - 1; i <= j;) {
            if (power >= tokens[i]) {
                power -= tokens[i++];
                t++;
                res = Math.max(res, t);
            } else {
                if (t == 0) break;
                power += tokens[j--];
                t--;
            }
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(1)
    public int bagOfTokensScore2(int[] tokens, int power) {
        Arrays.sort(tokens);
        int l = 0, n = tokens.length;
        while (l < n && power >= tokens[l]) power -= tokens[l++];

        int res = l;
        for (int r = n - 1; r > l; r--) {
            if (l < n - r) break;
            power += tokens[r];
            while (l < r && power >= tokens[l]) power -= tokens[l++];
            res = Math.max(res, l - (n - r));
        }
        return res;
    }
}
