package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: TheSkylineProblem
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 218. The Skyline Problem
 */
public class LC218_TheSkylineProblem {
    /**
     * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from
     * a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo
     * (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
     *
     * Buildings Skyline Contour
     * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri
     * are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is
     * guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect
     * rectangles grounded on an absolutely flat surface at height 0.
     *
     * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12],
     * [15 20 10], [19 24 8] ] .
     *
     * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
     * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the
     * last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and
     * always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the
     * skyline contour.
     *
     * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8],
     * [24, 0] ].
     *
     * Notes:
     *
     * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
     * The input list is already sorted in ascending order by the left x position Li.
     * The output list must be sorted by the x position.
     * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
     * @param buildings
     * @return
     */
    // S2 : TreeSet
    // time = O(nlogn), space = O(n)
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0) {
            return res;
        }

        List<EndPoint> eps = new ArrayList<>();
        List<EndPoint> lps = new ArrayList<>();
        for (int i= 0; i < buildings.length; i++) {
            int[] b = buildings[i];
            eps.add(new EndPoint(b[0], b[2], i, true));
            eps.add(new EndPoint(b[1], b[2], i, false));
            lps.add(new EndPoint(b[0], b[2], i, true));
        }

        Collections.sort(eps);
        TreeSet<Integer> maxHeap = new TreeSet<>((o1, o2) -> (lps.get(o1).height != lps.get(o2).height ?
                lps.get(o2).height - lps.get(o1).height : o1 - o2));

        for (EndPoint ep : eps) {
            if (ep.isStart) {
                int maxH = maxHeap.isEmpty() ? 0 : lps.get(maxHeap.first()).height;
                if (ep.height > maxH) res.add(Arrays.asList(ep.val, ep.height));
                maxHeap.add(ep.index);
            } else {
                maxHeap.remove(ep.index);
                int maxH = maxHeap.isEmpty() ? 0 : lps.get(maxHeap.first()).height;
                if (ep.height > maxH) res.add(Arrays.asList(ep.val, maxH));
            }
        }
        return res;
    }

    class EndPoint implements Comparable<EndPoint> {
        private int val;
        private int height;
        private int index;
        private boolean isStart;
        public EndPoint(int val, int height, int index, boolean isStart) {
            this.val = val;
            this.height = height;
            this.index = index;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(EndPoint that) {
            if (this.val != that.val) {
                return this.val - that.val;
            } else {
                if (this.isStart && that.isStart) {
                    return that.height - this.height;
                } else if (!this.isStart && !that.isStart) {
                    return this.height - that.height;
                } else {
                    return this.isStart ? -1 : 1;
                }
            }
        }
    }
}
