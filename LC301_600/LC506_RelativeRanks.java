package LC301_600;
import java.util.*;
public class LC506_RelativeRanks {
    /**
     * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition.
     * All the scores are guaranteed to be unique.
     *
     * The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place
     * athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
     *
     * The 1st place athlete's rank is "Gold Medal".
     * The 2nd place athlete's rank is "Silver Medal".
     * The 3rd place athlete's rank is "Bronze Medal".
     * For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's
     * rank is "x").
     * Return an array answer of size n where answer[i] is the rank of the ith athlete.
     *
     * Input: score = [5,4,3,2,1]
     * Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
     *
     * Input: score = [10,3,8,9,4]
     * Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
     *
     * Constraints:
     *
     * n == score.length
     * 1 <= n <= 10^4
     * 0 <= score[i] <= 10^6
     * All the values in score are unique.
     * @param score
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] res = new String[n];
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) arr[i] = new int[]{score[i], i};
        Arrays.sort(arr, (o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < n; i++) {
            int id = arr[i][1];
            if (i == 0) res[id] = "Gold Medal";
            else if (i == 1) res[id] = "Silver Medal";
            else if (i == 2) res[id] = "Bronze Medal";
            else res[id] = String.valueOf(i + 1);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public String[] findRelativeRanks2(int[] score) {
        int n = score.length, max = 0;
        String[] ranks = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        for (int x : score) max = Math.max(max, x);
        String[] arr = new String[max + 1];
        for (int i = 0; i < n; i++) arr[score[i]] = "";

        int k = 0;
        for (int i = max; i >= 0; i--) {
            if (arr[i] != null) {
                if (k < 3) arr[i] = ranks[k];
                else arr[i] = String.valueOf(k + 1);
                k++;
            }
        }

        String[] res = new String[n];
        for (int i = 0; i < n; i++) res[i] = arr[score[i]];
        return res;
    }
}
