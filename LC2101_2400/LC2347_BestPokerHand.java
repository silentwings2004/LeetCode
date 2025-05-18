package LC2101_2400;

public class LC2347_BestPokerHand {
    /**
     * You are given an integer array ranks and a character array suits. You have 5 cards where the ith card has a rank
     * of ranks[i] and a suit of suits[i].
     *
     * The following are the types of poker hands you can make from best to worst:
     *
     * "Flush": Five cards of the same suit.
     * "Three of a Kind": Three cards of the same rank.
     * "Pair": Two cards of the same rank.
     * "High Card": Any single card.
     * Return a string representing the best type of poker hand you can make with the given cards.
     *
     * Note that the return values are case-sensitive.
     *
     * Input: ranks = [13,2,3,1,9], suits = ["a","a","a","a","a"]
     * Output: "Flush"
     *
     * Constraints:
     *
     * ranks.length == suits.length == 5
     * 1 <= ranks[i] <= 13
     * 'a' <= suits[i] <= 'd'
     * No two cards have the same rank and suit.
     * @param ranks
     * @param suits
     * @return
     */
    // time = O(n), space = O(1)
    public String bestHand(int[] ranks, char[] suits) {
        int n = ranks.length, count = 1;
        for (int i = 1; i < n; i++) {
            if (suits[i] == suits[i - 1]) count++;
            else break;
        }
        if (count == 5) return "Flush";

        int[] f = new int[14];
        boolean hasPair = false;
        for (int x : ranks) {
            f[x]++;
            if (f[x] == 3) return "Three of a Kind";
            if (f[x] == 2) hasPair = true;
        }
        if (hasPair) return "Pair";
        return "High Card";
    }
}
