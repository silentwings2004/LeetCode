package LC901_1200;
import java.util.*;
public class LC1183_MaximumNumberofOnes {
    /**
     * Consider a matrix M with dimensions width * height, such that every cell has value 0 or 1, and any square
     * sub-matrix of M of size sideLength * sideLength has at most maxOnes ones.
     *
     * Return the maximum possible number of ones that the matrix M can have.
     *
     * Input: width = 3, height = 3, sideLength = 2, maxOnes = 1
     * Output: 4
     *
     * Input: width = 3, height = 3, sideLength = 2, maxOnes = 2
     * Output: 6
     *
     *
     Constraints:

     1 <= width, height <= 100
     1 <= sideLength <= width, height
     0 <= maxOnes <= sideLength * sideLength
     * @param width
     * @param height
     * @param sideLength
     * @param maxOnes
     * @return
     */
    // time = O(n * m), space = O(s * s)
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int n1 = width / sideLength, d1 = width % sideLength;
        int n2 = height / sideLength, d2 = height % sideLength;

        int[][] f = new int[sideLength][sideLength];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int x = i % sideLength, y = j % sideLength;
                f[x][y]++;
            }
        }

        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j < sideLength; j++) {
                q.add(f[i][j]);
            }
        }
        Collections.sort(q, (o1, o2) -> o2 - o1);
        int res = 0;
        for (int i = 0; i < maxOnes; i++) res += q.get(i);
        return res;
    }
}