package LC3601_3900;

public class LC3616_NumberofStudentReplacements {
    /**
     * You are given an integer array ranks where ranks[i] represents the rank of the ith student arriving in order. A
     * lower number indicates a better rank.
     *
     * Initially, the first student is selected by default.
     *
     * A replacement occurs when a student with a strictly better rank arrives and replaces the current selection.
     *
     * Return the total number of replacements made.
     *
     * Input: ranks = [4,1,2]
     * Output: 1
     *
     * Input: ranks = [2,2,3]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= ranks.length <= 10^5
     * 1 <= ranks[i] <= 10^5
     * @param ranks
     * @return
     */
    // time = O(n), space = O(1)
    public int totalReplacements(int[] ranks) {
        int res = 0, minv = -1;
        for (int x : ranks) {
            if (minv == -1) minv = x;
            else if (x < minv) {
                minv = x;
                res++;
            }
        }
        return res;
    }
}