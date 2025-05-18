package LC901_1200;
import java.util.*;
public class LC1093_StatisticsfromaLargeSample {
    /**
     * You are given a large sample of integers in the range [0, 255]. Since the sample is so large, it is represented
     * by an array count where count[k] is the number of times that k appears in the sample.
     *
     * Calculate the following statistics:
     *
     * minimum: The minimum element in the sample.
     * maximum: The maximum element in the sample.
     * mean: The average of the sample, calculated as the total sum of all elements divided by the total number of elements.
     * median:
     * If the sample has an odd number of elements, then the median is the middle element once the sample is sorted.
     * If the sample has an even number of elements, then the median is the average of the two middle elements once the
     * sample is sorted.
     * mode: The number that appears the most in the sample. It is guaranteed to be unique.
     * Return the statistics of the sample as an array of floating-point numbers [minimum, maximum, mean, median, mode].
     * Answers within 10-5 of the actual answer will be accepted.
     *
     * Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
     *
     * Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * Output: [1.00000,4.00000,2.18182,2.00000,1.00000]
     *
     * Constraints:
     *
     * count.length == 256
     * 0 <= count[i] <= 10^9
     * 1 <= sum(count) <= 10^9
     * The mode of the sample that count represents is unique.
     * @param count
     * @return
     */
    // time = O(n), space = O(1)
    public double[] sampleStats(int[] count) {
        int n = count.length;
        double[] res = new double[5];
        Arrays.fill(res, -1);
        double sum = 0;
        int cnt = 0, mc = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > 0) {
                if (res[0] == -1) res[0] = i;
                res[1] = Math.max(res[1], i);
                sum += (double)count[i] * i;
                cnt += count[i];
                if (count[i] > mc) {
                    mc = count[i];
                    res[4] = i;
                }
            }
        }
        res[2] = sum / cnt;
        if (cnt % 2 == 1) res[3] = get(count, cnt / 2 + 1);
        else res[3] = (get(count, cnt / 2) + get(count, cnt / 2 + 1)) / 2.0;
        return res;
    }

    private double get(int[] count, int cnt) {
        int res = 0;
        for (int i = 0; i < count.length; i++) {
            res += count[i];
            if (res >= cnt) return i;
        }
        return -1;
    }
}