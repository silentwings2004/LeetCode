package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: QueueReconstructionbyHeight
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 406. Queue Reconstruction by Height
 */
public class LC406_QueueReconstructionbyHeight {
    /**
     * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers
     * (h, k), where h is the height of the person and k is the number of people in front of this person who have a
     * height greater than or equal to h. Write an algorithm to reconstruct the queue.
     *
     * Note:
     * The number of people is less than 1,100.
     *
     *
     * Example
     *
     * Input:
     * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
     *
     * Output:
     * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
     * @param people
     * @return
     */
    // time = O(n^2), space = O(n)
    public int[][] reconstructQueue(int[][] people) {
        // corner case
        if (people == null || people.length == 0 || people[0] == null || people[0].length == 0) {
            return people;
        }
        int row = people.length, col = people[0].length;
        List<int[]> res = new LinkedList<>();
        Arrays.sort(people, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0])); // --> O(n)
        for (int[] person : people) {   // --> O(n)
            res.add(person[1], person); // --> O(n)
        }
        return res.toArray(new int[row][col]);
    }
}
