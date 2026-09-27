package LC3901_4200;

public class LC4062_TransformArrayUsingPairOperations {
    /**
     * You are given two integer arrays source and target.
     *
     * In one operation, you may choose two distinct indices i and j in source, along with any integer delta.
     * Then update source as follows:
     *
     * source[i] = source[i] + source[j] - delta
     * source[j] = delta
     *
     * Return true if it is possible to make source equal to target after performing the operation any (including zero)
     * number of times. Otherwise, return false.
     *
     * Input: source = [1,2,3], target = [0,2,4]
     * Output: true
     *
     * Input: source = [-5,-5], target = [-15,5]
     * Output: true
     *
     * Input: source = [1,2,1], target = [0,2,5]
     * Output: false
     *
     * Constraints:
     *
     * 2 <= source.length == target.length <= 10^5
     * -10^9 <= source[i], target[i] <= 10^9
     * @param source
     * @param target
     * @return
     */
    // time = O(n), space = O(1)
    public boolean canTransform(int[] source, int[] target) {
        int n = source.length;
        long s = 0;
        for (int i = 0; i < n; i++) {
            if (source[i] == target[i]) continue;
            s += source[i] - target[i];
        }
        return s == 0;
    }
}
/**
 * 不变量：操作不改变数组的元素和
 * 必要条件：sum(source) = sum(target)
 */