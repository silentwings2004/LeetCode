package LC2401_2700;
import java.util.*;
public class LC2449_MinimumNumberofOperationstoMakeArraysSimilar {
    /**
     * You are given two positive integer arrays nums and target, of the same length.
     *
     * In one operation, you can choose any two distinct indices i and j where 0 <= i, j < nums.length and:
     *
     * set nums[i] = nums[i] + 2 and
     * set nums[j] = nums[j] - 2.
     * Two arrays are considered to be similar if the frequency of each element is the same.
     *
     * Return the minimum number of operations required to make nums similar to target. The test cases are generated
     * such that nums can always be similar to target.
     *
     * Input: nums = [8,12,6], target = [2,14,10]
     * Output: 2
     *
     * Input: nums = [1,2,5], target = [4,1,3]
     * Output: 1
     *
     * Input: nums = [1,1,1,1,1], target = [1,1,1,1,1]
     * Output: 0
     *
     * Constraints:
     *
     * n == nums.length == target.length
     * 1 <= n <= 10^5
     * 1 <= nums[i], target[i] <= 10^6
     * It is possible to make nums similar to target.
     * @param nums
     * @param target
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long makeSimilar(int[] nums, int[] target) {
        TreeMap<Integer, Integer> os = new TreeMap<>();
        TreeMap<Integer, Integer> es = new TreeMap<>();
        for (int x : nums) {
            if (x % 2 == 1) os.put(x, os.getOrDefault(x, 0) + 1);
            else es.put(x, es.getOrDefault(x, 0) + 1);
        }

        Arrays.sort(target);
        long diff = 0;
        for (int x : target) {
            if (x % 2 == 1) {
                if (os.containsKey(x)) {
                    os.put(x, os.get(x) - 1);
                    if (os.get(x) == 0) os.remove(x);
                } else {
                    int y = os.firstKey();
                    diff += Math.abs((y - x) / 2);
                    os.put(y, os.get(y) - 1);
                    if (os.get(y) == 0) os.remove(y);
                }
            } else {
                if (es.containsKey(x)) {
                    es.put(x, es.get(x) - 1);
                    if (es.get(x) == 0) es.remove(x);
                } else {
                    int y = es.firstKey();
                    diff += Math.abs((y - x) / 2);
                    es.put(y, es.get(y) - 1);
                    if (es.get(y) == 0) es.remove(y);
                }
            }
        }
        return diff / 2;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long makeSimilar2(int[] nums, int[] target) {
        List<Integer> oddNums = new ArrayList<>();
        List<Integer> oddTarget = new ArrayList<>();
        List<Integer> evenNums = new ArrayList<>();
        List<Integer> evenTarget = new ArrayList<>();

        for (int x : nums) {
            if (x % 2 == 0) evenNums.add(x);
            else oddNums.add(x);
        }

        for (int x : target) {
            if (x % 2 == 0) evenTarget.add(x);
            else oddTarget.add(x);
        }

        return count(evenNums, evenTarget) + count(oddNums, oddTarget);
    }

    private long count(List<Integer> nums, List<Integer> target) {
        Collections.sort(nums);
        Collections.sort(target);

        int n = nums.size();
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (nums.get(i) > target.get(i)) {
                res += (nums.get(i) - target.get(i)) / 2;
            }
        }
        return res;
    }
}
/**
 * evenNums, evenTarget
 * oddNums, oddTarget
 * nums: 6 8 12
 * target: 2 10 14
 * 想让操作尽量少，绝对值差之和要尽量小
 * minimize: sum{ sbs(nums[j] - target[j]) /2 }
 */