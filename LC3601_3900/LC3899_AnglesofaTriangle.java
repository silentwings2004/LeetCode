package LC3601_3900;
import java.util.*;
public class LC3899_AnglesofaTriangle {
    /**
     * You are given a positive integer array sides of length 3.
     *
     * Determine if there exists a triangle with positive area whose three side lengths are given by the elements of sides.
     *
     * If such a triangle exists, return an array of three floating-point numbers representing its internal angles
     * (in degrees), sorted in non-decreasing order. Otherwise, return an empty array.
     *
     * Answers within 10^-5 of the actual answer will be accepted.
     *
     * Input: sides = [3,4,5]
     * Output: [36.86990,53.13010,90.00000]
     *
     * Input: sides = [2,4,2]
     * Output: []
     *
     * Constraints:
     *
     * sides.length == 3
     * 1 <= sides[i] <= 1000
     * @param sides
     * @return
     */
    // time = O(1), space = O(1)
    public double[] internalAngles(int[] sides) {
        Arrays.sort(sides);
        int a = sides[0], b = sides[1], c = sides[2];
        if (a + b <= c) return new double[0];

        double rad = 180 / Math.PI;
        double A = Math.acos(1.0 * (b * b + c * c - a * a) / (1.0 * b * c * 2)) * rad;
        double B = Math.acos(1.0 * (a * a + c * c - b * b) / (1.0 * a * c * 2)) * rad;
        return new double[]{A, B, 180 - A - B};
    }
}