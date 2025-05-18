package LC2401_2700;
import java.util.*;
public class LC2491_DividePlayersIntoTeamsofEqualSkill {
    /**
     * You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player.
     * Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.
     *
     * The chemistry of a team is equal to the product of the skills of the players on that team.
     *
     * Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into
     * teams such that the total skill of each team is equal.
     *
     * Input: skill = [3,2,5,1,3,4]
     * Output: 22
     *
     * Input: skill = [3,4]
     * Output: 12
     *
     * Input: skill = [1,1,2,3]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= skill.length <= 10^5
     * skill.length is even.
     * 1 <= skill[i] <= 1000
     * @param skill
     * @return
     */
    // S1: sort
    // time = O(nlogn), space = O(1)
    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        int n = skill.length, sum = -1;
        long res = 0;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int s = skill[i] + skill[j];
            if (sum == -1) sum = s;
            else if (sum != s) return -1;
            res += skill[i] * skill[j];
        }
        return res;
    }

    // S2: HashMap
    // time = O(n), space = O(n)
    public long dividePlayers2(int[] skill) {
        int s = 0, n = skill.length / 2;
        for (int x : skill) s += x;
        int sum = s / n;

        HashMap<Integer, Integer> map = new HashMap<>();
        long res = 0;
        for (int x : skill) {
            int y = sum - x;
            if (map.containsKey(y)) {
                map.put(y, map.get(y) - 1);
                if (map.get(y) == 0) map.remove(y);
                res += x * y;
            } else map.put(x, map.getOrDefault(x, 0) + 1);
        }
        return map.size() == 0 ? res : -1;
    }
}
