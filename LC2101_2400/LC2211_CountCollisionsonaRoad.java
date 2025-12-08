package LC2101_2400;

public class LC2211_CountCollisionsonaRoad {
    /**
     * There are n cars on an infinitely long road. The cars are numbered from 0 to n - 1 from left to right and each
     * car is present at a unique point.
     *
     * You are given a 0-indexed string directions of length n. directions[i] can be either 'L', 'R', or 'S' denoting
     * whether the ith car is moving towards the left, towards the right, or staying at its current point respectively.
     * Each moving car has the same speed.
     *
     * The number of collisions can be calculated as follows:
     *
     * When two cars moving in opposite directions collide with each other, the number of collisions increases by 2.
     * When a moving car collides with a stationary car, the number of collisions increases by 1.
     * After a collision, the cars involved can no longer move and will stay at the point where they collided. Other
     * than that, cars cannot change their state or direction of motion.
     *
     * Return the total number of collisions that will happen on the road.
     *
     * Input: directions = "RLRSLL"
     * Output: 5
     *
     * Input: directions = "LLRR"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= directions.length <= 10^5
     * directions[i] is either 'L', 'R', or 'S'.
     * @param directions
     * @return
     */
    // time = O(n), space = O(1)
    public int countCollisions(String directions) {
        int n = directions.length();
        int i = 0, j = n - 1;
        while (i < n && directions.charAt(i) == 'L') i++;
        while (j >= 0 && directions.charAt(j) == 'R') j--;
        int res = 0;
        for (int u = i; u <= j; u++) {
            if (directions.charAt(u) != 'S') res++;
        }
        return res;
    }
}