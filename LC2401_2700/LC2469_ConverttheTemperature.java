package LC2401_2700;

public class LC2469_ConverttheTemperature {
    /**
     * You are given a non-negative floating point number rounded to two decimal places celsius, that denotes the
     * temperature in Celsius.
     *
     * You should convert Celsius into Kelvin and Fahrenheit and return it as an array ans = [kelvin, fahrenheit].
     *
     * Return the array ans. Answers within 10-5 of the actual answer will be accepted.
     *
     * Note that:
     *
     * Kelvin = Celsius + 273.15
     * Fahrenheit = Celsius * 1.80 + 32.00
     *
     * Input: celsius = 36.50
     * Output: [309.65000,97.70000]
     *
     * Input: celsius = 122.11
     * Output: [395.26000,251.79800]
     *
     * Constraints:
     *
     * 0 <= celsius <= 1000
     * @param celsius
     * @return
     */
    // time = O(1), space = O(1)
    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15, celsius * 1.80 + 32.00};
    }
}
