package LC301_600;

public class LC573_SquirrelSimulation {
    /**
     * You are given two integers height and width representing a garden of size height x width. You are also given:
     *
     * an array tree where tree = [treer, treec] is the position of the tree in the garden,
     * an array squirrel where squirrel = [squirrelr, squirrelc] is the position of the squirrel in the garden,
     * and an array nuts where nuts[i] = [nutir, nutic] is the position of the ith nut in the garden.
     * The squirrel can only take at most one nut at one time and can move in four directions: up, down, left, and right,
     * to the adjacent cell.
     *
     * Return the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one.
     *
     * The distance is the number of moves.
     *
     * Input: height = 5, width = 7, tree = [2,2], squirrel = [4,4], nuts = [[3,0], [2,5]]
     * Output: 12
     *
     * Input: height = 1, width = 3, tree = [0,1], squirrel = [0,0], nuts = [[0,2]]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= height, width <= 100
     * tree.length == 2
     * squirrel.length == 2
     * 1 <= nuts.length <= 5000
     * nuts[i].length == 2
     * 0 <= treer, squirrelr, nutir <= height
     * 0 <= treec, squirrelc, nutic <= width
     * @param height
     * @param width
     * @param tree
     * @param squirrel
     * @param nuts
     * @return
     */
    // time = O(n), space = O(n)
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int n = nuts.length, res = (int) 1e9, sum = 0;
        int[] dist1 = new int[n], dist2 = new int[n];
        for (int i = 0; i < n; i++) {
            int x = nuts[i][0], y = nuts[i][1];
            dist1[i] = Math.abs(x - tree[0]) + Math.abs(y - tree[1]);
            dist2[i] = Math.abs(x - squirrel[0]) + Math.abs(y - squirrel[1]);
            sum += dist1[i] * 2;
        }

        for (int i = 0; i < n; i++) {
            res = Math.min(res, dist2[i] + sum - dist1[i]);
        }

        return res;
    }
}