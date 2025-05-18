package LC2700_3000;

public class LC2782_NumberofUniqueCategories {
    /**
     * You are given an integer n and an object categoryHandler of class CategoryHandler.
     *
     * There are nelements, numbered from 0 to n - 1. Each element has a category, and your task is to find the number
     * of unique categories.
     *
     * The class CategoryHandler contains the following function, which may help you:
     *
     * boolean haveSameCategory(integer a, integer b): Returns true if a and b are in the same category and false
     * otherwise. Also, if either a or b is not a valid number (i.e. it's greater than or equal to nor less than 0),
     * it returns false.
     * Return the number of unique categories.
     *
     * Input: n = 6, catagoryHandler = [1,1,2,2,3,3]
     * Output: 3
     *
     * Input: n = 5, catagoryHandler = [1,2,3,4,5]
     * Output: 5
     *
     * Input: n = 3, catagoryHandler = [1,1,1]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * @param n
     * @param categoryHandler
     * @return
     */
    // time = O(n^2), space = O(n)
    public int numberOfCategories(int n, CategoryHandler categoryHandler) {
        int cnt = 0;
        boolean[] st = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (st[i]) continue;
            for (int j = i; j < n; j++) {
                if (st[j]) continue;
                if (i == j) {
                    st[i] = true;
                    if (!categoryHandler.haveSameCategory(i, j)) break;
                    else cnt++;
                } else {
                    if (categoryHandler.haveSameCategory(i, j)) st[j] = true;
                }
            }
        }
        return cnt;
    }

    class CategoryHandler {
        public CategoryHandler(int[] categories);
        public boolean haveSameCategory(int a, int b);
    };
}
