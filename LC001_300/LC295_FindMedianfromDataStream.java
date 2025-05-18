package LC001_300;
import java.util.*;
public class LC295_FindMedianfromDataStream {
    /**
     * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle
     * value and the median is the mean of the two middle values.
     *
     * For example, for arr = [2,3,4], the median is 3.
     * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
     * Implement the MedianFinder class:
     *
     * MedianFinder() initializes the MedianFinder object.
     * void addNum(int num) adds the integer num from the data stream to the data structure.
     * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be
     * accepted.
     *
     * Input
     * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
     * [[], [1], [2], [], [3], []]
     * Output
     * [null, null, null, 1.5, null, 2.0]
     *
     * Constraints:
     *
     * -10^5 <= num <= 10^5
     * There will be at least one element in the data structure before calling findMedian.
     * At most 5 * 10^4 calls will be made to addNum and findMedian.
     *
     *
     * Follow up:
     *
     * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
     * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
     */
    /** initialize your data structure here. */
    // time = O(logn), space = O(n)
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    /** initialize your data structure here. */
    public LC295_FindMedianfromDataStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (minHeap.size() - maxHeap.size() > 1) maxHeap.offer(minHeap.poll());
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) return (minHeap.peek() + maxHeap.peek()) / 2.0;
        return minHeap.peek();
    }
}
/**
 * 只进不出
 * 如果动态维护一个序列的话 => 用平衡树  set map
 * 对顶堆，分别维护左半边和右半边
 * 快速找到边界
 * 大顶堆：左边 down
 * 小顶堆：右边 up
 * 左边 >= 右边 +
 *
 * 对顶堆
 * 如果数据流中所有整数都在 0 到 100 范围内，那么我们可以利用计数排序统计每一类数的数量，并使用双指针维护中位数。
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，那么我们依然利用计数排序统计每一类数的数量，并使用双指针维护中位数。
 * 对于超出范围的数，我们可以单独进行处理，建立两个数组，分别记录小于 0 的部分的数的数量和大于 100 的部分的数的数量即可。
 * 当小部分时间，中位数不落在区间 [0,100]中时，我们在对应的数组中暴力检查即可。
 */