import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/7 0007.
 **/
public class CombinationSumII {
    /**
     * 01背包，solution中0为true，1为false
     */
    public class Solution1 {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> list = new ArrayList<>();
            int[] solution = new int[candidates.length];
            for (int i = 0; i < candidates.length; i++)
                solution[i] = -1;
            int i = 0;
            while (i >= 0) {
                solution[i]++;
                while (solution[i] < 2 && !(getSum(i, solution, candidates) <= target))
                    solution[i]++;
                if (solution[i] < 2) {
                    if (getSum(i, solution, candidates) == target)
                        list.add(toList(solution, candidates));
                    else if (i < solution.length - 1) {
                        i++;
                        solution[i] = -1;
                    }
                } else
                    i--;
            }
            return list;
        }

        private int getSum(int index, int[] solution, int[] candidates) {
            int res = 0;
            for (int i = 0; i <= index; i++)
                res += solution[i] == 0 ? candidates[i] : 0;
            return res;
        }

        private List<Integer> toList(int[] solution, int[] candidates) {
            List<Integer> list = new LinkedList<>();
            for (int index = 0; index < solution.length; index++) {
                if (solution[index] == 0)
                    list.add(candidates[index]);
            }
            return list;
        }

    }

    /**
     * 不用01背包了，用solution中的index表示candidate中的值
     * Accept??but >480ms 费时：existInList O(n^2) ，getSum，怎么优化
     */
    public class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> list = new LinkedList<>();
            Arrays.sort(candidates);
            int[] solution = new int[candidates.length];
            for (int i = 0; i < candidates.length; i++)
                solution[i] = -1;
            int i = 0;
            while (i >= 0) {
                solution[i]++;
                while (solution[i] < candidates.length && !isValid(i, candidates, solution, target))
                    solution[i]++;
                if (solution[i] < candidates.length) {
                    if (getSum(i, solution, candidates) == target) {
                        if (!existInList(i, candidates, list, solution))
                            list.add(toList(i, solution, candidates));
                    } else if (i < solution.length - 1) {
                        i++;
                        solution[i] = -1;
                    }
                } else
                    i--;
            }
            return list;
        }

        private boolean existInList(int endIndex, int[] candidates, List<List<Integer>> list, int[] solution) {
            if (list.size() < 1)
                return false;
            boolean thisExist;
            for (int i = 0; i < list.size(); i++) {
                thisExist = false;
                for (int j = 0; j <= endIndex; j++) {
                    if (list.get(i).get(j) != candidates[solution[j]]) {
                        thisExist = false;
                        break;
                    } else
                        thisExist = true;
                }
                if (thisExist)
                    return true;
            }
            return false;
        }

        private boolean isValid(int i, int[] candidates, int[] solution, int target) {
            boolean dupValid = true;
            for (int j = 0; j < i; j++)
                if (candidates[solution[j]] == candidates[solution[i]]) {
                    dupValid = false;
                    break;
                }
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

    public static void main(String... at) {
        Solution solution = new CombinationSumII().new Solution();
        solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
    }
}
