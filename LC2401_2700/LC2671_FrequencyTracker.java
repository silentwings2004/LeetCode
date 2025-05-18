package LC2401_2700;

public class LC2671_FrequencyTracker {
    /**
     * Design a data structure that keeps track of the values in it and answers some queries regarding their frequencies.
     *
     * Implement the FrequencyTracker class.
     *
     * FrequencyTracker(): Initializes the FrequencyTracker object with an empty array initially.
     * void add(int number): Adds number to the data structure.
     * void deleteOne(int number): Deletes one occurence of number from the data structure. The data structure may not
     * contain number, and in this case nothing is deleted.
     * bool hasFrequency(int frequency): Returns true if there is a number in the data structure that occurs frequency
     * number of times, otherwise, it returns false.
     *
     * Input
     * ["FrequencyTracker", "add", "add", "hasFrequency"]
     * [[], [3], [3], [2]]
     * Output
     * [null, null, null, true]
     *
     * Input
     * ["FrequencyTracker", "hasFrequency", "add", "hasFrequency"]
     * [[], [2], [3], [1]]
     * Output
     * [null, false, null, true]
     *
     * Constraints:
     *
     * 1 <= number <= 10^5
     * 1 <= frequency <= 10^5
     * At most, 2 * 105 calls will be made to add, deleteOne, and hasFrequency in total.
     */
    // time = O(1), space = O(n)
    final int N = 100010;
    int[] nums, cnt;
    public LC2671_FrequencyTracker() {
        nums = new int[N];
        cnt = new int[N];
    }

    public void add(int number) {
        if (cnt[nums[number]] > 0) cnt[nums[number]]--;
        nums[number]++;
        cnt[nums[number]]++;
    }

    public void deleteOne(int number) {
        if (nums[number] == 0) return;
        cnt[nums[number]]--;
        nums[number]--;
        if (nums[number] > 0) cnt[nums[number]]++;
    }

    public boolean hasFrequency(int frequency) {
        return cnt[frequency] > 0;
    }
}