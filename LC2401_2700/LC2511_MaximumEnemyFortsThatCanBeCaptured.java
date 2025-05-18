package LC2401_2700;

public class LC2511_MaximumEnemyFortsThatCanBeCaptured {
    /**
     * You are given a 0-indexed integer array forts of length n representing the positions of several forts. forts[i]
     * can be -1, 0, or 1 where:
     *
     * -1 represents there is no fort at the ith position.
     * 0 indicates there is an enemy fort at the ith position.
     * 1 indicates the fort at the ith the position is under your command.
     * Now you have decided to move your army from one of your forts at position i to an empty position j such that:
     *
     * 0 <= i, j <= n - 1
     * The army travels over enemy forts only. Formally, for all k where min(i,j) < k < max(i,j), forts[k] == 0.
     * While moving the army, all the enemy forts that come in the way are captured.
     *
     * Return the maximum number of enemy forts that can be captured. In case it is impossible to move your army, or
     * you do not have any fort under your command, return 0.
     *
     * Input: forts = [1,0,0,-1,0,0,0,0,1]
     * Output: 4
     *
     * Input: forts = [0,0,1,-1]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= forts.length <= 1000
     * -1 <= forts[i] <= 1
     * @param forts
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int captureForts(int[] forts) {
        int n = forts.length, res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (forts[i] != 0) {
                if (forts[j] == -forts[i]) res = Math.max(res, i - j - 1);
                j = i;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int captureForts2(int[] forts) {
        int n = forts.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (forts[i] == 1) {
                int j = i + 1;
                while (j < n && forts[j] == 0) j++;
                if (j < n && forts[j] == -1) res = Math.max(res, j - i - 1);
                i = j - 1;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (forts[i] == 1) {
                int j = i - 1;
                while (j >= 0 && forts[j] == 0) j--;
                if (j >= 0 && forts[j] == -1) res = Math.max(res, i - j - 1);
                i = j + 1;
            }
        }
        return res;
    }
}