import java.util.LinkedList;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/7 0007.
 **/
public class CombinationSumIII {
    public class Solution {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> list = new LinkedList<>();
            int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            int[] solution = new int[k];
            for (int i = 0; i < solution.length; i++)
                solution[i] = -1;
            int i = 0;
            while (i >= 0) {
                solution[i]++;
                while (solution[i] < candidates.length && !isValid(i, candidates, solution, n))
                    solution[i]++;

                if (solution[i] < candidates.length) {

                    if (i == k - 1 && getSum(i, solution, candidates) == n)
                        list.add(toList(i, solution, candidates));

                    else if (i < solution.length - 1) {
                        i++;
                        solution[i] = -1;
                    }
                } else
                    i--;
            }
            return list;
        }

        private boolean isValid(int i, int[] candidates, int[] solution, int target) {
            return (i < 1 || (solution[i] > solution[i - 1])) &&
                    getSum(i, solution, candidates) <= target;
        }

        private int getSum(int index, int[] solution, int[] candidates) {
            int res = 0;
            for (int i = 0; i <= index; i++)
                res += candidates[solution[i]];
            return res;
        }

        private List<Integer> toList(int endIndex, int[] solution, int[] candidates) {
            List<Integer> list = new LinkedList<>();
            for (int index = 0; index <= endIndex; index++) {
                list.add(candidates[solution[index]]);
            }
            return list;
        }
    }

    public static void main(String... ar) {
        Solution solution = new CombinationSumIII().new Solution();
        solution.combinationSum3(2, 6);
    }
}
