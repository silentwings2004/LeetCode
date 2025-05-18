package LC1201_1500;
import java.util.*;
public class LC1431_KidsWiththeGreatestNumberofCandies {
    /**
     * There are n kids with candies. You are given an integer array candies, where each candies[i] represents the
     * number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
     *
     * Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the
     * extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.
     *
     * Note that multiple kids can have the greatest number of candies.
     *
     * Input: candies = [2,3,5,1,3], extraCandies = 3
     * Output: [true,true,true,false,true]
     *
     * Input: candies = [4,2,1,1,2], extraCandies = 1
     * Output: [true,false,false,false,false]
     *
     * Input: candies = [12,1,12], extraCandies = 10
     * Output: [true,false,true]
     *
     * Constraints:
     *
     * n == candies.length
     * 2 <= n <= 100
     * 1 <= candies[i] <= 100
     * 1 <= extraCandies <= 50
     * @param candies
     * @param extraCandies
     * @return
     */
    // time = O(n), space = O(1)
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res = new ArrayList<>();
        int maxv = candies[0];
        for (int x : candies) maxv = Math.max(maxv, x);
        for (int x : candies) {
            if (x + extraCandies >= maxv) res.add(true);
            else res.add(false);
        }
        return res;
    }
}