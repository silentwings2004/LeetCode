package LC2401_2700;
import java.util.*;
public class LC2512_RewardTopKStudents {
    /**
     * You are given two string arrays positive_feedback and negative_feedback, containing the words denoting positive
     * and negative feedback, respectively. Note that no word is both positive and negative.
     *
     * Initially every student has 0 points. Each positive word in a feedback report increases the points of a student
     * by 3, whereas each negative word decreases the points by 1.
     *
     * You are given n feedback reports, represented by a 0-indexed string array report and a 0-indexed integer array
     * student_id, where student_id[i] represents the ID of the student who has received the feedback report report[i].
     * The ID of each student is unique.
     *
     * Given an integer k, return the top k students after ranking them in non-increasing order by their points. In
     * case more than one student has the same points, the one with the lower ID ranks higher.
     *
     * Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report =
     * ["this student is studious","the student is smart"], student_id = [1,2], k = 2
     * Output: [1,2]
     *
     * Input: positive_feedback = ["smart","brilliant","studious"], negative_feedback = ["not"], report =
     * ["this student is not studious","the student is smart"], student_id = [1,2], k = 2
     * Output: [2,1]
     *
     * Constraints:
     *
     * 1 <= positive_feedback.length, negative_feedback.length <= 10^4
     * 1 <= positive_feedback[i].length, negative_feedback[j].length <= 100
     * Both positive_feedback[i] and negative_feedback[j] consists of lowercase English letters.
     * No word is present in both positive_feedback and negative_feedback.
     * n == report.length == student_id.length
     * 1 <= n <= 10^4
     * report[i] consists of lowercase English letters and spaces ' '.
     * There is a single space between consecutive words of report[i].
     * 1 <= report[i].length <= 100
     * 1 <= student_id[i] <= 10^9
     * All the values of student_id[i] are unique.
     * 1 <= k <= n
     * @param positive_feedback
     * @param negative_feedback
     * @param report
     * @param student_id
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        int n = student_id.length;
        HashSet<String> pos = new HashSet<>();
        HashSet<String> neg = new HashSet<>();
        for (String s : positive_feedback) pos.add(s);
        for (String s : negative_feedback) neg.add(s);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1]);
        for (int i = 0; i < n; i++) {
            String[] strs = report[i].split(" ");
            int score = 0;
            for (String s : strs) {
                if (pos.contains(s)) score += 3;
                else if (neg.contains(s)) score--;
            }
            pq.offer(new int[]{score, student_id[i]});
            if (pq.size() > k) pq.poll();
        }

        List<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) res.add(0, pq.poll()[1]);
        return res;
    }
}