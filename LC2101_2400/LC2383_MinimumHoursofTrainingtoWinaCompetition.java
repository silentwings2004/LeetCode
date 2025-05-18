package LC2101_2400;

public class LC2383_MinimumHoursofTrainingtoWinaCompetition {
    /**
     * You are entering a competition, and are given two positive integers initialEnergy and initialExperience denoting
     * your initial energy and initial experience respectively.
     *
     * You are also given two 0-indexed integer arrays energy and experience, both of length n.
     *
     * You will face n opponents in order. The energy and experience of the ith opponent is denoted by energy[i] and
     * experience[i] respectively. When you face an opponent, you need to have both strictly greater experience and
     * energy to defeat them and move to the next opponent if available.
     *
     * Defeating the ith opponent increases your experience by experience[i], but decreases your energy by energy[i].
     *
     * Before starting the competition, you can train for some number of hours. After each hour of training, you can
     * either choose to increase your initial experience by one, or increase your initial energy by one.
     *
     * Return the minimum number of training hours required to defeat all n opponents.
     *
     * Input: initialEnergy = 5, initialExperience = 3, energy = [1,4,3,2], experience = [2,6,3,1]
     * Output: 8
     *
     * Constraints:
     *
     * n == energy.length == experience.length
     * 1 <= n <= 100
     * 1 <= initialEnergy, initialExperience, energy[i], experience[i] <= 100
     * @param initialEnergy
     * @param initialExperience
     * @param energy
     * @param experience
     * @return
     */
    // S1: Simulation
    // time = O(n), space = O(1)
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int n = energy.length, a = initialEnergy, b = initialExperience, sum = 0;
        for (int i = 0; i < n; i++) {
            int x = energy[i], y = experience[i];
            if (a <= x) {
                sum += x + 1 - a;
                a = x + 1;
            }
            if (b <= y) {
                sum += y + 1 - b;
                b = y + 1;
            }
            a -= x;
            b += y;
        }
        return sum;
    }

    // S2
    // time = O(n), space = O(1)
    public int minNumberOfHours2(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int a = 0, b = 0, n = energy.length;
        for (int i = 0; i < n; i++) {
            a = Math.min(a, initialEnergy - energy[i] - 1);
            b = Math.min(b, initialExperience - experience[i] - 1);
            initialEnergy -= energy[i];
            initialExperience += experience[i];
        }
        return -a - b;
    }
}
/**
 * A - a0 - a1 - ... - ai-1 >= ai + 1
 * B + b0 + b1 + ... + bi-1 >= bi + 1
 */