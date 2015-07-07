import java.util.*;

/**
 * Created by Feiyu on 2015/7/6 0006.
 **/
public class CombinationSum {
    /**
     * Accept,But > 400ms
     */
    public class Solution1 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            int[] newCandidatas = new int[candidates.length];
            for (int i = 0; i < candidates.length; i++)
                newCandidatas[i] = candidates[candidates.length - 1 - i];
            newCandidatas = candidates;

            List<List<Integer>> list = new LinkedList<>();
            List<Integer> one = new ArrayList<>();
            one.add(-1);
            int count = 0;
            while (count >= 0) {
                one.set(count, one.get(count) + 1);
                while (one.get(count) < newCandidatas.length &&
                        !isValid(count, one, newCandidatas, target))
                    one.set(count, one.get(count) + 1);

                if (one.get(count) < newCandidatas.length) {
                    if (getSum(count, one, newCandidatas) == target) {
                        list.add(indexToNumber(one, newCandidatas));
                    } else {
                        count++;
                        one.add(one.get(count - 1) - 1);
                    }
                } else {
                    one.remove(count);
                    count--;
                }

            }
            return list;
        }

        private boolean isValid(int count, List<Integer> one, int[] candidates, int target) {
            boolean sumValid = (count > 0 ?
                    (getSum(count - 1, one, candidates) +
                            candidates[one.get(count)])
                    : 0) <= target;
            boolean numberValid = true;
            //多余的for循环，只需要检测前一个的值大小关系即可
            for (int i = 0; i < count; i++)
                if (candidates[one.get(i)] < candidates[one.get(count)]) {
                    numberValid = false;
                    break;
                }
            return sumValid && numberValid;
        }

        private List<Integer> indexToNumber(List<Integer> list, int[] candidates) {
            List<Integer> list1 = new LinkedList<>();
            for (int i = 0; i < list.size(); i++)
                list1.add(candidates[list.get(i)]);
            Collections.sort(list1);
            return list1;
        }

        private int getSum(int endIndex, List<Integer> one, int[] candidates) {
            int result = 0;
            for (int i = 0; i <= endIndex; i++) {
                result += candidates[one.get(i)];
            }
            return result;
        }
    }

    /**
     * Accept,time没有明显缩短？
     */
    public class Solution {
        private ArrayList<Integer> sum = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);

            List<List<Integer>> list = new LinkedList<>();
            ArrayList<Integer> one = new ArrayList<>();
            one.add(-1);
            sum.add(0);
            int count = 0;
            while (count >= 0) {
                one.set(count, one.get(count) + 1);

                while (one.get(count) < candidates.length &&
                        !isValid(count, one, candidates, target))
                    one.set(count, one.get(count) + 1);

                if (one.get(count) < candidates.length) {
                    if (count > 0)//getSum移到这里，一共用到两次计算sum变成一次
                        sum.set(count, sum.get(count - 1) + candidates[one.get(count)]);
                    else
                        sum.set(0, candidates[one.get(0)]);

                    if (sum.get(count) == target) {
                        list.add(indexToNumber(count, one, candidates));
                    } else {
                        count++;
                        one.add(one.get(count - 1) - 1);
                        this.sum.add(0);
                    }
                } else {
                    one.remove(count);
                    this.sum.remove(count);
                    count--;
                }

            }
            return list;
        }

        protected boolean isValid(int count, List<Integer> one, int[] candidates, int target) {
            boolean sumValid = (count > 0 ?
                    (this.sum.get(count - 1) +
                            candidates[one.get(count)])
                    : 0) <= target;
            boolean numberValid = count <= 0 || (candidates[one.get(count - 1)] <= candidates[one.get(count)]);

            return sumValid && numberValid;
        }

        protected List<Integer> indexToNumber(int count, List<Integer> list, int[] candidates) {
            List<Integer> list1 = new LinkedList<>();
            for (int i = 0; i <= count; i++)
                list1.add(candidates[list.get(i)]);
            return list1;
        }
    }

    public static void main(String... a) {
        Solution solution = new CombinationSum().new Solution();
        solution.combinationSum(new int[]{1, 2, 3, 4}, 4);
    }
}
