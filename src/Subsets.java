import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Feiyu on 2015/7/4 0004.
 * Given a set of distinct integers, nums, return all possible subsets.

 Note:

 ?Elements in a subset must be in non-descending order.
 ?The solution set must not contain duplicate subsets.


 For example,
 If nums = [1,2,3], a solution is:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]


 **/
public class Subsets {
    public class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> list = new LinkedList<>();
            if(nums.length==0)
                return list;
            int[] arr = removeDup(nums);
            int[] path = new int[arr.length];
            for(int i=0;i<=Math.pow(2,arr.length)-1;i++){
                parsePath(i,path);
                list.add(getList(arr,path));
            }
            return list;
        }
        private void parsePath(int num,int[] path){
            int number=1;
            for(int i=0;i<path.length;i++){
                path[i]=(num & number)==0?0:1;
                number*=2;
            }
        }
        private List<Integer> getList(int[] nums,int[] path){
            List<Integer> list = new LinkedList<>();
            for(int i=0;i<nums.length;i++){
                if(path[i] != 0)
                    list.add(nums[i]);
            }
            return list;
        }
        private int[] removeDup(int[] nums){
            List<Integer> list = new LinkedList<>();
            list.add(nums[0]);
            int i1=0,i2=0;
            for(i2=1;i2<nums.length;i2++){
                if(nums[i2]==nums[i1])
                    i2++;
                else {
                    i1=i2;
                    list.add(nums[i1]);
                }
            }
            int[] result = new int[list.size()];
            for(int i=0;i<result.length;i++)
                result[i]=list.get(i);
            Arrays.sort(result);
            return result;
        }
    }

    static public void main(String... atgs){
        Solution solution=new Subsets().new Solution();
        solution.subsets(new int[]{4, 1, 0});
    }
}
