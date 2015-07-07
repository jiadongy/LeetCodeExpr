import java.util.LinkedList;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/5 0005.
 **/
public class Permutations {
    /**
     * Accept
     */
    public class Solution {
        public List<List<Integer>> permute(int[] nums) {
            int[] solution = new int[nums.length];
            for (int i = 0; i < solution.length; i++) solution[i] = -1;
            List<List<Integer>> list = new LinkedList<>();
            int index = 0;
            while (index >= 0) {
                solution[index]++;
                while (index < solution.length && !isValid(index, solution))
                    solution[index]++;
                if (solution[index] < solution.length) {
                    if (index == nums.length - 1) {
                        list.add(toList(solution, nums));
                    } else {
                        index++;
                        solution[index] = -1;
                    }
                } else {
                    index--;
                }
            }
            return list;
        }

        protected final boolean isValid(int index, int[] solution) {
            if (index == 0)
                return true;
            for (int i = 0; i < index; i++)
                if (solution[i] == solution[index])
                    return false;
            return true;
        }

        protected final List<Integer> toList(int[] solution, int[] nums) {
            List<Integer> list = new LinkedList<>();
            for (int index : solution)
                list.add(nums[index]);
            return list;
        }
    }

    public static void main(String... a) {
        Solution solution = new Permutations().new Solution();
        solution.permute(new int[]{1, 1, 3});
    }
}
