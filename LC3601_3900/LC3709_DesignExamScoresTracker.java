package LC3601_3900;
import java.util.*;
public class LC3709_DesignExamScoresTracker {
    /**
     * Alice frequently takes exams and wants to track her scores and calculate the total scores over specific time
     * 'periods.
     *
     * Implement the ExamTracker class:
     *
     * ExamTracker(): Initializes the ExamTracker object.
     * void record(int time, int score): Alice takes a new exam at time time and achieves the score score.
     * long long totalScore(int startTime, int endTime): Returns an integer that represents the total score of all exams
     * taken by Alice between startTime and endTime (inclusive). If there are no recorded exams taken by Alice within
     * the specified time interval, return 0.
     * It is guaranteed that the function calls are made in chronological order. That is,
     *
     * Calls to record() will be made with strictly increasing time.
     * Alice will never ask for total scores that require information from the future. That is, if the latest record()
     * is called with time = t, then totalScore() will always be called with startTime <= endTime <= t.
     *
     * Input:
     * ["ExamTracker", "record", "totalScore", "record", "totalScore", "totalScore", "totalScore", "totalScore"]
     * [[], [1, 98], [1, 1], [5, 99], [1, 3], [1, 5], [3, 4], [2, 5]]
     *
     * Output:
     * [null, null, 98, null, 98, 197, 0, 99]
     *
     * Constraints:
     *
     * 1 <= time <= 109
     * 1 <= score <= 109
     * 1 <= startTime <= endTime <= t, where t is the value of time from the most recent call of record().
     * Calls of record() will be made with strictly increasing time.
     * After ExamTracker(), the first function call will always be record().
     * At most 105 calls will be made in total to record() and totalScore().
     */
    List<long[]> q;
    public LC3709_DesignExamScoresTracker() {
        q = new ArrayList<>();
    }
    // time = O(1), space = O(n)
    public void record(int time, int score) {
        if (q.isEmpty()) q.add(new long[]{time, score});
        else {
            long last = q.get(q.size() - 1)[1];
            q.add(new long[]{time, score + last});
        }
    }
    // time = O(logn), space = O(n)
    public long totalScore(int startTime, int endTime) {
        return get(endTime) - get(startTime - 1);
    }

    private long get(int t) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (q.get(mid)[0] <= t) l = mid;
            else r = mid - 1;
        }
        int res = q.get(r)[0] <= t ? r : r - 1;
        return res >= 0 ? q.get(res)[1] : 0;
    }
}