/**
 * Created by Feiyu on 2015/7/5 0005.
 **/
public class PermutationSequence {
    /**
     * Time Out , 只用第一个数字估算起始位置
     */
    public class Solution1 {
        public String getPermutation(int n, int k) {
            int[] nums = new int[n];
            int[] solution = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = i + 1;
                solution[i] = -1;
            }
            int index = 0;
            int sum = 1;
            int i = n;
            while (i >= 1)
                sum *= i--;
            int unit = sum / n;
            solution[0] = (k - 1) / unit;
            int nowK = unit * (solution[0]) + 1;
            index = (nowK - 1) / unit == 0 ? 0 : 1;
            if (index == 0)
                solution[0]--;
            while (index >= 0) {
                solution[index]++;
                while (index < n && !isValid(index, solution))
                    solution[index]++;
                if (solution[index] < n) {
                    if (index == n - 1) {
                        if (nowK == k) {
                            StringBuilder sb = new StringBuilder();
                            for (int pos : solution)
                                sb.append(nums[pos]);
                            return sb.toString();
                        }
                        nowK++;
                    } else {
                        index++;
                        solution[index] = -1;
                    }
                } else
                    index--;
            }
            return null;
        }

        private int getRecnetK(int k, int[] solution, int n) {
            int sum = 1;
            int i = n;
            while (i >= 1)
                sum *= i--;
            solution[0] = k / (sum / n);
            return (sum / n) * (solution[0]) + 1;
        }

        private boolean isValid(int nowPos, int[] solution) {
            for (int i = 0; i < nowPos; i++) {
                if (solution[i] == solution[nowPos])
                    return false;
            }
            return true;
        }
    }

    /**
     * Accept ，根据排列规律推算每一层solution的上下限
     */
    public class Solution {
        public String getPermutation(int n, int k) {
            int[] sum = new int[n];
            int[] unit = new int[n];
            int[] solution = new int[n];
            int[] min = new int[n];
            int[] max = new int[n];
            sum[n - 1] = 1;
            unit[n - 1] = 1;
            for (int i = n - 1; i >= 1; i--) {
                sum[i - 1] = sum[i] * (n - i + 1);
                unit[i - 1] = sum[i];
            }
            int index = 0;
            while (index >= 0) {
                solution[index]++;
                while (solution[index] <= n && !isValid(k, n, index, solution, sum, unit, min, max))
                    solution[index]++;
                if (solution[index] <= n) {
                    if (index == n - 1) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < n; i++)
                            sb.append(solution[i]);
                        return sb.toString();
                    } else {
                        index++;
                        solution[index] = 0;
                    }
                } else
                    index--;
            }
            return null;
        }

        private boolean isValid(int k, int n, int index, int[] solution, int[] sum, int[] unit, int[] min, int[] max) {
            int numberUsedBeforeMe = 0;
            for (int i = 0; i < index; i++) {
                if (solution[i] < solution[index])
                    numberUsedBeforeMe++;
                else if (solution[i] == solution[index]) {
                    return false;
                }
            }
            if (index >= 0 && index <= n - 2) {
                if (index == 0) {
                    min[0] = (solution[0] - 1) * unit[0] + 1;
                    max[0] = (solution[0]) * unit[0];
                } else if (index <= n - 2) {
                    min[index] = min[index - 1] + (solution[index] - 1 - numberUsedBeforeMe) * unit[index];
                    //错，不能用solution[index]表示位置，因为有元素在之前用掉了一部分
                    max[index] = min[index - 1] + (solution[index] - numberUsedBeforeMe) * unit[index] - 1;
                }
                return k <= max[index] && k >= min[index];
            } else
                for (int i = 0; i < index; i++) {
                    if (solution[i] == solution[index])
                        return false;
                }
            return true;
        }
    }

    /**
     * 可以直接通过商来确定k在序列中的相对位置
     */

    static public void main(String[] a) {
        Solution solution = new PermutationSequence().new Solution();
        solution.getPermutation(3, 1);
    }
}
