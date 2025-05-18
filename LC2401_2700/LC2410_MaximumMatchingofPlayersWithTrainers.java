package LC2401_2700;
import java.util.*;
public class LC2410_MaximumMatchingofPlayersWithTrainers {
    /**
     * You are given a 0-indexed integer array players, where players[i] represents the ability of the ith player. You
     * are also given a 0-indexed integer array trainers, where trainers[j] represents the training capacity of the jth
     * trainer.
     *
     * The ith player can match with the jth trainer if the player's ability is less than or equal to the trainer's
     * training capacity. Additionally, the ith player can be matched with at most one trainer, and the jth trainer can
     * be matched with at most one player.
     *
     * Return the maximum number of matchings between players and trainers that satisfy these conditions.
     *
     * Input: players = [4,7,9], trainers = [8,2,5,8]
     * Output: 2
     *
     * Input: players = [1,1,1], trainers = [10]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= players.length, trainers.length <= 10^5
     * 1 <= players[i], trainers[j] <= 10^9
     * @param players
     * @param trainers
     * @return
     */
    // time = O(mlogm + nlogn), space = O(1)
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int m = players.length, n = trainers.length, res = 0;
        for (int i = 0, j = 0; i < m; i++) {
            while (j < n && trainers[j] < players[i]) j++;
            if (j == n) break;
            res++;
            j++;
        }
        return res;
    }
}
