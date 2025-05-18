package LC1201_1500;
import java.util.*;
public class LC1276_NumberofBurgerswithNoWasteofIngredients {
    /**
     * Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
     *
     * Jumbo Burger: 4 tomato slices and 1 cheese slice.
     * Small Burger: 2 Tomato slices and 1 cheese slice.
     * Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the number of
     * remaining cheeseSlices equal to 0. If it is not possible to make the remaining tomatoSlices and cheeseSlices
     * equal to 0 return [].
     *
     * Input: tomatoSlices = 16, cheeseSlices = 7
     * Output: [1,6]
     *
     * Input: tomatoSlices = 17, cheeseSlices = 4
     * Output: []
     *
     * Input: tomatoSlices = 4, cheeseSlices = 17
     * Output: []
     *
     * Constraints:
     *
     * 0 <= tomatoSlices, cheeseSlices <= 10^7
     * @param tomatoSlices
     * @param cheeseSlices
     * @return
     */
    // time = O(1), space = O(1)
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> res = new ArrayList<>();
        int a = tomatoSlices, b = cheeseSlices;
        if (a < 2 * b || (a - 2 * b) % 2 == 1) return res;
        int x = (a - b * 2) / 2;
        if (b < x) return res;
        int y = b - x;
        return Arrays.asList(x, y);
    }
}