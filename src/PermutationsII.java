import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/5 0005.
 **/
public class PermutationsII {

    /**
     * Time Out , 用Hash来存每个排列的特征
     */
    public class Solution1 {
        public List<List<Integer>> permuteUnique(int[] nums) {
            int[] solution = new int[nums.length];
            for (int i = 0; i < solution.length; i++) solution[i] = -1;
            List<List<Integer>> list = new LinkedList<>();
            List<Integer> noDup = new LinkedList<>();
            int index = 0;
            while (index >= 0) {
                solution[index]++;
                while (index < solution.length && !isValid(index, solution))
                    solution[index]++;
                if (solution[index] < solution.length) {
                    if (index == nums.length - 1) {
                        List<Integer> temp = toList(solution, nums);
                        int hash = getHash(temp);
                        if (!noDup.contains(hash)) {
                            list.add(temp);
                            noDup.add(hash);
                        }

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

        protected final int getHash(List<Integer> list) {
            int res = 0;
            int multiple = (int) Math.pow(10, list.size() - 1);
            for (int i : list) {
                res += i * multiple;
                multiple /= 10;
            }
            return res;
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

    /**
     * Accept,实现了STL中next_permutation()
     */
    public class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> list = new LinkedList<>();
            Arrays.sort(nums);
            list.add(toList(nums));
            if (nums.length < 2)
                return list;
            int i, ii, j;
            for (i = nums.length - 2; i >= 0; i--) {
                ii = i + 1;
                if (nums[i] < nums[ii]) {
                    j = nums.length - 1;
                    while (!(nums[i] < nums[j]))
                        j--;
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                    //Sort??toIndex??Exclusive????小?????length
                    Arrays.sort(nums, ii, nums.length);
                    list.add(toList(nums));
                    //Reset if Find
                    i = nums.length - 1;
                }
            }
            return list;

        }

        protected final List<Integer> toList(int[] nums) {
            List<Integer> list = new LinkedList<>();
            for (int index : nums)
                list.add(index);
            return list;
        }

    }

    public static void main(String... a) {
        Solution solution = new PermutationsII().new Solution();
        solution.permuteUnique(new int[]{1, 2, 3});
    }
}
