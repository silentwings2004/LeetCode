package LC601_900;
import java.util.*;
public class LC881_BoatstoSavePeople {
    /**
     * The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
     *
     * Each boat carries at most 2 people at the same time, provided the sum of the weight of those people is at most
     * limit.
     *
     * Return the minimum number of boats to carry every given person.  (It is guaranteed each person can be carried
     * by a boat.)
     *
     * Input: people = [1,2], limit = 3
     * Output: 1
     * Explanation: 1 boat (1, 2)
     *
     * Input: people = [3,2,2,1], limit = 3
     * Output: 3
     * Explanation: 3 boats (1, 2), (2) and (3)
     *
     * Note:
     *
     * 1 <= people.length <= 50000
     * 1 <= people[i] <= limit <= 30000
     *
     * @param people
     * @param limit
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length, res = 0;
        for (int i = 0, j = n - 1; i <= j; j--) {
            if (people[i] + people[j] <= limit) i++;
            res++;
        }
        return res;
    }
}