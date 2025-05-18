package LC601_900;
import java.util.*;
public class LC735_AsteroidCollision {
    /**
     * We are given an array asteroids of integers representing asteroids in a row.
     *
     * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive
     * meaning right, negative meaning left). Each asteroid moves at the same speed.
     *
     * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
     * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
     *
     * Input: asteroids = [5,10,-5]
     * Output: [5,10]
     *
     * Constraints:
     *
     * 2 <= asteroids.length <= 10^4
     * -1000 <= asteroids[i] <= 1000
     * asteroids[i] != 0
     * @param asteroids
     * @return
     */
    // time = O(n), space = O(n)
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int x : asteroids) {
            if (stack.isEmpty()) stack.push(x);
            else if (x > 0) stack.push(x);
            else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(x)) stack.pop();
                if (stack.isEmpty() || stack.peek() < 0) stack.push(x);
                else if (stack.peek() == Math.abs(x)) stack.pop();
            }
        }
        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) res[i] = stack.pop();
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int[] asteroidCollision2(int[] asteroids) {
        List<Integer> res = new ArrayList<>();
        for (int x : asteroids) {
            if (x > 0) res.add(x);
            else {
                while (res.size() > 0 && res.get(res.size() - 1) > 0 && res.get(res.size() - 1) < -x) {
                    res.remove(res.size() - 1);
                }
                if (res.size() > 0 && res.get(res.size() - 1) == -x) res.remove(res.size() - 1);
                else if (res.size() == 0 || res.get(res.size() - 1) < 0) res.add(x);
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }
}