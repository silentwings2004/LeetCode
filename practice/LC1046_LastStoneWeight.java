package practice;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: LastStoneWeight
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 1046. Last Stone Weight
 */
public class LC1046_LastStoneWeight {
    /**
     * We have a collection of stones, each stone has a positive integer weight.
     *
     * Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y
     * with x <= y.  The result of this smash is:
     *
     * If x == y, both stones are totally destroyed;
     * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
     * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
     *
     *
     *
     * Example 1:
     *
     * Input: [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
     *
     *
     * Note:
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 1000
     * @param stones
     * @return
     */
    // S1
    // time = O((n - 1) * nlogn) = O(n^2 * logn), space = O(1)
    public int lastStoneWeight(int[] stones) {
        // corner case
        if (stones == null || stones.length == 0) return 0;
        if (stones.length == 1) return stones[0];

        int i = stones.length - 1;
        Arrays.sort(stones);
        while (stones[i - 1] != 0) {
            if (stones[i] == stones[i - 1]) {
                stones[i] = 0;
            } else {
                stones[i] -= stones[i -1];
            }
            stones[i - 1] = 0;
            Arrays.sort(stones);
        }
        return stones[i];
    }

    // S1.2: 优化S1的代码
    // time = O(n^2 * logn), space = O(1)
    public int lastStoneWeight12(int[] stones) {
        // corner case
        if (stones == null || stones.length == 0) return 0;

        int len = stones.length - 1;
        for (int i = 0; i < len; i++) {
            Arrays.sort(stones);
            stones[len] -= stones[len - 1];
            stones[len - 1] = 0;
        }
        return stones[len];
    }

    // S2: maxHeap
    // time = O(nlogn), space = O(n)
    public int lastStoneWeight2(int[] stones) {
        // corner case
        if (stones == null || stones.length == 0) return 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int stone : stones) maxHeap.offer(stone);

        while (maxHeap.size() > 1) {
            int heaviest = maxHeap.poll();
            int nextToHeaviest = maxHeap.poll();
            if (heaviest != nextToHeaviest) {
                maxHeap.offer(heaviest - nextToHeaviest);
            }
        }
        if (maxHeap.isEmpty()) return 0;
        return maxHeap.peek();
    }
}
