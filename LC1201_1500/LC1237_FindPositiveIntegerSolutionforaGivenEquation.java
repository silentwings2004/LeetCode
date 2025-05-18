package LC1201_1500;
import java.util.*;
public class LC1237_FindPositiveIntegerSolutionforaGivenEquation {
    /**
     * Given a callable function f(x, y) with a hidden formula and a value z, reverse engineer the formula and return
     * all positive integer pairs x and y where f(x,y) == z. You may return the pairs in any order.
     *
     * While the exact formula is hidden, the function is monotonically increasing, i.e.:
     *
     * f(x, y) < f(x + 1, y)
     * f(x, y) < f(x, y + 1)
     * The function interface is defined like this:
     *
     * interface CustomFunction {
     * public:
     *   // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
     *   int f(int x, int y);
     * };
     * We will judge your solution as follows:
     *
     * The judge has a list of 9 hidden implementations of CustomFunction, along with a way to generate an answer key of
     * all valid pairs for a specific z.
     * The judge will receive two inputs: a function_id (to determine which implementation to test your code with), and
     * the target z.
     * The judge will call your findSolution and compare your results with the answer key.
     * If your results match the answer key, your solution will be Accepted.
     *
     * Input: function_id = 1, z = 5
     * Output: [[1,4],[2,3],[3,2],[4,1]]
     *
     * Input: function_id = 2, z = 5
     * Output: [[1,5],[5,1]]
     *
     * Constraints:
     *
     * 1 <= function_id <= 9
     * 1 <= z <= 100
     * It is guaranteed that the solutions of f(x, y) == z will be in the range 1 <= x, y <= 1000.
     * It is also guaranteed that f(x, y) will fit in 32 bit signed integer if 1 <= x, y <= 1000.
     * @param customfunction
     * @param z
     * @return
     */
    // time = O(n), space = O(1)
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        int x = 1000, y = 1;
        while (x >= 1 && y <= 1000) {
            if (customfunction.f(x, y) == z) {
                res.add(Arrays.asList(x, y));
                x--;
            } else if (customfunction.f(x, y) > z) x--;
            else y++;
        }
        return res;
    }

    // mock helper function only
    class CustomFunction {
        int f(int x, int y) { return x + y; };
    }
}